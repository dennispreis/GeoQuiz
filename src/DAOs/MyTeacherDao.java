/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class MyTeacherDao extends MySqlDao implements TeacherDaoInterface
{

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    @Override
    public int getAccountId(String loginName)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = ("SELECT teacher_account_id FROM teacher_accounts WHERE username = '" + loginName + "' limit 1");

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next())
            {
                return resultSet.getInt("teacher_account_id");
            }

            return -1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public String getHash(String loginName)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT hash from teacher_accounts WHERE username = '" + loginName + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next())
            {
                return resultSet.getString("hash");
            }

            return "";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public String getLastDateTime(String loginName)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT lastSession FROM teacher_accounts WHERE username = '" + loginName + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next())
            {
                return resultSet.getString("lastSession");
            }

            return "";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void setLastDateTime(String loginName, String dateTime)
    {
        try
        {
            connection = this.getConnection();

            String query = "UPDATE teacher_accounts SET lastSession = '" + dateTime + "' WHERE username = '" + loginName + "'";
            statement = connection.prepareStatement(query);
            statement.executeUpdate(query);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public String getInvalidDateTime(String loginName)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT invalidTime FROM teacher_accounts WHERE username = '" + loginName + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next())
            {
                return resultSet.getString("invalidTime");
            }

            return "";
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void setInvalidDateTime(String loginName, String dateTime)
    {
        try
        {
            connection = this.getConnection();

            String query = "UPDATE teacher_accounts SET invalidTime = '" + dateTime + "' WHERE username = '" + loginName + "'";
            statement = connection.prepareStatement(query);
            statement.executeUpdate(query);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getAttempt(String loginName)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT attempt FROM teacher_accounts WHERE username = '" + loginName + "'";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next())
            {
                return resultSet.getInt("attempt");
            }

            return -1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void setAttempt(String loginName, int attempt)
    {
        try
        {
            connection = this.getConnection();

            String query = "UPDATE teacher_accounts SET attempt = '" + attempt + "' WHERE username = '" + loginName + "'";
            statement = connection.prepareStatement(query);
            statement.executeUpdate(query);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public int getPassCode(String loginName)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = ("SELECT passcode FROM teacher_accounts WHERE username = '" + loginName + "' limit 1");

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next())
            {
                return resultSet.getInt("passcode");
            }

            return -1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public Teacher createTeacherUser(int id)
    {
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();

            String query = "SELECT teacher_account_id, username FROM teacher_accounts WHERE teacher_account_id = " + id + "";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            resultSet.next();
            return new Teacher(resultSet.getInt("teacher_account_id"), resultSet.getString("username"));

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //-----------Created by Dennis (13.11.2019);
    public ArrayList<String> getTeacherUsernames()
    {
        ArrayList<String> userArray = new ArrayList<>();
        try
        {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = "SELECT username FROM teacher_accounts";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();

            while (resultSet.next())
            {
                userArray.add(resultSet.getString("username"));
            }
            return userArray;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    
    @Override
    public void setHash(String loginName, String hash)
    {
        try
        {
            connection = this.getConnection();

            String query = "UPDATE teacher_accounts SET hash = '" + hash + "' WHERE username = '" + loginName + "'";
            statement = connection.prepareStatement(query);
            statement.executeUpdate(query);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


}
