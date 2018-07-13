package com.example.bohdan.apixuweather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ModelApi {
    @GET("forecast.json?key=7178efb6c8324f3da44185408180507&days=5")
    Call<Model> idsInfo(@Query("q") String number);

}
