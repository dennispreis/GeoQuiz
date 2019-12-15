package GameManager;

import DAOs.MyPracticeDao;
import DAOs.MyQuestionDao;
import DAOs.MyRecordPracticeDao;
import DAOs.MyRecordTestDao;
import DAOs.MyTestDao;
import DAOs.TestDaoInterface;
import DTOs.Practice;
import DTOs.Question;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
import DTOs.Test;
import GameManager.gameElements.DragAndDrop;
import Images.ImageName;
import Main.GeoQuiz;
import controlP5.ControlP5;
import controlP5.Textarea;
import controlP5.Textlabel;
import processing.core.PApplet;

import java.awt.font.ImageGraphicAttribute;
import java.util.ArrayList;
import java.util.List;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.LEFT;
import DAOs.RecordDaoInterface;

public class GameManager {

    private PApplet applet;
    private ControlP5 cp5;
    private GameProperty category;
    private Question[] questions;
    private Question actualQuestion;
    private int actuallQuestionIndex;
    private int score, maxScore;
    private boolean isPlaying;
    private TypeChooser categoryChooser;
    private MyPracticeDao IPracticeDao;
    private static TestDaoInterface ITestDao = new MyTestDao();
    private static RecordDaoInterface IRecordPracticeDao = new MyRecordPracticeDao();
    private static RecordDaoInterface IRecordTestDao = new MyRecordTestDao();
    private int paper_id;
    private boolean test;
    private int record_id;
    private int id;

    public GameManager(PApplet applet, ControlP5 cp5) {
        this.id = 1;
        this.applet = applet;
        this.category = Category.WORLD;
        this.cp5 = cp5;
        cp5.addTextarea("Question_Textarea").setPosition(150, 50).setSize(500, 300).hide();
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
      
    }
    public GameManager(PApplet applet, ControlP5 cp5,int user_id) {
        this.id = user_id;
        this.applet = applet;
        this.category = Category.WORLD;
        this.cp5 = cp5;
        cp5.addTextarea("Question_Textarea").setPosition(150, 50).setSize(500, 300).hide();
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
    }
    
    public GameManager(PApplet applet, int id, boolean test)
    {
        this.id = id;
        this.applet = applet;
        this.category = Category.WORLD;
        this.test = test;
       
        score = 0;

        categoryChooser = new TypeChooser(applet).setElements(new ChooseAble[]
                {
                        new ChooseAble(applet, 175, 200, ImageName.CATEGORY_CITIES, Category.CITIES).setText("Cities"),
                        new ChooseAble(applet, 275, 200, ImageName.CATEGORY_MOUNTAINS, Category.MOUNTAINS).setText("Mountains"),
                        new ChooseAble(applet, 375, 200, ImageName.CATEGORY_RIVERS, Category.RIVERS).setText("Rivers"),
                        new ChooseAble(applet, 475, 200, ImageName.CATEGORY_WORLD, Category.WORLD).setText("World"),
                        new ChooseAble(applet, 575, 200, ImageName.PLACEHOLDER_SMALL, Category.ISLANDS).setText("Islands"),
                        new ChooseAble(applet, 675, 200, ImageName.PLACEHOLDER_SMALL, Category.LAKES).setText("Lakes"),
                });
        categoryChooser.updateActiveElement(categoryChooser.getElements()[0]);
    }

    public void createQuestions() {
        Practice p = IPracticeDao.getPractice(applet, this.id, category.getName());
        List<Question> tmp;
        this.test = false;
        IPracticeDao = new MyPracticeDao();
        tmp = p.getQuestionList();
        this.record_id = p.getPractice_id();
        questions = new Question[tmp.size()];
        tmp.toArray(questions);
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
        maxScore = questions.length;
    }
      public void createQuestions(int id,int test_id){
        Test t = ITestDao.attemptTest(applet, id, test_id);
        this.test = true;
        List<Question> tmp=null;
        tmp = t.getQuestionList();
        this.record_id = t.getTest_id();
        questions = new Question[tmp.size()];
        System.out.println(tmp.size());
        tmp.toArray(questions);
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
        maxScore = questions.length;
    }

