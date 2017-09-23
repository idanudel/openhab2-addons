package org.openhab.binding.arad.internal.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstimatedConsumption {

    @SerializedName("End_Of_Month_Estimated_Consumption")
    @Expose
    private Double endOfMonthEstimatedConsumption;
    @SerializedName("Meter_Count")
    @Expose
    private Integer meterCount;

    public Double getEndOfMonthEstimatedConsumption() {
        return endOfMonthEstimatedConsumption;
    }

    public void setEndOfMonthEstimatedConsumption(Double endOfMonthEstimatedConsumption) {
        this.endOfMonthEstimatedConsumption = endOfMonthEstimatedConsumption;
    }

    public Integer getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(Integer meterCount) {
        this.meterCount = meterCount;
    }

}