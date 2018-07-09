package com.cevs.rentall.services;

import com.cevs.rentall.dto.BusDto;
import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.dto.TruckDto;
import com.cevs.rentall.models.Bus;
import com.cevs.rentall.models.Car;
import com.cevs.rentall.models.Truck;
import com.cevs.rentall.models.Vehicle;

import java.util.List;

public interface IVehicleService {
    boolean addNewCar(CarDto carDto);
    boolean updateCar(CarDto carDto);
    boolean addNewTruck(TruckDto truckDto);
    boolean updateTruck(TruckDto truckDto);
    boolean addNewBus(BusDto busDto);
    boolean updateBus(BusDto busDto);
    boolean deleteVehicle(int vehicleId);
    List<Vehicle> getAllVehicles();
    List<Vehicle> getAllVehiclesOfType(String vehicleType);
    Car findCarById(int carId);
    Truck findTruckById(int truckId);
    Bus findBusById(int busId);
}
