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

    public List<Question> getPractice(PApplet applet);

    public List<Question> getPracticeByType(PApplet applet, String type);

    public List<Question> getPracticeByRegion(PApplet applet, String region);

    public List<Question> getPracticeByTypeRegion(PApplet applet, String type, String region);

    public ProfileHistory getProfileHistory(int id);
    
    
}
