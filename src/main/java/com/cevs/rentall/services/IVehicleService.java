package com.cevs.rentall.services;

import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.models.Vehicle;

import java.util.List;

public interface IVehicleService {
    boolean addNewCar(CarDto carDto);
    List<Vehicle> getAllVehicles(int userId);
}
