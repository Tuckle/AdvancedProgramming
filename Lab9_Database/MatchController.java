package AdvancedProgramming.Lab9_Database;

import java.sql.*;

/**
 * Created by apiriu on 5/15/2017.
 */
public class MatchController {
    public void create(Date matchDate, Integer firstTeam, Integer secondTeam,
                       Integer firstTeamsGoals, Integer secondTeamsGoals) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into matches (match_date, team_id1, team_id2, goals1, goals2) " +
                "values (?, ?, ?, ?, ?)")) {
            pstmt.setDate(1, matchDate);
            pstmt.setInt(2, firstTeam);
            pstmt.setInt(3, secondTeam);
            pstmt.setInt(4, firstTeamsGoals);
            pstmt.setInt(5, secondTeamsGoals);
            pstmt.executeUpdate();
        }
    }
    public Integer findByDate(Date inputDate) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id from matches where date='" + inputDate + "'");
            Integer id = rs.next() ? rs.getInt(1) : null;
            rs.close();
            return id;
        }
    }

}
