package com.example.bohdan.apixuweather;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bohdan.apixuweather.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Serializable{

    public final String URL = "http://api.apixu.com/v1/";

    public Thread loadObjectThread;
    public Call<Model> idsCall;
    public Model model;

    public String s = "";
    SearchView searchView;

    Adapternew adapternew;
    ActivityMainBinding activityMainBinding;
    TextView textView;
    GridView gridView;
    ArrayAdapter<String> arrayAdapter;
    ListView listView;
    ArrayList<String> stringArrayList;
    ArrayList<String> spinnerList = new ArrayList<>();
    Spinner spinnerone;
    ArrayListModels arrayListModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        activityMainBinding.setModelFor(model);

        activityMainBinding.searching.setQueryHint("Enter a city to search...");
        activityMainBinding.searching.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //if (stringArrayList.contains(query)) {
                    //arrayAdapter.getFilter().filter(query);
                    s = query;
                    try {
                        getIDSFromWeb();
                        adapternew = new Adapternew(model.getForecast());
                        System.out.print("Looking adapter");
                        activityMainBinding.setModelFor(model);
                        gridView.setAdapter(adapternew);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                //}
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

        //!!!...spinner
        // spinnerList.clear();
        spinnerList.add("Choose a city...");
        spinnerList.add("Paris");
        spinnerList.add("Munich");
        spinnerList.add("London");
        spinnerList.add("Uzhorod");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,
                R.layout.spinner_item,spinnerList);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        activityMainBinding.spinner.setAdapter(arrayAdapter);

        activityMainBinding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==1){
                    s = "Paris";
                    try {
                        getIDSFromWeb();
                        adapternew = new Adapternew(model.getForecast());
                        System.out.print("Looking adapter");
                        activityMainBinding.setModelFor(model);
                        gridView.setAdapter(adapternew);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        gridView = findViewById(R.id.gridListView);

        activityMainBinding.gridListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              /*  try {
                    getIDSFromWeb();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("item", model.getForecast().getForecastday().get(position));
                startActivity(intent);
            }
        });

    }

    public void getIDSFromWeb() throws InterruptedException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ModelApi idsApi = retrofit.create(ModelApi.class);

            idsCall = idsApi.idsInfo(s);
            loadObjectThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        model = idsCall.execute().body();
                        System.out.println("Look: ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            loadObjectThread.start();
            loadObjectThread.join();
        }


    }

