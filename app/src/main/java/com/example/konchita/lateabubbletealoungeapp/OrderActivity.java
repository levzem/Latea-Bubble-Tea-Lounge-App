package com.example.konchita.lateabubbletealoungeapp;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.title_of_recycler_page, new SizeFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
