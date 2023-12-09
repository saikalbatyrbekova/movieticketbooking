
package com.example.movieticketbookingsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private static final String dbURL = "jdbc:postgresql://localhost:5432/movie_booking";
    private static final String username = "postgres";
    private static final String password = "1234";

    public static Connection connectDb() throws SQLException {
        try {
            return DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(Database.class.getName());
            logger.log(Level.SEVERE, "Connection failed. Check the error message:", e);
            throw new SQLException("Failed to connect to the database", e);
        }
    }

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(Database.class.getName());

        try (Connection conn = connectDb()) {
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Connection failed. Error details:", e.getMessage());
        }
    }
}
