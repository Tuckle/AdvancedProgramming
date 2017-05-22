package AdvancedProgramming.Lab9_Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by apiriu on 5/15/2017.
 */
public class GoalController {
    private String insertGoalStmt = "insert into goals(match_id, player_id, minute, penalty) values(?, ?, ?, ?)";
    private String getMatchesQuery = "select id from matches";
    private String getPlayersQuery = "select id from players";
    private Random randomGenerator = new Random();

    public void create(Integer matchId, Integer playerId, Integer minute, Integer penalty) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(insertGoalStmt)) {
            pstmt.setInt(1, matchId);
            pstmt.setInt(2, playerId);
            pstmt.setInt(3, minute);
            pstmt.setInt(4, penalty);
            pstmt.executeUpdate();
        }
        con.commit();
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

    public void generateRandomGoals(Integer numberOfGoals) {
        List<Integer> matchesIds = Database.getQueryList(getMatchesQuery);
        List<Integer> playersIds = Database.getQueryList(getPlayersQuery);
        Integer matchId, playerId, minute, penalty;

        for(Integer i = 0; i < numberOfGoals; i++){
            matchId = matchesIds.get(randomGenerator.nextInt(matchesIds.size()));
            playerId = playersIds.get(randomGenerator.nextInt(playersIds.size()));
            minute = randomGenerator.nextInt(90);
            penalty = randomGenerator.nextInt(10);
            try {
                create(matchId, playerId, minute, penalty);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
