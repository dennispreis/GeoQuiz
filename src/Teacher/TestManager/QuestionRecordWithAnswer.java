/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Teacher.TestManager;

import DTOs.Question;
import processing.core.PApplet;

/**
 *
 * @author User
 */
public class QuestionRecordWithAnswer extends QuestionRecord
{
    private String answer;
    public QuestionRecordWithAnswer(PApplet applet, Question question, int num, float x, float y,String answer)
    {
        super(applet, question, num, x, y);
        this.answer = answer;
    }
    
    @Override
    public QuestionRecordWithAnswer Clone() {
        return new QuestionRecordWithAnswer(applet, question, num, xPos, yPos,answer);
    }
    
}
