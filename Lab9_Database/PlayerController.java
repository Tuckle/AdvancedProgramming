package AdvancedProgramming.Lab9_Database;

import java.sql.*;
import java.util.List;
import java.util.Random;

/**
 * Created by apiriu on 5/15/2017.
 */
public class PlayerController {
    private String inputCharacters = "qwertyuiopasdfghjklzxcvbnmQWERTYUaeIouUIOPASDFGHJKLZXCVBNM";
    private String getMatchesQuery = "select id from teams";
    private Random randomGenerator = new Random();

    public void create(String playerName, int playerTeam) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into players (name, team_id) values (?, ?)")) {
            pstmt.setString(1, playerName);
            pstmt.setInt(2, playerTeam);
            pstmt.executeUpdate();
        }
        con.commit();
    }

    private String getRandomString(String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(randomGenerator.nextInt(characters.length()));
        }
        return new String(text);
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
            ResultSet rs = stmt.executeQuery("select NAME from players where team_id='" + fcbId + "'");
            String name = rs.next() ? rs.getString(1) : null;
            System.out.println(name);
            while(name != null && name.length() > 0) {
                System.out.println(name);
                name = rs.next() ? rs.getString(1) : null;
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateRandomPlayers(Integer numberOfGoals) {
        List<Integer> teamsIds = Database.getQueryList(getMatchesQuery);
        Integer teamId;

        for(Integer i = 0; i < numberOfGoals; i++) {
            teamId = teamsIds.get(randomGenerator.nextInt(teamsIds.size()));
            try {
                create(getRandomString(inputCharacters, randomGenerator.nextInt(50)), teamId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
