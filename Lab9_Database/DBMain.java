package AdvancedProgramming.Lab9_Database;

import jdk.management.resource.internal.ResourceNatives;

import java.sql.SQLException;
import java.util.Random;

/**
 * Created by apiriu on 5/15/2017.
 */
public class DBMain {
    public static void main(String[] args) {
        try {
            TeamController teams = new TeamController();
            PlayerController players = new PlayerController();

//            teams.create("FC Barcelona");
//            teams.create("Real Madrid");
//            Database.commit();
//
//            int fcbId = teams.findByName("FC Barcelona");
//            players.create("Lionel Messi", fcbId);
//            players.create("Neymar", fcbId);
//            players.create("Luis Suarez", fcbId);
//            players.list(fcbId); //displays all the players from the specified team
            TeamController team = new TeamController();
            PlayerController player = new PlayerController();
            GoalController goal = new GoalController();
            MatchController match = new MatchController();
//            team.generateRandomTeams(10);
//            player.generateRandomPlayers(20);
//            Random randomGenerator = new Random();
//            for(int i = 0; i< 10; i++){
//                try {
//                    goal.create(randomGenerator.nextInt(5), randomGenerator.nextInt(10), randomGenerator.nextInt(90),
//                            randomGenerator.nextInt(10));
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            goal.generateRandomGoals(9);
//            match.generateRandomMatches(4);

            match.displayTeamRankByMatchesPlayed();
            Database.commit();
//        } catch (SQLException e) {
//            System.err.println(e.toString());
//            Database.rollback();
        } finally {
            Database.closeConnection();
        }
    }
}
