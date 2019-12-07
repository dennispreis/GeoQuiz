/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Paper;
import DTOs.Question;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public interface PaperDaoInterface
{

    public Paper getRandPaper(PApplet applet);

    public Paper getPaperByType(PApplet applet, String type);

    public Paper getPaperByRegion(PApplet applet, String region);

    public Paper getPaperByTypeRegion(PApplet applet, String type, String region);

    public Paper getPaperByID(PApplet appplet, int id);

    public int addNewPaper(List<Question> questionList);

}
