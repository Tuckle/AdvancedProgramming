package AdvancedProgramming.Lab9_Database;

import java.sql.*;

/**
 * Created by apiriu on 5/15/2017.
 */
public class TeamController {
    public void create(String teamName) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into teams (name) values (?)")) {
            pstmt.setString(1, teamName);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String teamName) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id from teams where name='" + teamName + "'");
            Integer id = rs.next() ? rs.getInt(1) : null;
            rs.close();
            return id;
        }
    }
}
