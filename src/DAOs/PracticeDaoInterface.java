/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.ProfileHistory;
import DTOs.Question;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public interface PracticeDaoInterface
{

    public List<Question> getPractice(PApplet applet,int id,String category,String level);

    public List<Question> getPracticeByType(PApplet applet, String type,int id,String category,String level);

    public List<Question> getPracticeByRegion(PApplet applet, String region,int id,String category,String level);

    public List<Question> getPracticeByTypeRegion(PApplet applet, String type, String region,int id,String category,String level);

    public ProfileHistory getProfileHistory(int id);
    
    public ProfileHistory getPracticeProfileHistoryByClass(String className);
    
    public boolean updateScore(int id,int score);
}
