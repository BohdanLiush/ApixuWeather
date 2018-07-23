package com.example.bohdan.apixuweather;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bohdan.apixuweather.databinding.SecondFragmentBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SecondFragment extends Fragment {

    SecondFragmentBinding secondFragmentBinding;
    Forecastday forecastday;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        secondFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.second_fragment, container, false);

        Bundle bundle = getArguments();
        if (bundle!=null){
            forecastday = (Forecastday) bundle.getSerializable("listForCastDay");
        }

        long timeInMilliseconds = forecastday.getDateEpoch();
        String forcastdayname = formatDateTime(timeInMilliseconds);
        secondFragmentBinding.textView15.setText("Day: " + forcastdayname);

        secondFragmentBinding.setModelFor(forecastday);

        secondFragmentBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity activityHome = (MainActivity) container.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.buttonBack();
            }
        });

        return secondFragmentBinding.getRoot();
    }

    private static String formatDateTime(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(("E"), Locale.ENGLISH);
        return formatter.format(new Date(timeInMilliseconds * 1000));
    }
}
