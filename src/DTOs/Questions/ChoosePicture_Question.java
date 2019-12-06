package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.ChoosePicture;
import Images.ImageName;
import Main.GeoQuiz;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class ChoosePicture_Question extends Question {

    private ChoosePicture choosePicture;

    public ChoosePicture_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer) {
        super(applet, id, type, region, question_text, correct_answer);
        choosePicture = new ChoosePicture(applet, GeoQuiz.getImage(ImageName.PLACEHOLDER), GeoQuiz.getImage(ImageName.PLACEHOLDER));
    }

    public ChoosePicture_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer, String a1, String a2, String a3, String a4) {
        super(applet, id, type, region, question_text, correct_answer);


        choosePicture = new ChoosePicture(applet, GeoQuiz.getImage(ImageName.PLACEHOLDER), GeoQuiz.getImage(ImageName.PLACEHOLDER));
        // choosePicture = new ChoosePicture(applet, GeoQuiz.getImage(ImageMap.getImageName(a3)), GeoQuiz.getImage(ImageMap.getImageName(a4)));
    }

    public void reset() {
        choosePicture.getButton_right().setChoosen(false);
        choosePicture.getButton_left().setChoosen(false);
    }

    public void show() {
        applet.rectMode(CENTER);
        applet.textAlign(CENTER, CENTER);
        applet.fill(255);
        applet.textSize(30);
        applet.text(getQuestion_text(), applet.width / 2, 100);
        choosePicture.getButton_left().show();
        choosePicture.getButton_right().show();
    }

    public ChoosePicture getChoosePicture() {
        return choosePicture;
    }

}
