package com.epicodus.signin2;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class SignInApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // TODO: integrate into application by changing app name in Manifest. Currently unused.
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "dOCMN4er20zZtpSZfGHuy3mJ5tb3Dmz1eIIUl41i", "wOAFcA233sW0LH8ilIsWQJ0JsIgGasTQMjjBJpgB");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        Log.e("PARSE.COM", "DOWNLOAD STARTED");
        testObject.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e("PARSE.COM", "FAILED" + e.getMessage());
                } else {
                    Log.e("PARSE.COM", "SUCCESS");
                }
            }
        });
    }
}
