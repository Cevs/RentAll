package com.cevs.rentall.dao;

import com.cevs.rentall.database.Database;
import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.models.Location;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;

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
                "(location).address, (location).zip_code " +
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
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
