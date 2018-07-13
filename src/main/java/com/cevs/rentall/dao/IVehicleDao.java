package com.cevs.rentall.dao;

import com.cevs.rentall.models.*;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface IVehicleDao {
    List<Vehicle> getRenterVehicles(int renterId, String search);
    List<Vehicle> getRenterVehiclesOfType(int renterId, String vehicleType, String search);
    List<VehicleRenter> getAllVehicleRenterOffersOfType(String search, String table);
    Car getCarById(int renterId, int carId);
    CarRenter getCarRenterById(int carId);
    Truck getTruckById(int renterId, int truckId);
    TruckRenter getTruckRenterById(int truckId);
    Bus getBusById(int renterId, int busId);
    BusRenter getBusRenterById(int busId);
    void insertCar(Car car) throws SQLException;
    void updateCar(Car car) throws SQLException;
    void insertTruck(Truck truck) throws SQLException;
    void updateTruck(Truck truck) throws SQLException;
    void insertBus(Bus bus) throws SQLException;
    void updateBus(Bus bus) throws SQLException;
    void deleteVehicle(int renterId, int vehicleId) throws SQLException;
}
