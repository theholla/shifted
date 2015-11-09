package com.epicodus.signin2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;
import com.epicodus.signin2.utiil.ActiveBikeCollective;
import com.facebook.stetho.Stetho;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.welcomeText) TextView mWelcomeText;
    @Bind(R.id.contactSignInRedirectButton) Button mContactSignInRedirectButton;
    @Bind(R.id.adminPanelButton) Button mAdminPanelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        ButterKnife.bind(this);

        setWelcomeText();

        if (!ActiveBikeCollective.isLoggedIn()) {
            Intent intent = new Intent(this, CoopLoginActivity.class);
            startActivity(intent);
        }

        mContactSignInRedirectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactSignInActivity.class);
                startActivity(intent);
            }
        });

        mAdminPanelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_logout) {
            ActiveBikeCollective.logout();

            Intent intent = new Intent(this, CoopLoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void setWelcomeText() {
        BikeCollective bikeCollective = ActiveBikeCollective.getActiveBikeCollective();
        if (bikeCollective != null) {
            mWelcomeText.setText("Welcome, " + bikeCollective.getName());
        }
    }
}

/**
 * TODO: allow maneuvering with keyboard
 * TODO: top bar nav
 * TODO: implement hasMany relationship for ContactSignInEvents
 * TODO: add image to MainActivity
 **/