package AdvancedProgramming.Lab9_Database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

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
    public void displayTeamRankByMatchesPlayed() {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            Map<Integer, Integer> team_ids = new HashMap<Integer, Integer>();

            ResultSet rs = stmt.executeQuery("select team_id1 from matches");
            Integer teamId = rs.next() ? rs.getInt(1) : null;
            while(teamId != null && teamId > 0) {
                if(team_ids.containsKey(teamId)){
                    team_ids[teamId]++;
                }
                else {
                    team_ids[teamId] = 1;
                }
                teamId = rs.next() ? rs.getInt(1) : null;
            }

            rs = stmt.executeQuery("select team_id2 from matches");
            teamId = rs.next() ? rs.getInt(1) : null;
            while(teamId != null && teamId > 0) {
                if(team_ids.containsKey(teamId)){
                    team_ids[teamId]++;
                }
                else {
                    team_ids[teamId] = 1;
                }
                teamId = rs.next() ? rs.getInt(1) : null;
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
