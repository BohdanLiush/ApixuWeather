package com.example.bohdan.apixuweather;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
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

public class Adapternew extends RecyclerView.Adapter<Adapternew.ViewHolder>{

    public LayoutInflater inflater;

    public List<Forecastday> listForecastday;

    public Adapternew(List<Forecastday> listForecastday)
    {
        this.listForecastday = listForecastday;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CustomBinding customBinding;

        public ViewHolder(CustomBinding customBinding) {
            super(customBinding.getRoot());
            this.customBinding = customBinding;
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (listForecastday == null)
            return 0;
        return listForecastday.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomBinding customBinding = DataBindingUtil.inflate(LayoutInflater
                .from(parent.getContext()),R.layout.custom,parent,false);

        ViewHolder viewHolder = new ViewHolder(customBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.customBinding.setModelFor(listForecastday.get(position));
        long timeInMilliseconds = listForecastday.get(position).getDateEpoch();
        String forcastdayname = formatDateTime(timeInMilliseconds);

        holder.customBinding.textView3.setText(forcastdayname);

/*        GradientDrawable tempCircleCurrent = (GradientDrawable) holder.customBinding.textView9.getBackground();
        int tempColor = getTemperatureColor((int) Double.parseDouble(String.valueOf(listForecastday.get(position).getDay().getMaxtempC())));
        tempCircleCurrent.setColor(tempColor);*/

    }

    /*@Override
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

    }*/

    private static String formatDateTime(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(("E"), Locale.ENGLISH);
        return formatter.format(new Date(timeInMilliseconds * 1000));
    }

    private int getTemperatureColor(int temp) {
        int tempColorResourceId;

        if (temp <= -21)
            tempColorResourceId = R.color.tempM21;
        else if (-20 <= temp && temp <= -16)
            tempColorResourceId = R.color.tempM20_M16;
        else if (-15 <= temp && temp <= -11)
            tempColorResourceId = R.color.tempM15_M11;
        else if (-10 <= temp && temp <= -6)
            tempColorResourceId = R.color.tempM10_M6;
        else if (-5 <= temp && temp <= -1)
            tempColorResourceId = R.color.tempM5_M1;
        else if (0 <= temp && temp <= 4)
            tempColorResourceId = R.color.temp0_P4;
        else if (5 <= temp && temp <= 9)
            tempColorResourceId = R.color.tempP5_P9;
        else if (10 <= temp && temp <= 14)
            tempColorResourceId = R.color.tempP10_P14;
        else if (15 <= temp && temp <= 19)
            tempColorResourceId = R.color.tempP15_P19;
        else if (20 <= temp && temp <= 24)
            tempColorResourceId = R.color.tempP20_P24;
        else if (25 <= temp && temp <= 29)
            tempColorResourceId = R.color.tempP25_P29;
        else if (30 <= temp && temp <= 34)
            tempColorResourceId = R.color.tempP30_P34;
        else if (temp >= 35)
            tempColorResourceId = R.color.tempP35;
        else
            tempColorResourceId = R.color.colorAccent;

        return ContextCompat.getColor(this.inflater.getContext(), tempColorResourceId);
    }
}
