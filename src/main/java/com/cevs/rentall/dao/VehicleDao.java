package com.cevs.rentall.dao;

import com.cevs.rentall.models.Bus;
import com.cevs.rentall.models.Car;
import com.cevs.rentall.models.Truck;
import com.cevs.rentall.models.Vehicle;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDao {
    List<Vehicle> getRenterVehicles(int renterId);
    void insertCar(Car car) throws SQLException;
    void insertTruck(Truck truck) throws SQLException;
    void insertBus(Bus bus) throws SQLException;
}
