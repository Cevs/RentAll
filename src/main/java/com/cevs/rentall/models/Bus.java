package com.cevs.rentall.models;

import com.cevs.rentall.dto.BusDto;

public class Bus extends Vehicle {
    private int seats;
    private boolean twoStroy;
    private int bunkerCapacity;

    public Bus(BusDto busDto, int renterId) {
        super(busDto.getManufacturer(),busDto.getYear(), busDto.getFuelTank(), busDto.getMileage(),
                busDto.getEngine(), busDto.getFuelConsumption(), busDto.getSpareTires(), busDto.getWeight(),
                busDto.getPayloadCapacity(), busDto.getAdditionalEquipment(), busDto.getRegistrationPlate(),
                "Bus",busDto.getVehicleSubtype(),busDto.isAvailable(), renterId);
        this.seats = busDto.getSeats();
        this.twoStroy = busDto.isTwoStory();
        this.bunkerCapacity = busDto.getBunkerCapacity();
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public boolean isTwoStroy() {
        return twoStroy;
    }

    public void setTwoStroy(boolean twoStroy) {
        this.twoStroy = twoStroy;
    }

    public int getBunkerCapacity() {
        return bunkerCapacity;
    }

    public void setBunkerCapacity(int bunkerCapacity) {
        this.bunkerCapacity = bunkerCapacity;
    }
}
