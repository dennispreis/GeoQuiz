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

            if (resultSet.next())
            {
                return true;
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        println("Account not found: " + loginName + ":" + loginPassword);
        return false;
    }

    @Override
    public int getAccountId(String loginName, String loginPassword, boolean isTeacher)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = (isTeacher
                    ? "select teacher_id from teacher_accounts where username = '" + loginName + "' AND passcode ='" + loginPassword + "' limit 1"
                    : "select account_id from accounts where username = '" + loginName + "' AND passcode ='" + loginPassword + "' limit 1");

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next())
            {
                return resultSet.getInt("account_id");
            }

            return -1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public User createStudentUser(int ID)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();

            String query = "select account_id, username from accounts where account_id = " + ID + "";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            resultSet.next();
            return new User(resultSet.getInt("account_id"), resultSet.getString("username"));

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public User createTeacherUser(int id)
    {
        return null;
    }
}
