package com.cevs.rentall.services;

import com.cevs.rentall.dao.IVehicleReservationDao;
import com.cevs.rentall.models.User;
import com.cevs.rentall.models.VehicleReservation;
import com.cevs.rentall.models.VehicleReservationRenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class VehicleReservationImpl implements IVehicleReservations {
    @Autowired
    IVehicleReservationDao vehicleReservationDao;


    @Override
    public List<VehicleReservationRenter> getAllBuyerReservations(String search, String type, String status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = (User) auth.getPrincipal();
        String table = "";
        if (type.equals("Car")) {
            table = "cars";
        } else if (type.equals("Bus")) {
            table = "buses";
        } else if (type.equals("Truck")) {
            table = "trucks";
        } else {
            table = "vehicles";
        }
        return vehicleReservationDao.getAllBuyerReservations(buyer.getId(), search, table, status);
    }

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
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User renter = (User) auth.getPrincipal();
            vehicleReservationDao.deleteReservation(renter.getId(), reservationId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean updateReservationStatus(int reservationId, String status) {
        try {
            vehicleReservationDao.updateReservationStatus(reservationId, status);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean reserveVehicle(int vehicleId, String dateFrom, String dateTo) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User buyer = (User) auth.getPrincipal();
            Date startDate = convertToSqlDate(dateFrom);
            Date endDate = convertToSqlDate(dateTo);
            vehicleReservationDao.reserveVehicle(vehicleId, startDate, endDate, buyer.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Date convertToSqlDate(String sDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("mm/dd/yyyy");
            java.util.Date parsed = sdf.parse(sDate);
            Date sqlDate = new Date(parsed.getTime());
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
