/**
 * Copyright (c) 2010-2017 by the respective copyright holders.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.arad.handler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.smarthome.config.core.Configuration;
import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.State;
import org.eclipse.smarthome.io.net.http.HttpUtil;
import org.openhab.binding.arad.AradBindingConstants;
import org.openhab.binding.arad.discovery.AradAccountDiscoveryService;
import org.openhab.binding.arad.internal.api.data.LoginData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * The {@link AradHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author y - Initial contribution
 */
public class AradHandler extends BaseThingHandler {

    private static final Logger log = LoggerFactory.getLogger(AradHandler.class);
    private static BigDecimal DEFAULTREFRESHTIME = new BigDecimal(60.0);

    private LoginData loginData;
    private ScheduledFuture<?> refreshTask;
    private BigDecimal refreshTime;

    public AradHandler(@NonNull Thing thing) {
        super(thing);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
    }

    @Override
    public void initialize() {
        // TODO: Initialize the thing. If done set status to ONLINE to indicate proper working.
        // Long running initialization should be done asynchronously in background.
        updateStatus(ThingStatus.ONLINE);
        Configuration config = getThing().getConfiguration();
        refreshTime = (BigDecimal) config.get(AradBindingConstants.CONFIG_REFRESHTIME);
        if (refreshTime != null && refreshTime.intValue() <= 0) {
            throw new IllegalArgumentException("Refresh time must be positive number!");
        } else {
            refreshTime = DEFAULTREFRESHTIME;
        }

        startAutomaticRefresh();

        // Note: When initialization can NOT be done set the status with more details for further
        // analysis. See also class ThingStatusDetail for all available status details.
        // Add a description to give user information to understand why thing does not work
        // as expected. E.g.
        // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.CONFIGURATION_ERROR,
        // "Can not access device as username and/or password are invalid");
    }

    private void startAutomaticRefresh() {

        Runnable refresher = new Runnable() {
            @Override
            public void run() {
                try {
                    refreshStateAndUpdate();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    log.error(e.getMessage());
                }

            }
        };

        this.refreshTask = scheduler.scheduleAtFixedRate(refresher, 0, refreshTime.intValue(), TimeUnit.SECONDS);
        log.debug("Start automatic refresh at {} minutes", refreshTime.intValue());
    }

