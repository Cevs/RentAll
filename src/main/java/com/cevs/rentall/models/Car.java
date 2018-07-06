package com.cevs.rentall.models;

import com.cevs.rentall.dto.CarDto;

public class Car extends Vehicle {
    private int doors;
    private String color;
    private int trunkCapacity;

    public Car(CarDto carDto, int renterId){
        super(carDto.getManufacturer(), carDto.getYear(), carDto.getFuelTank(), carDto.getMileage(), carDto.getEngine(),
                carDto.getFuelConsumption(), carDto.getSpareTires(), carDto.getWeight(), carDto.getPayloadCapacity(),
                carDto.getAdditionalEquipment(), carDto.getRegistrationPlate(), "Car", carDto.getVehicleSubtype(),
                carDto.isAvailable(), renterId);
        this.doors = carDto.getDoors();
        this.color = carDto.getColor();
        this.trunkCapacity = carDto.getTrunkCapacity();
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
