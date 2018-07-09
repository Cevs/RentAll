package com.cevs.rentall.services;

import com.cevs.rentall.dao.VehicleDao;
import com.cevs.rentall.dto.BusDto;
import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.dto.TruckDto;
import com.cevs.rentall.models.*;
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
    public boolean updateTruck(TruckDto truckDto) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            Truck truck = new Truck(truckDto, renter.getId());
            vehicleDao.updateTruck(truck);
            return true;
        }catch (SQLException e){
            e.printStackTrace();;
        }
        return false;
    }

    @Override
    public boolean addNewBus(BusDto busDto) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            Bus bus = new Bus(busDto, renter.getId());
            vehicleDao.insertBus(bus);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateBus(BusDto busDto) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            Bus bus = new Bus(busDto, renter.getId());
            vehicleDao.updateBus(bus);
            return  true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteVehicle(int vehicleId) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            vehicleDao.deleteVehicle(renter.getId(), vehicleId);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCar(CarDto carDto) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            Car car = new Car(carDto, renter.getId());
            vehicleDao.updateCar(car);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return vehicleDao.getRenterVehicles(renter.getId());
    }

    @Override
    public List<Vehicle> getAllVehiclesOfType(String vehicleType) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return vehicleDao.getRenterVehiclesOfType(renter.getId(), vehicleType);
    }

    @Override
    public Car findCarById(int carId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return  vehicleDao.getCarById(renter.getId(), carId);
    }

    @Override
    public Truck findTruckById(int truckId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return vehicleDao.getTruckById(renter.getId(), truckId);
    }

    @Override
    public Bus findBusById(int busId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return  vehicleDao.getBusById(renter.getId(), busId);
    }
}
