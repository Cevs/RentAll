package com.cevs.rentall.models;

import java.io.Serializable;

public class Location implements Serializable{
    private String country;
    private String city;
    private String address;
    private String zipCode;

    public Location() {
    }

    public Location(String country, String city, String address, String zipCode) {
        this.country = country;
        this.city = city;
        this.address = address;
        this.zipCode = zipCode;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
