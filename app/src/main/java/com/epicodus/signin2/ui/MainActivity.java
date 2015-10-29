package com.epicodus.signin2.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private BikeCollective mBikeCollective;
    private TextView mWelcomeText;
    private ImageView mMainImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("signinapp", Context.MODE_PRIVATE);
        mWelcomeText = (TextView) findViewById(R.id.welcomeText);
        mMainImageView = (ImageView) findViewById(R.id.mainImageView);

        if (!isLoggedIn()) {
            Intent intent = new Intent(this, CoopLoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean isLoggedIn() {
        String username = mPreferences.getString("name", null);
        if (username == null) {
            return false;
        } else {
            setBikeCollective(username);
            return true;
        }
    }

    private void setBikeCollective(String name) {
        BikeCollective bikeCollective = BikeCollective.find(name);
        if (bikeCollective != null) {
            mBikeCollective = bikeCollective;
            mWelcomeText.setText(bikeCollective.getName() + "'s Sign In App");
        }
    }

}
