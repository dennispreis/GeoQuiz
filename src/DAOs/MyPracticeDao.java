/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.HistoryRecord;
import DTOs.Paper;
import DTOs.Practice;
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
    public Practice getPractice(PApplet applet, int id, String category)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByType(applet,category);
        int insertId = 0;
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category) VALUES (?,?,?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.executeUpdate();
            
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                }
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return (new Practice(insertId,p));
    }

    public Practice getPracticeByType(PApplet applet, String type, int id, String category)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByType(applet, type);
         int insertId = 0;
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category) VALUES (?,?,?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.executeUpdate();
            
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                }
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return (new Practice(insertId,p));
    }

    @Override
    public Practice getPracticeByRegion(PApplet applet, String region, int id, String category)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByRegion(applet, region);
        int insertId = 0;
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category) VALUES (?,?,?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.executeUpdate();
            
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                }
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return (new Practice(insertId,p));
    }

    @Override
    public Practice getPracticeByTypeRegion(PApplet applet, String type, String region, int id, String category)
    {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getPaperByTypeRegion(applet, type, region);
         int insertId = 0;
        try
        {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category) VALUES (?,?,?)";
            ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.executeUpdate();
            
            
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                }
            }
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return (new Practice(insertId,p));
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
                String query = "SELECT category,score,data_attempt FROM practices WHERE student_id = ? ORDER BY data_attempt DESC";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                //Using a PreparedStatement to execute SQL...
                rs = ps.executeQuery();
                int idx = 0;
                while (rs.next())
                {

                    String category = rs.getString("category");
                    int score = rs.getInt("score");
                    Date date_attempt = rs.getDate("data_attempt");

                    HistoryRecord h = new HistoryRecord(idx, cm.getCategory(category.toLowerCase()), score, date_attempt);
                    ph.getHistoryRecord().add(h);
                    idx++;
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
            String query = "SELECT `class_name`, `student_name`, `practice_id`, `category`, `score`, `data_attempt` FROM `class_view_practice` WHERE class_name = ? ORDER BY data_attempt DESC";
            ps = con.prepareStatement(query);
            ps.setString(1, className);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            int idx = 0;
            while (rs.next())
            {
                String studentName = rs.getString("student_name");
                String category = rs.getString("category");
                int score = rs.getInt("score");
                Date date_attempt = rs.getDate("data_attempt");

                HistoryRecord h = new HistoryRecord(studentName, idx, cm.getCategory(category.toLowerCase()),  score, date_attempt);
                ph.getHistoryRecord().add(h);
                idx++;
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

    @Override
    public boolean updateScore(int id, int score,String[] answers)
    {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        try
        {

            conn = this.getConnection();
            String query = "UPDATE practices SET score = ?,answers = ? WHERE practice_id = ?";
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
}
