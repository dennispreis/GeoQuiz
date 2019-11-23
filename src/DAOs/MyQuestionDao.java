/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Question;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author DTOs.User
 */
public class MyQuestionDao extends MySqlDao implements QuestionDaoInterface
{

    @Override
    public List<Question> getRandQuestion(PApplet applet)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            String query = "SELECT question_id,type_id,region_id,question_text,correct_answer FROM questions ORDER BY RAND() LIMIT 10";
            ps = con.prepareStatement(query);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {

                int id = rs.getInt("question_id");
                int type_id = rs.getInt("type_id");
                int region_id = rs.getInt("region_id");
                String q_t = rs.getString("question_text");
                String c_a = rs.getString("correct_answer");

                String query2 = "SELECT region FROM regions WHERE region_id = ?";
                ps = con.prepareStatement(query2);
                ps.setInt(1, region_id);
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                String region = rs2.getString("region");
                String query3 = "SELECT type FROM types WHERE type_id = ?";
                ps = con.prepareStatement(query3);
                ps.setInt(1, type_id);
                rs2 = ps.executeQuery();
                rs2.next();
                String type = rs2.getString("type");
                String query4 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_dd WHERE question_id = ?";
                ps = con.prepareStatement(query4);
                ps.setInt(1, id);
                rs2 = ps.executeQuery();
                if (rs2.next())
                {
                    System.out.println("Here GG");
                    String a1 = rs2.getString("answer_one");
                    String a2 = rs2.getString("answer_two");
                    String a3 = rs2.getString("answer_three");
                    String a4 = rs2.getString("answer_four");
                    DragAndDrop_Question q = new DragAndDrop_Question(applet, id, type, region, q_t, c_a, a1, a2, a3, a4);
                    questions.add(q);
                } else
                {

                    System.out.println("Here GG2");
                    String query5 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_mc WHERE question_id = ?";
                    ps = con.prepareStatement(query5);
                    ps.setInt(1, id);
                    rs2 = ps.executeQuery();
                    if (rs2.next())
                    {
                        String a1 = rs2.getString("answer_one");
                        String a2 = rs2.getString("answer_two");
                        String a3 = rs2.getString("answer_three");
                        String a4 = rs2.getString("answer_four");
                        Multiplichoice_Question q = new Multiplichoice_Question(applet, id, type, region, q_t, c_a, a1, a2, a3, a4);
                        questions.add(q);
                    } else
                    {

                        System.out.println("Here GG3");
                        String query6 = "SELECT answer_one,answer_two,answer_one_dir,answer_two_dir FROM questions_pc WHERE question_id = ?";
                        ps = con.prepareStatement(query6);
                        ps.setInt(1, id);
                        rs2 = ps.executeQuery();
                        if (rs2.next())
                        {
                            String a1 = rs2.getString("answer_one");
                            String a2 = rs2.getString("answer_two");
                            String a3 = rs2.getString("answer_one_dir");
                            String a4 = rs2.getString("answer_two_dir");
                            ChoosePicture_Question q = new ChoosePicture_Question(applet, id, type, region, q_t, c_a, a1, a2, a3, a4);
                            questions.add(q);
                        } else
                        {

                            System.out.println("Here All GG3");
                            String query7 = "SELECT question_id FROM questions_tf WHERE question_id = ?";
                            ps = con.prepareStatement(query7);
                            ps.setInt(1, id);
                            rs2 = ps.executeQuery();
                            if (rs2.next())
                            {
                                TrueOrFalse_Question q = new TrueOrFalse_Question(applet, id, type, region, q_t, c_a);
                                questions.add(q);
                            }
                        }
                    }
                }

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
        return questions;     // may be empty  }
    }

