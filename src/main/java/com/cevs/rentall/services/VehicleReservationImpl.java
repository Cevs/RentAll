package com.cevs.rentall.services;

import com.cevs.rentall.dao.IVehicleReservationDao;
import com.cevs.rentall.models.User;
import com.cevs.rentall.models.VehicleReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class VehicleReservationImpl implements IVehicleReservations{
    @Autowired
    IVehicleReservationDao vehicleReservationDao;


    @Override
    public List<VehicleReservation> getAllReservations(String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return vehicleReservationDao.findAllReservations(renter.getId(), search);
    }

    @Override
    public List<VehicleReservation> getAllReservationsWithStatus(String status, String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return vehicleReservationDao.findAllReservationsWithStatus(renter.getId(), status, search);
    }

    @Override
    public List<VehicleReservation> getAllReservationsForVehicleType(String vehicleType, String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return vehicleReservationDao.findAllReservationsForVehicleType(renter.getId(), vehicleType, search);
    }

    @Override
    public List<VehicleReservation> getReservations(String vehicle, String status, String search) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User renter = (User) auth.getPrincipal();
        return vehicleReservationDao.findReservations(vehicle, status, renter.getId(), search);
    }

    @Override
    public boolean deleteReservation(int reservationId) {
        try{
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            vehicleReservationDao.deleteReservation(renter.getId(), reservationId);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean updateReservationStatus(int reservationId, String status) {
        try{
            vehicleReservationDao.updateReservationStatus(reservationId, status);
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
