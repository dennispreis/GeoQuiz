package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.ChoosePicture;
import Images.ImageName;
import Main.GeoQuiz;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class ChoosePicture_Question extends Question {

    private ChoosePicture choosePicture;

    public ChoosePicture_Question(PApplet applet, int id, String question_type, String type, String region, String question_text, String correct_answer) {
        super(applet, id, question_type, type, region, question_text, correct_answer);
        choosePicture = new ChoosePicture(applet, GeoQuiz.getImage(ImageName.PLACEHOLDER), GeoQuiz.getImage(ImageName.PLACEHOLDER));
    }

    public void show() {
        applet.rectMode(CENTER);
        applet.fill(100, 175);
        applet.stroke(0);
        applet.strokeWeight(2);
        applet.rect(applet.width/2, 100, 500, 50);
        applet.textAlign(CENTER, CENTER);
        applet.fill(255);
        applet.textSize(30);
        applet.text(getQuestion_text(), applet.width/2, 100);
        choosePicture.getButton_left().show();
        choosePicture.getButton_right().show();
    }

    public ChoosePicture getChoosePicture(){
        return choosePicture;
    }

}
