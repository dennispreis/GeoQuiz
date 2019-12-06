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
import java.sql.Statement;
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

}