    public void updateQuestionText(){
      Textarea ta = (Textarea) cp5.get("Question_Textarea");
      ta.setText(actualQuestion.getQuestion_text());
    }

    public ControlP5 getCp5(){
        return this.cp5;
    }

    public void showGameChoosing() {
        categoryChooser.show();
    }

    public void show() {
        actualQuestion.show();
    }

    public TypeChooser getCategoryChooser() {
        return categoryChooser;
    }


    public Question getActualQuestion() {
        return actualQuestion;
    }


    public Question[] getQuestions() {
        return questions;
    }

    public GameProperty getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPlaying(boolean bool) {
        this.isPlaying = bool;
        if(bool) cp5.get("Question_Textarea").show();
        else cp5.get("Question_Textarea").hide();
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
            updateQuestionText();
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

    public void loadPractiseFeedback()
    {
        ArrayList<Integer> questionId = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        int i = 0;
        for (Question q : questions)
        {
            if (q instanceof DragAndDrop_Question)
            {
                DragAndDrop_Question dad_question = (DragAndDrop_Question) q;
                //Get Text from AnswerElement
                answers.add(dad_question.getDragAndDrop().getAnswerRect().getDragAndDropElement().getText());
                questionId.add(q.getId());
                if (dad_question.getDragAndDrop().getAnswerRect().isOccupied())
                {
                    if (dad_question.getDragAndDrop().getAnswerRect().getDragAndDropElement().getText().equals(dad_question.getCorrect_answer()))
                    {
                        score++;
                    }
                }
            } else if (q instanceof Multiplichoice_Question) {
                Multiplichoice_Question mp_question = (Multiplichoice_Question) q;
                //Get right array index and check if isActive()
                questionId.add(q.getId());
                for(int j = 0 ; j<4;j++)
                {
                    if(mp_question.getCheckBox().getElements()[j].isActive())
                    {
                        answers.add(Integer.toString(j+1));
                    }
                }
                if (mp_question.getCheckBox().getElements()[Integer.parseInt(mp_question.getCorrect_answer()) - 1].isActive())
                {
                    score++;
                }
            } else if (q instanceof TrueOrFalse_Question) {
                TrueOrFalse_Question tof_question = (TrueOrFalse_Question) q;
                //Get array index and check for isActive()
                 questionId.add(q.getId());
                int tmp;
                if ("TRUE".equals(tof_question.getCorrect_answer())) {
                    tmp = 0;
                } else {
                    tmp = 1;
                }
                if(tof_question.getRadioButton().getElements()[0].isActive())
                {
                    answers.add("TRUE");
                }else{
                    answers.add("FALSE");
                }
                if (tof_question.getRadioButton().getElements()[tmp].isActive())
                {
                    score++;
                }
            } else if (q instanceof ChoosePicture_Question) {
                ChoosePicture_Question cp_question = (ChoosePicture_Question) q;
                //Get left or right picture and check if isChoosen()
               questionId.add(q.getId());
                if(cp_question.getChoosePicture().getButton_left().isChoosen())
                {
                    answers.add("1");
                }else{
                    answers.add("2");
                }
                if ("1".equals(cp_question.getCorrect_answer()))
                {
                    if (cp_question.getChoosePicture().getButton_left().isChoosen())
                    {
                        score++;
                    }
                } else if (cp_question.getChoosePicture().getButton_right().isChoosen()) {
                    score++;
                }

            }
            i++;
        }
        
     
        if (!test)
        {
            IPracticeDao.updateScore(record_id, score);
            IRecordPracticeDao.storeAnswer(record_id, questionId, answers);
        } else
        {
            ITestDao.updateScore(record_id, score);
            IRecordTestDao.storeAnswer(record_id, questionId, answers);
        }

    }

    public void setChoosenCategory() {
        this.category = categoryChooser.getActiveElement().getGameProperty();
    }


    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getId(){
        return this.id;
    }
}
