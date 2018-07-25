package com.example.bohdan.apixuweather;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String string) {
        Picasso.get()
                .load("https://" + string)
                .into(view);
        }

    @BindingAdapter("bind:items")
    public static void listBindGridview (RecyclerView view, List<Forecastday> forecastday){
        Adapternew adapterNew = new Adapternew(forecastday);
        view.setAdapter(adapterNew);
    }
}
