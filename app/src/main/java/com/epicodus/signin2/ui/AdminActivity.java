package com.epicodus.signin2.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.signin2.R;
import com.epicodus.signin2.adapters.SignInEventAdapter;
import com.epicodus.signin2.models.ContactSignInEvent;
import com.epicodus.signin2.utiil.ActiveBikeCollective;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdminActivity extends ListActivity {
    @Bind(R.id.signInListLabel) TextView mSignInListLabel;

    // TODO: Figure out @BindArray
    private ContactSignInEvent mContactSignInEvent;
    private ArrayList<ContactSignInEvent> mContactSignInEvents;
    private SignInEventAdapter mAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);

        mContactSignInEvents = (ArrayList) ContactSignInEvent.all();
        mListView = getListView();

        mAdapter = new SignInEventAdapter(this, mContactSignInEvents);
        setListAdapter(mAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ContactSignInEvent contactSignInEvent = mContactSignInEvents.get(position);
                ContactSignInEvent eventToDelete = ContactSignInEvent.find(contactSignInEvent);
                eventToDelete.delete();
                mContactSignInEvents.remove(contactSignInEvent);
                mAdapter.notifyDataSetChanged();
                Toast toast = Toast.makeText(AdminActivity.this, "You just deleted that sign-in event!", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
                return false;
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
}
