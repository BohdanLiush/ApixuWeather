package com.example.bohdan.apixuweather;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.bohdan.apixuweather.databinding.ActivitySecondBinding;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity implements Serializable {

    Forecastday forecastday_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        forecastday_ = (Forecastday) getIntent().getSerializableExtra("item");

        ActivitySecondBinding activitySecondBinding =
                DataBindingUtil.setContentView(this,  R.layout.activity_second);

        long timeInMilliseconds = forecastday_.getDateEpoch();
        String forcastdayname = formatDateTime(timeInMilliseconds);

        activitySecondBinding.textView15.setText("Day: "+forcastdayname);
        activitySecondBinding.setModelFor(forecastday_);

    }

    private static String formatDateTime(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(("E"), Locale.ENGLISH);
        return formatter.format(new Date(timeInMilliseconds * 1000));
    }


}
