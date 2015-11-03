package com.epicodus.signin2.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.adapters.SignInEventAdapter;
import com.epicodus.signin2.models.ContactSignInEvent;
import com.epicodus.signin2.utiil.ActiveBikeCollective;

import java.util.ArrayList;

public class AdminActivity extends ListActivity {
    private ContactSignInEvent mContactSignInEvent;
    private ArrayList<ContactSignInEvent> mContactSignInEvents;
    private TextView mSignInListLabel;
    private SignInEventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mSignInListLabel = (TextView) findViewById(R.id.signInListLabel);
        mContactSignInEvents = (ArrayList) ContactSignInEvent.all();

        mAdapter = new SignInEventAdapter(this, mContactSignInEvents);
        setListAdapter(mAdapter);

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
