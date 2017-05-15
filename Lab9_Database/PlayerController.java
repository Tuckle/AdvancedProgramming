package AdvancedProgramming.Lab9_Database;

import java.sql.*;

/**
 * Created by apiriu on 5/15/2017.
 */
public class PlayerController {
    public void create(String playerName, int playerTeam) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into players (name, team_id) values (?, ?)")) {
            pstmt.setString(1, playerName);
            pstmt.setInt(2, playerTeam);
            pstmt.executeUpdate();
        }
    }
    public Integer findByName(String playerName) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id from players where name='" + playerName + "'");
            Integer id = rs.next() ? rs.getInt(1) : null;
            rs.close();
            return id;
        }
    }

    public void list(int fcbId) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id from players where team_id='" + fcbId + "'");
            String id = rs.next() ? rs.getString(1) : null;
            System.out.println(id + '\n');
            while(id != null && id.length() > 0) {
                id = rs.next() ? rs.getString(1) : null;
                System.out.println(id + '\n');
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
