package com.cevs.rentall.dto;

import javax.validation.constraints.NotNull;

public class BusDto extends VehicleDto {
    @NotNull
    private  int seats;
    private boolean twoStory;
    @NotNull
    private  int bunkerCapacity;

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
