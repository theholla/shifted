package com.epicodus.signin2.ui;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.adapters.SignInEventAdapter;
import com.epicodus.signin2.models.SignInEvent;
import com.epicodus.signin2.utiil.ActiveBikeCollective;
import com.parse.ParseUser;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdminActivity extends ListActivity {

    // TODO: Figure out @BindArray

    private SignInEvent mSignInEvent;
    private ArrayList<SignInEvent> mSignInEvents;
    private SignInEventAdapter mAdapter;
    private ListView mListView;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        ButterKnife.bind(this);

        mSignInEvents = (ArrayList) SignInEvent.all();
        mListView = getListView();

        mAdapter = new SignInEventAdapter(this, mSignInEvents);
        setListAdapter(mAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(AdminActivity.this);
                dialog.setTitle("Alert")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                removeSignInEvent(position);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dialog.create();
                dialog.show();
                return false;
            }
        });
    }

    //TODO: investigate delete() and remove(), and attach to parse
    private void removeSignInEvent(int position) {
        SignInEvent signInEvent = mSignInEvents.get(position);
        SignInEvent eventToDelete = SignInEvent.find(signInEvent);
        eventToDelete.delete();
        mSignInEvents.remove(signInEvent);
        mAdapter.notifyDataSetChanged();
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
            ParseUser.getCurrentUser().logOut();

            Intent intent = new Intent(this, CoopLoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
