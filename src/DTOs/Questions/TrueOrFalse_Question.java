package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.RadioButton;
import controlP5.ControlP5;
import Main.GeoQuiz;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class TrueOrFalse_Question extends Question {

    private RadioButton radioButton;


    public TrueOrFalse_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer) {
        super(applet, id, type, region, question_text, correct_answer);
        this.radioButton = new RadioButton(applet);
    }

    public RadioButton getRadioButton(){
        return radioButton;
    }

    @Override
    public void reset(){
        radioButton.selectElement(null);
    }

    @Override
    public void show() {
        radioButton.show();
    }

}
