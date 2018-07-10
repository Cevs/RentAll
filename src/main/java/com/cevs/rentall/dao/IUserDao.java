package com.cevs.rentall.dao;

import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.models.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    void insertBuyer (Buyer buyer) throws SQLException;
    void insertRenter(Renter renter) throws SQLException;
    User findUserById(long userId);
    User findUserByEmail(String userEmail);
}
