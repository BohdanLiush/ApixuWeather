package com.example.bohdan.apixuweather.mvp;

import com.example.bohdan.apixuweather.Forecastday;
import com.example.bohdan.apixuweather.Model;

import java.util.List;

public interface MainContract {

    interface View {
        void getModel(Model models);
        void bucck();
        //new
    }

    interface Presenter {
        void onButtonWasClicked(String s) throws InterruptedException;
        void presenterLoadSecondFr(List<Forecastday> listForecastday, int position);
    }

    interface Repository {
        Model loadModel(String s) throws InterruptedException;
    }

}
