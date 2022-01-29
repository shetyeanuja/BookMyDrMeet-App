package com.example.bookmydoctormeet;

public class User {

    private String u_name;
    private String u_phone;
    private String u_pswd;

    public User(String u_name, String u_phone, String u_pswd) {
        this.u_name = u_name;
        this.u_phone = u_phone;
        this.u_pswd = u_pswd;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_phone() {
        return u_phone;
    }

    public void setU_phone(String u_phone) {
        this.u_phone = u_phone;
    }

    public String getU_pswd() {
        return u_pswd;
    }

    public void setU_pswd(String u_pswd) {
        this.u_pswd = u_pswd;
    }
}
