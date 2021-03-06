package com.epicodus.signin2.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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
    @Bind(R.id.fixBikeRadioButton) RadioButton mFixBikeRadioButton;
    @Bind(R.id.volunteerRadioButton) RadioButton mVolunteerRadioButon;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_sign_in);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setAgreementText();

        mContactSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BikeCollective bikeCollective = ActiveBikeCollective.getActiveBikeCollective();
                final String contactName = mContactName.getText().toString().trim();

                if (contactName.isEmpty()) {
                    showDialog(getString(R.string.dialog_oops), getString(R.string.error_name));
                } else if (!mFixBikeRadioButton.isChecked() && !mVolunteerRadioButon.isChecked()) {
                    showDialog(getString(R.string.dialog_oops), getString(R.string.error_choose_purpose));
                } else {
                    final int radioChoice = mPurposeRadioGroup.getCheckedRadioButtonId();
                    RadioButton choice = (RadioButton) findViewById(radioChoice);
                    String purpose = choice.getText().toString();
                    SignInEvent newSignInEvent = new SignInEvent(contactName, purpose, bikeCollective);
                    newSignInEvent.save();
                    showDialog(getString(R.string.dialog_thanks), getString(R.string.dialog_signedin));
                    clearFields();
                }
            }
        });
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ContactSignInActivity.this);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(android.R.string.ok, null)
                .create()
                .show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact_sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
