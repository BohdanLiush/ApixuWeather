package com.example.bohdan.apixuweather;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SearchView;

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


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        fragmentOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one,container,false);
        //fragmentOneBinding.setModelFor(model);


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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //gridView = container.findViewById(R.id.gridListView);
        fragmentOneBinding.gridListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /*Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("item", model.getForecast().getForecastday().get(position));
                startActivity(intent);*/

                MainActivity activityHome = (MainActivity) view.getContext();
                CallbackClass callbacks = new CallbackClass();
                callbacks.registerCallBack(activityHome);
                callbacks.loadObjectSecondFr(model.getForecast().getForecastday().get(position));
            }
        });

        return fragmentOneBinding.getRoot();
    }
}
