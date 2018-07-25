package com.example.bohdan.apixuweather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Model implements Serializable {

    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("current")
    @Expose
    private Current current;

    @SerializedName("forecast")
    @Expose
    private Forecast forecast;

    // введена буленова змінна
    private boolean  visibleOk;

    public boolean isVisibleOk() {
        return visibleOk;
    }

    public void setVisibleOk(boolean visibleOk) {
        this.visibleOk = visibleOk;
    }
    // кінець буленової змінної

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

}
