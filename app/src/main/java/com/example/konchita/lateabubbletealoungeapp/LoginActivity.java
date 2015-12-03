package com.example.konchita.lateabubbletealoungeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity  {
    ProgressDialog progressDialog;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ParseUser currentUser = ParseUser.getCurrentUser();
        if (currentUser != null && ParseFacebookUtils.isLinked(currentUser)) toOrderActivity();
    }

    @Override
    public void onPause() {
        super.onPause();
        AppEventsLogger.activateApp(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        AppEventsLogger.deactivateApp(this);
    }

    /**********************************************************************************************
     * Helper Methods
     */
    /* saves firstName, lastName, relStatus to Parse */
    /* make sure we ask Facebook for relationship_status permission */
    public void getUserDetails() {
        Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG, "UserDetails Method initiated");
        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,gender,email");
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse response) {
                        Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG, "Graph request completes");

                        ParseUser parseUser = ParseUser.getCurrentUser();

                        String first_name = jsonObject.optString("first_name");
                        String last_name = jsonObject.optString("last_name");
                        String gender = jsonObject.optString("gender");
                        String email = jsonObject.optString("email");

                        Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG, "First Name: " + first_name);
                        Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG, "Last Name: " + last_name);
                        Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG, "Gender: " + gender);
                        Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG, "Email: " + email);

                        parseUser.put(GlobalConstants.USER_FIRST_NAME, first_name);
                        parseUser.put(GlobalConstants.USER_LAST_NAME, last_name);
                        parseUser.put(GlobalConstants.USER_GENDER, gender);
                        parseUser.setEmail(email);
                        parseUser.saveInBackground();
                    }
                });
        request.setParameters(parameters);
        request.executeAsync();
        toOrderActivity();
    }

    public void onLoginButtonClick(View v) {
        Log.d("THIS", "Login Button Clicked");
        progressDialog = ProgressDialog.show(LoginActivity.this, "", "Logging in...", true);
        List<String> permissions = new ArrayList<>();
        permissions.add("public_profile");
        permissions.add("email");
        permissions.add("user_friends");

        ParseFacebookUtils.logInWithReadPermissionsInBackground(this,
                permissions, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        progressDialog.dismiss();
                        if (user == null) Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG,
                                "Facebook Login was cancelled somehow.");
                        else if (user.isNew()) {
                            Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG,
                                    "User signed up and logged in through Facebook");
                            getUserDetails();
                        } else {
                            Log.d(GlobalConstants.FACEBOOK_LOGIN_DEBUG,
                                    "User successfully logged into Facebook and Connect");
                            toOrderActivity();
                        }
                    }
                });
    }

    public void toOrderActivity() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
}
