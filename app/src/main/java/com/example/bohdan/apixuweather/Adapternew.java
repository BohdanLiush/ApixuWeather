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
import java.util.List;
import java.util.Locale;

public class Adapternew extends BaseAdapter {

    public LayoutInflater inflater;

    public List<Forecastday> listForecastday;

    public Adapternew(List<Forecastday> listForecastday)
    {
        this.listForecastday = listForecastday;
    }
    @Override
    public int getCount() {
        if (listForecastday == null)
            return 0;
        return listForecastday.size();
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

        if (inflater == null) {
            inflater = (LayoutInflater) viewGroup.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        CustomBinding customBinding = DataBindingUtil.inflate(inflater, R.layout.custom, viewGroup, false);

        long timeInMilliseconds = listForecastday.get(position).getDateEpoch();

        String forcastdayname = formatDateTime(timeInMilliseconds);
        customBinding.textView3.setText(forcastdayname);
        customBinding.setModelFor(listForecastday.get(position));
        return customBinding.getRoot();

    }

    private static String formatDateTime(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(("E"), Locale.ENGLISH);
        return formatter.format(new Date(timeInMilliseconds * 1000));
    }
}
