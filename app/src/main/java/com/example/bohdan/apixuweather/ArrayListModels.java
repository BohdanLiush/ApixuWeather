package com.example.bohdan.apixuweather;

import java.util.List;

public class ArrayListModels {
    //List<Model>
    Forecast forecast;

    public ArrayListModels(Forecast forecast) {
        this.forecast = forecast;
    }

    public Forecast getForecast() {
        return forecast;
    }
}
