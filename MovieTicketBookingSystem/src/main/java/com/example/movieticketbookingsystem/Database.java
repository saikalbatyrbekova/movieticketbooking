package com.example.movieticketbookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String dbURL = "jdbc:postgresql://localhost:5432/movie_booking";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(dbURL, username, password)) {
            System.out.println("Connected to Database");
            // You can perform database operations here as needed
        } catch (SQLException e) {
            System.out.println("Connection failed. Check the error message:");
            System.out.println(e.getMessage());
        }
    }
}

