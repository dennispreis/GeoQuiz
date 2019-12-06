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
import java.util.Date;
import java.util.List;

import GameManager.CategoryMap;
import GameManager.LevelMap;
import processing.core.PApplet;

/**
 * @author User
 */
public class MyPracticeDao extends MySqlDao implements PracticeDaoInterface {
    CategoryMap cm = new CategoryMap();
    LevelMap lm = new LevelMap();
    private PaperDaoInterface IPaperDao = new MyPaperDao();

    @Override
    public List<Question> getPractice(PApplet applet, int id, String category, String level) {
        Connection con = null;
        PreparedStatement ps = null;
        Paper p = IPaperDao.getRandPaper(applet);
        try {
            con = this.getConnection();
            String query = "INSERT INTO practices (paper_id,student_id,category,level) VALUES (?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, p.getId());
            ps.setInt(2, id);
            ps.setString(3, category);
            ps.setString(4, level);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p.getQuestions();
    }

    @Override
    public List<Question> getPracticeByType(PApplet applet, String type, int id, String category, String level) {
        return IPaperDao.getPaperByType(applet, type).getQuestions();
    }

    @Override
    public List<Question> getPracticeByRegion(PApplet applet, String region, int id, String category, String level) {
        return IPaperDao.getPaperByRegion(applet, region).getQuestions();
    }

    @Override
    public List<Question> getPracticeByTypeRegion(PApplet applet, String type, String region, int id, String category, String level) {
        return IPaperDao.getPaperByTypeRegion(applet, type, region).getQuestions();
    }

    @Override
    public ProfileHistory getProfileHistory(int id) {
        {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            ProfileHistory ph = new ProfileHistory();
            try {
                //Get connection object using the methods in the super class (MySqlDao.java)...
                con = this.getConnection();
                String query = "SELECT category,level,data_attempt FROM practices WHERE student_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                //Using a PreparedStatement to execute SQL...
                rs = ps.executeQuery();
                int idx = 0;
                while (rs.next()) {

                    String category = rs.getString("category");
                    String level = rs.getString("level");
                    Date date_attempt = rs.getDate("data_attempt");


                    HistoryRecord h = new HistoryRecord(idx, cm.getCategory(category.toLowerCase()), lm.getLevel(level.toLowerCase()), date_attempt);
                    ph.getHistoryRecord().add(h);
                    idx++;
                }

            } catch (SQLException e) {
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        freeConnection(con);
                    }
                } catch (SQLException e) {
                }
            }
            return ph;     // may be empty  }
        }
    }
}
