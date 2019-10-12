package com.timeisall.mstudyplanner.registration.database;

public class UserDao {

    private String mUserID;
    private String mUserPassword;
    private String mUserGender;
    private String mUserGroup;
    private String mUserEmail;

    public UserDao(String userID, String userPassword, String userGender, String userGroup, String userEmail) {
        mUserID = userID;
        mUserPassword = userPassword;
        mUserGender = userGender;
        mUserGroup = userGroup;
        mUserEmail = userEmail;
    }

    public String getUserID() {
        return mUserID;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }

    public String getUserPassword() {
        return mUserPassword;
    }

    public void setUserPassword(String userPassword) {
        mUserPassword = userPassword;
    }

    public String getUserGender() {
        return mUserGender;
    }

    public void setUserGender(String userGender) {
        mUserGender = userGender;
    }

    public String getUserGroup() {
        return mUserGroup;
    }

    public void setUserGroup(String userGroup) {
        mUserGroup = userGroup;
    }

    public String getUserEmail() {
        return mUserEmail;
    }

    public void setUserEmail(String userEmail) {
        mUserEmail = userEmail;
    }
}
