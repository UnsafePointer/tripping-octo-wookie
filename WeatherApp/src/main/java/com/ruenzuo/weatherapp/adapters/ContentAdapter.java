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

public class ContentAdapter extends ArrayAdapter<String> {

    static class ViewHolder {
        public TextView textContent;
    }

    private final ArrayList<String> contents;
    private final Context context;

    public ContentAdapter(Context context, List<String> contents) {
        super(context, R.layout.row_menu, contents);
        this.context = context;
        this.contents = new ArrayList<String>(contents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row_content, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textContent = (TextView) rowView.findViewById(R.id.txtContent);
            rowView.setTag(viewHolder);
        }
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.textContent.setText(contents.get(position));
        return rowView;
    }

}
