/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teacher.TestManager;

import DAOs.MyPaperDao;
import DAOs.MyTestDao;
import DAOs.PaperDaoInterface;
import DAOs.TestDaoInterface;
import DTOs.Paper;
import DTOs.Test;
import Main.GeoQuiz;
import Teacher.TestManager.MarkableQuestionList;
import Teacher.TestManager.QuestionRecord;
import controlP5.ControlP5;
import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.TOP;

/**
 *
 * @author User
 */
public class TestQuestionList
{
    private static TestDaoInterface ITestDao = new MyTestDao();
    private static PaperDaoInterface IPaperDao = new MyPaperDao();
    private ControlP5 cp5;
    private PApplet applet;
    private MarkableQuestionList markedQuestions;
    private int numOfMarkedQuestions;

    public TestQuestionList(PApplet applet, ControlP5 cp5)
    {
        this.applet = applet;
        this.cp5 = cp5;
    }

    public void show()
    {
        applet.rectMode(CORNER);
        applet.stroke(0);
        applet.strokeWeight(2);
        applet.fill(100, 120);
        applet.textAlign(LEFT, TOP);
        applet.rect(100, 150, 300, 425);
        applet.stroke(255);
        applet.line(105, 185, 395, 185);
        applet.textSize(20);
        applet.fill(255);
        applet.text(GeoQuiz.getLanguageManager().getString("questionText"), 150, 165);
        for (int i = 0; i < markedQuestions.getQuestionRecordList().size(); i++)
        {
            markedQuestions.getQuestionRecordList().get(i).show();
        }
    }
    
    public void setQuestionList(int test_id)
    {
        Test t = ITestDao.getTestObjectById(test_id);
        Paper p = IPaperDao.getPaperByID(applet, t.getPaper_id());
        this.markedQuestions = new MarkableQuestionList(applet,p.getQuestions());
    }
}
