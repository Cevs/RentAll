package com.cevs.rentall.dao;

import com.cevs.rentall.database.Database;
import com.cevs.rentall.models.*;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UserDao implements IUserDao {

    @Autowired
    Database db;

    @Override
    public void insertBuyer(Buyer buyer) throws SQLException {
        String sql = "INSERT INTO buyers (email,password, user_type, location, " +
                "firstname, lastname, phone_number, birth_date)" +
                " VALUES (?,?,?, ROW(?,?,?,?),?,?,?,?)";
        try(Connection conn = Database.openConnection();
        PreparedStatement ps = conn.prepareCall(sql)){
            ps.setString(1,buyer.getEmail());
            ps.setString(2,buyer.getPassword());
            ps.setObject(3,buyer.getUserType(), Types.OTHER);
            ps.setString(4,buyer.getLocation().getCountry());
            ps.setString(5,buyer.getLocation().getCity());
            ps.setString(6,buyer.getLocation().getAddress());
            ps.setString(7,buyer.getLocation().getZipCode());
            ps.setString(8,buyer.getFirstname());
            ps.setString(9,buyer.getLastname());
            ps.setString(10,buyer.getPhoneNumber());
            ps.setDate(11,buyer.getBirthDate());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }

    @Override
    public void insertRenter(Renter renter) throws SQLException {
        String sql = "INSERT INTO renters (email,password, user_type, location, " +
                "company_name, company_phone_number, bank_account)" +
                " VALUES (?,?,?, ROW(?,?,?,?),?,?,?)";
        try(Connection conn = Database.openConnection();
            PreparedStatement ps = conn.prepareCall(sql)){
            ps.setString(1,renter.getEmail());
            ps.setString(2,renter.getPassword());
            ps.setObject(3,renter.getUserType(), Types.OTHER);
            ps.setString(4,renter.getLocation().getCountry());
            ps.setString(5,renter.getLocation().getCity());
            ps.setString(6,renter.getLocation().getAddress());
            ps.setString(7,renter.getLocation().getZipCode());
            ps.setString(8,renter.getCompanyName());
            ps.setString(9,renter.getCompanyPhoneNumber());
            ps.setString(10,renter.getBankAccount());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Insert failed");
        }
    }


    @Override
    public User findUserById(long userId) {
        //TODO
        return null;
    }

    @Override
    public User findUserByEmail(String userEmail) {
        String sql = "SELECT id, email, password, user_type, " +
                "(location).country, (location).city, " +
                "(location).address, (location).zip_code, locked " +
                "FROM users WHERE email = ?";

        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareCall(sql))
        {
            ps.setString(1,userEmail);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String userType = rs.getString("user_type");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String address = rs.getString("address");
                String zipCode = rs.getString("zip_code");
                Location location = new Location(country,city,address,zipCode);
                User user = new User(id,email,password,userType,location);
                user.setLocked(rs.getBoolean("locked"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateRenter(Renter renter) throws SQLException {
        if(!renter.getImage().isEmpty()){
            String sql = "UPDATE renters SET email = ?, password = ?, location.country = ?, " +
                    "location.city = ?, location.address = ?, location.zip_code = ?, " +
                    "image = ?, company_name = ?, company_phone_number = ?, bank_account = ? " +
                    "WHERE id = ?";
            try(Connection conn = db.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, renter.getEmail());
                ps.setString(2,renter.getPassword());
                ps.setString(3,renter.getLocation().getCountry());
                ps.setString(4,renter.getLocation().getCity());
                ps.setString(5,renter.getLocation().getAddress());
                ps.setString(6,renter.getLocation().getZipCode());
                ps.setString(7, renter.getImage());
                ps.setString(8,renter.getCompanyName());
                ps.setString(9, renter.getCompanyPhoneNumber());
                ps.setString(10,renter.getBankAccount());
                ps.setInt(11, renter.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Update failed");
            }
        }else{
            String sql = "UPDATE renters SET email = ?, password = ?, location.country = ?, " +
                    "location.city = ?, location.address = ?, location.zip_code = ?, " +
                    "company_name = ?, company_phone_number = ?, bank_account = ? " +
                    "WHERE id = ?";
            try(Connection conn = db.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, renter.getEmail());
                ps.setString(2,renter.getPassword());
                ps.setString(3,renter.getLocation().getCountry());
                ps.setString(4,renter.getLocation().getCity());
                ps.setString(5,renter.getLocation().getAddress());
                ps.setString(6,renter.getLocation().getZipCode());
                ps.setString(7,renter.getCompanyName());
                ps.setString(8, renter.getCompanyPhoneNumber());
                ps.setString(9,renter.getBankAccount());
                ps.setInt(10, renter.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Update failed");
            }
        }
    }

    @Override
    public void updateBuyer(Buyer buyer) throws SQLException {
        if(!buyer.getImage().isEmpty()){
            String sql = "UPDATE buyers SET email = ?, password = ?, location.country = ?, " +
                    "location.city = ?, location.address = ?, location.zip_code = ?, " +
                    "image = ?, firstname = ?, lastname = ?, phone_number = ?, birth_date = ? " +
                    "WHERE id = ?";
            try(Connection conn = db.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, buyer.getEmail());
                ps.setString(2,buyer.getPassword());
                ps.setString(3,buyer.getLocation().getCountry());
                ps.setString(4,buyer.getLocation().getCity());
                ps.setString(5,buyer.getLocation().getAddress());
                ps.setString(6,buyer.getLocation().getZipCode());
                ps.setString(7, buyer.getImage());
                ps.setString(8,buyer.getFirstname());
                ps.setString(9, buyer.getLastname());
                ps.setString(10,buyer.getPhoneNumber());
                ps.setDate(11,buyer.getBirthDate());
                ps.setInt(12, buyer.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Update failed");
            }
        }else{
            String sql = "UPDATE buyers SET email = ?, password = ?, location.country = ?, " +
                    "location.city = ?, location.address = ?, location.zip_code = ?, " +
                    "firstname = ?, lastname = ?, phone_number = ?, birth_date = ? " +
                    "WHERE id = ?";
            try(Connection conn = db.openConnection();
                PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1, buyer.getEmail());
                ps.setString(2,buyer.getPassword());
                ps.setString(3,buyer.getLocation().getCountry());
                ps.setString(4,buyer.getLocation().getCity());
                ps.setString(5,buyer.getLocation().getAddress());
                ps.setString(6,buyer.getLocation().getZipCode());
                ps.setString(7,buyer.getFirstname());
                ps.setString(8, buyer.getLastname());
                ps.setString(9,buyer.getPhoneNumber());
                ps.setDate(10,buyer.getBirthDate());
                ps.setInt(11, buyer.getId());
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Update failed");
            }
        }
    }

    @Override
    public Renter getRenterProfileInfo(int renterId) {
        Renter renter = new Renter();
        String sql = "SELECT email, password, user_type, (location).country, " +
                "(location).city, (location).address, (location).zip_code, image, " +
                "company_name, company_phone_number, bank_account " +
                "FROM renters WHERE id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,renterId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                renter.setEmail(rs.getString("email"));
                renter.setPassword(rs.getString("password"));
                renter.setUserType(rs.getString("user_type"));
                Location loc = new Location();
                loc.setCountry(rs.getString("country"));
                loc.setCity(rs.getString("city"));
                loc.setAddress(rs.getString("address"));
                loc.setZipCode(rs.getString("zip_code"));
                renter.setLocation(loc);
                renter.setImage(rs.getString("image"));
                renter.setCompanyName(rs.getString("company_name"));
                renter.setCompanyPhoneNumber(rs.getString("company_phone_number"));
                renter.setBankAccount(rs.getString("bank_account"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return renter;
    }

    @Override
    public Buyer getBuyerProfileInfo(int buyerId) {
        Buyer buyer = new Buyer();
        String sql = "SELECT email, password, user_type, (location).country, (location).city, " +
                "(location).address, (location).zip_code, image, firstname, lastname, phone_number, " +
                "birth_date FROM buyers WHERE id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,buyerId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                buyer.setEmail(rs.getString("email"));
                buyer.setPassword(rs.getString("password"));
                buyer.setUserType(rs.getString("user_type"));
                Location loc = new Location();
                loc.setCountry(rs.getString("country"));
                loc.setCity(rs.getString("city"));
                loc.setAddress(rs.getString("address"));
                loc.setZipCode(rs.getString("zip_code"));
                buyer.setLocation(loc);
                buyer.setImage(rs.getString("image"));
                buyer.setFirstname(rs.getString("firstname"));
                buyer.setLastname(rs.getString("lastname"));
                buyer.setPhoneNumber(rs.getString("phone_number"));
                buyer.setBirthDate(rs.getDate("birth_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyer;
    }

    @Override
    public RenterInfo getRenterInfo(int renterId) {
        RenterInfo ri = new RenterInfo();
        String sql = "SELECT email, (location).country, " +
                "(location).city, (location).address, (location).zip_code, image, " +
                "company_name, company_phone_number, bank_account " +
                "FROM renters WHERE id = ?";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,renterId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                ri.setEmail(rs.getString("email"));
                Location loc = new Location();
                loc.setCountry(rs.getString("country"));
                loc.setCity(rs.getString("city"));
                loc.setAddress(rs.getString("address"));
                loc.setZipCode(rs.getString("zip_code"));
                ri.setLocation(loc);
                ri.setImage(rs.getString("image"));
                ri.setCompanyName(rs.getString("company_name"));
                ri.setCompanyPhoneNumber(rs.getString("company_phone_number"));
                ri.setBankAccount(rs.getString("bank_account"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ri;
    }

    @Override
    public List<User> getUsers(String search, String table) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, email, password, user_type, (location).country, (location).city, " +
                "(location).address, (location).zip_code, image, locked FROM "+table+" " +
                "WHERE user_type != 'Administrator' " +
                "AND (email LIKE ? OR password LIKE ? OR (location).country LIKE ? " +
                "OR (location).city LIKE ? OR (location).address LIKE ? OR (location).zip_code LIKE ?);";
        try(Connection conn = db.openConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            for(int i = 1; i<= 6 ;i++){
                ps.setString(i, search+"%");
            }
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setUserType(rs.getString("user_type"));
                u.setLocation(
                        new Location(
                                rs.getString("country"),
                                rs.getString("city"),
                                rs.getString("address"),
                                rs.getString("zip_code")
                        )
                );
                u.setImage(rs.getString("image"));
                u.setLocked(rs.getBoolean("locked"));
                users.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setInt(1,userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Delete failed");
        }
    }

    @Override
    public void lockUser(int id, boolean locked) throws SQLException {
        String sql = "UPDATE users SET locked = ? " +
                "WHERE id = ?";
        try(Connection conn = db.openConnection();
        PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setBoolean(1, locked);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Update failed");
        }
    }
}
