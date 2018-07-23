package com.example.bohdan.apixuweather;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.bohdan.apixuweather.databinding.FragmentOneBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class FirstFragment extends Fragment implements Serializable {

    public Model model;
    public String s = "";
    ArrayList<String> spinnerList = new ArrayList<>();
    GridView gridView;
    Adapternew adapternew;
    FragmentOneBinding fragmentOneBinding;
    public boolean visibleOk = false;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        fragmentOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one,container,false);
        //fragmentOneBinding.setModelFor(model);
        LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        fragmentOneBinding.gridListView.setLayoutManager(layoutManager);
        fragmentOneBinding.gridListView.addItemDecoration(new DividerItemDecoration
                (container.getContext(), DividerItemDecoration.HORIZONTAL));

        fragmentOneBinding.searching.setQueryHint("Enter a city to search...");

        fragmentOneBinding.searching.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                s = query;

                    MainActivity activityHome = (MainActivity) container.getContext();
                    CallbackClass callbacks = new CallbackClass();
                    callbacks.registerCallBack(activityHome);

                    try {
                        model = callbacks.loadObject(s);
                        visibleOk = true;

                        if (model==null) {
                            Toast.makeText(getActivity(), "no such town", Toast.LENGTH_SHORT).show();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                // так ми робим різні кольори в круглій textview в залежності від температури
                GradientDrawable tempCircleCurrent = (GradientDrawable) fragmentOneBinding.textView11.getBackground();
                int tempColor = getTemperatureColor((int) Double.parseDouble(String.valueOf(model.getCurrent().getTempC())));
                tempCircleCurrent.setColor(tempColor);

                    //adapternew = new Adapternew(model.getForecast().getForecastday());
                    fragmentOneBinding.setModelFor(model);
                    //gridView.setAdapter(adapternew);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        /** ......................................!SPINNER!...................................*/
        // spinnerList.clear();
        spinnerList.add("Choose a city...");
        spinnerList.add("Paris");
        spinnerList.add("Munich");
        spinnerList.add("London");
        spinnerList.add("Uzhorod");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(container.getContext(),
                R.layout.spinner_item,spinnerList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        fragmentOneBinding.spinner.setAdapter(arrayAdapter);

        fragmentOneBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==1){
                    s = "Paris";
                    MainActivity activityHome = (MainActivity) container.getContext();
                    CallbackClass callbacks = new CallbackClass();
                    callbacks.registerCallBack(activityHome);
                    try {
                        model = callbacks.loadObject(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //adapternew = new Adapternew(model.getForecast().getForecastday());
                    fragmentOneBinding.setModelFor(model);
                    //gridView.setAdapter(adapternew);
                    GradientDrawable tempCircleCurrent = (GradientDrawable) fragmentOneBinding.textView11.getBackground();
                    int tempColor = getTemperatureColor((int) Double.parseDouble(String.valueOf(model.getCurrent().getTempC())));
                    tempCircleCurrent.setColor(tempColor);
                }
                if (position==2){
                    s = "Munich";
                    MainActivity activityHome = (MainActivity) container.getContext();
                    CallbackClass callbacks = new CallbackClass();
                    callbacks.registerCallBack(activityHome);
                    try {
                        model = callbacks.loadObject(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //adapternew = new Adapternew(model.getForecast().getForecastday());
                    fragmentOneBinding.setModelFor(model);
                    //gridView.setAdapter(adapternew);
                    GradientDrawable tempCircleCurrent = (GradientDrawable) fragmentOneBinding.textView11.getBackground();
                    int tempColor = getTemperatureColor((int) Double.parseDouble(String.valueOf(model.getCurrent().getTempC())));
                    tempCircleCurrent.setColor(tempColor);
                }
                if (position==3){
                    s = "London";
                    MainActivity activityHome = (MainActivity) container.getContext();
                    CallbackClass callbacks = new CallbackClass();
                    callbacks.registerCallBack(activityHome);
                    try {
                        model = callbacks.loadObject(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //adapternew = new Adapternew(model.getForecast().getForecastday());
                    fragmentOneBinding.setModelFor(model);
                    //gridView.setAdapter(adapternew);
                }
                if (position==4){
                    s = "Uzhorod";
                    MainActivity activityHome = (MainActivity) container.getContext();
                    CallbackClass callbacks = new CallbackClass();
                    callbacks.registerCallBack(activityHome);
                    try {
                        model = callbacks.loadObject(s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //adapternew = new Adapternew(model.getForecast().getForecastday());
                    fragmentOneBinding.setModelFor(model);
                    //gridView.setAdapter(adapternew);
                    GradientDrawable tempCircleCurrent = (GradientDrawable) fragmentOneBinding.textView11.getBackground();
                    int tempColor = getTemperatureColor((int) Double.parseDouble(String.valueOf(model.getCurrent().getTempC())));
                    tempCircleCurrent.setColor(tempColor);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //gridView = container.findViewById(R.id.gridListView);
        fragmentOneBinding.gridListView.addOnItemTouchListener(new RecyclerItemClickListener(container.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MainActivity activityHome = (MainActivity) view.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.loadObjectSecondFr(model.getForecast().getForecastday().get(position));
            }
        }));

       /* fragmentOneBinding.gridListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                *//*Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("item", model.getForecast().getForecastday().get(position));
                startActivity(intent);*//*

                MainActivity activityHome = (MainActivity) view.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.loadObjectSecondFr(model.getForecast().getForecastday().get(position));
            }
        });*/

        return fragmentOneBinding.getRoot();

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

        return ContextCompat.getColor(this.getActivity(), tempColorResourceId);
    }

}
