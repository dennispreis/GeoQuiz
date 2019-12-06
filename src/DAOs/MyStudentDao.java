/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Student;

import java.sql.*;
import java.util.ArrayList;

import static processing.core.PApplet.println;

/**
 * @author User
 */
public class MyStudentDao extends MySqlDao implements StudentDaoInterface {

    private Connection connection;
    private ResultSet resultSet;
    private Statement statement;

    @Override
    public boolean isAccountExisting(String loginName, String loginPassword) {
        try {
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
    public int getAccountId(String loginName, String loginPassword) {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();
            String query = ("SELECT account_id FROM student_accounts WHERE username = '" + loginName + "' AND passcode ='" + loginPassword + "' limit 1");

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return resultSet.getInt("account_id");
            }

            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Student createStudentUser(int ID) {
        try {
            connection = this.getConnection();
            statement = connection.createStatement();

            String query = "select account_id, username,class_id,nickname,avatar from student_accounts sa JOIN students s ON sa.account_id = s.student_id where account_id = " + ID + "";

            statement.executeQuery(query);
            resultSet = statement.getResultSet();
            resultSet.next();

            int id = resultSet.getInt("account_id");
            String name = resultSet.getString("username");
            int class_id = resultSet.getInt("class_id");
            String avatar = resultSet.getString("avatar");
            String nickname = resultSet.getString("nickname");

            return new Student(class_id, nickname, id, name, avatar);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
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

    @Override
    public boolean saveStudent(Student s) {
        boolean success = false;
        try {
            connection = this.getConnection();

            String query = "UPDATE students SET class_id = ?, student_name = ? , nickname = ? , avatar = ? WHERE student_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, s.getClass_id());
            ps.setString(2, s.getName());
            ps.setString(3, s.getNickname());
            ps.setString(4, s.getAvatar());
            ps.setInt(5, s.getId());
            ps.executeQuery(query);
            success = (ps.executeUpdate() == 1);
        } catch (SQLException e) {
        }
        return success;
    }

    @Override
    public boolean saveStudentNickName(Student s) {
        boolean success = false;
        try {
            connection = this.getConnection();

            String query = "UPDATE students SET nickname = ? WHERE student_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, s.getNickname());
            ps.setInt(2, s.getId());
            success = (ps.executeUpdate() == 1);
        } catch (SQLException e) {
        }
        return success;
    }

    @Override
    public boolean saveStudentAvatar(Student s) {
        boolean success = false;
        try {
            connection = this.getConnection();
            String query = "UPDATE students SET avatar = ? WHERE student_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, s.getAvatar());
            ps.setInt(2, s.getId());
            success = (ps.executeUpdate() == 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }
}
