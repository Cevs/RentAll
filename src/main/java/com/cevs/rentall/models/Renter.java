package com.cevs.rentall.models;

import com.cevs.rentall.dto.RenterDto;

public class Renter extends User {
    private String companyName;
    private String companyPhoneNumber;
    private String bankAccount;

    public Renter(int id, String email, String password, String userType, Location location,
                  String companyName, String companyPhoneNumber, String bankAccount) {
        super(id, email, password, userType, location);
        this.companyName = companyName;
        this.companyPhoneNumber = companyPhoneNumber;
        this.bankAccount = bankAccount;
    }

    public Renter(RenterDto renterDto) {
        super(renterDto.getEmail(), renterDto.getPassword(), renterDto.getUserType(),
                new Location(renterDto.getCountry(), renterDto.getCity(), renterDto.getAddress(), renterDto.getZipCode()));
        this.companyName = renterDto.getCompanyName();
        this.companyPhoneNumber = renterDto.getCompanyPhoneNumber();
        this.bankAccount = renterDto.getBankAccount();
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
}
