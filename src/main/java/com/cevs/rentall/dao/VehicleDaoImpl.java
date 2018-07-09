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
                v.setId(rs.getInt("id"));
                v.setManufacturer(rs.getString("manufacturer"));
                v.setYear(rs.getInt("year"));
                v.setRegistrationPlate(rs.getString("registration_plate"));
                v.setVehicleType(rs.getString("vehicle_type"));
                v.setVehicleSubtype(rs.getString("vehicle_subtype"));
                v.setAvailable(rs.getBoolean("available"));
                v.setPricePerDay(rs.getInt("price_per_day"));

                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getRenterVehiclesOfType(int renterId, String vehicleType){
        String sql = "SELECT *FROM vehicles WHERE renter_id = ? AND vehicle_type = ?";
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,renterId);
            ps.setObject(2,vehicleType, Types.OTHER);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setManufacturer(rs.getString("manufacturer"));
                v.setYear(rs.getInt("year"));
                v.setRegistrationPlate(rs.getString("registration_plate"));
                v.setVehicleType(rs.getString("vehicle_type"));
                v.setVehicleSubtype(rs.getString("vehicle_subtype"));
                v.setAvailable(rs.getBoolean("available"));
                v.setPricePerDay(rs.getInt("price_per_day"));
                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public Car getCarById(int renterId, int carId) {
        Car car = new Car();
        String sql = "SELECT *FROM cars WHERE renter_id = ? AND id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, renterId);
            ps.setInt(2, carId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                car.setId(rs.getInt("id"));
                car.setManufacturer(rs.getString("manufacturer"));
                car.setYear(rs.getInt("year"));
                car.setFuelTank(rs.getInt("fuel_tank"));
                car.setMileage(rs.getInt("mileage"));
                car.setEngine(rs.getString("engine"));
                car.setFuelConsumption(rs.getInt("fuel_consumption"));
                car.setSpareTires(rs.getInt("spare_tires"));
                car.setWeight(rs.getInt("weight"));
                car.setPayloadCapacity(rs.getInt("payload_capacity"));
                car.setAdditionalEquipment(rs.getString("additional_equipment"));
                car.setRegistrationPlate(rs.getString("registration_plate"));
                car.setVehicleType(rs.getString("vehicle_type")); //Da li je ovo dobro ili mora ici getObject
                car.setVehicleSubtype(rs.getString("vehicle_subtype"));
                car.setAvailable(rs.getBoolean("available"));
                car.setRenterId(rs.getInt("renter_id"));
                car.setPricePerDay(rs.getInt("price_per_day"));

                car.setDoors(rs.getInt("doors"));
                car.setColor(rs.getString("color"));
                car.setTrunkCapacity(rs.getInt("trunk_capacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public Truck getTruckById(int renterId, int truckId) {
        Truck truck = new Truck();
        String sql = "SELECT *FROM ONLY trucks WHERE id = ? AND renter_id = ?;";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,truckId);
            ps.setInt(2,renterId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                truck.setId(rs.getInt("id"));
                truck.setManufacturer(rs.getString("manufacturer"));
                truck.setYear(rs.getInt("year"));
                truck.setFuelTank(rs.getInt("fuel_tank"));
                truck.setMileage(rs.getInt("mileage"));
                truck.setEngine(rs.getString("engine"));
                truck.setFuelConsumption(rs.getInt("fuel_consumption"));
                truck.setSpareTires(rs.getInt("spare_tires"));
                truck.setWeight(rs.getInt("weight"));
                truck.setPayloadCapacity(rs.getInt("payload_capacity"));
                truck.setAdditionalEquipment(rs.getString("additional_equipment"));
                truck.setRegistrationPlate(rs.getString("registration_plate"));
                truck.setVehicleType(rs.getString("vehicle_type")); //Da li je ovo dobro ili mora ici getObject
                truck.setVehicleSubtype(rs.getString("vehicle_subtype"));
                truck.setAvailable(rs.getBoolean("available"));
                truck.setRenterId(rs.getInt("renter_id"));
                truck.setPricePerDay(rs.getInt("price_per_day"));

                truck.setTruckHeight(rs.getFloat("truck_height"));
                truck.setTrailer(rs.getBoolean("trailer"));
                truck.setTrailerLength(rs.getFloat("trailer_length"));
                truck.setTrailerWidth(rs.getFloat("trailer_width"));
                truck.setTrailerHeight(rs.getFloat("trailer_height"));
                truck.setFreightSpace(rs.getInt("freight_space"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return truck;
    }

    @Override
    public Bus getBusById(int renterId, int busId) {
        Bus bus = new Bus();
        String sql = "SELECT *FROM buses WHERE renter_id = ? AND id = ? ";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,renterId);
            ps.setInt(2,busId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                bus.setId(rs.getInt("id"));
                bus.setManufacturer(rs.getString("manufacturer"));
                bus.setYear(rs.getInt("year"));
                bus.setFuelTank(rs.getInt("fuel_tank"));
                bus.setMileage(rs.getInt("mileage"));
                bus.setEngine(rs.getString("engine"));
                bus.setFuelConsumption(rs.getInt("fuel_consumption"));
                bus.setSpareTires(rs.getInt("spare_tires"));
                bus.setWeight(rs.getInt("weight"));
                bus.setPayloadCapacity(rs.getInt("payload_capacity"));
                bus.setAdditionalEquipment(rs.getString("additional_equipment"));
                bus.setRegistrationPlate(rs.getString("registration_plate"));
                bus.setVehicleType(rs.getString("vehicle_type")); //Da li je ovo dobro ili mora ici getObject
                bus.setVehicleSubtype(rs.getString("vehicle_subtype"));
                bus.setAvailable(rs.getBoolean("available"));
                bus.setRenterId(rs.getInt("renter_id"));
                bus.setPricePerDay(rs.getInt("price_per_day"));

                bus.setSeats(rs.getInt("seats"));
                bus.setTwoStory(rs.getBoolean("two_story"));
                bus.setBunkerCapacity(rs.getInt("bunker_capacity"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  bus;
    }

    @Override
    public void insertCar(Car car) throws SQLException {
        String sql = "INSERT INTO cars (manufacturer, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, price_per_day, renter_id, doors, color, trunk_capacity) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
            ps.setInt(9,car.getPayloadCapacity());
            ps.setString(10,car.getAdditionalEquipment());
            ps.setString(11,car.getRegistrationPlate());
            ps.setObject(12,car.getVehicleType(), Types.OTHER);
            ps.setObject(13,car.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,car.isAvailable());
            ps.setInt(15,car.getPricePerDay());
            ps.setInt(16,car.getRenterId());
            ps.setInt(17,car.getDoors());
            ps.setString(18,car.getColor());
            ps.setInt(19, car.getTrunkCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE cars SET manufacturer = ?, year = ?, fuel_tank = ?, mileage = ?, engine = ?, " +
                "fuel_consumption = ?, spare_tires = ?, weight = ?, payload_capacity = ?, additional_equipment = ?, " +
                "registration_plate = ?, vehicle_subtype = ?, available = ?, price_per_day = ?, doors = ?, " +
                "color = ?, trunk_capacity = ? " +
                "WHERE id = ? AND renter_id = ?;";
        try(Connection conn=db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,car.getManufacturer());
            ps.setInt(2,car.getYear());
            ps.setInt(3,car.getFuelTank());
            ps.setInt(4,car.getMileage());
            ps.setString(5,car.getEngine());
            ps.setInt(6,car.getFuelConsumption());
            ps.setInt(7,car.getSpareTires());
            ps.setInt(8,car.getWeight());
            ps.setInt(9,car.getPayloadCapacity());
            ps.setString(10,car.getAdditionalEquipment());
            ps.setString(11,car.getRegistrationPlate());
            ps.setObject(12,car.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(13,car.isAvailable());
            ps.setInt(14,car.getPricePerDay());

            ps.setInt(15,car.getDoors());
            ps.setString(16,car.getColor());
            ps.setInt(17, car.getTrunkCapacity());
            ps.setInt(18,car.getId());
            ps.setInt(19,car.getRenterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Update failed");
        }
    }

    @Override
    public void insertTruck(Truck truck) throws SQLException {
        String sql = "INSERT INTO trucks (manufacturer, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, price_per_day, renter_id, truck_height, trailer, " +
                "trailer_length, trailer_width, trailer_height, freight_space ) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
            ps.setInt(9,truck.getPayloadCapacity());
            ps.setString(10,truck.getAdditionalEquipment());
            ps.setString(11,truck.getRegistrationPlate());
            ps.setObject(12,truck.getVehicleType(), Types.OTHER);
            ps.setObject(13,truck.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,truck.isAvailable());
            ps.setInt(15,truck.getPricePerDay());
            ps.setInt(16,truck.getRenterId());
            ps.setFloat(17,truck.getTruckHeight());
            ps.setBoolean(18, truck.isTrailer());
            ps.setFloat(19, truck.getTrailerLength());
            ps.setFloat(20, truck.getTrailerWidth());
            ps.setFloat(21, truck.getTrailerHeight());
            ps.setInt(22, truck.getFreightSpace());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void updateTruck(Truck truck) throws SQLException {
        String sql = "UPDATE trucks SET manufacturer = ?, year = ?, fuel_tank = ?, mileage = ?, engine = ?, " +
                "fuel_consumption = ?, spare_tires = ?, weight = ?, payload_capacity = ?, additional_equipment = ?, " +
                "registration_plate = ?, vehicle_subtype = ?, available = ?, price_per_day = ? ,truck_height = ?, " +
                "trailer = ?, trailer_length = ?, trailer_width = ?, trailer_height = ?, freight_space = ? " +
                "WHERE id = ? AND renter_id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,truck.getManufacturer());
            ps.setInt(2,truck.getYear());
            ps.setInt(3,truck.getFuelTank());
            ps.setInt(4,truck.getMileage());
            ps.setString(5,truck.getEngine());
            ps.setInt(6,truck.getFuelConsumption());
            ps.setInt(7,truck.getSpareTires());
            ps.setInt(8,truck.getWeight());
            ps.setInt(9,truck.getPayloadCapacity());
            ps.setString(10,truck.getAdditionalEquipment());
            ps.setString(11,truck.getRegistrationPlate());
            ps.setObject(12,truck.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(13,truck.isAvailable());
            ps.setInt(14,truck.getPricePerDay());

            ps.setFloat(15, truck.getTruckHeight());
            ps.setBoolean(16,truck.isTrailer());
            ps.setFloat(17,truck.getTrailerLength());
            ps.setFloat(18,truck.getTrailerWidth());
            ps.setFloat(19,truck.getTrailerHeight());
            ps.setInt(20,truck.getFreightSpace());

            ps.setInt(21,truck.getId());
            ps.setInt(22,truck.getRenterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Update failed");
        }
    }

    @Override
    public void insertBus(Bus bus) throws SQLException {
        String sql = "INSERT INTO buses (manufacturer, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, price_per_day, renter_id, seats, two_story, " +
                "bunker_capacity) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
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
            ps.setInt(9,bus.getPayloadCapacity());
            ps.setString(10,bus.getAdditionalEquipment());
            ps.setString(11,bus.getRegistrationPlate());
            ps.setObject(12,bus.getVehicleType(), Types.OTHER);
            ps.setObject(13,bus.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,bus.isAvailable());
            ps.setInt(15, bus.getPricePerDay());
            ps.setInt(16,bus.getRenterId());
            ps.setInt(17,bus.getSeats());
            ps.setBoolean(18, bus.isTwoStory());
            ps.setInt(19, bus.getBunkerCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void updateBus(Bus bus) throws SQLException {
        String sql = "UPDATE buses SET manufacturer = ?, year = ?, fuel_tank = ?, mileage = ?, engine = ?, " +
                "fuel_consumption = ?, spare_tires = ?, weight = ?, payload_capacity = ?, additional_equipment = ?, " +
                "registration_plate = ?, vehicle_subtype = ?, available = ?, price_per_day = ? seats = ?, " +
                "two_story = ?, bunker_capacity = ? " +
                "WHERE id = ? AND renter_id = ?;";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,bus.getManufacturer());
            ps.setInt(2,bus.getYear());
            ps.setInt(3,bus.getFuelTank());
            ps.setInt(4,bus.getMileage());
            ps.setString(5,bus.getEngine());
            ps.setInt(6,bus.getFuelConsumption());
            ps.setInt(7,bus.getSpareTires());
            ps.setInt(8,bus.getWeight());
            ps.setInt(9,bus.getPayloadCapacity());
            ps.setString(10,bus.getAdditionalEquipment());
            ps.setString(11,bus.getRegistrationPlate());
            ps.setObject(12,bus.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(13,bus.isAvailable());
            ps.setInt(14,bus.getPricePerDay());

            ps.setInt(15, bus.getSeats());
            ps.setBoolean(16,bus.isTwoStory());
            ps.setInt(17,bus.getBunkerCapacity());

            ps.setInt(18, bus.getId());
            ps.setInt(19, bus.getRenterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Update failed");
        }
    }

    @Override
    public void deleteVehicle(int renterId, int vehicleId) throws SQLException {
        String sql = "DELETE FROM vehicles WHERE renter_id = ? AND id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, renterId);
            ps.setInt(2, vehicleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Delete failed");
        }
    }
}
