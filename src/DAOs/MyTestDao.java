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
import DTOs.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    public ProfileHistory getProfileHistory(String class_name)
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
                String query = "SELECT `student_name`, `test_id`, `TestName`, `score`, `date_attempt` FROM `class_view_test` WHERE testName= ?";
                ps = con.prepareStatement(query);
                ps.setString(1, class_name);
                //Using a PreparedStatement to execute SQL...
                rs = ps.executeQuery();
                while (rs.next())
                {
                    int test_id = rs.getInt("test_id");
                    String student_name = rs.getString("student_name");
                    String test_name = rs.getString("TestName");
                    int score = rs.getInt("score");
                    Date date_attempt = rs.getDate("data_attempt");

                    HistoryRecord h = new HistoryRecord(student_name, test_name, test_id, score, date_attempt);
                    ph.getHistoryRecord().add(h);
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
            return ph;     // may be empty  }
        }

    }

    @Override
    public boolean updateScore(int id, int score,String[] answers)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        try
        {
            conn = this.getConnection();
            String query = "UPDATE tests SET score = ?,answers = ? WHERE test_id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, score);
            ps.setString(2, answers.toString());
            ps.setInt(3, id);
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
            ps.setString(1, test_name);

            ps.setInt(2, insertId);
            ps.executeUpdate();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Test> getAllTest()
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Test> testList = new ArrayList<>();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            String query = "SELECT `test_id`, `test_name`, `paper_id` FROM `tests`";
            ps = con.prepareStatement(query);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int test_id = rs.getInt("test_id");
                String test_name = rs.getString("test_name");
                int paper_id = rs.getInt("paper_id");

                testList.add(new Test(test_id, test_name, paper_id));
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
        return testList;     // may be empty  }
    }

    @Override
    public List<Question> attemptTest(PApplet applet,int student_id, int test_id)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Test t = this.getTestObjectById(test_id);
        Paper p = IPaperDao.getPaperByID(applet,t.getPaper_id());
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO tests_students (test_id,student_id) VALUES (?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, test_id);
            ps.setInt(2, student_id);
            ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return p.getQuestions();
    }

    @Override
    public Test getTestObjectById(int test_id)
    {
      Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Test t=null;
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            String query = "SELECT `test_id`, `test_name`, `paper_id` FROM `tests` WHERE test_id = ?";
            ps = con.prepareStatement(query);
            //Using a PreparedStatement to execute SQL...
            ps.setInt(1, test_id);
            rs = ps.executeQuery();
            while (rs.next())
            {
                String test_name = rs.getString("test_name");
                int paper_id = rs.getInt("paper_id");

               t = new Test(test_id, test_name, paper_id);
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
        return t;  
    }

}
