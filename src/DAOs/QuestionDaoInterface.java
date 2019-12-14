/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
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
    
    public List<Question> getAllQuestionByType(PApplet applet, String type);

    public List<Question> getQuestionByRegion(PApplet applet, String region);

    public List<Question> getQuestionByTypeRegion(PApplet applet, String type, String region);

    public Question getQuestionById(PApplet applet, int id);
    
    public boolean addQuestion(Question q);
    
    public boolean addMCQuestion(String type,String region,String question_text,String correct_answer,String a1,String a2,String a3,String a4);
    
    public boolean addTFQuestion(String type,String region,String question_text,String correct_answer);
    
    public boolean addDDQuestion(String type,String region,String question_text,String correct_answer,String a1,String a2,String a3,String a4);
    
    public boolean updateQuestion(int id, String field, String value);

    public boolean deleteQuestion(int id);
}
