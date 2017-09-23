package org.openhab.binding.arad.discovery;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.eclipse.smarthome.config.discovery.AbstractDiscoveryService;
import org.eclipse.smarthome.config.discovery.DiscoveryResult;
import org.eclipse.smarthome.config.discovery.DiscoveryResultBuilder;
import org.eclipse.smarthome.core.thing.ThingUID;
import org.eclipse.smarthome.io.net.http.HttpUtil;
import org.openhab.binding.arad.AradBindingConstants;
import org.openhab.binding.arad.internal.AradHandlerFactory;
import org.openhab.binding.arad.internal.api.data.LoginData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class AradAccountDiscoveryService extends AbstractDiscoveryService {

    private static final Logger log = LoggerFactory.getLogger(AradAccountDiscoveryService.class);
    private static final int TIMEOUT = 15;

    public static final String DOMAIN = "https://cp.city-mind.com";
    private static final String LOGIN_URL = "/app_api/mob/login";
    public static final String EXECUTE_COMMANDS_URL = "/app_api/mob/execute_commands";

    public AradAccountDiscoveryService() throws IllegalArgumentException {
        super(AradHandlerFactory.SUPPORTED_THING_TYPES_UIDS, TIMEOUT);
    }

    @Override
    protected void startScan() {
        LoginData aradKey = loginAngGetAradKey();
        if (!aradKey.getErrorCode().equals("0") || aradKey.getResponseMessage() == null
                || aradKey.getResponseMessage().getKey() == null) {
            log.error("Arad - Error response code while try to create Arad Key: " + aradKey.getErrorCode()
                    + " response: " + aradKey.toString());
            return;
        }
        ThingUID thingUID = new ThingUID(AradBindingConstants.THING_TYPE_ARAD, AradHandlerFactory.deviceId);
        Map<String, Object> properties = new HashMap<>(0);
        properties.put(AradBindingConstants.ARAD_KEY, aradKey.getResponseMessage().getKey());
        DiscoveryResult discoveryResult = DiscoveryResultBuilder.create(thingUID).withProperties(properties).build();

        thingDiscovered(discoveryResult);
        // pullDataFromArad(aradKey.getResponseMessage().getKey());
    }

    public static LoginData loginAngGetAradKey() {
        try {
            if (AradHandlerFactory.email == null || AradHandlerFactory.password == null) {
                log.error("Arad - Missing requied fields user name or password");
                return null;
            }
            String data = "{\"Email\":\"" + AradHandlerFactory.email + "\",\"Password\":\""
                    + AradHandlerFactory.password + "\",\"OS_Type_ID\":1,\"Device_ID\":\"" + AradHandlerFactory.deviceId
                    + "\",\"Package_ID\":\"arad\"}";
            String query = "data=" + URLEncoder.encode(data, "UTF-8");
            StringBuilder result = new StringBuilder();
            result.append(URLEncoder.encode("data", "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(data, "UTF-8"));

            log.debug("Arad - try to create login key with data: " + data);

            InputStream stream = new ByteArrayInputStream(query.getBytes());
            String resultString = HttpUtil.executeUrl("POST", DOMAIN + LOGIN_URL, new Properties(), stream,
                    "application/x-www-form-urlencoded; charset=UTF-8", 20000);
            log.debug("Arad - result from create login key: " + resultString);
            return new Gson().fromJson(resultString, LoginData.class);

        } catch (Exception e) {
            log.error("Arad - Error while try to  create Arad Key:", e);
        }
        return null;
    }

    private void pullDataFromArad(String aradKey) {

    }

}
