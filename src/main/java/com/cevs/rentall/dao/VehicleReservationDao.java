package com.cevs.rentall.dao;

import com.cevs.rentall.database.Database;
import com.cevs.rentall.models.VehicleReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleReservationDao implements IVehicleReservationDao{

    @Autowired
    Database db;

    @Override
    public List<VehicleReservation> findAllReservations(int renterId, String search) {
        List<VehicleReservation> vehicleReservations = new ArrayList<>();
        String sql = "SELECT vb.id, vehicle_id, buyer_id, reservation_time, reserve_from, reserve_to, email, status, " +
                "firstname, lastname, phone_number, model, vehicle_type, vehicle_subtype, registration_plate " +
                "FROM vehicles_buyers vb " +
                "LEFT JOIN buyers b ON vb.buyer_id = b.id " +
                "LEFT JOIN vehicles v ON vb.vehicle_id = v.id " +
                "WHERE vehicle_id IN (SELECT id FROM vehicles WHERE renter_id = ?) " +
                "AND (email LIKE ? OR firstname LIKE ? OR lastname LIKE ? OR phone_number LIKE ? "+
                "OR model LIKE ? OR registration_plate LIKE ?)";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1,renterId);
            for(int i = 2; i<=7; i++){
                ps.setString(i,search+"%");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
               vehicleReservations.add(createVehicleReservationObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleReservations;
    }

    @Override
    public List<VehicleReservation> findAllReservationsWithStatus(int renterId, String status, String search) {
        List<VehicleReservation> vehicleReservations = new ArrayList<>();
        String sql = "SELECT vb.id, vehicle_id, buyer_id, reservation_time, reserve_from, reserve_to, email, status, " +
                "firstname, lastname, phone_number, model, vehicle_type, vehicle_subtype, registration_plate " +
                "FROM vehicles_buyers vb " +
                "LEFT JOIN buyers b ON vb.buyer_id = b.id " +
                "LEFT JOIN vehicles v ON vb.vehicle_id = v.id " +
                "WHERE vehicle_id IN (SELECT id FROM vehicles WHERE renter_id = ?) " +
                "AND status = ? "+
                "AND (email LIKE ? OR firstname LIKE ? OR lastname LIKE ? OR phone_number LIKE ? " +
                "OR model LIKE ? OR registration_plate LIKE ?)";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1,renterId);
            ps.setObject(2, status, Types.OTHER);
            for(int i = 3; i<=8; i++){
                ps.setString(i,search+"%");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vehicleReservations.add(createVehicleReservationObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleReservations;
    }

    @Override
    public List<VehicleReservation> findAllReservationsForVehicleType(int renterId, String vehicleType,
                                                                      String search) {
        List<VehicleReservation> vehicleReservations = new ArrayList<>();
        String sql = "SELECT vb.id, vehicle_id, buyer_id, reservation_time, reserve_from, reserve_to, email, status, " +
                "firstname, lastname, phone_number, model, vehicle_type, vehicle_subtype, registration_plate " +
                "FROM vehicles_buyers vb " +
                "LEFT JOIN buyers b ON vb.buyer_id = b.id " +
                "LEFT JOIN vehicles v ON vb.vehicle_id = v.id " +
                "WHERE vehicle_id IN (SELECT id FROM vehicles WHERE renter_id = ?) " +
                "AND vehicle_type = ? "+
                "AND (email LIKE ? OR firstname LIKE ? OR lastname LIKE ? OR phone_number LIKE ? " +
                "OR model LIKE ? OR registration_plate LIKE ?)";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1,renterId);
            ps.setObject(2, vehicleType, Types.OTHER);
            for(int i = 3; i<=8; i++){
                ps.setString(i,search+"%");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vehicleReservations.add(createVehicleReservationObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleReservations;
    }

    @Override
    public List<VehicleReservation> findReservations(String vehicle, String status, int renterId, String search) {
        List<VehicleReservation> vehicleReservations = new ArrayList<>();
        String sql = "SELECT vb.id, vehicle_id, buyer_id, reservation_time, reserve_from, reserve_to, email, status, " +
                "firstname, lastname, phone_number, model, vehicle_type, vehicle_subtype, registration_plate " +
                "FROM vehicles_buyers vb " +
                "LEFT JOIN buyers b ON vb.buyer_id = b.id " +
                "LEFT JOIN vehicles v ON vb.vehicle_id = v.id " +
                "WHERE vehicle_id IN (SELECT id FROM vehicles WHERE renter_id = ?) " +
                "AND status = ? AND vehicle_type = ? "+
                "AND (email LIKE ? OR firstname LIKE ? OR lastname LIKE ? OR phone_number LIKE ? " +
                "OR model LIKE ? OR registration_plate LIKE ?)";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setInt(1,renterId);
            ps.setObject(2, status, Types.OTHER);
            ps.setObject(3, vehicle, Types.OTHER);
            for(int i = 4; i<=9; i++){
                ps.setString(i,search+"%");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                vehicleReservations.add(createVehicleReservationObject(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicleReservations;
    }

    @Override
    public void deleteReservation(int renterId, int reservationId) throws SQLException {
        String sql = "DELETE from vehicles_buyers WHERE id = (" +
                "SELECT vb.id FROM vehicles_buyers vb " +
                "LEFT JOIN vehicles v ON vb.vehicle_id = v.id " +
                "LEFT JOIN renters r ON v.renter_id = r.id " +
                "WHERE v.renter_id = ? AND vb.id = ?)";
        try(Connection conn=db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,renterId);
            ps.setInt(2,reservationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Delete failed");
        }
    }

    @Override
    public void updateReservationStatus(int reservationId, String status) throws SQLException {
        String sql = "UPDATE vehicles_buyers SET status = ? " +
                "WHERE id = ?";
        try(Connection conn=db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setObject(1,status, Types.OTHER);
            ps.setInt(2,reservationId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Update failed");
        }
    }

    @Override
    public void reserveVehicle(int vehicleId, Date beginningDate, Date endDate, int buyerId) throws SQLException {
        String sql = "INSERT INTO vehicles_buyers (vehicle_id, buyer_id, reserve_from, reserve_to) " +
                "VALUES (?,?,?,?);";
        try(Connection conn= db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,vehicleId);
            ps.setInt(2,buyerId);
            ps.setDate(3,beginningDate);
            ps.setDate(4,endDate);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    public VehicleReservation createVehicleReservationObject(ResultSet rs) throws SQLException {
        VehicleReservation vh = new VehicleReservation();
        vh.setId(rs.getInt("id"));
        vh.setVehicleId(rs.getInt("vehicle_id"));
        vh.setBuyerId(rs.getInt("buyer_id"));
        vh.setReservationTime(rs.getTimestamp("reservation_time"));
        vh.setReserveFrom(rs.getDate("reserve_from"));
        vh.setReserveTo(rs.getDate("reserve_to"));
        vh.setBuyerEmail(rs.getString("email"));
        vh.setBuyerFirstname(rs.getString("firstname"));
        vh.setBuyerLastname(rs.getString("lastname"));
        vh.setBuyerPhoneNumber(rs.getString("phone_number"));
        vh.setVehicleModel(rs.getString("model"));
        vh.setVehicleType(rs.getString("vehicle_type"));
        vh.setVehicleSubtype(rs.getString("vehicle_subtype"));
        vh.setVehicleRegistrationPlate(rs.getString("registration_plate"));
        vh.setStatus(rs.getString("status"));
        return vh;
    }
}
