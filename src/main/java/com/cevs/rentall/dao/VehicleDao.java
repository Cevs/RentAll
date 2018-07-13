package com.cevs.rentall.dao;

import com.cevs.rentall.database.Database;
import com.cevs.rentall.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class VehicleDao implements IVehicleDao {

    @Autowired
    Database db;

    @Override
    public List<Vehicle> getRenterVehicles(int renterId, String search) {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        String sql = "SELECT *FROM vehicles WHERE renter_id = ? " +
                "AND (manufacturer LIKE ? OR model LIKE ? OR registration_plate LIKE ?)";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, renterId);
            for(int i = 2; i<=4; i++){
                ps.setString(i,search+"%");
            }
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setManufacturer(rs.getString("manufacturer"));
                v.setModel(rs.getString("model"));
                v.setYear(rs.getInt("year"));
                v.setRegistrationPlate(rs.getString("registration_plate"));
                v.setVehicleType(rs.getString("vehicle_type"));
                v.setVehicleSubtype(rs.getString("vehicle_subtype"));
                v.setAvailable(rs.getBoolean("available"));
                v.setPricePerDay(rs.getInt("price_per_day"));
                v.setImage(rs.getString("image"));

                vehicles.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getRenterVehiclesOfType(int renterId, String vehicleType, String search){
        String sql = "SELECT *FROM vehicles WHERE renter_id = ? AND vehicle_type = ? " +
                "AND (manufacturer LIKE ? OR model LIKE ? OR registration_plate LIKE ?)";
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,renterId);
            ps.setObject(2,vehicleType, Types.OTHER);
            for(int i = 3; i<=5; i++){
                ps.setString(i,search+"%");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Vehicle v = new Vehicle();
                v.setId(rs.getInt("id"));
                v.setManufacturer(rs.getString("manufacturer"));
                v.setModel(rs.getString("model"));
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
    public List<VehicleRenter> getAllVehicleRenterOffersOfType(String search, String table) {
        List<VehicleRenter> vrList = new ArrayList<>();
        String sql = "SELECT t.id, t.manufacturer, t.model, t.year, t.fuel_tank, t.mileage, t.engine, " +
                "t.fuel_consumption, t.spare_tires, t.weight, t.payload_capacity, t.additional_equipment, " +
                "t.registration_plate, t.vehicle_type, t.vehicle_subtype, t.available, t.price_per_day, " +
                "t.image, t.renter_id, r.email, (location).country, (location).city, (location).address, " +
                "(location).zip_code, r.company_name, r.company_phone_number, " +
                "r.bank_account " +
                "FROM "+table+" t "+
                "LEFT JOIN renters r ON t.renter_id = r.id "+
                "WHERE t.available = ? " +
                "AND (manufacturer LIKE ? OR model LIKE ? OR engine LIKE ?)";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setBoolean(1,true);
            for(int i = 2; i<=4; i++){
                ps.setString(i,search+"%");
            }
            ResultSet rs  = ps.executeQuery();
            while(rs.next()){
                VehicleRenter vr = populateVehicleRenter(rs);
                vrList.add(vr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vrList;
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
                car = populateCarObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    @Override
    public CarRenter getCarRenterById(int carId) {
        CarRenter cr = null;
        String sql = "SELECT ca.id, ca.manufacturer, ca.model, ca.year, ca.fuel_tank, ca.mileage, " +
                "ca.engine, ca.fuel_consumption, ca.spare_tires, ca.weight, ca.payload_capacity, " +
                "ca.additional_equipment, ca.registration_plate, ca.vehicle_type, ca.vehicle_subtype, " +
                "ca.available, ca.price_per_day, ca.image, ca.renter_id, ca.doors, ca.color, ca.trunk_capacity, " +
                "r.email, (location).country, (location).city, (location).address, (location).zip_code, " +
                "r.company_name, r.company_phone_number, r.bank_account " +
                "FROM cars ca " +
                "LEFT JOIN renters r ON ca.renter_id = r.id " +
                "WHERE ca.id = ?";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1, carId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
               cr = new CarRenter(populateVehicleRenter(rs));
               cr.setDoors(rs.getInt("doors"));
               cr.setColor(rs.getString("color"));
               cr.setTrunkCapacity(rs.getInt("trunk_capacity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cr;
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
               truck = populateTruckObject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return truck;
    }

    @Override
    public TruckRenter getTruckRenterById(int truckId) {
        TruckRenter tr = null;
        String sql = "SELECT t.id, t.manufacturer, t.model, t.year, t.fuel_tank, t.mileage, " +
                "t.engine, t.fuel_consumption, t.spare_tires, t.weight, t.payload_capacity, " +
                "t.additional_equipment, t.registration_plate, t.vehicle_type, t.vehicle_subtype, " +
                "t.available, t.price_per_day, t.image, t.renter_id, t.truck_height, t.trailer, t.trailer_length, " +
                "t.trailer_width, t.trailer_height, t.freight_space, r.email, (location).country, " +
                "(location).city, (location).address, (location).zip_code, r.company_name, " +
                "r.company_phone_number, r.bank_account, r.id " +
                "FROM trucks t " +
                "LEFT JOIN renters r ON t.renter_id = r.id " +
                "WHERE t.id = ?;";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,truckId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                tr = new TruckRenter(populateVehicleRenter(rs));
                tr.setTrailerHeight(rs.getFloat("truck_height"));
                tr.setTrailer(rs.getBoolean("trailer"));
                tr.setTrailerLength(rs.getFloat("trailer_length"));
                tr.setTrailerWidth(rs.getFloat("trailer_width"));
                tr.setTrailerHeight(rs.getFloat("trailer_height"));
                tr.setFreightSpace(rs.getInt("freight_space"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tr;
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
                bus = populateBusObject(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  bus;
    }

    @Override
    public BusRenter getBusRenterById(int busId) {
        BusRenter br = null;
        String sql = "SELECT b.id, b.manufacturer, b.model, b.year, b.fuel_tank, b.mileage, " +
                "b.engine, b.fuel_consumption, b.spare_tires, b.weight, b.payload_capacity, " +
                "b.additional_equipment, b.registration_plate, b.vehicle_type, b.vehicle_subtype, " +
                "b.available, b.price_per_day, b.image, b.renter_id, b.seats, b.two_story, b.bunker_capacity, " +
                "r.email, (location).country, (location).city, (location).address, (location).zip_code, " +
                "r.company_name, r.company_phone_number, r.bank_account " +
                "FROM buses b " +
                "LEFT JOIN renters r ON b.renter_id = r.id " +
                "WHERE b.id = ? ";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,busId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                br = new BusRenter(populateVehicleRenter(rs));
                br.setSeats(rs.getInt("seats"));
                br.setTwoStory(rs.getBoolean("two_story"));
                br.setBunkerCapacity(rs.getInt("bunker_capacity"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return  br;
    }

    @Override
    public void insertCar(Car car) throws SQLException {
        String sql = "INSERT INTO cars (manufacturer, model, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, price_per_day, image, " +
                "renter_id, doors, color, trunk_capacity) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(Connection conn=db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,car.getManufacturer());
            ps.setString(2,car.getModel());
            ps.setInt(3,car.getYear());
            ps.setInt(4,car.getFuelTank());
            ps.setInt(5,car.getMileage());
            ps.setString(6,car.getEngine());
            ps.setInt(7,car.getFuelConsumption());
            ps.setInt(8,car.getSpareTires());
            ps.setInt(9, car.getWeight());
            ps.setInt(10,car.getPayloadCapacity());
            ps.setString(11,car.getAdditionalEquipment());
            ps.setString(12,car.getRegistrationPlate());
            ps.setObject(13,car.getVehicleType(), Types.OTHER);
            ps.setObject(14,car.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(15,car.isAvailable());
            ps.setInt(16,car.getPricePerDay());
            ps.setString(17, car.getImage());
            ps.setInt(18,car.getRenterId());
            ps.setInt(19,car.getDoors());
            ps.setString(20,car.getColor());
            ps.setInt(21, car.getTrunkCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void updateCar(Car car) throws SQLException {
        String sql = "UPDATE cars SET manufacturer = ?, model = ?, year = ?, fuel_tank = ?, mileage = ?, engine = ?, " +
                "fuel_consumption = ?, spare_tires = ?, weight = ?, payload_capacity = ?, additional_equipment = ?, " +
                "registration_plate = ?, vehicle_subtype = ?, available = ?, price_per_day = ?, " +
                "doors = ?, color = ?, trunk_capacity = ? " +
                "WHERE id = ? AND renter_id = ?;";
        try(Connection conn=db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,car.getManufacturer());
            ps.setString(2,car.getModel());
            ps.setInt(3,car.getYear());
            ps.setInt(4,car.getFuelTank());
            ps.setInt(5,car.getMileage());
            ps.setString(6,car.getEngine());
            ps.setInt(7,car.getFuelConsumption());
            ps.setInt(8,car.getSpareTires());
            ps.setInt(9,car.getWeight());
            ps.setInt(10,car.getPayloadCapacity());
            ps.setString(11,car.getAdditionalEquipment());
            ps.setString(12,car.getRegistrationPlate());
            ps.setObject(13,car.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,car.isAvailable());
            ps.setInt(15,car.getPricePerDay());

            ps.setInt(16,car.getDoors());
            ps.setString(17,car.getColor());
            ps.setInt(18, car.getTrunkCapacity());
            ps.setInt(19,car.getId());
            ps.setInt(20,car.getRenterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Update failed");
        }
    }

    @Override
    public void insertTruck(Truck truck) throws SQLException {
        String sql = "INSERT INTO trucks (manufacturer, model, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, price_per_day, image, renter_id, truck_height, trailer, " +
                "trailer_length, trailer_width, trailer_height, freight_space ) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,truck.getManufacturer());
            ps.setString(2,truck.getModel());
            ps.setInt(3,truck.getYear());
            ps.setInt(4,truck.getFuelTank());
            ps.setInt(5,truck.getMileage());
            ps.setString(6,truck.getEngine());
            ps.setInt(7,truck.getFuelConsumption());
            ps.setInt(8,truck.getSpareTires());
            ps.setInt(9, truck.getWeight());
            ps.setInt(10,truck.getPayloadCapacity());
            ps.setString(11,truck.getAdditionalEquipment());
            ps.setString(12,truck.getRegistrationPlate());
            ps.setObject(13,truck.getVehicleType(), Types.OTHER);
            ps.setObject(14,truck.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(15,truck.isAvailable());
            ps.setInt(16,truck.getPricePerDay());
            ps.setString(17,truck.getImage());
            ps.setInt(18,truck.getRenterId());
            ps.setFloat(19,truck.getTruckHeight());
            ps.setBoolean(20, truck.isTrailer());
            ps.setFloat(21, truck.getTrailerLength());
            ps.setFloat(22, truck.getTrailerWidth());
            ps.setFloat(23, truck.getTrailerHeight());
            ps.setInt(24, truck.getFreightSpace());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void updateTruck(Truck truck) throws SQLException {
        String sql = "UPDATE trucks SET manufacturer = ?, model = ?, year = ?, fuel_tank = ?, mileage = ?, engine = ?, " +
                "fuel_consumption = ?, spare_tires = ?, weight = ?, payload_capacity = ?, additional_equipment = ?, " +
                "registration_plate = ?, vehicle_subtype = ?, available = ?, price_per_day = ? ,truck_height = ?, " +
                "trailer = ?, trailer_length = ?, trailer_width = ?, trailer_height = ?, freight_space = ? " +
                "WHERE id = ? AND renter_id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,truck.getManufacturer());
            ps.setString(2,truck.getModel());
            ps.setInt(3,truck.getYear());
            ps.setInt(4,truck.getFuelTank());
            ps.setInt(5,truck.getMileage());
            ps.setString(6,truck.getEngine());
            ps.setInt(7,truck.getFuelConsumption());
            ps.setInt(8,truck.getSpareTires());
            ps.setInt(9,truck.getWeight());
            ps.setInt(10,truck.getPayloadCapacity());
            ps.setString(11,truck.getAdditionalEquipment());
            ps.setString(12,truck.getRegistrationPlate());
            ps.setObject(13,truck.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,truck.isAvailable());
            ps.setInt(15,truck.getPricePerDay());

            ps.setFloat(16, truck.getTruckHeight());
            ps.setBoolean(17,truck.isTrailer());
            ps.setFloat(18,truck.getTrailerLength());
            ps.setFloat(19,truck.getTrailerWidth());
            ps.setFloat(20,truck.getTrailerHeight());
            ps.setInt(21,truck.getFreightSpace());

            ps.setInt(22,truck.getId());
            ps.setInt(23,truck.getRenterId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Update failed");
        }
    }

    @Override
    public void insertBus(Bus bus) throws SQLException {
        String sql = "INSERT INTO buses (manufacturer,model, year, fuel_tank, mileage, engine, fuel_consumption, " +
                "spare_tires, weight, payload_capacity, additional_equipment, registration_plate, " +
                "vehicle_type, vehicle_subtype, available, price_per_day, image, renter_id, seats, two_story, " +
                "bunker_capacity) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1,bus.getManufacturer());
            ps.setString(2,bus.getModel());
            ps.setInt(3,bus.getYear());
            ps.setInt(4,bus.getFuelTank());
            ps.setInt(5,bus.getMileage());
            ps.setString(6,bus.getEngine());
            ps.setInt(7,bus.getFuelConsumption());
            ps.setInt(8,bus.getSpareTires());
            ps.setInt(9, bus.getWeight());
            ps.setInt(10,bus.getPayloadCapacity());
            ps.setString(11,bus.getAdditionalEquipment());
            ps.setString(12,bus.getRegistrationPlate());
            ps.setObject(13,bus.getVehicleType(), Types.OTHER);
            ps.setObject(14,bus.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(15,bus.isAvailable());
            ps.setInt(16, bus.getPricePerDay());
            ps.setString(17,bus.getImage());
            ps.setInt(18,bus.getRenterId());
            ps.setInt(19,bus.getSeats());
            ps.setBoolean(20, bus.isTwoStory());
            ps.setInt(21, bus.getBunkerCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void updateBus(Bus bus) throws SQLException {
        String sql = "UPDATE buses SET manufacturer = ?, model = ?, year = ?, fuel_tank = ?, mileage = ?, engine = ?, " +
                "fuel_consumption = ?, spare_tires = ?, weight = ?, payload_capacity = ?, additional_equipment = ?, " +
                "registration_plate = ?, vehicle_subtype = ?, available = ?, price_per_day = ? seats = ?, " +
                "two_story = ?, bunker_capacity = ? " +
                "WHERE id = ? AND renter_id = ?;";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,bus.getManufacturer());
            ps.setString(2,bus.getModel());
            ps.setInt(3,bus.getYear());
            ps.setInt(4,bus.getFuelTank());
            ps.setInt(5,bus.getMileage());
            ps.setString(6,bus.getEngine());
            ps.setInt(7,bus.getFuelConsumption());
            ps.setInt(8,bus.getSpareTires());
            ps.setInt(9,bus.getWeight());
            ps.setInt(10,bus.getPayloadCapacity());
            ps.setString(11,bus.getAdditionalEquipment());
            ps.setString(12,bus.getRegistrationPlate());
            ps.setObject(13,bus.getVehicleSubtype(), Types.OTHER);
            ps.setBoolean(14,bus.isAvailable());
            ps.setInt(15,bus.getPricePerDay());

            ps.setInt(16, bus.getSeats());
            ps.setBoolean(17,bus.isTwoStory());
            ps.setInt(18,bus.getBunkerCapacity());

            ps.setInt(19, bus.getId());
            ps.setInt(20, bus.getRenterId());
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

    private Truck populateTruckObject(ResultSet rs) throws SQLException {
        Truck truck = new Truck();
        truck.setId(rs.getInt("id"));
        truck.setManufacturer(rs.getString("manufacturer"));
        truck.setModel(rs.getString("model"));
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
        truck.setImage(rs.getString("image"));

        truck.setTruckHeight(rs.getFloat("truck_height"));
        truck.setTrailer(rs.getBoolean("trailer"));
        truck.setTrailerLength(rs.getFloat("trailer_length"));
        truck.setTrailerWidth(rs.getFloat("trailer_width"));
        truck.setTrailerHeight(rs.getFloat("trailer_height"));
        truck.setFreightSpace(rs.getInt("freight_space"));
        return truck;
    }

    private Car populateCarObject(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setManufacturer(rs.getString("manufacturer"));
        car.setModel(rs.getString("model"));
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
        car.setImage(rs.getString("image"));
        car.setDoors(rs.getInt("doors"));
        car.setColor(rs.getString("color"));
        car.setTrunkCapacity(rs.getInt("trunk_capacity"));
        return car;
    }

    private Bus populateBusObject(ResultSet rs) throws SQLException {
        Bus bus = new Bus();
        bus.setId(rs.getInt("id"));
        bus.setManufacturer(rs.getString("manufacturer"));
        bus.setModel(rs.getString("model"));
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
        bus.setImage(rs.getString("image"));

        bus.setSeats(rs.getInt("seats"));
        bus.setTwoStory(rs.getBoolean("two_story"));
        bus.setBunkerCapacity(rs.getInt("bunker_capacity"));
        return bus;
    }

    private VehicleRenter populateVehicleRenter(ResultSet rs) throws SQLException {
        VehicleRenter vr = new VehicleRenter();
        vr.setId(rs.getInt("id"));
        vr.setManufacturer(rs.getString("manufacturer"));
        vr.setModel(rs.getString("model"));
        vr.setYear(rs.getInt("year"));
        vr.setFuelTank(rs.getInt("fuel_tank"));
        vr.setMileage(rs.getInt("mileage"));
        vr.setEngine(rs.getString("engine"));
        vr.setFuelConsumption(rs.getInt("fuel_consumption"));
        vr.setSpareTires(rs.getInt("spare_tires"));
        vr.setWeight(rs.getInt("weight"));
        vr.setPayloadCapacity(rs.getInt("payload_capacity"));
        vr.setAdditionalEquipment(rs.getString("additional_equipment"));
        vr.setRegistrationPlate(rs.getString("registration_plate"));
        vr.setVehicleType(rs.getString("vehicle_type"));
        vr.setVehicleSubtype(rs.getString("vehicle_subtype"));
        vr.setAvailable(rs.getBoolean("available"));
        vr.setPricePerDay(rs.getInt("price_per_day"));
        vr.setImage(rs.getString("image"));
        vr.setEmail(rs.getString("email"));
        vr.setLocation(new Location(
                rs.getString("country"),
                rs.getString("city"),
                rs.getString("address"),
                rs.getString("zip_code")
        ));
        vr.setCompanyName(rs.getString("company_name"));
        vr.setCompanyPhoneNumber(rs.getString("company_phone_number"));
        vr.setBankAccount(rs.getString("bank_account"));
        vr.setCompanyId(rs.getInt("renter_id"));
        return vr;
    }
}
