package com.cevs.rentall.models;

public class CarRenter extends VehicleRenter {
    private int doors;
    private String color;
    private int trunkCapacity;

    public CarRenter() {
    }

    public CarRenter(int id, String manufacturer, String model, int year, int fuelTank, int mileage,
                     String engine, int fuelConsumption, int spareTires, int weight, int payloadCapacity,
                     String additionalEquipment, String registrationPlate, String vehicleType,
                     String vehicleSubtype, boolean available, int pricePerDay, String image, String email,
                     Location location,String companyImage, String companyName, String companyPhoneNumber, String bankAccount,
                     int companyId, int doors, String color, int trunkCapacity) {
        super(id, manufacturer, model, year, fuelTank, mileage, engine, fuelConsumption, spareTires, weight,
                payloadCapacity, additionalEquipment, registrationPlate, vehicleType, vehicleSubtype, available,
                pricePerDay, image, email, location, companyImage, companyName, companyPhoneNumber, bankAccount, companyId);
        this.doors = doors;
        this.color = color;
        this.trunkCapacity = trunkCapacity;
    }

    public CarRenter(VehicleRenter vehicleRenter) {
        super(vehicleRenter);
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTrunkCapacity() {
        return trunkCapacity;
    }

    public void setTrunkCapacity(int trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
    }
}
