package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.myCheckBox;
import GameManager.gameElements.myCheckBoxElement;
import Main.GeoQuiz;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class Multiplichoice_Question extends Question {

    private myCheckBox checkBox;

    public Multiplichoice_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer, String a1, String a2, String a3, String a4) {
        super(applet, id, type, region, question_text, correct_answer);
        this.checkBox = new myCheckBox(applet, a1, a2, a3, a4);
    }

    public myCheckBox getCheckBox() {
        return this.checkBox;
    }

    public void reset() {
        for (myCheckBoxElement ele : checkBox.getElements()) {
            ele.setActive(false);
        }
    }

    public void show() {
        checkBox.show();
    }


}
