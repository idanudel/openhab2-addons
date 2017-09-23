package org.openhab.binding.arad.internal.api.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DailyConsumption {

    @SerializedName("Data")
    @Expose
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}