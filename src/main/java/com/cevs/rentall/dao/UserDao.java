package com.cevs.rentall.dao;

import com.cevs.rentall.models.User;

public interface UserDao {
    void insert (User user);
    User findUserById(long userId);
    User findUserByEmail(String userEmail);

}
