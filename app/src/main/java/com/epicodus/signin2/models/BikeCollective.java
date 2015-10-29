package com.epicodus.signin2.models;

/**
 * Created by admin on 10/29/15.
 */
@Table(name="BikeCollective", id="_id")

public class BikeCollective extends Model {
    @Column (name="name")
    private String mName;
    @Column(name="email")
    private String mEmail;
    @Column (name="password")
    private String mPassword;
    @Column (name="agreement")
    private String mAgreement;

    //TODO: add address

    public BikeCollective() {
        super();
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public BikeCollective(String name, String email, String password, String agreement) {
        mName = name;
        mEmail = email;
        mPassword = password;
        mAgreement = agreement;
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


    public static BikeCollective find(String email) {
        return new Select()
                .from(BikeCollective.class)
                .where("Email = ?", email)
                .executeSingle();
    }
}
