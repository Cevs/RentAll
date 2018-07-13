package com.cevs.rentall.services;

import com.cevs.rentall.dto.BusDto;
import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.dto.TruckDto;
import com.cevs.rentall.models.*;

import java.util.List;

public interface IVehicleService {
    boolean addNewCar(CarDto carDto);
    boolean updateCar(CarDto carDto);
    boolean addNewTruck(TruckDto truckDto);
    boolean updateTruck(TruckDto truckDto);
    boolean addNewBus(BusDto busDto);
    boolean updateBus(BusDto busDto);
    boolean deleteVehicle(int vehicleId);
    List<Vehicle> getAllVehicles(String search);
    List<Vehicle> getAllVehiclesOfType(String vehicleType, String search);
    Car findCarById(int carId);
    Truck findTruckById(int truckId);
    Bus findBusById(int busId);
    CarRenter findCarRenterById(int carId);
    TruckRenter findTruckRenterById(int truckId);
    BusRenter findBusRenterById(int busId);
    List<VehicleRenter> getAllVehicleRenterOffersOfType(String search, String type);
}
