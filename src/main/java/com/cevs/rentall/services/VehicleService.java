package com.cevs.rentall.services;

import com.cevs.rentall.dao.VehicleDao;
import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.dto.TruckDto;
import com.cevs.rentall.models.Car;
import com.cevs.rentall.models.Truck;
import com.cevs.rentall.models.User;
import com.cevs.rentall.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class VehicleService implements  IVehicleService{

    @Autowired
    VehicleDao vehicleDao;

    @Override
    public boolean addNewCar(CarDto carDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            Car car = new Car(carDto,renter.getId());
            vehicleDao.insertCar(car);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addNewTruck(TruckDto truckDto) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            Truck truck = new Truck(truckDto, renter.getId());
            vehicleDao.insertTruck(truck);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }

    @Override
    public List<Vehicle> getAllVehicles(int userId) {
        return vehicleDao.getRenterVehicles(userId);
    }
}
