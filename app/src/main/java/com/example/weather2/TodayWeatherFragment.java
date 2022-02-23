package com.example.weather2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TodayWeatherFragment extends Fragment {

    static TodayWeatherFragment instance;

    public static TodayWeatherFragment getInstance(){
        if(instance == null)
            instance = new TodayWeatherFragment();
        return instance;

    }

    public TodayWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_today_weather, container, false);
    }
}