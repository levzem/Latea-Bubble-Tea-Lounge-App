package com.example.konchita.lateabubbletealoungeapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;

/**
 * Created by Derek on 12/2/2015.
 */
public class LateaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, GlobalConstants.PARSE_APP_ID, GlobalConstants.PARSE_CLIENT_KEY);
        ParseFacebookUtils.initialize(this);
    }
}
