package org.openhab.binding.arad.internal.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MeterDetails {

    @SerializedName("Meter_Count")
    @Expose
    private Integer meterCount;
    @SerializedName("Last_Read")
    @Expose
    private Double lastRead;

    public Integer getMeterCount() {
        return meterCount;
    }

    public void setMeterCount(Integer meterCount) {
        this.meterCount = meterCount;
    }

    public Double getLastRead() {
        return lastRead;
    }

    public void setLastRead(Double lastRead) {
        this.lastRead = lastRead;
    }

}