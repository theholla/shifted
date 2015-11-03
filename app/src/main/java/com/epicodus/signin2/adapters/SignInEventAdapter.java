package com.epicodus.signin2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.ContactSignInEvent;

import java.util.ArrayList;

public class SignInEventAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<ContactSignInEvent> mContactSignInEvents;

    public SignInEventAdapter(Context context, ArrayList<ContactSignInEvent> contactSignInEvents) {
        mContext = context;
        mContactSignInEvents = contactSignInEvents;
    }

    @Override
    public int getCount() {
        return mContactSignInEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return mContactSignInEvents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.sign_in_event, null);
            holder = new ViewHolder();
            holder.eventName = (TextView) convertView.findViewById(R.id.eventName);
            holder.eventType = (TextView) convertView.findViewById(R.id.eventType);
            holder.eventSignedInAtLabel = (TextView) convertView.findViewById(R.id.eventSignedInAtLabel);
            holder.eventSignInTime = (TextView) convertView.findViewById(R.id.eventSignInTime);
            holder.eventSignedOutAtLabel = (TextView) convertView.findViewById(R.id.eventSignedOutAtLabel);
            holder.eventSignOutTime = (TextView) convertView.findViewById(R.id.eventSignOutTime);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ContactSignInEvent contactSignInEvent = mContactSignInEvents.get(position);

        holder.eventName.setText(contactSignInEvent.getName());
        holder.eventType.setText("test");
        holder.eventSignInTime.setText("test");
        holder.eventSignOutTime.setText("test");

        return convertView;
    }

    private static class ViewHolder {
        TextView eventName, eventType, eventSignedInAtLabel, eventSignInTime, eventSignedOutAtLabel, eventSignOutTime;
    }
}
