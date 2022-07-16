package com.example.bookmydoctormeet;

public class DoctorClinicHospital {

    private String d_name;
    private String d_phone1;
    private String designation;
    private String specialization;
    private String yrsofexp;
    private String name_clinic_hospital;
    private String telephone;
    private String whatsapp;
    private String address_clinic_hospital;
    private String timings_clinic_hospital;
    private String website;

    public String getD_name() {
        return d_name;
    }

    public void setD_name(String d_name) {
        this.d_name = d_name;
    }

    public String getD_phone1() {
        return d_phone1;
    }

    public void setD_phone1(String d_phone1) {
        this.d_phone1 = d_phone1;
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

    public String getName_clinic_hospital() {
        return name_clinic_hospital;
    }

    public void setName_clinic_hospital(String name_clinic_hospital) {
        this.name_clinic_hospital = name_clinic_hospital;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getAddress_clinic_hospital() {
        return address_clinic_hospital;
    }

    public void setAddress_clinic_hospital(String address_clinic_hospital) {
        this.address_clinic_hospital = address_clinic_hospital;
    }

    public String getTimings_clinic_hospital() {
        return timings_clinic_hospital;
    }

    public void setTimings_clinic_hospital(String timings_clinic_hospital) {
        this.timings_clinic_hospital = timings_clinic_hospital;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public DoctorClinicHospital(String d_name, String d_phone1, String designation, String specialization, String yrsofexp, String name_clinic_hospital, String telephone, String whatsapp, String address_clinic_hospital, String timings_clinic_hospital, String website) {
        this.d_name = d_name;
        this.d_phone1 = d_phone1;
        this.designation = designation;
        this.specialization = specialization;
        this.yrsofexp = yrsofexp;
        this.name_clinic_hospital = name_clinic_hospital;
        this.telephone = telephone;
        this.whatsapp = whatsapp;
        this.address_clinic_hospital = address_clinic_hospital;
        this.timings_clinic_hospital = timings_clinic_hospital;
        this.website = website;
    }

    public DoctorClinicHospital() {

    }
}
