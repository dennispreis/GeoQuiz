package GameManager;

import DAOs.MyPracticeDao;
import DAOs.MyQuestionDao;
import DTOs.Question;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
import GameManager.gameElements.DragAndDrop;
import Images.ImageName;
import Main.GeoQuiz;
import processing.core.PApplet;

import java.awt.font.ImageGraphicAttribute;
import java.util.List;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;

public class GameManager {

    private PApplet applet;
    private GameProperty category;
    private GameProperty level;
    private Question[] questions;
    private Question actualQuestion;
    private int actuallQuestionIndex;
    private int score, maxScore;
    private boolean isPlaying;
    private TypeChooser categoryChooser, levelChooser;
    private MyPracticeDao IPracticeDao;

    public GameManager(PApplet applet) {
        this.applet = applet;
        this.category = Category.WORLD;
        this.level = Level.EASY;
        this.IPracticeDao = new MyPracticeDao();
        score = 0;

        categoryChooser = new TypeChooser(applet).setElements(new ChooseAble[]{
                new ChooseAble(applet, 175, 200, ImageName.CATEGORY_CITIES, Category.CITIES).setText("Cities"),
                new ChooseAble(applet, 275, 200, ImageName.CATEGORY_MOUNTAINS, Category.MOUNTAINS).setText("Mountains"),
                new ChooseAble(applet, 375, 200, ImageName.CATEGORY_RIVERS, Category.RIVERS).setText("Rivers"),
                new ChooseAble(applet, 475, 200, ImageName.CATEGORY_WORLD, Category.WORLD).setText("World"),
                new ChooseAble(applet, 575, 200, ImageName.PLACEHOLDER_SMALL, Category.ISLANDS).setText("Islands"),
                new ChooseAble(applet, 675, 200, ImageName.PLACEHOLDER_SMALL, Category.LAKES).setText("Lakes"),
        });
        categoryChooser.updateActiveElement(categoryChooser.getElements()[0]);
/*
        levelChooser = new TypeChooser(applet).setElements(new ChooseAble[]{
                new ChooseAble(applet, 275, 400, ImageName.LEVEL_EASY, Level.EASY).setText("Easy"),
                new ChooseAble(applet, 425, 400, ImageName.PLACEHOLDER_SMALL, Level.MEDIUM).setText("Medium"),
                new ChooseAble(applet, 575, 400, ImageName.LEVEL_HARD, Level.HARD).setText("Hard")
        });
        levelChooser.updateActiveElement(levelChooser.getElements()[0]);
*/
    }

    public void createQuestions() {
        List<Question> tmp = IPracticeDao.getPractice(applet, GeoQuiz.getUser().getId(), category.getName(), level.getName());
        questions = new Question[tmp.size()];
        tmp.toArray(questions);
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
        maxScore = questions.length;
    }

    public void showGameChoosing() {
        categoryChooser.show();
//        levelChooser.show();
    }

    public void show() {
        actualQuestion.show();
    }

    public TypeChooser getCategoryChooser() {
        return categoryChooser;
    }

    public TypeChooser getLevelChooser() {
        return levelChooser;
    }

    public Question getActualQuestion() {
        return actualQuestion;
    }

    public GameProperty getLevel() {
        return level;
    }

    public Question[] getQuestions() {
        return questions;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public GameProperty getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPlaying(boolean bool) {
        this.isPlaying = bool;
    }

    public boolean isPlaying() {
        return this.isPlaying;
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
        applet.text(GeoQuiz.getLanguageManager().getString("score") + " : " + score + " / " + maxScore, applet.width / 2, applet.height / 2);
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

    public void setChoosenCategory() {
        this.category = categoryChooser.getActiveElement().getGameProperty();
    }

    public void setChoosenLevel() {
        this.level = levelChooser.getActiveElement().getGameProperty();
    }
}
