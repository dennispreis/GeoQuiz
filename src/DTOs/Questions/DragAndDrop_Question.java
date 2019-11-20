package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.DragAndDrop;
import GameManager.gameElements.DragAndDropElement;
import GameManager.gameElements.FixRect;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class DragAndDrop_Question extends Question {

    private DragAndDrop dragAndDrop;

    public DragAndDrop_Question(PApplet applet, int id, String question_type, String type, String region, String question_text, String correct_answer) {
        super(applet, id, question_type, type, region, question_text, correct_answer);

        dragAndDrop = new DragAndDrop(applet,
                new DragAndDropElement[]
                        {
                                new DragAndDropElement(applet).setPosition(250, 300).setText("Paris"),
                                new DragAndDropElement(applet).setPosition(400, 300).setText("Dublin"),
                                new DragAndDropElement(applet).setPosition(550, 300).setText("Berlin")
                        },
                new FixRect(applet, 400, 150, 100, 50));
    }

    public void show() {
        dragAndDrop.show();
        applet.rectMode(CENTER);
        applet.fill(100, 175);
        applet.stroke(0);
        applet.strokeWeight(2);
        applet.rect(applet.width/2, 100, 500, 50);
        applet.textAlign(CENTER, CENTER);
        applet.fill(255);
        applet.textSize(30);
        applet.text(getQuestion_text(), applet.width/2, 100);
    }

    public DragAndDrop getDragAndDrop(){
        return dragAndDrop;
    }

}
