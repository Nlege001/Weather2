package com.example.weather2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AboutFragment extends Fragment {
    static AboutFragment instance;

    public static AboutFragment getInstance(){
        if(instance == null)
            instance = new AboutFragment();
        return instance;

    }




    public AboutFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_about, container, false);
        return itemView;
    }
}