package com.example.bohdan.apixuweather;

import android.databinding.BindingAdapter;
import android.net.Uri;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BindingAdapters {

    @BindingAdapter("android:src")
    public static void loadImage(ImageView view, String string) {
        //Glide.with(view.getContext()).load(string).into(view);
        Picasso.get()
                .load("https://" + string)
                .into(view);

        }

    @BindingAdapter("android:src")
    public static void listBindGridview (GridView view, Forecast forecast){
        Adapternew adapterNew = new Adapternew(forecast);
        view.setAdapter(adapterNew);
    }
}
