package com.cevs.rentall.dao;

import com.cevs.rentall.database.Database;
import com.cevs.rentall.models.VehicleReservation;
import com.cevs.rentall.models.VehicleReservationRenter;
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

    @Override
    public List<VehicleReservationRenter> getAllBuyerReservations(int buyerId, String search, String table, String status) {
        List<VehicleReservationRenter> vrrList = new ArrayList<>();
        if(status.equals("All")){
            String sql = "SELECT t.id, t.manufacturer, t.model, t.year, t.fuel_tank, t.mileage, t.engine, t.fuel_consumption, " +
                    "t.spare_tires, t.weight, t.payload_capacity, t.additional_equipment, t.registration_plate, t.vehicle_type, " +
                    "t.vehicle_subtype, t.available, t.price_per_day, t.image, t.renter_id, vb.id as id_reservation, " +
                    "vb.reserve_from, vb.reserve_to, vb.status, r.email, r.company_name, " +
                    "r.company_phone_number, r.bank_account " +
                    "FROM "+table+" t " +
                    "LEFT JOIN renters r ON t.renter_id = r.id " +
                    "LEFT JOIN vehicles_buyers vb ON vb.vehicle_id = t.id " +
                    "WHERE vb.buyer_id = ? " +
                    "AND (manufacturer LIKE ? OR model LIKE ? OR registration_plate LIKE ? " +
                    "OR company_name  LIKE ? OR company_phone_number LIKE ? OR company_phone_number LIKE ? " +
                    "OR bank_account LIKE ?)";
            try(Connection conn = db.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, buyerId);
                for(int i = 2; i<= 8; i++){
                    ps.setString(i, search+"%");
                }
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    VehicleReservationRenter vrr = populateVehicleReservationRenter(rs);
                    vrrList.add(vrr);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            String sql = "SELECT t.id, t.manufacturer, t.model, t.year, t.fuel_tank, t.mileage, t.engine, t.fuel_consumption, " +
                    "t.spare_tires, t.weight, t.payload_capacity, t.additional_equipment, t.registration_plate, t.vehicle_type, " +
                    "t.vehicle_subtype, t.available, t.price_per_day, t.image, t.renter_id, vb.id as id_reservation, " +
                    "vb.reserve_from, vb.reserve_to, vb.status, r.email, r.company_name, " +
                    "r.company_phone_number, r.bank_account " +
                    "FROM "+table+" t " +
                    "LEFT JOIN renters r ON t.renter_id = r.id " +
                    "LEFT JOIN vehicles_buyers vb ON vb.vehicle_id = t.id " +
                    "WHERE vb.buyer_id = ? " +
                    "AND status = ? " +
                    "AND (manufacturer LIKE ? OR model LIKE ? OR registration_plate LIKE ? " +
                    "OR company_name  LIKE ? OR company_phone_number LIKE ? OR company_phone_number LIKE ? " +
                    "OR bank_account LIKE ?)";
            try(Connection conn = db.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, buyerId);
                ps.setObject(2, status, Types.OTHER);
                for(int i = 3; i<= 9; i++){
                    ps.setString(i, search+"%");
                }
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    VehicleReservationRenter vrr = populateVehicleReservationRenter(rs);
                    vrrList.add(vrr);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  vrrList;
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

    private VehicleReservationRenter populateVehicleReservationRenter(ResultSet rs) throws SQLException {
        VehicleReservationRenter vrr = new VehicleReservationRenter();
        vrr.setId(rs.getInt("id"));
        vrr.setManufacturer(rs.getString("manufacturer"));
        vrr.setModel(rs.getString("model"));
        vrr.setYear(rs.getInt("year"));
        vrr.setFuelTank(rs.getInt("fuel_tank"));
        vrr.setMileage(rs.getInt("mileage"));
        vrr.setEngine(rs.getString("engine"));
        vrr.setFuelConsumption(rs.getInt("fuel_consumption"));
        vrr.setSpareTires(rs.getInt("spare_tires"));
        vrr.setWeight(rs.getInt("weight"));
        vrr.setPayloadCapacity(rs.getInt("payload_capacity"));
        vrr.setAdditionalEquipment(rs.getString("additional_equipment"));
        vrr.setRegistrationPlate(rs.getString("registration_plate"));
        vrr.setVehicleType(rs.getString("vehicle_type"));
        vrr.setVehicleSubtype(rs.getString("vehicle_subtype"));
        vrr.setAvailable(rs.getBoolean("available"));
        vrr.setPricePerDay(rs.getInt("price_per_day"));
        vrr.setImage(rs.getString("image"));

        vrr.setReservationId(rs.getInt("id_reservation"));
        vrr.setReserveFrom(rs.getDate("reserve_from"));
        vrr.setReserveTo(rs.getDate("reserve_to"));
        vrr.setStatus(rs.getString("status"));

        vrr.setCompanyId(rs.getInt("renter_id"));
        vrr.setCompanyName(rs.getString("company_name"));
        vrr.setCompanyPhoneNumber(rs.getString("company_phone_number"));
        vrr.setBankAccount(rs.getString("bank_account"));

        return vrr;
    }
}
