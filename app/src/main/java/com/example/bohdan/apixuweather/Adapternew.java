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
import java.util.Calendar;
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

        String dateFormat = "dd-MM-yyyy hh:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMilliseconds);
        return simpleDateFormat.format(calendar.getTime());
   /*
        SimpleDateFormat formatter = new SimpleDateFormat(("E"), Locale.ENGLISH);
        return formatter.format(new Date(timeInMilliseconds * 1000));*/
    }
}
