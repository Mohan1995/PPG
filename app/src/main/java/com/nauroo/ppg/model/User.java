package com.nauroo.ppg.model;

/**
 * Created by Mohan M on 1/4/2018.
 */

public class User {
    String name,last_name,country_id,email,interests,company;
    boolean executive_contact;
    String city;
    boolean get_info;
    String segment;

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }



    public boolean isGet_info() {
        return get_info;
    }

    public void setGet_info(boolean get_info) {
        this.get_info = get_info;
    }

    public boolean isExecutive_contact() {
        return executive_contact;
    }

    public void setExecutive_contact(boolean executive_contact) {
        this.executive_contact = executive_contact;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }


    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }



    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
