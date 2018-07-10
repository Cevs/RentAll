package com.cevs.rentall.models;

import java.sql.Date;
import java.sql.Timestamp;

public class VehicleReservation {
    private int id;
    private int vehicleId;
    private int buyerId;
    private String buyerEmail;
    private String buyerFirstname;
    private String buyerLastname;
    private String buyerPhoneNumber;
    private Timestamp reservationTime;
    private Date reserveFrom;
    private Date reserveTo;
    private String vehicleModel;
    private String vehicleType;
    private String vehicleSubtype;
    private String vehicleRegistrationPlate;
    private String status;


    public VehicleReservation(){}

    public VehicleReservation(int id, int vehicleId, int buyerId, String buyerEmail, String buyerFirstname,
                              String buyerLastname, String buyerPhoneNumber, Timestamp reservationTime,
                              Date reserveFrom, Date reserveTo, String vehicleModel, String vehicleType,
                              String  vehicleSubtype, String vehicleRegistrationPlate, String status) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.buyerId = buyerId;
        this.buyerEmail = buyerEmail;
        this.buyerFirstname = buyerFirstname;
        this.buyerLastname = buyerLastname;
        this.buyerPhoneNumber = buyerPhoneNumber;
        this.reservationTime = reservationTime;
        this.reserveFrom = reserveFrom;
        this.reserveTo = reserveTo;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.vehicleSubtype = vehicleSubtype;
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public String getBuyerFirstname() {
        return buyerFirstname;
    }

    public void setBuyerFirstname(String buyerFirstname) {
        this.buyerFirstname = buyerFirstname;
    }

    public String getBuyerLastname() {
        return buyerLastname;
    }

    public void setBuyerLastname(String buyerLastname) {
        this.buyerLastname = buyerLastname;
    }

    public String getBuyerPhoneNumber() {
        return buyerPhoneNumber;
    }

    public void setBuyerPhoneNumber(String buyerPhoneNumber) {
        this.buyerPhoneNumber = buyerPhoneNumber;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public Date getReserveFrom() {
        return reserveFrom;
    }

    public void setReserveFrom(Date reserveFrom) {
        this.reserveFrom = reserveFrom;
    }

    public Date getReserveTo() {
        return reserveTo;
    }

    public void setReserveTo(Date reserveTo) {
        this.reserveTo = reserveTo;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleSubtype() {
        return vehicleSubtype;
    }

    public void setVehicleSubtype(String vehicleSubtype) {
        this.vehicleSubtype = vehicleSubtype;
    }

    public String getVehicleRegistrationPlate() {
        return vehicleRegistrationPlate;
    }

    public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
        this.vehicleRegistrationPlate = vehicleRegistrationPlate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
