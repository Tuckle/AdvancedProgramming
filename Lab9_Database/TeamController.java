package AdvancedProgramming.Lab9_Database;

import java.sql.*;
import java.util.Random;

/**
 * Created by apiriu on 5/15/2017.
 */
public class TeamController {
    private Random randomGenerator = new Random();
    private String insertStmt = "insert into teams(name) values (?)";
    private String inputCharacters = "qwertyuiopasdfghjklzxcvbnmQWERTYUaeIouUIOPASDFGHJKLZXCVBNM";

    public void create(String teamName) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement("insert into teams (name) values (?)")) {
            pstmt.setString(1, teamName);
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

    public Integer findByName(String teamName) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id from teams where name='" + teamName + "'");
            Integer id = rs.next() ? rs.getInt(1) : null;
            rs.close();
            return id;
        }
    }

    public void generateRandomTeams(Integer numberOfTeams) {
        for(Integer i = 0; i < numberOfTeams; i++){
            try {
                create(getRandomString(inputCharacters, randomGenerator.nextInt(50)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
