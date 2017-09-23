package org.openhab.binding.arad.internal.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LowPriceConsumption {

    @SerializedName("Low_Price_Consumption_MC")
    @Expose
    private Integer lowPriceConsumptionMC;
    @SerializedName("Meter_Count")
    @Expose
    private Integer meterCount;
    @SerializedName("Label_Text")
    @Expose
    private String labelText;
    @SerializedName("Low_Price_Consumption_Type")
    @Expose
    private Integer lowPriceConsumptionType;

    public Integer getLowPriceConsumptionMC() {
        return lowPriceConsumptionMC;
    }

    public void setLowPriceConsumptionMC(Integer lowPriceConsumptionMC) {
        this.lowPriceConsumptionMC = lowPriceConsumptionMC;
    }

    public Integer getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(Integer meterCount) {
        this.meterCount = meterCount;
    }

    public String getLabelText() {
        return labelText;
    }

    public void setLabelText(String labelText) {
        this.labelText = labelText;
    }

    public Integer getLowPriceConsumptionType() {
        return lowPriceConsumptionType;
    }

    public void setLowPriceConsumptionType(Integer lowPriceConsumptionType) {
        this.lowPriceConsumptionType = lowPriceConsumptionType;
    }

}