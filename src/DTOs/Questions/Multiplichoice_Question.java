package DTOs.Questions;

import DTOs.Question;
import processing.core.PApplet;
public class Multiplichoice_Question extends Question {

    public Multiplichoice_Question(PApplet applet, int id, String question_type, String type, String region, String question_text, String correct_answer)
    {
        super(applet, id, question_type, type, region, question_text, correct_answer);
    }

    @Override
    public void show()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
