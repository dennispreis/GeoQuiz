package Teacher.TestManager;


import processing.core.PApplet;

import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.TOP;

public class QuestionRecord {

    private PApplet applet;
    private String questionText;
    private MyQuestionCheckBox myQuestionCheckBox;
    private boolean hasToBeShown;
    private float xPos, yPos;
    private int num;


    public QuestionRecord(PApplet applet, int num, float x, float y) {
        this.applet = applet;
        this.xPos = x;
        this.yPos = y;
        this.num = num;
        myQuestionCheckBox = new MyQuestionCheckBox(applet, xPos, yPos);
        questionText = num + ") Generic Question Text";
    }

    public void show() {
        applet.textAlign(LEFT, TOP);
        applet.textSize(15);
        applet.fill(255);
        applet.text(questionText, xPos + 30, yPos+5);
        myQuestionCheckBox.show();
    }

    void setHasToBeShown(boolean bool) {
        this.hasToBeShown = bool;
    }

    public boolean isHasToBeShown() {
        return this.hasToBeShown;
    }

    public MyQuestionCheckBox getMyQuestionCheckBox() {
        return this.myQuestionCheckBox;
    }

    public String getQuestionText() {
        return this.questionText;
    }

}
