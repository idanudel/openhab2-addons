
package org.openhab.binding.arad.internal.api.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumerMeter {

    @SerializedName("Meter_Count")
    @Expose
    private Integer meterCount;
    @SerializedName("Property_ID")
    @Expose
    private String propertyID;
    @SerializedName("City_Name")
    @Expose
    private String cityName;
    @SerializedName("Street_Name")
    @Expose
    private String streetName;
    @SerializedName("Number")
    @Expose
    private String number;
    @SerializedName("Apartment")
    @Expose
    private String apartment;
    @SerializedName("Water_Type_ID")
    @Expose
    private Integer waterTypeID;
    @SerializedName("Cons_Type_Desc")
    @Expose
    private String consTypeDesc;
    @SerializedName("Last_Read")
    @Expose
    private Double lastRead;
    @SerializedName("Meter_SN")
    @Expose
    private String meterSN;
    @SerializedName("Meter_ID")
    @Expose
    private String meterID;
    @SerializedName("Residents")
    @Expose
    private Integer residents;
    @SerializedName("Floor")
    @Expose
    private Integer floor;
    @SerializedName("Have_A_Garden")
    @Expose
    private Boolean haveAGarden;
    @SerializedName("Replacements")
    @Expose
    private List<Object> replacements = null;
    @SerializedName("Low_Price_Consumption_MC")
    @Expose
    private Integer lowPriceConsumptionMC;
    @SerializedName("Low_Price_Consumption_Type")
    @Expose
    private Integer lowPriceConsumptionType;

    public Integer getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(Integer meterCount) {
        this.meterCount = meterCount;
    }

    public String getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public Integer getWaterTypeID() {
        return waterTypeID;
    }

    public void setWaterTypeID(Integer waterTypeID) {
        this.waterTypeID = waterTypeID;
    }

    public String getConsTypeDesc() {
        return consTypeDesc;
    }

    public void setConsTypeDesc(String consTypeDesc) {
        this.consTypeDesc = consTypeDesc;
    }

    public Double getLastRead() {
        return lastRead;
    }

    public void setLastRead(Double lastRead) {
        this.lastRead = lastRead;
    }

    public String getMeterSN() {
        return meterSN;
    }

    public void setMeterSN(String meterSN) {
        this.meterSN = meterSN;
    }

    public String getMeterID() {
        return meterID;
    }

    public void setMeterID(String meterID) {
        this.meterID = meterID;
    }

    public Integer getResidents() {
        return residents;
    }

    public void setResidents(Integer residents) {
        this.residents = residents;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Boolean getHaveAGarden() {
        return haveAGarden;
    }

    public void setHaveAGarden(Boolean haveAGarden) {
        this.haveAGarden = haveAGarden;
    }

    public List<Object> getReplacements() {
        return replacements;
    }

    public void setReplacements(List<Object> replacements) {
        this.replacements = replacements;
    }

    public Integer getLowPriceConsumptionMC() {
        return lowPriceConsumptionMC;
    }

    public void setLowPriceConsumptionMC(Integer lowPriceConsumptionMC) {
        this.lowPriceConsumptionMC = lowPriceConsumptionMC;
    }

    public Integer getLowPriceConsumptionType() {
        return lowPriceConsumptionType;
    }

    public void setLowPriceConsumptionType(Integer lowPriceConsumptionType) {
        this.lowPriceConsumptionType = lowPriceConsumptionType;
    }

}
