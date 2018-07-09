package com.cevs.rentall.models;

import com.cevs.rentall.dto.BusDto;

public class Bus extends Vehicle {
    private int seats;
    private boolean twoStory;
    private int bunkerCapacity;

    public Bus(){}

    public Bus(BusDto busDto, int renterId) {
        super(busDto.getId(), busDto.getManufacturer(),busDto.getYear(), busDto.getFuelTank(), busDto.getMileage(),
                busDto.getEngine(), busDto.getFuelConsumption(), busDto.getSpareTires(), busDto.getWeight(),
                busDto.getPayloadCapacity(), busDto.getAdditionalEquipment(), busDto.getRegistrationPlate(),
                "Bus",busDto.getVehicleSubtype(),busDto.isAvailable(), busDto.getPricePerDay(), renterId);
        this.seats = busDto.getSeats();
        this.twoStory = busDto.isTwoStory();
        this.bunkerCapacity = busDto.getBunkerCapacity();
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
