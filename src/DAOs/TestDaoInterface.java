/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.ProfileHistory;
import DTOs.Question;
import DTOs.Test;
import java.util.List;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public interface TestDaoInterface
{

    public ProfileHistory getProfileHistory(String class_name);

    public boolean updateScore(int id, int score,String answers);

    public void addTest(String test_name,List<Question> questionList);
    
    public List<Test> getAllTest();
    
    public Test attemptTest(PApplet applet,int student_id,int test_id);
    
    public Test getTestObjectById(int test_id);
}
