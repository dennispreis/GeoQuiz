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
 * @author Tunjing
 */
public interface QuestionDaoInterface
{
    public List<Question> getRandQuestion(PApplet applet);

    public List<Question> getQuestionByType(PApplet applet, String type);

    public List<Question> getQuestionByRegion(PApplet applet, String region);

    public List<Question> getQuestionByTypeRegion(PApplet applet, String type, String region);

    public boolean addQuestion(Question q);

    public boolean updateQuestion(int id, String field, String value);

    public boolean deleteQuestion(int id);
}
