package com.cevs.rentall.services;

import com.cevs.rentall.models.VehicleReservation;
import com.cevs.rentall.models.VehicleReservationRenter;

import java.util.List;

public interface IVehicleReservations {
    List<VehicleReservationRenter> getAllBuyerReservations(String search, String type, String status);
    List<VehicleReservation> getAllReservations(String search);
    List<VehicleReservation> getAllReservationsWithStatus(String status, String search);
    List<VehicleReservation> getAllReservationsForVehicleType(String vehicleType, String search);
    List<VehicleReservation> getReservations(String vehicle, String status, String search);
    boolean deleteReservation(int reservationId);
    boolean updateReservationStatus(int reservationId, String status);
    boolean reserveVehicle(int vehicleId, String beginningDate, String endDate);
}
