package com.cevs.rentall.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class VehicleDto {
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String manufacturer;
    @NotNull
    private int year;
    @NotNull
    private int fuelTank;
    @NotNull
    private int mileage;
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String engine;
    @NotNull
    private int fuelConsumption;
    @NotNull
    private int spareTires;
    @NotNull
    private int weight;
    @NotNull
    private int payloadCapacity;
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String additionalEquipment;
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String registrationPlate;
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String vehicleSubtype;
    @NotNull
    private boolean available;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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

    public void setPayloadCapacity(int payloadCapacity) {
        this.payloadCapacity = payloadCapacity;
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
}
