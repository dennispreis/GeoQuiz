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

import GameManager.CategoryMap;
import GameManager.LevelMap;
import processing.core.PApplet;

/**
 * @author User
 */
public class MyPracticeDao extends MySqlDao implements PracticeDaoInterface
{

    CategoryMap cm = new CategoryMap();
    LevelMap lm = new LevelMap();
    private PaperDaoInterface IPaperDao = new MyPaperDao();

    @Override
    public List<Question> getPractice(PApplet applet, int id, String category, String level)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getRandPaper(applet);
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category,level) VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.setString(4, level);
            ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return p.getQuestions();
    }

    public List<Question> getPracticeByType(PApplet applet, String type, int id, String category, String level)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByType(applet, type);
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category,level) VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.setString(4, level);
            ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return p.getQuestions();
    }

    @Override
    public List<Question> getPracticeByRegion(PApplet applet, String region, int id, String category, String level)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByRegion(applet, region);
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category,level) VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.setString(4, level);
            ps.executeUpdate();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return p.getQuestions();
    }

    @Override
    public List<Question> getPracticeByTypeRegion(PApplet applet, String type, String region, int id, String category, String level)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByTypeRegion(applet, type, region);
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category,level) VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.setString(4, level);
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
                String query = "SELECT category,level,score,data_attempt FROM practices WHERE student_id = ? ORDER BY data_attempt DESC";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                //Using a PreparedStatement to execute SQL...
                rs = ps.executeQuery();
                int idx = 0;
                while (rs.next())
                {

                    String category = rs.getString("category");
                    String level = rs.getString("level");
                    int score = rs.getInt("score");
                    Date date_attempt = rs.getDate("data_attempt");

                    HistoryRecord h = new HistoryRecord(idx, cm.getCategory(category.toLowerCase()), lm.getLevel(level.toLowerCase()), score, date_attempt);
                    ph.getHistoryRecord().add(h);
                    idx++;
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
    public ProfileHistory getPracticeProfileHistoryByClass(String className)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ProfileHistory ph = new ProfileHistory(10);
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            String query = "SELECT `class_name`, `student_name`, `practice_id`, `category`, `level`, `score`, `data_attempt` FROM `class_view_practice` WHERE class_name = ? ORDER BY data_attempt DESC";
            ps = con.prepareStatement(query);
            ps.setString(1, className);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            int idx = 0;
            while (rs.next())
            {
                String studentName = rs.getString("student_name");
                String category = rs.getString("category");
                String level = rs.getString("level");
                int score = rs.getInt("score");
                Date date_attempt = rs.getDate("data_attempt");

                HistoryRecord h = new HistoryRecord(studentName, idx, cm.getCategory(category.toLowerCase()), lm.getLevel(level.toLowerCase()), score, date_attempt);
                ph.getHistoryRecord().add(h);
                idx++;
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

    @Override
    public boolean updateScore(int id, int score)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        try
        {

            conn = this.getConnection();
            String query = "UPDATE practices SET score = ? WHERE practice_id = ?";
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
}