    public void refreshStateAndUpdate() {
        try {
            List<Channel> channels = getThing().getChannels();

            Configuration config = getThing().getConfiguration();
            Object arad_key = config.get(AradBindingConstants.ARAD_KEY);
            if (arad_key == null) {
                loginData = AradAccountDiscoveryService.loginAngGetAradKey();
                if (loginData == null || !loginData.getErrorCode().equals("0") || loginData.getResponseMessage() == null
                        || loginData.getResponseMessage().getKey() == null) {
                    log.error("Arad - Error response code while try to create Arad Key: " + loginData == null ? null
                            : loginData.getErrorCode() + " response: " + loginData == null ? null
                                    : loginData.toString());
                    return;
                }
                config.put(AradBindingConstants.ARAD_KEY, loginData.getResponseMessage().getKey());

            }

            String aradKey = (String) config.get(AradBindingConstants.ARAD_KEY);
            LoginData aradData = getAradData(aradKey);
            if (aradData == null || !aradData.getErrorCode().equals("0")) {
                log.error("Arad - Error response code while try to get arad data: " + aradData == null ? null
                        : aradData.getErrorCode() + " response: " + aradData == null ? null : aradData.toString());
                return;
            }

            for (Channel channel : channels) {
                if (isLinked(channel.getUID().getId())) {
                    String channelID = channel.getUID().getId();
                    State state = null;
                    switch (channelID) {
                        case AradBindingConstants.CHANNEL_LAST_READ:
                            state = new DecimalType(aradData.getResponseMessage().getMeterDetails().getLastRead());
                            break;
                        case AradBindingConstants.CHANNEL_MONTHLY_CURRENT_CONSUMPTION:
                            state = new DecimalType(
                                    aradData.getResponseMessage().getCurrentConsumption().getCurrentConsumptionMC());
                            break;
                        case AradBindingConstants.CHANNEL_LOW_PRICE_CONSUMPTION_MC:
                            state = new DecimalType(
                                    aradData.getResponseMessage().getLowPriceConsumption().getLowPriceConsumptionMC());
                            break;
                        case AradBindingConstants.CHANNEL_CURRENT_CONSUMPTION:
                            state = new DecimalType(0);
                            if (aradData.getResponseMessage().getDailyConsumption() != null
                                    && !aradData.getResponseMessage().getDailyConsumption().isEmpty()
                                    && aradData.getResponseMessage().getDailyConsumption().get(0) != null
                                    && !aradData.getResponseMessage().getDailyConsumption().get(0).isEmpty()
                                    && aradData.getResponseMessage().getDailyConsumption().get(0).get(1) != null) {
                                try {
                                    state = new DecimalType(Double.parseDouble(aradData.getResponseMessage()
                                            .getDailyConsumption().get(0).get(1).getData()));
                                } catch (Exception e) {
                                    log.error("Failed getting DailyConsumption.", e);
                                }
                            }

                            break;
                    }
                    updateState(channel.getUID().getId(), state);
                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static LoginData getAradData(String aradKey) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            // sdf.setTimeZone(TimeZone.getTimeZone(System.getProperty("user.timezone")));
            Calendar from = Calendar.getInstance();
            from.set(Calendar.HOUR_OF_DAY, 00);
            from.set(Calendar.MINUTE, 00);

            Calendar to = Calendar.getInstance();
            to.set(Calendar.HOUR_OF_DAY, 23);
            to.set(Calendar.MINUTE, 59);

            String fromDate = sdf.format(from.getTime());
            String toDate = sdf.format(to.getTime());
            String data = "{\r\n" + "    \"Commands\": [\r\n" + "        {\r\n"
                    + "            \"Command\": \"Estimated_Consumption\",\r\n"
                    + "            \"Meter_Count\": 14656\r\n" + "        },\r\n" + "        {\r\n"
                    + "            \"Command\": \"Low_Price_Consumption\",\r\n"
                    + "            \"Meter_Count\": 14656\r\n" + "        },\r\n" + "        {\r\n"
                    + "            \"Command\": \"Current_Consumption\",\r\n" + "            \"Meter_Count\": 14656\r\n"
                    + "        },\r\n" + "        {\r\n" + "            \"Command\": \"Meter_Details\",\r\n"
                    + "            \"Meter_Count\": 14656\r\n" + "        },\r\n" + " {\r\n"
                    + "    \"Command\": \"Daily_Consumption\",\r\n" + "    \"From_Date\": \"" + fromDate + "\",\r\n"
                    + "    \"To_Date\": \"" + toDate + "\",\r\n" + "    \"Meter_Count\": 14656\r\n" + "}\r\n"
                    + "    ],\r\n" + "    \"Key\": \"" + aradKey + "\"\r\n" + "}";
            String query = "data=" + URLEncoder.encode(data, "UTF-8");
            StringBuilder result = new StringBuilder();
            result.append(URLEncoder.encode("data", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(data, "UTF-8"));

            log.debug("Arad - try to get arad data: " + data);

            InputStream stream = new ByteArrayInputStream(query.getBytes());
            String resultString = HttpUtil.executeUrl("POST",
                    AradAccountDiscoveryService.DOMAIN + AradAccountDiscoveryService.EXECUTE_COMMANDS_URL,
                    new Properties(), stream, "application/x-www-form-urlencoded; charset=UTF-8", 20000);
            log.debug("Arad - result from get arad data: " + resultString);
            return new Gson().fromJson(resultString, LoginData.class);

        } catch (Exception e) {
            log.error("Arad - Error while try to  create Arad Key:", e);
        }
        return null;
    }

}
