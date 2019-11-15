/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static processing.core.PApplet.println;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MyUserDao extends MySqlDao implements UserDaoInterface
{

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    @Override
    public boolean isAccountExisting(String loginName, String loginPassword)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();

            String query = "select username, passcode from accounts where username = '" + loginName + "' AND passcode ='" + loginPassword + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        println("Account not found: " + loginName + ":" + loginPassword);
        return false;
    }

    @Override
    public int getAccountId(String loginName, String loginPassword, boolean isTeacher) {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = (isTeacher
                    ? "SELECT teacher_account_id FROM teacher_accounts WHERE username = '" + loginName + "' limit 1"
                    : "SELECT account_id FROM student_accounts WHERE username = '" + loginName + "' AND passcode ='" + loginPassword + "' limit 1");

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return isTeacher ? resultSet.getInt("teacher_account_id") : resultSet.getInt("account_id");
            }

            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String getHash(String loginName) {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT hash from teacher_accounts WHERE username = '" + loginName + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("hash");
            }

            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String getDateTime(String loginName) {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT attempts FROM teacher_accounts WHERE username = '" + loginName + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getString("attempts");
            }

            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void setDateTime(String loginName, String dateTime) {
        try {
            connection = this.getConnection();

            String query = "UPDATE teacher_accounts SET attempts = '" + dateTime + "' WHERE username = '" + loginName + "'";
            statement = connection.prepareStatement(query);
            statement.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getUsernames() {
        return null;
    }

    public User createStudentUser(int ID) {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();

            String query = "select account_id, username from student_accounts where account_id = " + ID + "";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            resultSet.next();

            int id = resultSet.getInt("account_id");
            String name = resultSet.getString("username");
            int avatarIndex = 1;

            return new User(id, name, avatarIndex);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public User createTeacherUser(int id) {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();

            String query = "SELECT teacher_account_id, username FROM teacher_accounts WHERE teacher_account_id = " + id + "";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            resultSet.next();
            return new User(resultSet.getInt("teacher_account_id"), resultSet.getString("username"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //-----------Created by Dennis (13.11.2019);

    public ArrayList<String> getTeacherUsernames() {
        ArrayList<String> userArray = new ArrayList<>();
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT username FROM teacher_accounts";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                userArray.add(resultSet.getString("username"));
            }
            return userArray;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<String> getStudentUsernames() {
        ArrayList<String> userArray = new ArrayList<>();
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT username FROM student_accounts";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();

            while (resultSet.next()) {
                userArray.add(resultSet.getString("username"));
            }
            return userArray;
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
