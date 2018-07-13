package com.cevs.rentall.models;

public class TruckRenter extends VehicleRenter {
    private float truckHeight;
    private boolean trailer;
    private float trailerHeight;
    private float trailerWidth;
    private float trailerLength;
    private int freightSpace;

    public TruckRenter() {
    }

    public TruckRenter(int id, String manufacturer, String model, int year, int fuelTank, int mileage,
                       String engine, int fuelConsumption, int spareTires, int weight, int payloadCapacity,
                       String additionalEquipment, String registrationPlate, String vehicleType,
                       String vehicleSubtype, boolean available, int pricePerDay, String image,
                       String email, Location location, String companyImage, String companyName, String companyPhoneNumber,
                       String bankAccount, int companyId, float truckHeight, boolean trailer, float trailerHeight,
                       float trailerWidth, float trailerLength, int freightSpace) {
        super(id, manufacturer, model, year, fuelTank, mileage, engine, fuelConsumption, spareTires, weight,
                payloadCapacity, additionalEquipment, registrationPlate, vehicleType, vehicleSubtype,
                available, pricePerDay, image, email, location, companyImage, companyName, companyPhoneNumber,
                bankAccount, companyId);
        this.truckHeight = truckHeight;
        this.trailer = trailer;
        this.trailerHeight = trailerHeight;
        this.trailerWidth = trailerWidth;
        this.trailerLength = trailerLength;
        this.freightSpace = freightSpace;
    }

    public TruckRenter(VehicleRenter vehicleRenter) {
        super(vehicleRenter);
    }

    public float getTruckHeight() {
        return truckHeight;
    }

    public void setTruckHeight(float truckHeight) {
        this.truckHeight = truckHeight;
    }

    public boolean isTrailer() {
        return trailer;
    }

    public void setTrailer(boolean trailer) {
        this.trailer = trailer;
    }

    public float getTrailerHeight() {
        return trailerHeight;
    }

    public void setTrailerHeight(float trailerHeight) {
        this.trailerHeight = trailerHeight;
    }

    public float getTrailerWidth() {
        return trailerWidth;
    }

    public void setTrailerWidth(float trailerWidth) {
        this.trailerWidth = trailerWidth;
    }

    public float getTrailerLength() {
        return trailerLength;
    }

    public void setTrailerLength(float trailerLength) {
        this.trailerLength = trailerLength;
    }

    public int getFreightSpace() {
        return freightSpace;
    }

    public void setFreightSpace(int freightSpace) {
        this.freightSpace = freightSpace;
    }
}
