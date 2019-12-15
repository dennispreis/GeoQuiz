package Teacher.TestManager;


import DTOs.Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
import Main.GeoQuiz;
import controlP5.ControlP5;
import processing.core.PApplet;

import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.TOP;

public class QuestionRecord {

    private ControlP5 cp5;
    private PApplet applet;
    private Question question;
    private String shrinkedQuestionText;
    private MyQuestionCheckBox myQuestionCheckBox;
    private boolean hasToBeShown;
    private float xPos, yPos, xSize, ySize;
    private int num;
    private String answer;
    
    public QuestionRecord(PApplet applet, Question question, int num, float x, float y) {
        this.applet = applet;
        this.question = question;
        this.cp5 = GeoQuiz.getCP5();
        this.xPos = x;
        this.yPos = y;
        this.xSize = 250;
        this.ySize = 25;
        this.num = num;
        this.shrinkedQuestionText = GeoQuiz.getShortString(this.question.getQuestion_text());
        myQuestionCheckBox = new MyQuestionCheckBox(applet, xPos, yPos);
        this.cp5.addTextarea("questionText_" + question.getId()).setPosition(440, 165).setSize(400, 200)
                .setText(this.question.getQuestion_text()).hide();
    }
    
     public QuestionRecord(PApplet applet, Question question, int num, float x, float y,String answer) {
        this.applet = applet;
        this.question = question;
        this.cp5 = GeoQuiz.getCP5();
        this.xPos = x;
        this.yPos = y;
        this.xSize = 250;
        this.ySize = 25;
        this.num = num;
        this.shrinkedQuestionText = GeoQuiz.getShortString(this.question.getQuestion_text());
        myQuestionCheckBox = new MyQuestionCheckBox(applet, xPos, yPos);
        this.cp5.addTextarea("questionText_" + question.getId()).setPosition(440, 165).setSize(400, 200)
                .setText(this.question.getQuestion_text()).hide();
        this.answer = answer;
    }

    public QuestionRecord Clone() {
        return new QuestionRecord(applet, question, num, xPos, yPos);
    }
    
    public QuestionRecord CloneWithAnswer() {
        return new QuestionRecord(applet, question, num, xPos, yPos,answer);
    }

    public void show() {
        if (isMouseWithIn()) {
            applet.fill(200, 100);
            applet.rectMode(CORNER);
            applet.noStroke();
            applet.rect(xPos, yPos, xSize, ySize);
        }
        showThisQuestion(this.question);
        applet.textAlign(LEFT, TOP);
        applet.textSize(15);
        applet.fill(255);
        applet.text(shrinkedQuestionText, xPos + (myQuestionCheckBox.isShowing() ? 40 : 0), yPos + 5);
        myQuestionCheckBox.show();
    }
    
    public void showWithAnswer(){
         if (isMouseWithIn()) {
            applet.fill(200, 100);
            applet.rectMode(CORNER);
            applet.noStroke();
            applet.rect(xPos, yPos, xSize, ySize);
        }
        showThisQuestion(this.question,answer);
        applet.textAlign(LEFT, TOP);
        applet.textSize(15);
        applet.fill(255);
        applet.text(shrinkedQuestionText, xPos + (myQuestionCheckBox.isShowing() ? 40 : 0), yPos + 5);
        myQuestionCheckBox.show();
    }

    public void setPosition(float x, float y) {
        this.xPos = x;
        this.yPos = y;
    }

