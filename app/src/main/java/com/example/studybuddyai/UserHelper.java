package com.example.studybuddyai;

public class UserHelper {

    //Variables for the Sign Up Page
    String signup_username, signup_email, signup_cellNo, signup_password;

    public UserHelper() {
    }

    public UserHelper(String signup_username, String signup_email, String signup_cellno, String signup_password) {
        this.signup_username = signup_username;
        this.signup_email = signup_email;
        this.signup_cellNo = signup_cellno;
        this.signup_password = signup_password;

    }

    public String getSignup_username() {
        return signup_username;
    }

    public void setSignup_username(String signup_username) {
        this.signup_username = signup_username;
    }

    public String getSignup_email() {
        return signup_email;
    }

    public void setSignup_email(String signup_email) {
        this.signup_email = signup_email;
    }

    public String getSignup_cellNo() {
        return signup_cellNo;
    }

    public void setSignup_cellNo(String signup_email) {
        this.signup_cellNo = signup_cellNo;
    }

    public String getSignup_password() {
        return signup_password;
    }

    public void setSignup_password(String signup_password) {
        this.signup_password = signup_password;
    }
}
