package com.cevs.rentall.dao;

import com.cevs.rentall.database.Database;
import com.cevs.rentall.models.Location;
import com.cevs.rentall.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class UserDaoImpl implements UserDao {

    @Autowired
    Database db;

    @Override
    public void insert(User user) {
        //TODO
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
