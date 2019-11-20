package DTOs.Questions;

import DTOs.Question;
import processing.core.PApplet;

public class TrueOrFalse_Question extends Question {



    public TrueOrFalse_Question(PApplet applet, int id, String question_type, String type, String region, String question_text, String correct_answer) {
        super(applet, id, question_type, type, region, question_text, correct_answer);
    }

    public void show() {

    }


}
