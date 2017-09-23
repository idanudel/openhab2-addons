
package org.openhab.binding.arad.internal.api.data;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseMessage {

    @SerializedName("Key")
    @Expose
    private String key;
    @SerializedName("Device_ID")
    @Expose
    private String deviceID;
    @SerializedName("Server_Timezone")
    @Expose
    private String serverTimezone;
    @SerializedName("Municipal_Desc")
    @Expose
    private String municipalDesc;
    @SerializedName("Logo_URL")
    @Expose
    private String logoURL;
    @SerializedName("Notifications_Data")
    @Expose
    private NotificationsData notificationsData;
    @SerializedName("Consumer_Details")
    @Expose
    private ConsumerDetails consumerDetails;
    @SerializedName("Consumer_Meters")
    @Expose
    private List<ConsumerMeter> consumerMeters = null;
    @SerializedName("Estimated_Consumption")
    @Expose
    private EstimatedConsumption estimatedConsumption;
    @SerializedName("Low_Price_Consumption")
    @Expose
    private LowPriceConsumption lowPriceConsumption;
    @SerializedName("Current_Consumption")
    @Expose
    private CurrentConsumption currentConsumption;
    @SerializedName("Meter_Details")
    @Expose
    private MeterDetails meterDetails;
    @SerializedName("Daily_Consumption")
    @Expose
    private List<List<DailyConsumption>> dailyConsumption = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getServerTimezone() {
        return serverTimezone;
    }

    public void setServerTimezone(String serverTimezone) {
        this.serverTimezone = serverTimezone;
    }

    public String getMunicipalDesc() {
        return municipalDesc;
    }

    public void setMunicipalDesc(String municipalDesc) {
        this.municipalDesc = municipalDesc;
    }

    public String getLogoURL() {
        return logoURL;
    }

    public void setLogoURL(String logoURL) {
        this.logoURL = logoURL;
    }

    public NotificationsData getNotificationsData() {
        return notificationsData;
    }

    public void setNotificationsData(NotificationsData notificationsData) {
        this.notificationsData = notificationsData;
    }

    public ConsumerDetails getConsumerDetails() {
        return consumerDetails;
    }

    public void setConsumerDetails(ConsumerDetails consumerDetails) {
        this.consumerDetails = consumerDetails;
    }

    public List<ConsumerMeter> getConsumerMeters() {
        return consumerMeters;
    }

    public void setConsumerMeters(List<ConsumerMeter> consumerMeters) {
        this.consumerMeters = consumerMeters;
    }

    public EstimatedConsumption getEstimatedConsumption() {
        return estimatedConsumption;
    }

    public void setEstimatedConsumption(EstimatedConsumption estimatedConsumption) {
        this.estimatedConsumption = estimatedConsumption;
    }

    public LowPriceConsumption getLowPriceConsumption() {
        return lowPriceConsumption;
    }

    public void setLowPriceConsumption(LowPriceConsumption lowPriceConsumption) {
        this.lowPriceConsumption = lowPriceConsumption;
    }

    public CurrentConsumption getCurrentConsumption() {
        return currentConsumption;
    }

    public void setCurrentConsumption(CurrentConsumption currentConsumption) {
        this.currentConsumption = currentConsumption;
    }

    public MeterDetails getMeterDetails() {
        return meterDetails;
    }

    public void setMeterDetails(MeterDetails meterDetails) {
        this.meterDetails = meterDetails;
    }

    public List<List<DailyConsumption>> getDailyConsumption() {
        return dailyConsumption;
    }

    public void setDailyConsumption(List<List<DailyConsumption>> dailyConsumption) {
        this.dailyConsumption = dailyConsumption;
    }

}
