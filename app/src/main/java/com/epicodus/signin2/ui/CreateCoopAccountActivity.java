package com.epicodus.signin2.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, getString(R.string.parseKey), getString(R.string.parseValue));

        mRegisterCoopAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String coopName = mName.getText().toString().trim();
                String coopEmail = mEmail.getText().toString().trim();
                final String coopPassword = mPassword.getText().toString().trim();
                String coopAgreement = mAgreement.getText().toString().trim();

                if (coopName.isEmpty() || coopEmail.isEmpty() || coopPassword.isEmpty()) {
                    showDialog(getString(R.string.dialog_oops), getString(R.string.error_fill_all_fields));
                } else if (isAlreadyRegistered(coopEmail)) {
                    mPassword.setText("");
                    mEmail.setText("");
                    mEmail.requestFocus();
                    showDialog(getString(R.string.dialog_oops), getString(R.string.error_already_registered));
                } else {
                    BikeCollective newBikeCollective = new BikeCollective(coopName, coopEmail, coopPassword, coopAgreement);
                    newBikeCollective.save();

                    ActiveBikeCollective.setActiveBikeCollective(newBikeCollective);

                    ParseUser.getCurrentUser().logOut();

                    ParseUser newCoop = new ParseUser();
                    newCoop.setUsername(coopName);
                    newCoop.setPassword(coopPassword);
                    newCoop.setEmail(coopEmail);
                    newCoop.put("agreement", coopAgreement);

                    newCoop.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            setProgressBarIndeterminateVisibility(false);
                            if (e == null) {
                                showDialogAndNewIntent(getString(R.string.dialog_thanks), getString(R.string.dialog_registered_coop));
                                clearFields();
                            } else {
                                showDialog(getString(R.string.dialog_oops), getString(R.string.error_there_was_an_exception) + e.getLocalizedMessage());
                            }
                        }
                    });
                }
            }
        });
    }

    private void goToMainPage() {
        Intent intent = new Intent(CreateCoopAccountActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void showDialogAndNewIntent(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CreateCoopAccountActivity.this);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        goToMainPage();
                    }
                });
        dialog.create();
        dialog.show();
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(CreateCoopAccountActivity.this);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null);
        dialog.create();
        dialog.show();
    }

    public static boolean isAlreadyRegistered(String coopEmail) {
        if (BikeCollective.find(coopEmail) != null) {
            return true;
        } else {
            return false;
        }
    }

    public void clearFields() {
        mName.setText("");
        mEmail.setText("");
        mPassword.setText("");
        mAgreement.setText("");
    }

}