package com.example.bohdan.apixuweather;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Serializable, CallbackClass.Callback{

    NetworkManager networkManager = new NetworkManager();
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    public String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();
    }

    public void loadFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, firstFragment);
        fragmentTransaction.commit();
    }

    @Override
    public Model callingBack(String string) throws InterruptedException {

        networkManager.loadStringFromMain(string);
        return networkManager.model;

    }

    @Override
    public void callingBackSecondFr(List<Forecastday> listForecastday, int position) {

        /*Bundle arguments = new Bundle();
        arguments.putSerializable("listForCastDay", (Serializable) listForecastday);
        secondFragment.setArguments(arguments);*/

        Bundle argumentstwo = new Bundle();
        argumentstwo.putSerializable("ForCastDay", (Serializable) listForecastday);
        argumentstwo.putSerializable("position", position);

        secondFragment.setArguments(argumentstwo);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, secondFragment).addToBackStack(null).commit();

    }

    @Override
    public void callingBackButton() {
        FragmentManager fm = getFragmentManager();
            fm.popBackStack();
    }
}

