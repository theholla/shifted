package com.epicodus.signin2.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name="contacts", id="_id")

public class Contact extends Model {
    @Column(name="name")
    private String mName;
    @Column(name="password")
    private String mPassword;
    @Column(name="email")
    private String mEmail;
    @Column(name="birthday")
    private String mBirthday;
    @Column BikeCollective bikeCollective;
    private BikeCollective mBikeCollective;

    public Contact() {
        super();
    }

    public Contact(String name, String password, String email, String birthday, BikeCollective bikeCollective) {
        mName = name;
        mPassword = password;
        mEmail = email;
        mBirthday = birthday;
        mBikeCollective = bikeCollective;
    }

    public static Contact find(String email) {
        return new Select()
                .from(Contact.class)
                .where("Email = ?", email)
                .executeSingle();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getBirthday() {
        return mBirthday;
    }

    public void setBirthday(String birthday) {
        mBirthday = birthday;
    }
}
