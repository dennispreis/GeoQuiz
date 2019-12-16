/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class MyTestStudentDao extends MySqlDao
{

    public int checkAttempt(int student_id, int test_id)
    {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int score = 0;
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            String query = "SELECT `score` FROM `tests_students` WHERE student_id = ? AND test_id = ?";
            ps = con.prepareStatement(query);
            //Using a PreparedStatement to execute SQL...
            ps.setInt(1, student_id);
            ps.setInt(2,test_id);
            rs = ps.executeQuery();
            while (rs.next())
            {
               score = rs.getInt("score");
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {

            }
        }
        return score;
    }
}
