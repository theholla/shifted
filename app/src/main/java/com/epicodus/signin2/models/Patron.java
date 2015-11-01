package com.epicodus.signin2.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

/**
 * Created by admin on 10/30/15.
 */

@Table(name="Patron", id="_id")
public class Patron extends Model {
    @Column(name="name")
    private String mName;
    @Column(name="password")
    private String mPassword;
    @Column(name="email")
    private String mEmail;
    @Column(name="birthday")
    private String mBirthday;

    public Patron() {
        super();
    }

    public Patron(String name, String password, String email, String birthday) {
        this.mName = name;
        this.mPassword = password;
        this.mEmail = email;
        this.mBirthday = birthday;
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

    public void setmPassword(String password) {
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

    public static Patron find(String email) {
        return new Select()
                .from(Patron.class)
                .where("Email = ?", email)
                .executeSingle();
    }
}
