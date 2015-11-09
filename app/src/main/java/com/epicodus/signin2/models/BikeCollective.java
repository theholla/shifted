package com.epicodus.signin2.models;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

@Table(name="bike_collectives", id="_id")

public class BikeCollective extends Model {
    @Column(name="name")
    private String mName;
    @Column(name="email")
    private String mEmail;
    @Column (name="password")
    private String mPassword;
    @Column (name="agreement")
    private String mAgreement;

    public BikeCollective() {
        super();
    }

    public BikeCollective(String name, String email, String password, String agreement) {
        mName = name;
        mEmail = email;
        mPassword = password;
        mAgreement = agreement;
    }

    public static List<BikeCollective> all() {
        return new Select().from(BikeCollective.class).execute();
    }

    public List<Contact> contacts() {
        return getMany(Contact.class, "BikeCollective");
    }

    public List<SignInEvent> contactSignInEvents() {
        return getMany(SignInEvent.class, "BikeCollective");
    }

    public static BikeCollective find(String email) {
        return new Select()
                .from(BikeCollective.class)
                .where("Email = ?", email)
                .executeSingle();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getAgreement() {
        return mAgreement;
    }

    public void setAgreement(String agreement) {
        this.mAgreement = mAgreement;
    }
}
