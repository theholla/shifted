package com.epicodus.signin2;

import android.app.Application;

import com.parse.Parse;

public class SignInApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: integrate into application by changing app name in Manifest. Currently unused.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "dOCMN4er20zZtpSZfGHuy3mJ5tb3Dmz1eIIUl41i", "wOAFcA233sW0LH8ilIsWQJ0JsIgGasTQMjjBJpgB");

    }
}
