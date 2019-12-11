/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.HistoryRecord;

import DTOs.Paper;
import DTOs.ProfileHistory;
import DTOs.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public class MyTestDao extends MySqlDao implements TestDaoInterface
{

    private PaperDaoInterface IPaperDao = new MyPaperDao();

    @Override
    public List<Question> getTestByID(PApplet applet, int id, int paper_id)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByID(applet, paper_id);
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO test (paper_id,student_id) VALUES (?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return p.getQuestions();

    }

    @Override
    public ProfileHistory getProfileHistory(int id)
    {
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            ProfileHistory ph = new ProfileHistory();
            try
            {
                //Get connection object using the methods in the super class (MySqlDao.java)...
                con = this.getConnection();
                String query = "SELECT test_id,data_attempt FROM tests WHERE student_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                //Using a PreparedStatement to execute SQL...
                rs = ps.executeQuery();
                while (rs.next())
                {
                    int test_id = rs.getInt("test_id");
                    Date date_attempt = rs.getDate("data_attempt");

                    HistoryRecord h = new HistoryRecord(test_id, date_attempt);
                    ph.getHistoryRecord().add(h);
                }
            } catch (SQLException e)
            {

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
            return ph;     // may be empty  }
        }

    }

    @Override
    public boolean updateScore(int id, int score)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        try
        {
            conn = this.getConnection();
            String query = "UPDATE tests SET score = ? WHERE test_id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, score);
            ps.setInt(2, id);
            return (ps.executeUpdate() == 1);

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void addTest(String test_name, List<Question> questionList)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        int insertId = IPaperDao.addNewPaper(questionList);
        try
        {
            
            conn = this.getConnection();
            String query = "INSERT INTO tests (test_name,paper_id) VALUES(?,?)";
            ps = conn.prepareStatement(query);
            ps.setString(1,test_name );
            ps.setInt(2, insertId);
            ps.executeUpdate();

           
           
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
