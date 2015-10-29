package com.epicodus.signin2.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.signin2.R;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
//    private BikeCollective mBikeCollective;
    private TextView mWelcomeText;
    private ImageView mMainImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("signinapp", Context.MODE_PRIVATE);
        mWelcomeText = (TextView) findViewById(R.id.welcomeText);
        mMainImageView = (ImageView) findViewById(R.id.mainImageView);
    }

}
