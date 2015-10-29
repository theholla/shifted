package com.epicodus.signin2.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;

public class CoopLoginActivity extends AppCompatActivity {

    private TextView mMainHeaderTextView;
    private SharedPreferences mPreferences;
    private EditText mCoopEmail, mCoopPassword;
    private Button mCoopSignInButton, mCoopCreateAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coop_login);

        mMainHeaderTextView = (TextView) findViewById(R.id.mainHeaderTextView);
        mPreferences = getApplicationContext().getSharedPreferences("signinapp", Context.MODE_PRIVATE);
        mCoopEmail = (EditText) findViewById(R.id.coopEmailEditText);
        mCoopPassword = (EditText) findViewById(R.id.coopPasswordEditText);
        mCoopSignInButton = (Button) findViewById(R.id.coopSignInButton);
        mCoopCreateAccountButton = (Button) findViewById(R.id.coopCreateAccountButton);

        mCoopSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coopEmail = mCoopEmail.getText().toString().trim();
                String coopPassword = mCoopPassword.getText().toString().trim();
                BikeCollective bikeCollective = BikeCollective.find(coopEmail);

                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("name", bikeCollective.getName());
                editor.commit();

                if (bikeCollective != null) {
                    if (bikeCollective.getPassword().equals(coopPassword)) {
                        Intent intent = new Intent(CoopLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(CoopLoginActivity.this);
                        builder.setMessage("You're either not registered, or you login information is wrong.")
                                .setTitle("Oops!")
                                .setPositiveButton(android.R.string.ok, null);
                        AlertDialog dialog = builder.create();
                        dialog.show();

                        //TODO: tell user if registered or not
                    }
                }
            }
        });

        mCoopCreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoopLoginActivity.this, CreateCoopAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
