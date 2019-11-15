/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class MyQuestionDao extends MySqlDao implements QuestionDaoInterface
{

    @Override
    public List<Question> getPractice()
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM questions ORDER BY RAND() LIMIT 10";
            ps = con.prepareStatement(query);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("question_id");
                String question_type = rs.getString("question_type");
                String type = rs.getString("type");
                String region = rs.getString("region");
                String q_t = rs.getString("question_text");
                String a_1 = rs.getString("answer_one");
                String a_2 = rs.getString("answer_two");
                String a_3 = rs.getString("answer_three");
                String a_4 = rs.getString("answer_four");
                String c_a = rs.getString("correct_answer");
                Question u = new Question(id, question_type, type, region, q_t, a_1, a_2, a_3, a_4, c_a);
                questions.add(u);
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
        return questions;     // may be empty
    }

    @Override
    public List<Question> getPracticeByType(String typeIn)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM questions WHERE type = ? ORDER BY RAND()";
            ps = con.prepareStatement(query);
            ps.setString(1, typeIn);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("question_id");
                String question_type = rs.getString("question_type");
                String type = rs.getString("type");
                String region = rs.getString("region");
                String q_t = rs.getString("question_text");
                String a_1 = rs.getString("answer_one");
                String a_2 = rs.getString("answer_two");
                String a_3 = rs.getString("answer_three");
                String a_4 = rs.getString("answer_four");
                String c_a = rs.getString("correct_answer");
                Question u = new Question(id, question_type, type, region, q_t, a_1, a_2, a_3, a_4, c_a);
                questions.add(u);
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
        return questions;     // may be empty
    }

    @Override
    public List<Question> getPracticeByRegion(String regionIn)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM questions WHERE region = ? ORDER BY RAND()";
            ps = con.prepareStatement(query);
            ps.setString(1, regionIn);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("question_id");
                String question_type = rs.getString("question_type");
                String type = rs.getString("type");
                String region = rs.getString("region");
                String q_t = rs.getString("question_text");
                String a_1 = rs.getString("answer_one");
                String a_2 = rs.getString("answer_two");
                String a_3 = rs.getString("answer_three");
                String a_4 = rs.getString("answer_four");
                String c_a = rs.getString("correct_answer");
                Question u = new Question(id, question_type, type, region, q_t, a_1, a_2, a_3, a_4, c_a);
                questions.add(u);
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
        return questions; // may be empty
    }

    @Override
    public List<Question> getPracticeByLevel()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addQuestion(Question q)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateQuestion(int id, String field, String value)
    {
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;
        try
        {

            con = this.getConnection();
            String query = "UPDATE `questions` SET " + field + " = ? WHERE `questions`.`question_id` = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, value);
            ps.setInt(2, id);
            success = (ps.executeUpdate() == 1);
        } catch (SQLException e)
        {

        } finally
        {
            try
            {
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
                System.out.println("Delete Question" + e.getMessage());
            }
        }
        return success;
    }

    @Override
    public boolean deleteQuestion(int id)
    {
        Connection con = null;
        PreparedStatement ps = null;
        boolean success = false;
        try
        {

            con = this.getConnection();
            String query = "DELETE FROM `questions` WHERE `questions`.`question_id` = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            success = (ps.executeUpdate() == 1);
        } catch (SQLException e)
        {

        } finally
        {
            try
            {
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
                System.out.println("Delete Question" + e.getMessage());
            }
        }
        return success;
    }

}
