package com.epicodus.signin2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.epicodus.signin2.R;
import com.epicodus.signin2.models.SignInEvent;

import java.util.ArrayList;

public class SignInEventAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<SignInEvent> mSignInEvents;

    public SignInEventAdapter(Context context, ArrayList<SignInEvent> signInEvents) {
        mContext = context;
        mSignInEvents = signInEvents;
    }

    @Override
    public int getCount() {
        return mSignInEvents.size();
    }

    @Override
    public Object getItem(int position) {
        return mSignInEvents.get(position);
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

        SignInEvent signInEvent = mSignInEvents.get(position);

        holder.eventName.setText(signInEvent.getName());
        holder.eventType.setText(signInEvent.getContactType());
        holder.eventSignInTime.setText("test");
        holder.eventSignOutTime.setText("");

        return convertView;
    }

    private static class ViewHolder {
        TextView eventName, eventType, eventSignedInAtLabel, eventSignInTime, eventSignedOutAtLabel, eventSignOutTime;
    }
}
