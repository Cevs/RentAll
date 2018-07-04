package com.cevs.rentall.dto;

import com.cevs.rentall.dto.com.cevs.rentall.dto.validation.PasswordMatches;
import com.cevs.rentall.dto.com.cevs.rentall.dto.validation.ValidEmail;
import org.jboss.logging.Message;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@PasswordMatches
public class BuyerDto {
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String firstname;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String lastname;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String phoneNumber;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String  birthDate;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String country;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String city;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String address;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String zipCode;
    @ValidEmail
    private String email;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String password;
    private String rePassword;

    private String  userType = "Buyer";

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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
