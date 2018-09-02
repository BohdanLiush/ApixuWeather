package com.example.bohdan.apixuweather.mvp;

import com.example.bohdan.apixuweather.Model;
import com.example.bohdan.apixuweather.NetworkManager;

import java.util.ArrayList;
import java.util.List;

public class MainRepository implements MainContract.Repository {

    NetworkManager networkManager = new NetworkManager();

    @Override
    public Model loadModel(String s) throws InterruptedException {
        networkManager.loadStringFromMain(s);
        return networkManager.model;
    }
}
