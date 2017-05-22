package AdvancedProgramming.Lab9_Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apiriu on 5/15/2017.
 */
public class Database {
    private static final String URL = "jdbc:derby://localhost:1527/football";
    private static final String USER = "dba";
    private static final String PASSWORD = "sql";
    private static Connection connection = null;
    private Database() { }
    public static Connection getConnection() {
        if (connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe",
                    "student", "STUDENT");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection to database failed. Please try again!");
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean commit() {
        try {
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean rollback() {
        try {
            connection.rollback();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Integer> getQueryList(String inputQuery) {
        List<Integer> resultList = new ArrayList<Integer>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(inputQuery);
            Integer id = rs.next() ? rs.getInt(1) : null;
            resultList.add(id);
            while (id != null && id > 0) {
                id = rs.next() ? rs.getInt(1) : null;
                resultList.add(id);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    //Implement the method createConnection()
    //Implement the method closeConnection()
    //Implement the method commit()
    //Implement the method rollback()
}
