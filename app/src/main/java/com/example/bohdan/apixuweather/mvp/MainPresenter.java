package com.example.bohdan.apixuweather.mvp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.bohdan.apixuweather.Forecastday;
import com.example.bohdan.apixuweather.Model;
import com.example.bohdan.apixuweather.R;
import com.example.bohdan.apixuweather.SecondFragment;

import java.io.Serializable;
import java.util.List;

public class MainPresenter implements MainContract.Presenter {


    private MainContract.View mView;
    private MainContract.Repository mRepository;

    Model listModel;
    SecondFragment secondFragment = new SecondFragment();
    String message;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
    }

    @Override
    public void onButtonWasClicked(String s) throws InterruptedException {
        listModel = mRepository.loadModel(s);
        mView.getModel(listModel);
    }

    @Override
    public void presenterLoadSecondFr(List<Forecastday> listForecastday, int position) {

        Bundle argumentstwo = new Bundle();
        argumentstwo.putSerializable("ForCastDay", (Serializable) listForecastday);
        argumentstwo.putSerializable("position", position);

        secondFragment.setArguments(argumentstwo);

      /*  FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, secondFragment).addToBackStack(null).commit();*/
    }
}
