package com.cevs.rentall.dao;

import com.cevs.rentall.dto.RenterDto;
import com.cevs.rentall.models.Buyer;
import com.cevs.rentall.models.Renter;
import com.cevs.rentall.models.RenterInfo;
import com.cevs.rentall.models.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {
    void insertBuyer (Buyer buyer) throws SQLException;
    void insertRenter(Renter renter) throws SQLException;
    User findUserById(long userId);
    User findUserByEmail(String userEmail);
    void updateRenter(Renter renter) throws SQLException;
    void updateBuyer(Buyer  buyer) throws SQLException;
    Renter getRenterProfileInfo(int renterId);
    Buyer getBuyerProfileInfo(int buyerId);
    RenterInfo getRenterInfo(int renterId);
    List<User> getUsers(String search, String table);
    void deleteUser(int userId) throws SQLException;
    void lockUser(int id, boolean locked) throws SQLException;
}
