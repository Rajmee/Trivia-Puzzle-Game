package com.example.rajme.triviaapp;

/**
 * Created by RAJME on 4/21/2020.
 */

public class Countryinfo {

    private String country;
    private String city;

    public  Countryinfo(String country, String city){
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
