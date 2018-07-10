package com.cevs.rentall.dao;

import com.cevs.rentall.models.VehicleReservation;

import java.sql.SQLException;
import java.util.List;

public interface IVehicleReservationDao {
    List<VehicleReservation> findAllReservations(int renterId, String search);
    List<VehicleReservation> findAllReservationsWithStatus(int renterId, String status, String search);
    List<VehicleReservation> findAllReservationsForVehicleType(int renterId, String vehicleType, String search);
    List<VehicleReservation> findReservations(String vehicle, String status, int renterId, String search);
    void deleteReservation(int renterId, int reservationId) throws SQLException;
    void updateReservationStatus(int reservationId, String status) throws SQLException;
}
