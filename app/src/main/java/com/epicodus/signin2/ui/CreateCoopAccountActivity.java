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

public class CreateCoopAccountActivity extends AppCompatActivity {
    private EditText mName, mEmail, mPassword, mAgreement;
    private TextView mRegisterCoop, mRegisterCoopAgreement;
    private Button mRegisterCoopAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coop_account);

        mName = (EditText) findViewById(R.id.registerCoopNameEditText);
        mEmail = (EditText) findViewById(R.id.registerCoopEmailEditText);
        mPassword = (EditText) findViewById(R.id.registerCoopPasswordEditText);
        mAgreement = (EditText) findViewById(R.id.registerCoopAgreementEditText);
        mRegisterCoop = (TextView) findViewById(R.id.registerCoopTextView);
        mRegisterCoopAgreement = (TextView) findViewById(R.id.registerCoopAgreementTextView);
        mRegisterCoopAccountButton = (Button) findViewById(R.id.registerCoopAccountButton);

        mRegisterCoopAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coopName = mName.getText().toString().trim();
                String coopEmail = mEmail.getText().toString().trim();
                String coopPassword = mPassword.getText().toString().trim();
                String coopAgreement = mAgreement.getText().toString().trim();

                BikeCollective alreadyRegistered = BikeCollective.find(coopEmail);

                if (alreadyRegistered == null) {
                    BikeCollective newBikeCollective = new BikeCollective(coopName, coopEmail, coopPassword, coopAgreement);
                    newBikeCollective.save();

                    ActiveBikeCollective.setActiveBikeCollective(newBikeCollective);

                    Intent intent = new Intent(CreateCoopAccountActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(CreateCoopAccountActivity.this);
                    dialog.setMessage("This email is already registered.")
                        .setTitle("Oops!")
                        .setPositiveButton(android.R.string.ok, null);
                    dialog.create();
                    dialog.show();
                }
            }
        });
    }
}