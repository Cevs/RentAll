package com.cevs.rentall.models;

public class BusRenter extends VehicleRenter {
    private int seats;
    private boolean twoStory;
    private int bunkerCapacity;

    public BusRenter() {
    }

    public BusRenter(int id, String manufacturer, String model, int year, int fuelTank, int mileage,
                     String engine, int fuelConsumption, int spareTires, int weight, int payloadCapacity,
                     String additionalEquipment, String registrationPlate, String vehicleType,
                     String vehicleSubtype, boolean available, int pricePerDay, String image, String email,
                     Location location, String companyImage, String companyName, String companyPhoneNumber,
                     String bankAccount, int companyId, int seats, boolean twoStory, int bunkerCapacity) {
        super(id, manufacturer, model, year, fuelTank, mileage, engine, fuelConsumption, spareTires, weight,
                payloadCapacity, additionalEquipment, registrationPlate, vehicleType, vehicleSubtype,
                available, pricePerDay, image, email, location, companyImage, companyName, companyPhoneNumber,
                bankAccount, companyId);
        this.seats = seats;
        this.twoStory = twoStory;
        this.bunkerCapacity = bunkerCapacity;
    }

    public BusRenter(VehicleRenter vehicleRenter) {
        super(vehicleRenter);
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isTwoStory() {
        return twoStory;
    }

    public void setTwoStory(boolean twoStory) {
        this.twoStory = twoStory;
    }

    public int getBunkerCapacity() {
        return bunkerCapacity;
    }

    public void setBunkerCapacity(int bunkerCapacity) {
        this.bunkerCapacity = bunkerCapacity;
    }
}
