package com.epicodus.signin2.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    private SharedPreferences mPreferences;
    private BikeCollective mBikeCollective;
    private TextView mWelcomeText;
    private Button mAdminPanelButton, mPatronSignInButton;

//  TODO: add image! Made app crash.
//  private ImageView mMainImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getApplicationContext().getSharedPreferences("signinapp", Context.MODE_PRIVATE);
        mWelcomeText = (TextView) findViewById(R.id.welcomeText);
        mPatronSignInButton = (Button) findViewById(R.id.patronSignInButton);
        mAdminPanelButton = (Button) findViewById(R.id.adminPanelButton);
//        mMainImageView = (ImageView) findViewById(R.id.mainImageView);

        if (!isLoggedIn()) {
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

    private boolean isLoggedIn() {
        String name = mPreferences.getString("name", null);
        if (name == null) {
            return false;
        } else {
            setBikeCollective(name);
            return true;
        }
    }

    private void setBikeCollective(String name) {
        BikeCollective bikeCollective = BikeCollective.find(name);
        if (bikeCollective != null) {
            mBikeCollective = bikeCollective;
            mWelcomeText.setText(bikeCollective.getName() + "'s Sign In App");
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
            SharedPreferences.Editor editor = mPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, CoopLoginActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
