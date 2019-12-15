/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Practice;
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

    public Practice getPractice(PApplet applet,int id,String category);

    public Practice getPracticeByType(PApplet applet, String type,int id,String category);

    public Practice getPracticeByRegion(PApplet applet, String region,int id,String category);

    public Practice getPracticeByTypeRegion(PApplet applet, String type, String region,int id,String category);

    public ProfileHistory getProfileHistory(int id);
    
    public ProfileHistory getPracticeProfileHistoryByClass(String className);
    
    public boolean updateScore(int id,int score,String answers);
    
    public Practice getPracticeByID(PApplet applet,int id);
}
