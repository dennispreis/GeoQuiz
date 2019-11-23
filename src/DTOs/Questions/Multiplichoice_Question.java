package DTOs.Questions;

import DTOs.Question;
import processing.core.PApplet;
public class Multiplichoice_Question extends Question {

    public Multiplichoice_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer,String a1,String a2,String a3,String a4)
    {
        super(applet, id, type, region, question_text, correct_answer);
        
    }

    @Override
    public void show()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
