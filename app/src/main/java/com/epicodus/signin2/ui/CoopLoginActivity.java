package com.epicodus.signin2.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;
import com.epicodus.signin2.utiil.ActiveBikeCollective;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CoopLoginActivity extends AppCompatActivity {
    @Bind(R.id.coopEmailEditText) EditText mCoopEmail;
    @Bind(R.id.coopPasswordEditText) EditText mCoopPassword;
    @Bind(R.id.coopSignInButton) Button mCoopSignInButton;
    @Bind(R.id.coopCreateAccountButton) Button mCoopCreateAccountButton;
    @Bind(R.id.mainHeaderTextView) TextView mMainHeaderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coop_login);
        ButterKnife.bind(this);

        mCoopSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coopEmail = mCoopEmail.getText().toString().trim();
                String coopPassword = mCoopPassword.getText().toString().trim();

                BikeCollective bikeCollective = BikeCollective.find(coopEmail);

                if (bikeCollective != null) {
                    if (bikeCollective.getPassword().equals(coopPassword)) {
                        ActiveBikeCollective.setActiveBikeCollective(bikeCollective);
                        clearFields();

                        Intent intent = new Intent(CoopLoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(CoopLoginActivity.this);
                        dialog.setMessage("Your email and password don't match.")
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                        dialog.create();
                        dialog.show();
                        clearFields();
                    }
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(CoopLoginActivity.this);
                    dialog.setMessage("You don't appear to be registered.")
                            .setTitle("Oops!")
                            .setPositiveButton(android.R.string.ok, null);
                    dialog.create();
                    dialog.show();
                    clearFields();
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

    public void clearFields() {
        mCoopEmail.setText("");
        mCoopPassword.setText("");
    }
}
