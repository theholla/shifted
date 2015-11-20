package com.epicodus.signin2;

import android.app.Application;

import com.parse.Parse;

public class SignInApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parseKey), getString(R.string.parseValue));
    }
}
