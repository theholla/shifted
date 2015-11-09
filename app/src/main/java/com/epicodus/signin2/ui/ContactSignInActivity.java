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
import com.epicodus.signin2.models.SignInEvent;
import com.epicodus.signin2.utiil.ActiveBikeCollective;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactSignInActivity extends AppCompatActivity {
    @Bind(R.id.contactSignInButton) Button mContactSignInButton;
    @Bind(R.id.agreementTextView) TextView mAgreementsTextView;
    @Bind(R.id.nameEditText) EditText mContactName;
    @Bind(R.id.purposeRadioGroup) RadioGroup mPurposeRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sign_in);
        ButterKnife.bind(this);

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

                SignInEvent newSignInEvent = new SignInEvent(contactName, contactType, bikeCollective);
                newSignInEvent.save();

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
        // TODO: implement scroll bar in safe space agreement
        BikeCollective bikeCollective = ActiveBikeCollective.getActiveBikeCollective();
        if (bikeCollective != null) {
            mAgreementsTextView.setText(bikeCollective.getAgreement());
        }
    }

}
