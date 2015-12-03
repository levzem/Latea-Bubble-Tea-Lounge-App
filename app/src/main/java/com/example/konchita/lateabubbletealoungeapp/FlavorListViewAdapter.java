package com.example.konchita.lateabubbletealoungeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Derek on 12/3/2015.
 */
public class FlavorListViewAdapter extends ArrayAdapter<FlavorListViewItem> {
    public FlavorListViewAdapter(Context context, List<FlavorListViewItem> items) {
        super(context, R.layout.fragment_flavor_list_item, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_flavor_list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.flavor = (TextView)
                    convertView.findViewById(R.id.fragment_flavor_list_item_text);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();

        FlavorListViewItem item = getItem(position);
        viewHolder.flavor.setText(item.flavor);

        return convertView;
    }

    private static class ViewHolder {
        TextView flavor;
    }
}
