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
public interface PaperDaoInterface
{

    public List<Question> getRandPaper(PApplet applet);

    public List<Question> getPaperByType(PApplet applet,String type);

    public List<Question> getPaperByRegion(PApplet applet,String region);

    public List<Question> getPaperByTypeRegion(PApplet applet,String type,String region);
}
