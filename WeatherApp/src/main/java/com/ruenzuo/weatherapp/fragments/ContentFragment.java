package com.ruenzuo.weatherapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.adapters.ContentAdapter;

import java.util.Arrays;

public class ContentFragment extends ListFragment {

    public static final String ARG_OPTION_NUMBER = "option_number";

    public interface OnItemSelectedListener {
        public void onContentItemSelected(int contentItem);
    }

    private OnItemSelectedListener listener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[20];
        int option = getArguments().getInt(ARG_OPTION_NUMBER);
        for (int i = 0; i < 20; i++) {
            values[i] = "Option: " + (option + 1) + ", Detail: " + (i + 1);
        }
        ContentAdapter adapter = new ContentAdapter(getActivity(), Arrays.asList(values));
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet ContentFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listener.onContentItemSelected(position);
    }

}