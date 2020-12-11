package com.vyy.scooby.mysql;

import java.io.File;
import java.sql.*;

public class SQLConnection {
    public static SQLConnection instance;
    private String url = "jdbc:sqlite:ScoobyDB.db";
    private SQLConnection() {
        Connection conn;
        final File f = new File("ScoobyDB.db");
        if (!f.exists()) {
            SQLError("Database file not found.");
            System.exit(1);
        }
        conn = getConnection();
        if (conn != null) {
            closeConnection(conn);
        }
    }
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            SQLError(e.getMessage());
        }
        return conn;
    }
    public void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            SQLError(e.getMessage());
        }
    }
    public void closeResult(ResultSet result) {
        try {
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            SQLError(e.getMessage());
        }
    }
    public void closePreparedStatement(PreparedStatement pstmt) {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            SQLError(e.getMessage());
        }
    }
    public void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            SQLError(e.getMessage());
        }
    }
    public static void SQLError(String error) {
        System.out.println("[IMPORTANT] SQLite Error. [IMPORTANT]...\n" + error);
    }
    public static SQLConnection getInstance() {
        if (instance == null) {
            instance = new SQLConnection();
        }
        return instance;
    }
}
