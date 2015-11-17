package com.epicodus.signin2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.BikeCollective;
import com.epicodus.signin2.utiil.ActiveBikeCollective;
import com.facebook.stetho.Stetho;
import com.parse.Parse;
import com.parse.ParseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.welcomeText) TextView mWelcomeText;
    @Bind(R.id.dataVizButton) Button mDataVizButton;
    @Bind(R.id.adminPanelButton) Button mAdminPanelButton;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Stetho.initializeWithDefaults(this);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setWelcomeText();

        if (!ActiveBikeCollective.isLoggedIn()) {
            goToPage(CoopLoginActivity.class);
        }

        mDataVizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DataActivity.class);
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

    private void setWelcomeText() {
        BikeCollective bikeCollective = ActiveBikeCollective.getActiveBikeCollective();
        if (bikeCollective != null) {
            mWelcomeText.setText("Welcome, " + bikeCollective.getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_logout) {
            ActiveBikeCollective.logout();

            ParseUser.getCurrentUser().logOut();

            goToPage(CoopLoginActivity.class);
        } else if (itemId == R.id.action_sign_in) {
            goToPage(ContactSignInActivity.class);
        } else if (itemId == R.id.action_admin) {
            goToPage(AdminActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToPage(Class newActivity) {
        Intent intent = new Intent(this, newActivity);
        startActivity(intent);
    }
}