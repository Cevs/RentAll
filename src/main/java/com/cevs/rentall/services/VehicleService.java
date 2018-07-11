package com.cevs.rentall.services;

import com.cevs.rentall.dao.IVehicleDao;
import com.cevs.rentall.dto.BusDto;
import com.cevs.rentall.dto.CarDto;
import com.cevs.rentall.dto.TruckDto;
import com.cevs.rentall.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

@Service
public class VehicleService implements  IVehicleService{

    @Autowired
    IVehicleDao IVehicleDao;

    @Override
    public boolean addNewCar(CarDto carDto) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            Car car = new Car(carDto,renter.getId());
            car.setImage(convertToBase64(carDto.getImage()));
            IVehicleDao.insertCar(car);
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
            truck.setImage(convertToBase64(truckDto.getImage()));
            IVehicleDao.insertTruck(truck);
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
            IVehicleDao.updateTruck(truck);
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
            bus.setImage(convertToBase64(busDto.getImage()));
            IVehicleDao.insertBus(bus);
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
            IVehicleDao.updateBus(bus);
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
            IVehicleDao.deleteVehicle(renter.getId(), vehicleId);
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
            car.setImage(convertToBase64(carDto.getImage()));
            IVehicleDao.updateCar(car);
            return true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Vehicle> getAllVehicles(String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return IVehicleDao.getRenterVehicles(renter.getId(),search);
    }

    @Override
    public List<Vehicle> getAllVehiclesOfType(String vehicleType, String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return IVehicleDao.getRenterVehiclesOfType(renter.getId(), vehicleType, search);
    }

    @Override
    public Car findCarById(int carId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return  IVehicleDao.getCarById(renter.getId(), carId);
    }

    @Override
    public Truck findTruckById(int truckId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return IVehicleDao.getTruckById(renter.getId(), truckId);
    }

    @Override
    public Bus findBusById(int busId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return  IVehicleDao.getBusById(renter.getId(), busId);
    }


    public String convertToBase64(MultipartFile image){
        try {
            byte[]  encoded = Base64.getEncoder().encode(image.getBytes());
            return (new String(encoded));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "";

    }
}
