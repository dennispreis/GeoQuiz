package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.RadioButton;
import controlP5.ControlP5;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class TrueOrFalse_Question extends Question {

    private RadioButton radioButton;

    public TrueOrFalse_Question(PApplet applet, int id, String question_type, String type, String region, String question_text, String correct_answer) {
        super(applet, id, question_type, type, region, question_text, correct_answer);
        this.radioButton = new RadioButton(applet);
    }

    public RadioButton getRadioButton(){
        return radioButton;
    }

    public void reset(){
        radioButton.selectElement(null);
    }

    public void show() {
        applet.rectMode(CENTER);
        applet.fill(100, 175);
        applet.stroke(0);
        applet.strokeWeight(2);
        applet.rect(applet.width / 2, 100, 500, 50);
        applet.textAlign(CENTER, CENTER);
        applet.fill(255);
        applet.textSize(30);
        applet.text(getQuestion_text(), applet.width / 2, 100);
        radioButton.show();
    }

}
