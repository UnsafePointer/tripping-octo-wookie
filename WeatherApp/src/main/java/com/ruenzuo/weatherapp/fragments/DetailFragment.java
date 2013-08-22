package com.ruenzuo.weatherapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.adapters.ContentAdapter;

import java.util.Arrays;

public class DetailFragment extends Fragment {

    public static final String ARG_DETAIL_NUMBER = "detail_number";

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Bundle args = getArguments();
        if (args != null) {
            int detail = args.getInt(ARG_DETAIL_NUMBER);
            TextView textView = (TextView) view.findViewById(R.id.txtDetail);
            textView.setText("Detail: " + detail);
        }
        return view;
    }

}
