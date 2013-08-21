package com.ruenzuo.weatherapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ruenzuo.weatherapp.R;

import java.util.ArrayList;
import java.util.List;

public class MenuAdapter extends ArrayAdapter <String> {

    static class ViewHolder {
        public TextView textOption;
    }

    private final ArrayList<String> options;
    private final Context context;

    public MenuAdapter(Context context, List<String> options) {
        super(context, R.layout.row_menu, options);
        this.context = context;
        this.options = new ArrayList<String>(options);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_menu, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textOption = (TextView) rowView.findViewById(R.id.txtOption);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textOption.setText(options.get(position));
        return rowView;
    }

}
