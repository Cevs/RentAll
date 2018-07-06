package com.cevs.rentall.models;

import com.cevs.rentall.dto.TruckDto;

public class Truck extends Vehicle {
    private float truckHeight;
    private boolean trailer;
    private float trailerLength;
    private float trailerWidth;
    private float trailerHeight;
    private int freightSpace;

    public Truck(TruckDto truckDto, int renterId) {
        super(truckDto.getManufacturer(), truckDto.getYear(), truckDto.getFuelTank(), truckDto.getMileage(),
                truckDto.getEngine(), truckDto.getFuelConsumption(), truckDto.getSpareTires(),
                truckDto.getWeight(), truckDto.getPayloadCapacity(), truckDto.getAdditionalEquipment(),
                truckDto.getRegistrationPlate(), "Truck", truckDto.getVehicleSubtype(),
                truckDto.isAvailable(), renterId);
        this.truckHeight = truckDto.getTruckHeight();
        this.trailer = truckDto.isTrailer();
        this.trailerLength = truckDto.getTrailerLength();
        this.trailerWidth = truckDto.getTrailerWidth();
        this.trailerHeight = truckDto.getTrailerHeight();
        this.freightSpace = truckDto.getFreightSpace();
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

    public float getTrailerLength() {
        return trailerLength;
    }

    public void setTrailerLength(float trailerLength) {
        this.trailerLength = trailerLength;
    }

    public float getTrailerWidth() {
        return trailerWidth;
    }

    public void setTrailerWidth(float trailerWidth) {
        this.trailerWidth = trailerWidth;
    }

    public float getTrailerHeight() {
        return trailerHeight;
    }

    public void setTrailerHeight(float trailerHeight) {
        this.trailerHeight = trailerHeight;
    }

    public int getFreightSpace() {
        return freightSpace;
    }

    public void setFreightSpace(int freightSpace) {
        this.freightSpace = freightSpace;
    }
}
