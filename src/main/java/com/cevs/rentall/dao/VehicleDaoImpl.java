package com.cevs.rentall.dao;

import com.cevs.rentall.database.Database;
import com.cevs.rentall.models.Bus;
import com.cevs.rentall.models.Car;
import com.cevs.rentall.models.Truck;
import com.cevs.rentall.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleDaoImpl implements VehicleDao {

    @Autowired
    Database db;

    @Override
    public List<Vehicle> getRenterVehicles(int renterId) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        String sql = "SELECT *FROM vehicles WHERE renter_id = ?";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, renterId);
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                Vehicle v = new Vehicle();
                v.setManufacturer(rs.getString("manufacturer"));
                v.setYear(rs.getInt("year"));
                v.setRegistrationPlate(rs.getString("registration_plate"));
                v.setVehicleType(rs.getString("vehicle_type"));
                v.setVehicleSubtype(rs.getString("vehicle_subtype"));
                v.setAvailable(rs.getBoolean("available"));

                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void insertCar(Car car) throws SQLException {
        String sql = "INSERT INTO cars (manufacturer, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, renter_id, doors, color, trunk_capacity) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(Connection conn=db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,car.getManufacturer());
            ps.setInt(2,car.getYear());
            ps.setInt(3,car.getFuelTank());
            ps.setInt(4,car.getMileage());
            ps.setString(5,car.getEngine());
            ps.setInt(6,car.getFuelConsumption());
            ps.setInt(7,car.getSpareTires());
            ps.setInt(8, car.getWeight());
            ps.setInt(9,car.getPayloadCapacitiy());
            ps.setString(10,car.getAdditionalEquipment());
            ps.setString(11,car.getRegistrationPlate());
            ps.setObject(12,car.getVehicleType(), Types.OTHER);
            ps.setObject(13,car.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,car.isAvailable());
            ps.setInt(15,car.getRenterId());
            ps.setInt(16,car.getDoors());
            ps.setString(17,car.getColor());
            ps.setInt(18,car.getTrunkCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void insertTruck(Truck truck) throws SQLException {
        String sql = "INSERT INTO trucks (manufacturer, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, renter_id, truck_height, trailer, " +
                "trailer_length, trailer_width, trailer_height, freight_space ) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,truck.getManufacturer());
            ps.setInt(2,truck.getYear());
            ps.setInt(3,truck.getFuelTank());
            ps.setInt(4,truck.getMileage());
            ps.setString(5,truck.getEngine());
            ps.setInt(6,truck.getFuelConsumption());
            ps.setInt(7,truck.getSpareTires());
            ps.setInt(8, truck.getWeight());
            ps.setInt(9,truck.getPayloadCapacitiy());
            ps.setString(10,truck.getAdditionalEquipment());
            ps.setString(11,truck.getRegistrationPlate());
            ps.setObject(12,truck.getVehicleType(), Types.OTHER);
            ps.setObject(13,truck.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,truck.isAvailable());
            ps.setInt(15,truck.getRenterId());
            ps.setFloat(16,truck.getTruckHeight());
            ps.setBoolean(17, truck.isTrailer());
            ps.setFloat(18, truck.getTrailerLength());
            ps.setFloat(19, truck.getTrailerWidth());
            ps.setFloat(20, truck.getTrailerHeight());
            ps.setInt(21, truck.getFreightSpace());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void insertBus(Bus bus) throws SQLException {
        String sql = "INSERT INTO buses (manufacturer, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, renter_id, seats, two_story, bunker_capacity) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,bus.getManufacturer());
            ps.setInt(2,bus.getYear());
            ps.setInt(3,bus.getFuelTank());
            ps.setInt(4,bus.getMileage());
            ps.setString(5,bus.getEngine());
            ps.setInt(6,bus.getFuelConsumption());
            ps.setInt(7,bus.getSpareTires());
            ps.setInt(8, bus.getWeight());
            ps.setInt(9,bus.getPayloadCapacitiy());
            ps.setString(10,bus.getAdditionalEquipment());
            ps.setString(11,bus.getRegistrationPlate());
            ps.setObject(12,bus.getVehicleType(), Types.OTHER);
            ps.setObject(13,bus.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,bus.isAvailable());
            ps.setInt(15,bus.getRenterId());
            ps.setInt(16,bus.getSeats());
            ps.setBoolean(17, bus.isTwoStroy());
            ps.setInt(18, bus.getBunkerCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }


}
