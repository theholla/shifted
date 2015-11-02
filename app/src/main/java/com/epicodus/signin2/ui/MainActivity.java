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

public class MainActivity extends AppCompatActivity {
    private TextView mWelcomeText;
    private Button mAdminPanelButton, mPatronSignInButton;

//  TODO: add image! Made app crash.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeText = (TextView) findViewById(R.id.welcomeText);
        mPatronSignInButton = (Button) findViewById(R.id.patronSignInButton);
        mAdminPanelButton = (Button) findViewById(R.id.adminPanelButton);

        setWelcomeText();

        if (!ActiveBikeCollective.isLoggedIn()) {
            Intent intent = new Intent(this, CoopLoginActivity.class);
            startActivity(intent);
        }

        mPatronSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PatronSignInActivity.class);
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
}

/**
 * TODO: only store bikecollectives in one place, preferences or sql
 * implement scroll bar in safe space agreement
 * allow maneuvering with keyboard
 * top bar nav
 * make BikeCollective table in SQL plural
 **/