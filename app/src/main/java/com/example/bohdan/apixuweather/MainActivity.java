package com.example.bohdan.apixuweather;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        searchView = findViewById(R.id.searching);
        spinnerone = findViewById(R.id.spinner);
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
        spinnerList.add("    Look");
        spinnerList.add("    Paris");
        spinnerList.add("    Munich");
        spinnerList.add("    London");
        spinnerList.add("    Uzhorod");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item,spinnerList);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerone.setAdapter(arrayAdapter);

        spinnerone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
        gridView.setAdapter(adapternew);
        // adapternew.notifyDataSetChanged();
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
