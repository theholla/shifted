package com.epicodus.signin2.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;
import com.epicodus.signin2.models.ContactSignInEvent;
import com.epicodus.signin2.utiil.ActiveBikeCollective;

public class ContactSignInActivity extends AppCompatActivity {
    private TextView mAgreementsTextView;
    private Button mContactSignInButton;
    private EditText mContactName;
    private RadioGroup mPurposeRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sign_in);

        mContactSignInButton = (Button) findViewById(R.id.contactSignInButton);
        mAgreementsTextView = (TextView) findViewById(R.id.agreementTextView);
        mContactName = (EditText) findViewById(R.id.nameEditText);
        mPurposeRadioGroup = (RadioGroup) findViewById(R.id.purposeRadioGroup);

        setAgreementText();

        mContactSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BikeCollective bikeCollective = ActiveBikeCollective.getActiveBikeCollective();
                String contactName = mContactName.getText().toString().trim();
                //TODO: grab value using getCheckedRadioButtonId()
                String contactType = "Volunteer";

                String contactPassword = "test";
                String contactEmail = "test";
                String contactBirthday = "test";

                ContactSignInEvent newContactSignInEvent = new ContactSignInEvent(contactName, contactType, bikeCollective);
                newContactSignInEvent.save();

                //TODO: make this Toast into a modal
                Toast toast = Toast.makeText(ContactSignInActivity.this, "Thanks for signing in!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();

                clearFields();
            }
        });
    }

    public void clearFields() {
        mContactName.setText("");
        mPurposeRadioGroup.clearCheck();
    }

    private void setAgreementText() {
        BikeCollective bikeCollective = ActiveBikeCollective.getActiveBikeCollective();
        if (bikeCollective != null) {
            mAgreementsTextView.setText(bikeCollective.getAgreement());
        }
    }

}
