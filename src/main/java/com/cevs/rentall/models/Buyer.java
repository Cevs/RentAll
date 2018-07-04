package com.cevs.rentall.models;


import com.cevs.rentall.dto.BuyerDto;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Buyer extends User {
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Date birthDate;

    public Buyer() {
    }

    public Buyer(int id, String email, String password, String userType, Location location,
                 String firstname, String lastname, String phoneNumber, Date birthDate) {
        super(id, email, password, userType, location);
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public Buyer(BuyerDto buyerDto) throws ParseException {
        super(buyerDto.getEmail(), buyerDto.getPassword(), buyerDto.getUserType(),
                new Location(buyerDto.getCountry(), buyerDto.getCity(), buyerDto.getAddress(), buyerDto.getZipCode()));
        this.firstname = buyerDto.getFirstname();
        this.lastname = buyerDto.getLastname();
        this.phoneNumber = buyerDto.getPhoneNumber();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        java.util.Date date  = sdf.parse(buyerDto.getBirthDate());
        this.birthDate = new Date(date.getTime());
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}

