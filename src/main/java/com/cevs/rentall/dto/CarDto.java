package com.cevs.rentall.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CarDto extends VehicleDto {
    @NotNull
    private int doors;
    @NotNull
    @NotEmpty(message = "must not be empty")
    private String color;
    @NotNull
    private int trunkCapacity;

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
