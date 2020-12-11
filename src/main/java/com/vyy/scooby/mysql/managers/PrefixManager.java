package com.vyy.scooby.mysql.managers;

import com.vyy.scooby.mysql.SQLConnection;

import java.sql.*;

public class PrefixManager {
    public static PrefixManager instance;
    public PrefixManager() {
        createPrefixTable();
    }
    public void createPrefixTable() {
        Connection conn = null;
        Statement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS \"prefixs\" (\n" +
                "\t\"guildid\"\tTEXT,\n" +
                "\t\"prefix\"\tTEXT\n" +
                ")";
        try{
            conn = SQLConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            stmt.execute(sql);
        }catch (SQLException e) {
            SQLConnection.getInstance().SQLError(e.getMessage());
        }finally {
            SQLConnection.getInstance().closeConnection(conn);
            SQLConnection.getInstance().closeStatement(stmt);
        }
    }
    public String getPrefix(String guildid) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String sql = "SELECT * FROM prefixs";
        try {
            conn = SQLConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
                if (result.getString("guildid").equalsIgnoreCase(guildid)) {
                    return result.getString("prefix");
                }
            }
        }catch (SQLException e) {
            SQLConnection.getInstance().SQLError(e.getMessage());
        }finally {
            SQLConnection.getInstance().closeConnection(conn);
            SQLConnection.getInstance().closeStatement(stmt);
            SQLConnection.getInstance().closeResult(result);
        }
        return null;
    }
    public void setPrefix(String guildid, String prefix) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE prefixs SET prefix = '" + prefix + "' WHERE guildid = '" + guildid + "'";

        try {
            conn = SQLConnection.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            SQLConnection.getInstance().SQLError(e.getMessage());
        }finally {
            SQLConnection.getInstance().closeConnection(conn);
            SQLConnection.getInstance().closePreparedStatement(pstmt);
        }
    }
    public void addNewGuild(String guildid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO prefixs(guildid, prefix) VALUES(" + guildid + ",';')";

        try {
            conn = SQLConnection.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            SQLConnection.getInstance().SQLError(e.getMessage());
        }finally {
            SQLConnection.getInstance().closeConnection(conn);
            SQLConnection.getInstance().closePreparedStatement(pstmt);
        }
    }
    public void removeGuild(String guildid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE from prefixs WHERE guildid = '" + guildid + "'";

        try {
            conn = SQLConnection.getInstance().getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            SQLConnection.getInstance().SQLError(e.getMessage());
        }finally {
            SQLConnection.getInstance().closeConnection(conn);
            SQLConnection.getInstance().closePreparedStatement(pstmt);
        }
    }
    public static PrefixManager getInstance() {
        if (instance == null) {
            instance = new PrefixManager();
        }
        return instance;
    }
}
