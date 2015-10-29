package com.epicodus.signin2.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;

public class CreateCoopAccountActivity extends AppCompatActivity {
    private EditText mName, mEmail, mPassword, mAgreement;
    private TextView mRegisterCoop, mRegisterCoopAgreement;
    private Button mRegisterCoopAccountButton;
    private SharedPreferences mPreferences;

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
        mPreferences = getApplicationContext().getSharedPreferences("signinapp", Context.MODE_PRIVATE);

        mRegisterCoopAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mName.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String agreement = mAgreement.getText().toString().trim();

                SharedPreferences.Editor editor = mPreferences.edit();
                editor.putString("username", name);
                editor.commit();

                BikeCollective newBikeCollective = new BikeCollective(name, email, password, agreement);
                newBikeCollective.save();
                Toast.makeText(CreateCoopAccountActivity.this, newBikeCollective.getName() + " " + newBikeCollective.getEmail() + " " + newBikeCollective.getPassword() + " " + newBikeCollective.getAgreement(), Toast.LENGTH_LONG).show();

                //this is accidentally directing back to signInActivity. Saves newBikeCollective.
                Intent intent = new Intent(CreateCoopAccountActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
