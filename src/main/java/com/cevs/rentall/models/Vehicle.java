package com.cevs.rentall.models;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.Base64;

public class Vehicle {
    private int id;
    private String manufacturer;
    private String model;
    private int year;
    private int fuelTank;
    private int mileage;
    private String engine;
    private int fuelConsumption;
    private int spareTires;
    private int weight;
    private int payloadCapacity;
    private String additionalEquipment;
    private String registrationPlate;
    private String vehicleType;
    private String vehicleSubtype;
    private boolean available;
    private int pricePerDay;
    private String image;
    private int renterId;

    public Vehicle() {
    }

    public Vehicle(int id, String manufacturer, String  model, int year, int fuelTank,
                   int mileage, String engine, int fuelConsumption, int spareTires,
                   int weight, int payloadCapacity, String additionalEquipment,
                   String registrationPlate, String vehicleType, String vehicleSubtype,
                   boolean available, int pricePerDay, int renterId) {
        this.id=id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.fuelTank = fuelTank;
        this.mileage = mileage;
        this.engine = engine;
        this.fuelConsumption = fuelConsumption;
        this.spareTires = spareTires;
        this.weight = weight;
        this.payloadCapacity = payloadCapacity;
        this.additionalEquipment = additionalEquipment;
        this.registrationPlate = registrationPlate;
        this.vehicleType = vehicleType;
        this.vehicleSubtype = vehicleSubtype;
        this.available = available;
        this.pricePerDay = pricePerDay;
        this.renterId = renterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getFuelTank() {
        return fuelTank;
    }

    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(int fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public int getSpareTires() {
        return spareTires;
    }

    public void setSpareTires(int spareTires) {
        this.spareTires = spareTires;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPayloadCapacity() {
        return payloadCapacity;
    }

    public void setPayloadCapacity(int payloadCapacitiy) {
        this.payloadCapacity = payloadCapacitiy;
    }

    public String getAdditionalEquipment() {
        return additionalEquipment;
    }

    public void setAdditionalEquipment(String additionalEquipment) {
        this.additionalEquipment = additionalEquipment;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getRenterId() {
        return renterId;
    }

    public void setRenterId(int renterId) {
        this.renterId = renterId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
