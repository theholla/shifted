package com.epicodus.signin2.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Table(name="contact_sign_in_events", id="_id")

public class SignInEvent extends Model {
    //TODO: make column for contact_id (from contact class) instead of name
    @Column(name="name")
    private String mName;
    @Column(name="contact_type")
    private String mContactType;
    @Column(name="sign_in_time")
    private long mSignInTime;
    @Column(name="sign_out_time")
    private long mSignOutTime;
    @Column(name="bike_collective")
    private BikeCollective mBikeCollective;

    public SignInEvent() {
        super();
    }

    public SignInEvent(String name, String contactType, BikeCollective bikeCollective) {
        mName = name;
        mContactType = contactType;
        mBikeCollective = bikeCollective;
        mSignInTime = new Date().getTime();
    }

    public BikeCollective getBikeCollective() {
        return mBikeCollective;
    }

    public void setBikeCollective(BikeCollective bikeCollective) {
        mBikeCollective = bikeCollective;
    }

    public static List<SignInEvent> all() {
        //TODO: retrieve only from active bikeCollective
        return new Select()
                .from(SignInEvent.class)
                .orderBy("_id DESC")
                .execute();
    }

    public static SignInEvent find(SignInEvent signInEvent) {
        return new Select()
                .from(SignInEvent.class)
                .where("Name = ?", signInEvent.getName())
                .executeSingle();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getContactType() {
        return mContactType;
    }

    public void setContactType(String contactType) {
        mContactType = contactType;
    }

    public long getSignInTime() {
        return mSignInTime;
    }

    public String getFormattedSignInTime() {
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        String timeToString = format.format(mSignInTime);
        return timeToString;
    }

    public String getFormattedSignInDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateToString = format.format(mSignInTime);
        return dateToString;
    }

    public void setSignInTime(long signInTime) {
        mSignInTime = signInTime;
    }

    public long getSignOutTime() {
        return mSignOutTime;
    }

    public String getFormattedSignOutTime() {
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        String timeToString = format.format(mSignOutTime);
        return timeToString;
    }

    public String getFormattedSignOutDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateToString = format.format(mSignOutTime);
        return dateToString;
    }

    public void setSignOutTime(long signOutTime) {
        mSignOutTime = signOutTime;
    }
}
