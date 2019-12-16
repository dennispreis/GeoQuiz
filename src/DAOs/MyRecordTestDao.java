/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author User
 */
public class MyRecordTestDao extends MySqlDao implements RecordDaoInterface
{

    @Override
    public void storeAnswer(int recordId, ArrayList<Integer> questionId, ArrayList<String> answers)
    {
          Connection conn = null;
        PreparedStatement ps = null;
        int insertId = 0;
        try
        {
            conn = this.getConnection();
            String query = "INSERT INTO `test_result` (`record_id`, `question_id`, `answer`) VALUES (?, ?, ?)";
            for (int i = 0; i < questionId.size(); i++)
            {
                ps = conn.prepareStatement(query);
                ps.setInt(1, recordId);
                ps.setInt(2, questionId.get(i));
                ps.setString(3, answers.get(i));
                ps.executeUpdate();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public Map<Integer, String> getAnswerList(int recordId)
    {
         Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<Integer,String> toReturn = new HashMap<>();
        try
        {
            conn = this.getConnection();
            String query = "SELECT `record_id`, `question_id`, `answer` FROM `test_result` WHERE record_id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, recordId);
            rs = ps.executeQuery();
            while(rs.next())
            {
                toReturn.put(rs.getInt("question_id"), rs.getString("answer"));
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return toReturn;
    }
}
