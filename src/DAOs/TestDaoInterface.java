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
public interface TestDaoInterface
{

    public List<Question> getTestByID(PApplet applet, int id, int paper_id);

    public ProfileHistory getProfileHistory(int id);

    public boolean updateScore(int id, int score);

    public void addTest(String test_name,List<Question> questionList);
}
