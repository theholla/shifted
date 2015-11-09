package com.epicodus.signin2.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

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
    private long mSignInTme;
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
        mSignInTme = new Date().getTime();
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

    public long getSignInTme() {
        return mSignInTme;
    }

    public void setSignInTme(long signInTme) {
        mSignInTme = signInTme;
    }

    public long getSignOutTime() {
        return mSignOutTime;
    }

    public void setSignOutTime(long signOutTime) {
        mSignOutTime = signOutTime;
    }
}
