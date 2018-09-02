package com.example.bohdan.apixuweather;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.bohdan.apixuweather.databinding.SecondFragmentBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SecondFragment extends Fragment {

    SecondFragmentBinding secondFragmentBinding;
    Forecastday forecastday;
    List<Forecastday> listForecastday;
    Model model;
    int position;
    Button closeButton, yesButton;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        secondFragmentBinding = DataBindingUtil.inflate(inflater,
                R.layout.second_fragment, container, false);

        Bundle bundle = getArguments();
        if (bundle!=null){
            listForecastday = (List<Forecastday>) bundle.getSerializable("ForCastDay");
            position = (int) bundle.getSerializable("position");
        }

        long timeInMilliseconds = listForecastday.get(position).getDateEpoch();
        String forcastdayname = formatDateTime(timeInMilliseconds);
        secondFragmentBinding.textView15.setText("Day: " + forcastdayname);

        secondFragmentBinding.setModelFor(listForecastday.get(position));

        /** button forward to next fragment day */
        secondFragmentBinding.buttonForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position==listForecastday.size()-1) {
                    Toast toast =  Toast.makeText(getActivity(), "LAST ELEMENT", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,10,10);
                    //LinearLayout toastcontainer = (LinearLayout) toast.getView();
                    //ImageView imageView = new ImageView(container.getContext());
                    //imageView.setImageResource(R.drawable.disxtwo);
                    //toastcontainer.addView(imageView,300,300);
                    toast.show();
                }else
                {
                    position++;
                    long timeInMilliseconds = listForecastday.get(position).getDateEpoch();
                    String forcastdayname = formatDateTime(timeInMilliseconds);
                    secondFragmentBinding.textView15.setText("Day: " + forcastdayname);
                    secondFragmentBinding.setModelFor(listForecastday.get(position));
                }
            }
        });

        /** button reward to previous fragment day */
        secondFragmentBinding.buttonReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position==0){
                    Toast toast =  Toast.makeText(getActivity(), "FIRST ELEMENT", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,10,10);
                    //LinearLayout toastcontainer = (LinearLayout) toast.getView();
                    //ImageView imageView = new ImageView(container.getContext());
                    //imageView.setImageResource(R.drawable.disxtwo);
                    //toastcontainer.addView(imageView,300,300);
                    toast.show();
                }
                else {
                    position--;
                    long timeInMilliseconds = listForecastday.get(position).getDateEpoch();
                    String forcastdayname = formatDateTime(timeInMilliseconds);
                    secondFragmentBinding.textView15.setText("Day: " + forcastdayname);
                    secondFragmentBinding.setModelFor(listForecastday.get(position));
                }
            }
        });

        /** button back with popUpWindow */

        secondFragmentBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.popup,null);
                final PopupWindow popupWindow = new PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT,
                                WindowManager.LayoutParams.MATCH_PARENT);

                closeButton = popupView.findViewById(R.id.closePopupBtn);
                yesButton = popupView.findViewById(R.id.yesBtn);
                popupWindow.showAtLocation(secondFragmentBinding.getRoot(),Gravity.CENTER,0,0);


                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity activityHome = (MainActivity) container.getContext();
                        CallbackClass callbacks = new CallbackClass();
                        callbacks.registerCallBack(activityHome);
                        callbacks.buttonBack();
                        popupWindow.dismiss();
                    }
                });
            }
        });
        return secondFragmentBinding.getRoot();
    }

    private static String formatDateTime(long timeInMilliseconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(("E"), Locale.ENGLISH);
        return formatter.format(new Date(timeInMilliseconds * 1000));
    }
}
