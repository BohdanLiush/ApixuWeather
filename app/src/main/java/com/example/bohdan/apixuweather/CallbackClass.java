package com.example.bohdan.apixuweather;

import android.telecom.Call;

import java.util.List;

public class CallbackClass {

    interface Callback {

        Model callingBack(String string) throws InterruptedException;

        void callingBackSecondFr(List<Forecastday> listForecastday, int position);

        void callingBackButton();

    }

    public Callback callback;

    public void registerCallBack(Callback callback){
        this.callback = callback;
    }

    Model loadObject(String string) throws InterruptedException {
        return callback.callingBack(string);
    }

    void loadObjectSecondFr(List<Forecastday> listForecastday, int position){
        callback.callingBackSecondFr(listForecastday, position);
    }

    void buttonBack(){
        callback.callingBackButton();
    }

}
