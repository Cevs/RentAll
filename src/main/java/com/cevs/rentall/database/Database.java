package com.cevs.rentall.database;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class Database {
    private static String url = "jdbc:postgresql://localhost:5432/RentAll";
    private static String user = "postgres";
    private static String password = "47184718";
    private static Database instance;
    private Connection connection;

    private Database() {
        try {
            Class.forName("org.postgresql.Driver");
            instance = this;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection openConnection(){
        if(instance == null){
            instance = new Database();
        }
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
