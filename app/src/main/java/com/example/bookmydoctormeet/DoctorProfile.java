package com.example.bookmydoctormeet;

public class DoctorProfile {

    private String d_name;
    private String d_phone;
    private String address;
    private String contact;
    private String timings;

    public DoctorProfile(String d_name, String d_phone, String address, String contact, String timings) {
        this.d_name = d_name;
        this.d_phone = d_phone;
        this.address = address;
        this.contact = contact;
        this.timings = timings;
    }

    public DoctorProfile(){

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }
}
