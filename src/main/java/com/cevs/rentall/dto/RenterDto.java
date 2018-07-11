package com.cevs.rentall.dto;

import com.cevs.rentall.dto.com.cevs.rentall.dto.validation.PasswordMatches;
import com.cevs.rentall.dto.com.cevs.rentall.dto.validation.ValidEmail;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class RenterDto {
    @ValidEmail
    private String email;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String password;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String rePassword;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String userType = "Renter";
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
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String companyName;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String companyPhoneNumber;
    @NotNull
    @NotEmpty(message = "Must not be empty")
    private String bankAccount;

    private MultipartFile file;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
