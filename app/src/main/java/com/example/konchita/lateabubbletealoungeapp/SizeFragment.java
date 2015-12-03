package com.example.konchita.lateabubbletealoungeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;



/**
 * A simple {@link Fragment} subclass.
 */
public class SizeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_size, container, false);

        LinearLayout rl = (LinearLayout) view.findViewById(R.id.fragment_size_layout);

        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);

        Button b1 = new Button(getActivity());
        b1.setText("Small");
        b1.setLayoutParams(lp);
        rl.addView(b1);

        Button b2 = new Button(getActivity());
        b2.setText("Medium");
        b2.setLayoutParams(lp);
        rl.addView(b2);

        Button b3 = new Button(getActivity());
        b3.setText("Large");
        b3.setLayoutParams(lp);
        rl.addView(b3);

        return view;
    }
}
