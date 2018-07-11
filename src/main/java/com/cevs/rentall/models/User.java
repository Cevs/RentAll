package com.cevs.rentall.models;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String email;
    private String password;
    private String userType;
    private Location location;
    private MultipartFile image;
    public User() {
    }

    public User(String email, String password, String userType, Location location) {
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.location = location;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String encodeImage(){
        try {
            byte[]  encoded = Base64.getEncoder().encode(getImage().getBytes());
            return (new String(encoded));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";

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
