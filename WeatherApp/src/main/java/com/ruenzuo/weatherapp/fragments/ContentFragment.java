package com.ruenzuo.weatherapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContentFragment extends ListFragment {

    public static final String ARG_OPTION_NUMBER = "option_number";

    public interface OnItemSelectedListener {
        public void onContentItemSelected(int content);
    }

    private OnItemSelectedListener listener;

    public ContentFragment() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] values = new String[10];
        int option = getArguments().getInt(ARG_OPTION_NUMBER);
        for (int i = 0; i < 10; i++) {
            values[i] = "Option: " + (option + 1) + " Content: " + (i + 1);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listener.onContentItemSelected(position);
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

}