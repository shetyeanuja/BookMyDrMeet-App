package com.example.bookmydoctormeet;

public class Doctor {

    private String d_name;
    private String d_phone;
    private String d_pswd;
    private String d_regno;

    public Doctor(String d_name, String d_phone, String d_pswd, String d_regno) {
        this.d_name = d_name;
        this.d_phone = d_phone;
        this.d_pswd = d_pswd;
        this.d_regno = d_regno;

    }

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_phone() {
        return d_phone;
    }

    public void setD_phone(String d_phone) {
        this.d_phone = d_phone;
    }

    public String getD_pswd() {
        return d_pswd;
    }

    public void setD_pswd(String d_pswd) {
        this.d_pswd = d_pswd;
    }

    public String getD_regno() {
        return d_regno;
    }

    public void setD_regno(String d_regno) {
        this.d_regno = d_regno;
    }


}