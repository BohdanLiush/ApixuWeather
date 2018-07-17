package com.example.bohdan.apixuweather;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.bohdan.apixuweather.databinding.CustomBinding;
import com.example.bohdan.apixuweather.databinding.CustomBinding;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Adapternew extends BaseAdapter {

    public Forecast forecast;
    public LayoutInflater inflater;
    public Context context;

    public Adapternew(Forecast forecast)
    {
        this.forecast = forecast;
    }
    @Override
    public int getCount() {
        return forecast.getForecastday().size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        /*View v;
        if (view == null) {  // if it's not recycled, initialize some attributes
            inflater = (LayoutInflater) viewGroup.getContext().getSystemService(context.LAYOUT_INFLATER_SERVICE );
            v = inflater.inflate(R.layout.custom, viewGroup, false);
        } else {
            v = view;
        }
*/
/*
        ImageView imageView = v.findViewById(R.id.imageView);
        TextView tempetature = v.findViewById(R.id.textView9);
        //TextView cloudy = v.findViewById(R.id.textView10);

        tempetature.setText(String .valueOf(forecast.getForecastday().get(position).getDay().getMaxtempC()));
        Picasso.get().load("https://"+forecast.
                getForecastday().get(position).getDay().getCondition().getIcon()).into(imageView);
        return v;
*/

        if (inflater == null) {
            inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        CustomBinding customBinding = DataBindingUtil.inflate(inflater, R.layout.custom, viewGroup, false);
        //customBinding.
        long timeInMilliseconds = forecast.getForecastday().get(position).getDateEpoch();

        String forcastdayname = formatDateTime(timeInMilliseconds);
        customBinding.textView3.setText(forcastdayname);
        customBinding.setModelFor(forecast.getForecastday().get(position));
        return customBinding.getRoot();

    }

    private static String formatDateTime(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(("E"), Locale.ENGLISH);
        return formatter.format(new Date(timeInMilliseconds * 1000));
    }
}
