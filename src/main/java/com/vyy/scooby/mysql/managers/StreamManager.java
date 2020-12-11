package com.vyy.scooby.mysql.managers;

import com.vyy.scooby.mysql.SQLConnection;

import java.sql.*;

public class StreamManager {
    public static StreamManager instance;
    public StreamManager() {
        createStreamTable();
    }
    public void createStreamTable() {
        Connection conn = null;
        Statement stmt = null;
        String sql = "CREATE TABLE IF NOT EXISTS \"stream\" (\n" +
                "\t\"guildid\"\tTEXT,\n" +
                "\t\"toggle\"\tTEXT,\n" +
                "\t\"channelid\"\tTEXT\n" +
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
    public String getStreamToggle(String guildid) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String sql = "SELECT * FROM stream";
        try {
            conn = SQLConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
                if (result.getString("guildid").equalsIgnoreCase(guildid)) {
                    return result.getString("toggle");
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
    public void setStreamToggle(String guildid, String toggle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE stream SET toggle = '" + toggle + "' WHERE guildid = '" + guildid + "'";

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
    public void setStreamChannel(String guildid, String channelid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE stream SET channelid = '" + channelid + "' WHERE guildid = '" + guildid + "'";

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
    public String getStreamChannel(String guildid) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        String sql = "SELECT * FROM stream";
        try {
            conn = SQLConnection.getInstance().getConnection();
            stmt = conn.createStatement();
            result = stmt.executeQuery(sql);

            while (result.next()) {
                if (result.getString("guildid").equalsIgnoreCase(guildid)) {
                    return result.getString("channelid");
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
    public void addNewGuild(String guildid) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO stream(guildid, toggle, channelid) VALUES(" + guildid + ",'false','null')";

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
        String sql = "DELETE from stream WHERE guildid = '" + guildid + "'";

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
    public static StreamManager getInstance() {
        if (instance == null) {
            instance = new StreamManager();
        }
        return instance;
    }
}

