package GameManager;

import DTOs.Question;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
import GameManager.gameElements.DragAndDrop;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class GameManager {

    private PApplet applet;
    private Category category;
    private Level level;
    private Question[] questions;
    private Question actualQuestion;
    private int actuallQuestionIndex;
    private int score, maxScore;

    public GameManager(PApplet applet) {
        this.applet = applet;
        this.category = Category.MOUNTAINS;
        this.level = Level.EASY;
        questions = new Question[]{
                new DragAndDrop_Question(applet, 0,
                        "DragAndDrop",
                        "QuestionType",
                        "Region",
                        "What is the Capital of Ireland?",
                        "Dublin"),
                new ChoosePicture_Question(applet, 1,
                        "ChoosePicture",
                        "QuestionType",
                        "Region",
                        "Which of these is Paris?",
                        "answerText"),
                new TrueOrFalse_Question(applet, 2,
                        "TrueOrFalse",
                        "QuestionType",
                        "Region",
                        "Which country is in europe?",
                        "answerText"
                ),
                new Multiplichoice_Question(applet, 3,
                        "Multipliechoice",
                        "QuestionType",
                        "Region",
                        "Which cities are in Ireland?",
                        "answerText")
        };
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
        score = 0;
        maxScore = questions.length;
    }

    public void show() {
        actualQuestion.show();
    }

    public Question getActualQuestion() {
        return actualQuestion;
    }

    public void increaseLevel() {
    }

    public void decreaseLevel() {
    }

    public void increaseCategory() {

    }

    public void decreaseCategory() {

    }

    public Level getLevel() {
        return level;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void reset() {
        this.score = 0;
        for (Question q : questions) {
            q.reset();
        }

    }

    public boolean nextQuestion() {
        if (actuallQuestionIndex != questions.length - 1) {
            actuallQuestionIndex++;
            actualQuestion = questions[actuallQuestionIndex];
            return true;
        }
        return false;
    }

    public void setActuallQuestionIndex(int idx) {
        actuallQuestionIndex = idx;
        actualQuestion = questions[actuallQuestionIndex];

    }

    public void showPractiseFeedback() {
        applet.textSize(50);
        applet.textAlign(CENTER, CENTER);
        applet.text("Score : " + score + " / " + maxScore, applet.width / 2, applet.height / 2);
    }

    public void loadPractiseFeedback() {
        for (Question q : questions) {
            if (q instanceof DragAndDrop_Question) {
                DragAndDrop_Question dad_question = (DragAndDrop_Question) q;
                //Get Text from AnswerElement
                if (dad_question.getDragAndDrop().getAnswerRect().isOccupied()) {
                    if (dad_question.getDragAndDrop().getAnswerRect().getDragAndDropElement().getText().equals("Dublin"))
                        score++;
                }
            } else if (q instanceof Multiplichoice_Question) {
                Multiplichoice_Question mp_question = (Multiplichoice_Question) q;
                //Get right array index and check if isActive()
                if (mp_question.getCheckBox().getElements()[0].isActive() && mp_question.getCheckBox().getElements()[2].isActive())
                    score++;
            } else if (q instanceof TrueOrFalse_Question) {
                TrueOrFalse_Question tof_question = (TrueOrFalse_Question) q;
                //Get array index and check for isActive()
                if (tof_question.getRadioButton().getElements()[0].isActive()) score++;
            } else if (q instanceof ChoosePicture_Question) {
                ChoosePicture_Question cp_question = (ChoosePicture_Question) q;
                //Get left or right picture and check if isChoosen()
                if (cp_question.getChoosePicture().getButton_left().isChoosen()) score++;
            }
        }
    }
}
