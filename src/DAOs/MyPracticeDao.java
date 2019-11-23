/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.HistoryRecord;
import DTOs.ProfileHistory;
import DTOs.Question;
import GameManager.Category;
import GameManager.CategoryMap;
import GameManager.Level;
import GameManager.LevelMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public class MyPracticeDao extends MySqlDao implements PracticeDaoInterface
{
    CategoryMap cm = new CategoryMap();
    LevelMap lm = new LevelMap();
    private QuestionDaoInterface IQuestionDao = new MyQuestionDao();

    @Override
    public List<Question> getPractice(PApplet applet)
    {
        return IQuestionDao.getRandQuestion(applet);
    }

    @Override
    public List<Question> getPracticeByType(PApplet applet, String type)
    {
        return IQuestionDao.getQuestionByType(applet, type);
    }

    @Override
    public List<Question> getPracticeByRegion(PApplet applet, String region)
    {
        return IQuestionDao.getQuestionByRegion(applet, region);
    }

    @Override
    public List<Question> getPracticeByTypeRegion(PApplet applet, String type, String region)
    {
        return IQuestionDao.getQuestionByTypeRegion(applet, type, region);
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
                String query = "SELECT category,level,data_attempt FROM practices WHERE student_id = ?";
                ps = con.prepareStatement(query);
                ps.setInt(1, id);
                //Using a PreparedStatement to execute SQL...
                rs = ps.executeQuery();
                while (rs.next())
                {

                    String category = rs.getString("category");
                    String level = rs.getString("level");
                    Date date_attempt = rs.getDate("data_attempt");

                    
                    HistoryRecord h = new HistoryRecord(cm.categoryMap.get(category), lm.levelMap.get(level), date_attempt);
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

}
