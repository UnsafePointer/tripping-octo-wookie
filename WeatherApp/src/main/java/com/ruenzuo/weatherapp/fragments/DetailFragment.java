package com.ruenzuo.weatherapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ruenzuo.weatherapp.R;

public class DetailFragment extends Fragment {

    public static final String ARG_DETAIL_NUMBER = "detail_number";

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}
