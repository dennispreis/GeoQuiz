package GameManager;


import DTOs.Question;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.TrueOrFalse_Question;
import processing.core.PApplet;

public class GameManager {

    private Category category;
    private Level level;
    private Question[] questions;
    private Question actualQuestion;
    private PApplet applet;
    private int actuallQuestionIndex;

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
                        "Which of this countries are in europa?",
                        "answerText"
                        )
        };
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
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

    public void setLevel(Level level) {
        this.level = level;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void nextQuestion() {
        if (actuallQuestionIndex != questions.length-1) {
            actuallQuestionIndex++;
            actualQuestion = questions[actuallQuestionIndex];
        }
    }

    public void setActuallQuestionIndex(int idx) {
        actuallQuestionIndex = idx;
        actualQuestion = questions[actuallQuestionIndex];

    }
}
