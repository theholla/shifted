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

public class CreateCoopAccountActivity extends AppCompatActivity {
    @Bind(R.id.registerCoopNameEditText) EditText mName;
    @Bind(R.id.registerCoopEmailEditText) EditText mEmail;
    @Bind(R.id.registerCoopPasswordEditText) EditText mPassword;
    @Bind(R.id.registerCoopAgreementEditText) EditText mAgreement;
    @Bind(R.id.registerCoopTextView) TextView mRegisterCoop;
    @Bind(R.id.registerCoopAgreementTextView) TextView mRegisterCoopAgreement;
    @Bind(R.id.registerCoopAccountButton) Button mRegisterCoopAccountButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_coop_account);
        ButterKnife.bind(this);

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
                    clearFields();

                    ActiveBikeCollective.setActiveBikeCollective(newBikeCollective);

                    Intent intent = new Intent(CreateCoopAccountActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    mPassword.setText("");
                    mEmail.setText("");
                    mEmail.requestFocus();
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

    public void clearFields() {
        mName.setText("");
        mEmail.setText("");
        mPassword.setText("");
        mAgreement.setText("");
    }

}