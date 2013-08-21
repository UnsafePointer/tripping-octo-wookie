package com.ruenzuo.weatherapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ruenzuo.weatherapp.R;
import com.ruenzuo.weatherapp.adapters.MenuAdapter;

import java.util.Arrays;

public class MenuFragment extends ListFragment{

    public interface OnItemSelectedListener {
        public void onMenuItemSelected(int option);
    }

    private OnItemSelectedListener listener;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] menuOptions = getResources().getStringArray(R.array.menu_options);
        MenuAdapter adapter = new MenuAdapter(getActivity(), Arrays.asList(menuOptions));
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener)activity;
        } else {
            throw new ClassCastException(activity.toString()
                    + " must implemenet MenuFragment.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listener.onMenuItemSelected(position);
    }

}
