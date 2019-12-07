/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Paper;
import DTOs.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;

/**
 * @author User
 */
public class MyPaperDao extends MySqlDao implements PaperDaoInterface {

    private QuestionDaoInterface IQuestionDao = new MyQuestionDao();

    @Override
    public Paper getRandPaper(PApplet applet) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        List<Question> toReturn = IQuestionDao.getRandQuestion(applet);
        int insertId = 0;
        try {

            conn = this.getConnection();
            String query = "INSERT INTO papers (question_id) VALUES(?)";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, toReturn.get(0).getId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                    for (int i = 1; i < toReturn.size(); i++) {
                        query = "INSERT INTO papers (paper_id,question_id) VALUES(?,?)";
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, insertId);
                        ps.setInt(2, toReturn.get(i).getId());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paper p = new Paper(insertId, toReturn);
        return p;
    }

    @Override
    public Paper getPaperByType(PApplet applet, String type) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        List<Question> toReturn = IQuestionDao.getQuestionByType(applet, type);
        int insertId = 0;
        try {

            conn = this.getConnection();
            String query = "INSERT INTO papers (question_id) VALUES(?)";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, toReturn.get(0).getId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                    for (int i = 1; i < toReturn.size(); i++) {
                        query = "INSERT INTO papers (paper_id,question_id) VALUES(?,?)";
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, insertId);
                        ps.setInt(2, toReturn.get(i).getId());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paper p = new Paper(insertId, toReturn);
        return p;
    }

    @Override
    public Paper getPaperByRegion(PApplet applet, String region) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        List<Question> toReturn = IQuestionDao.getQuestionByRegion(applet, region);
        int insertId = 0;
        try {

            conn = this.getConnection();
            String query = "INSERT INTO papers (question_id) VALUES(?)";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, toReturn.get(0).getId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                    for (int i = 1; i < toReturn.size(); i++) {
                        query = "INSERT INTO papers (paper_id,question_id) VALUES(?,?)";
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, insertId);
                        ps.setInt(2, toReturn.get(i).getId());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paper p = new Paper(insertId, toReturn);
        return p;

    }

    @Override
    public Paper getPaperByTypeRegion(PApplet applet, String type, String region) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean success = false;
        List<Question> toReturn = IQuestionDao.getQuestionByTypeRegion(applet, type, region);
        int insertId = 0;
        try {

            conn = this.getConnection();
            String query = "INSERT INTO papers (question_id) VALUES(?)";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, toReturn.get(0).getId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                    for (int i = 1; i < toReturn.size(); i++) {
                        query = "INSERT INTO papers (paper_id,question_id) VALUES(?,?)";
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, insertId);
                        ps.setInt(2, toReturn.get(i).getId());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Paper p = new Paper(insertId, toReturn);
        return p;
    }

    @Override
    public Paper getPaperByID(PApplet appplet, int id) {
        List<Question> questionList = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = this.getConnection();
            String query = "SELECT paper_id,question_id FROM paper WHERE paper_id=?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                int question_id = rs.getInt("question_id");
                questionList.add(IQuestionDao.getQuestionById(appplet, question_id));
            }
        } catch (SQLException e) {

        }
        return new Paper(id, questionList);
    }

    @Override
    public int addNewPaper(List<Question> questionList) {
        Connection conn = null;
        PreparedStatement ps = null;
        int insertId = 0;
        try {

            conn = this.getConnection();
            String query = "INSERT INTO papers (question_id) VALUES(?)";
            ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, questionList.get(0).getId());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    insertId = generatedKeys.getInt(1);
                    for (int i = 1; i < questionList.size(); i++) {
                        query = "INSERT INTO papers (paper_id,question_id) VALUES(?,?)";
                        ps = conn.prepareStatement(query);
                        ps.setInt(1, insertId);
                        ps.setInt(2, questionList.get(i).getId());
                        ps.executeUpdate();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertId;
    }

}
