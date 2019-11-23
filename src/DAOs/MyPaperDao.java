/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Question;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public class MyPaperDao extends MySqlDao implements PaperDaoInterface
{
   private QuestionDaoInterface IQuestionDao =new MyQuestionDao();

    @Override
    public List<Question> getRandPaper(PApplet applet)
    {
        return IQuestionDao.getRandQuestion(applet);
    }

    @Override
    public List<Question> getPaperByType(PApplet applet, String type)
    {
        return IQuestionDao.getQuestionByType(applet, type);
    }
    @Override
    public List<Question> getPaperByRegion(PApplet applet, String region)
    {
        return IQuestionDao.getQuestionByRegion(applet, region);
    }

    @Override
    public List<Question> getPaperByTypeRegion(PApplet applet, String type, String region)
    {
        return IQuestionDao.getQuestionByTypeRegion(applet, type, region);
    }

 
}
