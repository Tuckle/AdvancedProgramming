package AdvancedProgramming.Lab9_Database;

import java.sql.SQLException;

/**
 * Created by apiriu on 5/15/2017.
 */
public class DBMain {
    public static void main(String[] args) {
        try {
            TeamController teams = new TeamController();
            PlayerController players = new PlayerController();

            teams.create("FC Barcelona");
            teams.create("Real Madrid");
            Database.commit();

            int fcbId = teams.findByName("FC Barcelona");
            players.create("Lionel Messi", fcbId);
            players.create("Neymar", fcbId);
            players.create("Luis Suarez", fcbId);
            players.list(fcbId); //displays all the players from the specified team
            Database.commit();
        } catch (SQLException e) {
            System.err.println(e.toString());
            Database.rollback();
        } finally {
            Database.closeConnection();
        }
    }
}
