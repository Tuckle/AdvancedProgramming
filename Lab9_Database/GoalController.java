package AdvancedProgramming.Lab9_Database;

import java.sql.*;

/**
 * Created by apiriu on 5/15/2017.
 */
public class GoalController {
    public void create(Integer matchId, Integer playerId, Integer minute, Integer penalty) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into goals (match_id, player_id, minute, penalty)" +
                " values (?, ?, ?, ?)")) {
            pstmt.setInt(1, matchId);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, minute);
            pstmt.setInt(4, penalty);
            pstmt.executeUpdate();
        }
    }
    public Integer findByMinute(Integer minute) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id from goals where minute='" + minute + "'");
            Integer id = rs.next() ? rs.getInt(1) : null;
            rs.close();
            return id;
        }
    }

    public Integer findByMatchAndPlayer(Integer matchid, Integer playerId) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id from goals where match_id='" + matchid + "' and player_id='" + playerId + "'");
            Integer id = rs.next() ? rs.getInt(1) : null;
            rs.close();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
