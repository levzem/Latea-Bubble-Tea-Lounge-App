package com.example.konchita.lateabubbletealoungeapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.FindCallback;
import java.util.List;
import com.parse.ParseException;
import android.util.Log;


/**
 *
 */
public class BaseTeaFragment extends Fragment
{
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState)
    {
        //TODO: progromatically add buttons
        final View view = inflater.inflate(R.layout.fragment_base_tea, container, false);
        final LinearLayout ll = (LinearLayout) view.findViewById(R.id.fragment_base_tea_layout);


        ParseQuery<ParseObject> query = ParseQuery.getQuery(GlobalConstants.TEABASES_TABLE);

        /*
        try {
            List<ParseObject> teaList = query.find();

            for (ParseObject tea : teaList) {

                Button button = new Button(getActivity());
                button.setText(tea.getString(GlobalConstants.TEABASES_ID));
                LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
                button.setLayoutParams(lp);
                ll.addView(button);
            }
        } catch (ParseException error) {
            Log.d("HI", "HELLP");
        }
        */


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> teaList, ParseException e) {
                if (e == null) {
                    for (ParseObject tea : teaList) {

                        Button button = new Button(getActivity());
                        button.setText(tea.getString(GlobalConstants.TEABASES_ID));
                        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
                        LinearLayout ll = (LinearLayout) view.findViewById(R.id.fragment_base_tea_layout);
                        ll.addView(button, lp);
                    }
                } else Log.d("ChangethislaterDerek", e.getMessage());
            }
        });


        return view;
    }
}
