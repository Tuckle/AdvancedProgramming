package AdvancedProgramming.Lab9_Database;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by apiriu on 5/15/2017.
 */
public class MatchController {
    private String insertMatchStmt = "insert into matches(match_date, team_id1, team_id2, goals1, goals2) " +
            "values(?, ?, ?, ?, ?)";
    private String getTeamsQuery = "select id from teams";
    private String getGoalsQuery = "select id from goals";
    private Random randomGenerator = new Random();

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
            con.commit();
        }
    }

    private Date getRandomDate() {
        long ms;
        ms = -946771200000L + (Math.abs(randomGenerator.nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
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
            Map<Integer, Integer> team_ids;
            team_ids = new HashMap<Integer, Integer>();
            ResultSet rs = stmt.executeQuery("select team_id1, team_id2, goals1, goals2 from matches");
            rs.next();
            Integer teamId1 = rs.getInt(1);
            Integer teamId2 = rs.getInt(2);
            Integer goals1 = rs.getInt(3);
            Integer goals2 = rs.getInt(4);
            while(teamId1 != null && teamId1 > 0) {
                if(team_ids.containsKey(teamId1)){
                    team_ids.put(teamId1, team_ids.get(teamId1) + goals1);
                }
                else {
                    team_ids.put(teamId1, goals1);
                }
                if(team_ids.containsKey(teamId2)){
                    team_ids.put(teamId2, team_ids.get(teamId2) + goals2);
                }
                else {
                    team_ids.put(teamId2, goals2);
                }
                if(!rs.next()){
                    break;
                }
                teamId1 = rs.getInt(1);
                teamId2 = rs.getInt(2);
                goals1 = rs.getInt(3);
                goals2 = rs.getInt(4);
            }
            Map<Integer, List<Integer>> goalsGivenBytTeams = new HashMap<Integer, List<Integer>>();
            for(Integer key : team_ids.keySet()) {
                if(goalsGivenBytTeams.containsKey(team_ids.get(key))){
                    List<Integer> temp = goalsGivenBytTeams.get(team_ids.get(key));
                    temp.add(key);
                    goalsGivenBytTeams.put(team_ids.get(key), temp);
                }
                else {
                    List<Integer> temp = new ArrayList<Integer>();
                    temp.add(key);
                    goalsGivenBytTeams.put(team_ids.get(key), temp);
                }
            }
            List<Integer> keyList = new ArrayList<>(goalsGivenBytTeams.keySet());
            Collections.sort(keyList);
            Collections.reverse(keyList);
            for(Integer key : keyList) {
                List<Integer> teamsWithGoals = goalsGivenBytTeams.get(key);
                for(Integer team : teamsWithGoals) {
                    System.out.println("Team " + team);
                }
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateRandomMatches(Integer numberOfMatches){
        List<Integer> teamsIds = Database.getQueryList(getTeamsQuery);
        List<Integer> goalsIds = Database.getQueryList(getGoalsQuery);
        Integer firstTeam, secondTeam, firstTeamsGoals, secondTeamsGoals;

        for(Integer i = 0; i < numberOfMatches; i++){
            firstTeam = randomGenerator.nextInt(teamsIds.size());
            secondTeam = randomGenerator.nextInt(teamsIds.size());
            firstTeamsGoals = randomGenerator.nextInt(goalsIds.size());
            secondTeamsGoals = randomGenerator.nextInt(goalsIds.size());
            try {
                create(getRandomDate(), firstTeam, secondTeam, firstTeamsGoals, secondTeamsGoals);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
