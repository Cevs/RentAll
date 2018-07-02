package com.cevs.rentall.models;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String email;
    private String password;
    private String userType;
    private Location location;

    public User() {
    }

    public User(int id, String email, String password, String userType, Location location) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.location = location;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", location=" + location +
                '}';
    }
}
