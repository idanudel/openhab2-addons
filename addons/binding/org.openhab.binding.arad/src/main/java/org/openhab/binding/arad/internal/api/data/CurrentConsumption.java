package org.openhab.binding.arad.internal.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentConsumption {

    @SerializedName("Current_Consumption_MC")
    @Expose
    private Double currentConsumptionMC;
    @SerializedName("Meter_Count")
    @Expose
    private Integer meterCount;

    public Double getCurrentConsumptionMC() {
        return currentConsumptionMC;
    }

    public void setCurrentConsumptionMC(Double currentConsumptionMC) {
        this.currentConsumptionMC = currentConsumptionMC;
    }

    public Integer getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(Integer meterCount) {
        this.meterCount = meterCount;
    }

}