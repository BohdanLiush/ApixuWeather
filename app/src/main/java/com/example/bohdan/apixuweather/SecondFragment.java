package com.example.bohdan.apixuweather;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bohdan.apixuweather.databinding.SecondFragmentBinding;

public class SecondFragment extends Fragment {

    SecondFragmentBinding secondFragmentBinding;
    Forecastday forecastday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        secondFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.second_fragment, container, false);

        Bundle bundle = getArguments();
        if (bundle!=null){
            forecastday = (Forecastday) bundle.getSerializable("listForCastDay");
        }

        secondFragmentBinding.setModelFor(forecastday);

        return secondFragmentBinding.getRoot();
    }
}
