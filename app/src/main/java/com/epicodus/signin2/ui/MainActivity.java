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

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.welcomeText) TextView mWelcomeText;
    @Bind(R.id.contactSignInRedirectButton) Button mContactSignInRedirectButton;
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

        // TODO: initialize Parse in SignInApp.java instead
//        Parse.enableLocalDatastore(MainActivity.this);
//        Parse.initialize(MainActivity.this, "dOCMN4er20zZtpSZfGHuy3mJ5tb3Dmz1eIIUl41i", "wOAFcA233sW0LH8ilIsWQJ0JsIgGasTQMjjBJpgB");

        setWelcomeText();

        if (!ActiveBikeCollective.isLoggedIn()) {
            goToPage(CoopLoginActivity.class);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.action_logout) {
            ActiveBikeCollective.logout();
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

    private void setWelcomeText() {
        BikeCollective bikeCollective = ActiveBikeCollective.getActiveBikeCollective();
        if (bikeCollective != null) {
            mWelcomeText.setText("Welcome, " + bikeCollective.getName());
        }
    }
}