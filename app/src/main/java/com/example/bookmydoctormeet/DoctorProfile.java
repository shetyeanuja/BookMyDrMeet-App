package com.example.bookmydoctormeet;

public class DoctorProfile {

    private String d_name;
    private String d_phone;
    private String designation;
    private String specialization;
    private String yrsofexp;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getYrsofexp() {
        return yrsofexp;
    }

    public void setYrsofexp(String yrsofexp) {
        this.yrsofexp = yrsofexp;
    }

    public DoctorProfile(String d_name, String d_phone, String designation, String specialization, String yrsofexp) {
        this.d_name = d_name;
        this.d_phone = d_phone;
        this.designation = designation;
        this.specialization = specialization;
        this.yrsofexp = yrsofexp;
    }

    public DoctorProfile(){

    }


}
