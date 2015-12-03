package com.example.konchita.lateabubbletealoungeapp;


import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Derek on 12/3/2015.
 */
public class FlavorFragment extends ListFragment {
    private List<FlavorListViewItem> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ParseQuery<ParseObject> query = ParseQuery.getQuery(GlobalConstants.FLAVOR_TABLE);
        query.whereEqualTo(GlobalConstants.FLAVOR_BASE, "Green");
        Log.d(GlobalConstants.PARSE_DEBUG, "I GET HERE");
        List<ParseObject> list;
        items = new ArrayList<>();

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e != null) {
                    Log.d(GlobalConstants.PARSE_DEBUG, e.getMessage());
                    return;
                }

                for (ParseObject po : objects) {
                    items.add(new FlavorListViewItem(po.getString(GlobalConstants.FLAVOR_ID)));
                }

                setListAdapter(new FlavorListViewAdapter(getActivity(), items));
            }
        });
    }

    @Override
    public void onListItemClick(ListView lv, View view, int position, long id) {
        FlavorListViewItem item = items.get(position);
        Toast.makeText(getActivity(), "Change this later", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(null);
    }
}