    public void showThisQuestion(Question q) {
        if (isMouseWithIn()) {
            applet.fill(100, 120);
            applet.stroke(0);
            applet.strokeWeight(2);
            applet.rect(420, 150, 430, 425);
            this.cp5.get("questionText_" + q.getId()).show();
            if (q instanceof DragAndDrop_Question) {
                DragAndDrop_Question dad = (DragAndDrop_Question) q;
                applet.stroke(200);
                applet.strokeWeight(3);
                if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[0].getText().toLowerCase())) {
                    applet.rect(480, 380, 140, 90);
                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[1].getText().toLowerCase())) {
                    applet.rect(680, 380, 140, 90);
                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[2].getText().toLowerCase())) {
                    applet.rect(480, 480, 140, 90);
                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[3].getText().toLowerCase())) {
                    applet.rect(680, 480, 140, 90);
                }

                dad.getDragAndDrop().getSolutions()[0].setPosition(500, 400);
                dad.getDragAndDrop().getSolutions()[0].show();
                dad.getDragAndDrop().getSolutions()[1].setPosition(700, 400);
                dad.getDragAndDrop().getSolutions()[1].show();
                dad.getDragAndDrop().getSolutions()[2].setPosition(500, 500);
                dad.getDragAndDrop().getSolutions()[2].show();
                dad.getDragAndDrop().getSolutions()[3].setPosition(700, 500);
                dad.getDragAndDrop().getSolutions()[3].show();

            } else if (q instanceof Multiplichoice_Question) {
                Multiplichoice_Question mq = (Multiplichoice_Question) q;
                for (int i = 0; i < mq.getCheckBox().getElements().length; i++) {
                    mq.getCheckBox().getElements()[i].setPosition(475, 350 + (60 * i));
                    mq.getCheckBox().getElements()[i].show();
                    if ((Integer.parseInt(q.getCorrect_answer()) - 1) == i) {
                        mq.getCheckBox().getElements()[i].setActive(true);
                    }
                }
            } else if (q instanceof TrueOrFalse_Question) {
                TrueOrFalse_Question tof = (TrueOrFalse_Question) q;
                for (int i = 0; i < tof.getRadioButton().getElements().length; i++) {
                    tof.getRadioButton().getElements()[i].setPosition(475, 400 + (60 * i));
                    tof.getRadioButton().show();
                    if (q.getCorrect_answer().toLowerCase().equals(tof.getRadioButton().getElements()[i].getText().toLowerCase())) {
                        tof.getRadioButton().getElements()[i].setActive(true);
                    }
                }
            }
        } else {
            this.cp5.get("questionText_" + question.getId()).hide();
        }
    }

    public void showThisQuestion(Question q,String answer)
    {
        if (isMouseWithIn())
        {
            applet.fill(100, 120);
            applet.stroke(0);
            applet.strokeWeight(2);
            applet.rect(420, 150, 430, 425);
            this.cp5.get("questionText_" + q.getId()).show();
            if (q instanceof DragAndDrop_Question)
            {
                DragAndDrop_Question dad = (DragAndDrop_Question) q;
                applet.stroke(200);
                applet.strokeWeight(3);
                if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[0].getText().toLowerCase()))
                {
                    applet.rect(480, 380, 140, 90);

                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[1].getText().toLowerCase()))
                {
                    applet.rect(680, 380, 140, 90);      
                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[2].getText().toLowerCase()))
                {
                    applet.rect(480, 480, 140, 90);
                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[3].getText().toLowerCase()))
                {
                    applet.rect(680, 480, 140, 90);
                }
                if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[0].getText().toLowerCase()))
                {
                    applet.fill(255, 0, 0);
                    applet.line(480,380,500,400);
                    applet.line(500,400,600,380);

                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[1].getText().toLowerCase()))
                {
                    applet.fill(255, 0, 0);
                    applet.line(680,380,700,400);
                    applet.line(700,400,800,380);
                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[2].getText().toLowerCase()))
                {
                    applet.fill(255, 0, 0);
                    applet.line(480,480,500,500);
                    applet.line(500,500,600,480);
                } else if (q.getCorrect_answer().toLowerCase().equals(dad.getDragAndDrop().getSolutions()[3].getText().toLowerCase()))
                {
                    applet.fill(255, 0, 0);
                    applet.line(680,480,700,500);
                    applet.line(700,500,800,480);
                }

                dad.getDragAndDrop().getSolutions()[0].setPosition(500, 400);
                dad.getDragAndDrop().getSolutions()[0].show();
                dad.getDragAndDrop().getSolutions()[1].setPosition(700, 400);
                dad.getDragAndDrop().getSolutions()[1].show();
                dad.getDragAndDrop().getSolutions()[2].setPosition(500, 500);
                dad.getDragAndDrop().getSolutions()[2].show();
                dad.getDragAndDrop().getSolutions()[3].setPosition(700, 500);
                dad.getDragAndDrop().getSolutions()[3].show();

            } else if (q instanceof Multiplichoice_Question)
            {
                Multiplichoice_Question mq = (Multiplichoice_Question) q;
                for (int i = 0; i < mq.getCheckBox().getElements().length; i++)
                {
                    mq.getCheckBox().getElements()[i].setPosition(475, 350 + (60 * i));
                    mq.getCheckBox().getElements()[i].show();
                    if ((Integer.parseInt(q.getCorrect_answer()) - 1) == i)
                    {
                        mq.getCheckBox().getElements()[i].setActive(true);
                        //applet.line(0,0,0,0);
                        //applet.line(0,0,0,0);
                    }
                }
            } else if (q instanceof TrueOrFalse_Question)
            {
                TrueOrFalse_Question tof = (TrueOrFalse_Question) q;
                for (int i = 0; i < tof.getRadioButton().getElements().length; i++)
                {
                    tof.getRadioButton().getElements()[i].setPosition(475, 400 + (60 * i));
                    tof.getRadioButton().show();
                    if (q.getCorrect_answer().toLowerCase().equals(tof.getRadioButton().getElements()[i].getText().toLowerCase()))
                    {
                        tof.getRadioButton().getElements()[i].setActive(true);
                        //applet.line(0,0,0,0);
                        //applet.line(0,0,0,0);
                    }
                }
            }
        } else
        {
            this.cp5.get("questionText_" + question.getId()).hide();
        }
    }
    
    private boolean isMouseWithIn() {
        return (applet.mouseX > xPos && applet.mouseX < xPos + xSize && applet.mouseY > yPos && applet.mouseY < yPos + ySize);
    }

    void setHasToBeShown(boolean bool) {
        this.hasToBeShown = bool;
    }

    public Question getQuestion() {
        return this.question;
    }

    public boolean isHasToBeShown() {
        return this.hasToBeShown;
    }

    public MyQuestionCheckBox getMyQuestionCheckBox() {
        return this.myQuestionCheckBox;
    }

    public String getQuestionText() {
        return this.question.getQuestion_text();
    }

}