    @Override
    public List<Question> getQuestionByType(PApplet applet, String type)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query0 = "SELECT type_id FROM types WHERE type = ?";
            ps = con.prepareStatement(query0);
            ps.setString(1, type);
            ResultSet rs0 = ps.executeQuery();
            int type_id_in = rs0.getInt("type_id");
            String query = "SELECT question_id,type_id,region_id,question_text,correct_answer FROM questions WHERE type_id =?  ORDER BY RAND() LIMIT 10";
            ps = con.prepareStatement(query);
            ps.setInt(1, type_id_in);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("question_id");
                int type_id = rs.getInt("type_id");
                int region_id = rs.getInt("region_id");
                String q_t = rs.getString("question_text");
                String c_a = rs.getString("correct_answer");

                String query2 = "SELECT region FROM regions WHERE region_id = ?";
                ps = con.prepareStatement(query2);
                ps.setInt(1, region_id);
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                String region = rs2.getString("region");

                String query3 = "SELECT type FROM types WHERE type_id = ?";
                ps = con.prepareStatement(query3);
                ps.setInt(1, type_id);
                rs2 = ps.executeQuery();
                rs2.next();
                String type2 = rs2.getString("type");

                String query4 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_dd WHERE question_id = ?";
                ps = con.prepareStatement(query4);
                ps.setInt(1, id);
                rs2 = ps.executeQuery();
                if (rs2.next())
                {
                    String a1 = rs2.getString("answer_one");
                    String a2 = rs2.getString("answer_two");
                    String a3 = rs2.getString("answer_three");
                    String a4 = rs2.getString("answer_four");
                    DragAndDrop_Question q = new DragAndDrop_Question(applet, id, type2, region, q_t, c_a, a1, a2, a3, a4);
                    questions.add(q);
                } else
                {
                    String query5 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_mc WHERE question_id = ?";
                    ps = con.prepareStatement(query5);
                    ps.setInt(1, id);
                    rs2 = ps.executeQuery();
                    if (rs2.next())
                    {
                        String a1 = rs2.getString("answer_one");
                        String a2 = rs2.getString("answer_two");
                        String a3 = rs2.getString("answer_three");
                        String a4 = rs2.getString("answer_four");
                        Multiplichoice_Question q = new Multiplichoice_Question(applet, id, type2, region, q_t, c_a, a1, a2, a3, a4);
                        questions.add(q);
                    } else
                    {
                        String query6 = "SELECT answer_one,answer_two,answer_one_dir,answer_two_dir FROM questions_pc WHERE question_id = ?";
                        ps = con.prepareStatement(query6);
                        ps.setInt(1, id);
                        rs2 = ps.executeQuery();
                        if (rs2.next())
                        {
                            String a1 = rs2.getString("answer_one");
                            String a2 = rs2.getString("answer_two");
                            String a3 = rs2.getString("answer_one_dir");
                            String a4 = rs2.getString("answer_two_dir");
                            ChoosePicture_Question q = new ChoosePicture_Question(applet, id, type2, region, q_t, c_a, a1, a2, a3, a4);
                            questions.add(q);
                        } else
                        {
                            String query7 = "SELECT question_id FROM question_tf WHERE questions_id = ?";
                            ps = con.prepareStatement(query7);
                            ps.setInt(1, id);
                            rs2 = ps.executeQuery();
                            if (rs2.next())
                            {
                                TrueOrFalse_Question q = new TrueOrFalse_Question(applet, id, type2, region, q_t, c_a);
                                questions.add(q);
                            }
                        }
                    }
                }

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
        return questions;     // may be empty  }
    }

    @Override
    public List<Question> getQuestionByRegion(PApplet applet, String region)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query0 = "SELECT region_id FROM regions WHERE region = ?";
            ps = con.prepareStatement(query0);
            ps.setString(1, region);
            ResultSet rs0 = ps.executeQuery();
            int region_id_in = rs0.getInt("region_id");
            String query = "SELECT question_id,type_id,region_id,question_text,correct_answer FROM questions WHERE region_id =?  ORDER BY RAND() LIMIT 10";
            ps = con.prepareStatement(query);
            ps.setInt(1, region_id_in);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("question_id");
                int type_id = rs.getInt("type_id");
                int region_id = rs.getInt("region_id");
                String q_t = rs.getString("question_text");
                String c_a = rs.getString("correct_answer");

                String query2 = "SELECT region FROM regions WHERE region_id = ?";
                ps = con.prepareStatement(query2);
                ps.setInt(1, region_id);
                ResultSet rs2 = ps.executeQuery();
                rs2.next();
                String region2 = rs2.getString("region");

                String query3 = "SELECT type FROM types WHERE type_id = ?";
                ps = con.prepareStatement(query3);
                ps.setInt(1, type_id);
                rs2 = ps.executeQuery();
                rs2.next();
                String type2 = rs2.getString("type");

                String query4 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_dd WHERE question_id = ?";
                ps = con.prepareStatement(query4);
                ps.setInt(1, id);
                rs2 = ps.executeQuery();
                if (rs2.next())
                {
                    String a1 = rs2.getString("answer_one");
                    String a2 = rs2.getString("answer_two");
                    String a3 = rs2.getString("answer_three");
                    String a4 = rs2.getString("answer_four");
                    DragAndDrop_Question q = new DragAndDrop_Question(applet, id, type2, region2, q_t, c_a, a1, a2, a3, a4);
                    questions.add(q);
                } else
                {
                    String query5 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_mc WHERE question_id = ?";
                    ps = con.prepareStatement(query5);
                    ps.setInt(1, id);
                    rs2 = ps.executeQuery();
                    if (rs2.next())
                    {
                        String a1 = rs2.getString("answer_one");
                        String a2 = rs2.getString("answer_two");
                        String a3 = rs2.getString("answer_three");
                        String a4 = rs2.getString("answer_four");
                        Multiplichoice_Question q = new Multiplichoice_Question(applet, id, type2, region2, q_t, c_a, a1, a2, a3, a4);
                        questions.add(q);
                    } else
                    {
                        String query6 = "SELECT answer_one,answer_two,answer_one_dir,answer_two_dir FROM questions_pc WHERE question_id = ?";
                        ps = con.prepareStatement(query6);
                        ps.setInt(1, id);
                        rs2 = ps.executeQuery();
                        if (rs2.next())
                        {
                            String a1 = rs2.getString("answer_one");
                            String a2 = rs2.getString("answer_two");
                            String a3 = rs2.getString("answer_one_dir");
                            String a4 = rs2.getString("answer_two_dir");
                            ChoosePicture_Question q = new ChoosePicture_Question(applet, id, type2, region2, q_t, c_a, a1, a2, a3, a4);
                            questions.add(q);
                        } else
                        {
                            String query7 = "SELECT question_id FROM questions_tf WHERE question_id = ?";
                            ps = con.prepareStatement(query7);
                            ps.setInt(1, id);
                            rs2 = ps.executeQuery();
                            if (rs2.next())
                            {
                                TrueOrFalse_Question q = new TrueOrFalse_Question(applet, id, type2, region2, q_t, c_a);
                                questions.add(q);
                            }
                        }
                    }
                }

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
        return questions;     // may be empty  }
    }

    @Override
    public List<Question> getQuestionByTypeRegion(PApplet applet, String type, String region)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Question> questions = new ArrayList<>();
        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query0 = "SELECT region_id FROM regions WHERE region = ?";
            ps = con.prepareStatement(query0);
            ps.setString(1, region);
            ResultSet rs0 = ps.executeQuery();
            rs0.next();
            int region_id_in = rs0.getInt("region_id");
            query0 = "SELECT type_id FROM types WHERE type = ?";
            ps = con.prepareStatement(query0);
            ps.setString(1, type);
            rs0 = ps.executeQuery();
            rs0.next();
            int type_id_in = rs0.getInt("type_id");
            String query = "SELECT question_id,type_id,region_id,question_text,correct_answer FROM questions WHERE region_id =? AND type_id = ? ORDER BY RAND() LIMIT 10";
            ps = con.prepareStatement(query);
            ps.setInt(1, region_id_in);
            ps.setInt(2, type_id_in);
            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {
                int id = rs.getInt("question_id");
                int type_id = rs.getInt("type_id");
                int region_id = rs.getInt("region_id");
                String q_t = rs.getString("question_text");
                String c_a = rs.getString("correct_answer");

                String query2 = "SELECT region FROM regions WHERE region_id = ?";
                ps = con.prepareStatement(query2);
                ps.setInt(1, region_id);
                ResultSet rs2 = ps.executeQuery();
                String region2 = rs2.getString("region");

                String query3 = "SELECT type FROM types WHERE type_id = ?";
                ps = con.prepareStatement(query3);
                ps.setInt(1, type_id);
                rs2 = ps.executeQuery();
                String type2 = rs2.getString("type");

                String query4 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_dd WHERE question_id = ?";
                ps = con.prepareStatement(query4);
                ps.setInt(1, id);
                rs2 = ps.executeQuery();
                if (rs2.next())
                {
                    String a1 = rs2.getString("answer_one");
                    String a2 = rs2.getString("answer_two");
                    String a3 = rs2.getString("answer_three");
                    String a4 = rs2.getString("answer_four");
                    DragAndDrop_Question q = new DragAndDrop_Question(applet, id, type2, region2, q_t, c_a, a1, a2, a3, a4);
                    questions.add(q);
                } else
                {
                    String query5 = "SELECT answer_one,answer_two,answer_three,answer_four FROM questions_mc WHERE question_id = ?";
                    ps = con.prepareStatement(query5);
                    ps.setInt(1, id);
                    rs2 = ps.executeQuery();
                    if (rs2.next())
                    {
                        String a1 = rs2.getString("answer_one");
                        String a2 = rs2.getString("answer_two");
                        String a3 = rs2.getString("answer_three");
                        String a4 = rs2.getString("answer_four");
                        Multiplichoice_Question q = new Multiplichoice_Question(applet, id, type2, region2, q_t, c_a, a1, a2, a3, a4);
                        questions.add(q);
                    } else
                    {
                        String query6 = "SELECT answer_one,answer_two,answer_one_dir,answer_two_dir FROM questions_pc WHERE question_id = ?";
                        ps = con.prepareStatement(query6);
                        ps.setInt(1, id);
                        rs2 = ps.executeQuery();
                        if (rs2.next())
                        {
                            String a1 = rs2.getString("answer_one");
                            String a2 = rs2.getString("answer_two");
                            String a3 = rs2.getString("answer_one_dir");
                            String a4 = rs2.getString("answer_two_dir");
                            ChoosePicture_Question q = new ChoosePicture_Question(applet, id, type2, region2, q_t, c_a, a1, a2, a3, a4);
                            questions.add(q);
                        } else
                        {
                            String query7 = "SELECT question_id FROM questions_tf WHERE question_id = ?";
                            ps = con.prepareStatement(query7);
                            ps.setInt(1, id);
                            rs2 = ps.executeQuery();
                            if (rs2.next())
                            {
                                TrueOrFalse_Question q = new TrueOrFalse_Question(applet, id, type2, region2, q_t, c_a);
                                questions.add(q);
                            }
                        }
                    }
                }

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
        return questions;     // may be empty  }
    }

    /**
     * @Override public List<Question> getPractice() { Connection con = null;
     * PreparedStatement ps = null; ResultSet rs = null; List<Question>
     * questions = new ArrayList<>();
     *
     * try { //Get connection object using the methods in the super class
     * (MySqlDao.java)... con = this.getConnection();
     *
     * String query = "SELECT * FROM questions ORDER BY RAND() LIMIT 10"; ps =
     * con.prepareStatement(query); //Using a PreparedStatement to execute
     * SQL... rs = ps.executeQuery(); while (rs.next()) { int id =
     * rs.getInt("question_id"); String question_type =
     * rs.getString("question_type"); String type = rs.getString("type"); String
     * region = rs.getString("region"); String q_t =
     * rs.getString("question_text"); String a_1 = rs.getString("answer_one");
     * String a_2 = rs.getString("answer_two"); String a_3 =
     * rs.getString("answer_three"); String a_4 = rs.getString("answer_four");
     * String c_a = rs.getString("correct_answer"); // Question u = new
     * Question(null, id, question_type, type, region, q_t, a_1, a_2, a_3, a_4,
     * c_a); // questions.add(u); } } catch (SQLException e) {
     *
     * } finally { try { if (rs != null) { rs.close(); } if (ps != null) {
     * ps.close(); } if (con != null) { freeConnection(con); } } catch
     * (SQLException e) {
     *
     * }
     * }
     * return questions; // may be empty }
     *
     * @Override public List<Question> getPracticeByType(String typeIn) {
     * Connection con = null; PreparedStatement ps = null; ResultSet rs = null;
     * List<Question> questions = new ArrayList<>();
     *
     * try { //Get connection object using the methods in the super class
     * (MySqlDao.java)... con = this.getConnection();
     *
     * String query = "SELECT * FROM questions WHERE type = ? ORDER BY RAND()";
     * ps = con.prepareStatement(query); ps.setString(1, typeIn); //Using a
     * PreparedStatement to execute SQL... rs = ps.executeQuery(); while
     * (rs.next()) { int id = rs.getInt("question_id"); String question_type =
     * rs.getString("question_type"); String type = rs.getString("type"); String
     * region = rs.getString("region"); String q_t =
     * rs.getString("question_text"); String a_1 = rs.getString("answer_one");
     * String a_2 = rs.getString("answer_two"); String a_3 =
     * rs.getString("answer_three"); String a_4 = rs.getString("answer_four");
     * String c_a = rs.getString("correct_answer"); // Question u = new
     * Question(null id, question_type, type, region, q_t, a_1, a_2, a_3, a_4,
     * c_a); // questions.add(u); } } catch (SQLException e) {
     *
     * } finally { try { if (rs != null) { rs.close(); } if (ps != null) {
     * ps.close(); } if (con != null) { freeConnection(con); } } catch
     * (SQLException e) {
     *
     * }
     * }
     * return questions; // may be empty }
     *
     * @Override public List<Question> getPracticeByRegion(String regionIn) {
     * Connection con = null; PreparedStatement ps = null; ResultSet rs = null;
     * List<Question> questions = new ArrayList<>();
     *
     * try { //Get connection object using the methods in the super class
     * (MySqlDao.java)... con = this.getConnection();
     *
     * String query = "SELECT * FROM questions WHERE region = ? ORDER BY
     * RAND()"; ps = con.prepareStatement(query); ps.setString(1, regionIn);
     * //Using a PreparedStatement to execute SQL... rs = ps.executeQuery();
     * while (rs.next()) { int id = rs.getInt("question_id"); String
     * question_type = rs.getString("question_type"); String type =
     * rs.getString("type"); String region = rs.getString("region"); String q_t
     * = rs.getString("question_text"); String a_1 = rs.getString("answer_one");
     * String a_2 = rs.getString("answer_two"); String a_3 =
     * rs.getString("answer_three"); String a_4 = rs.getString("answer_four");
     * String c_a = rs.getString("correct_answer"); //Question u = new
     * Question(null, id, question_type, type, region, q_t, a_1, a_2, a_3, a_4,
     * c_a); // questions.add(u); } } catch (SQLException e) {
     *
     * } finally { try { if (rs != null) { rs.close(); } if (ps != null) {
     * ps.close(); } if (con != null) { freeConnection(con); } } catch
     * (SQLException e) {
     *
     * }
     * }
     * return questions; // may be empty }
     *
     * @Override public List<Question> getPracticeByLevel() { throw new
     * UnsupportedOperationException("Not supported yet."); //To change body of
     * generated methods, choose Tools | Templates. }
     */
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
                System.out.println("Delete Questions" + e.getMessage());
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
                System.out.println("Delete Questions" + e.getMessage());
            }
        }
        return success;
    }

}
