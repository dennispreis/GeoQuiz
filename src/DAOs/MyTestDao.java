/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.HistoryRecord;
import DTOs.ProfileHistory;
import DTOs.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public class MyTestDao extends MySqlDao implements TestDaoInterface
{

    @Override
    public List<Question> getTestByID(PApplet applet, int id, int paper_id)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                    
                    HistoryRecord h = new HistoryRecord(test_id,date_attempt);
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
