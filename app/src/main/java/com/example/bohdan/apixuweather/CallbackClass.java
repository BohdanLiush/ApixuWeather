package com.example.bohdan.apixuweather;

import android.telecom.Call;

import java.util.List;

public class CallbackClass {

    interface Callback {

        Model callingBack(String string) throws InterruptedException;

        void callingBackSecondFr(Forecastday listForecastday);

        void callingBackButton();

    }

    public Callback callback;

    public void registerCallBack(Callback callback){
        this.callback = callback;
    }

    Model loadObject(String string) throws InterruptedException {
        return callback.callingBack(string);
    }

    void loadObjectSecondFr(Forecastday listForecastday){
        callback.callingBackSecondFr(listForecastday);
    }

    void buttonBack(){
        callback.callingBackButton();
    }

}
