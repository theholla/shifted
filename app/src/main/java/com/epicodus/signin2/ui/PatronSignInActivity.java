package com.epicodus.signin2.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.epicodus.signin2.R;

public class PatronSignInActivity extends AppCompatActivity {
    private TextView mAgreementsTextView;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patron_sign_in);

        mPreferences = getApplicationContext().getSharedPreferences("signinapp", Context.MODE_PRIVATE);
        mAgreementsTextView = (TextView) findViewById(R.id.agreementTextView);
        mAgreementsTextView.setText(mPreferences.getString("agreement", null));
    }

}
