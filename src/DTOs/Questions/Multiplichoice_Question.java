package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.myCheckBox;
import GameManager.gameElements.myCheckBoxElement;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class Multiplichoice_Question extends Question {

    private myCheckBox checkBox;
    
    public Multiplichoice_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer,String a1,String a2,String a3,String a4)
    {
        super(applet, id, type, region, question_text, correct_answer);
        this.checkBox = new myCheckBox(applet,a1,a2,a3,a4);
    }
    public Multiplichoice_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer) {
        super(applet, id, type, region, question_text, correct_answer);
        this.checkBox = new myCheckBox(applet);
    }

    public myCheckBox getCheckBox() {
        return this.checkBox;
    }

    public void reset(){
        for(myCheckBoxElement ele : checkBox.getElements()){
            ele.setActive(false);
        }
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
        checkBox.show();
    }
    
    
}
