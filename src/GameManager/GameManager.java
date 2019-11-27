package GameManager;

import DAOs.MyPaperDao;
import DAOs.MyPracticeDao;
import DAOs.PaperDaoInterface;
import DAOs.PracticeDaoInterface;
import DTOs.Question;
import DTOs.Questions.*;
import java.util.List;
import processing.core.PApplet;
import GameManager.gameElements.DragAndDrop;
import static processing.core.PConstants.CENTER;

public class GameManager
{

    private PracticeDaoInterface IPracticeDao;
    private PApplet applet;
    private Category category;
    private Level level;
    private Question[] questions;
    private Question actualQuestion;
    private int actuallQuestionIndex;
    private List<Question> questionsTmp;
    private int score, maxScore;
    private int id;
    
    public GameManager(PApplet applet)
    {
        this.IPracticeDao = new MyPracticeDao();
        this.applet = applet;
        this.category = Category.MOUNTAINS;
        this.level = Level.EASY;

        questionsTmp = IPracticeDao.getPractice(applet,1,"tmp","tmp");
        System.out.println(questionsTmp.size());
        questions = new Question[questionsTmp.size()];
        for (int i = 0; i < questionsTmp.size(); i++)
        {
            questions[i] = questionsTmp.get(i);
        }
//        System.out.println(questions.length);
//        questions = new Question[]
//        {
//            new DragAndDrop_Question(applet, 0,
//            "RIVER",
//            "CAPITAL",
//            "What is the Capital of Ireland?",
//            "Dublin"),
//            new ChoosePicture_Question(applet, 1,
//            "MOUNTAIN",
//            "Region",
//            "Which of these is Paris?",
//            "1"),
//            new TrueOrFalse_Question(applet, 2,
//            "Capital",
//            "Region",
//            "Which country is in europe?",
//            "TRUE"
//            ),
//            new Multiplichoice_Question(applet, 3,
//            "General",
//            "Region",
//            "Which cities are in Ireland?",
//            "1")
//        };
//        System.out.println(questions.length);
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
//        actualQuestion = questions.get(actuallQuestionIndex);
        score = 0;
        maxScore = questions.length;
    }

    public GameManager(PApplet applet, int id,String category,String level)
    {
        this.IPracticeDao = new MyPracticeDao();
        this.applet = applet;
        this.category = Category.MOUNTAINS;
        this.level = Level.EASY;
        this.id = id;
        questionsTmp = IPracticeDao.getPractice(applet,id,"tmp","tmp");
        System.out.println(questionsTmp.size());
        questions = new Question[questionsTmp.size()];
        for (int i = 0; i < questionsTmp.size(); i++)
        {
            questions[i] = questionsTmp.get(i);
        }
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
        score = 0;
        maxScore = questions.length;
    }

    public void show()
    {
        actualQuestion.show();
    }

    public Question getActualQuestion()
    {
        return actualQuestion;
    }

    public void increaseLevel()
    {
    }

    public void decreaseLevel()
    {
    }

    public void increaseCategory()
    {

    }

    public void decreaseCategory()
    {

    }

    public Level getLevel()
    {
        return level;
    }

    public Question[] getQuestions()
    {
        return questions;
    }

    public void setLevel(Level level)
    {
        this.level = level;
    }

    public Category getCategory()
    {
        return category;
    }

    public void setCategory(Category category)
    {
        this.category = category;
    }

    public void reset()
    {
        this.score = 0;
        for (Question q : questions)
        {
            q.reset();
        }

    }

    public boolean nextQuestion()
    {
        if (actuallQuestionIndex != questions.length - 1)
        {
            actuallQuestionIndex++;
            actualQuestion = questions[actuallQuestionIndex];
            return true;
        }
        return false;
    }

    public void setActuallQuestionIndex(int idx)
    {
        actuallQuestionIndex = idx;
        actualQuestion = questions[actuallQuestionIndex];
    }

    public void showPractiseFeedback()
    {
        applet.textSize(50);
        applet.textAlign(CENTER, CENTER);
        applet.text("Score : " + score + " / " + maxScore, applet.width / 2, applet.height / 2);
    }

    public void loadPractiseFeedback()
    {
        for (Question q : questions)
        {
            if (q instanceof DragAndDrop_Question)
            {
                DragAndDrop_Question dad_question = (DragAndDrop_Question) q;
                //Get Text from AnswerElement
                if (dad_question.getDragAndDrop().getAnswerRect().isOccupied())
                {
                    if (dad_question.getDragAndDrop().getAnswerRect().getDragAndDropElement().getText().equals(dad_question.getCorrect_answer()))
                    {
                        score++;
                    }
                }
            } else if (q instanceof Multiplichoice_Question)
            {
                Multiplichoice_Question mp_question = (Multiplichoice_Question) q;
                //Get right array index and check if isActive()
                System.out.println(mp_question.getCorrect_answer());

                if (mp_question.getCheckBox().getElements()[Integer.parseInt(mp_question.getCorrect_answer()) - 1].isActive())
                {
                    score++;
                }
//                if (mp_question.getCheckBox().getElements()[0].isActive() && mp_question.getCheckBox().getElements()[2].isActive())
//                {
//                    score++;
//                }
            } else if (q instanceof TrueOrFalse_Question)
            {
                TrueOrFalse_Question tof_question = (TrueOrFalse_Question) q;
                //Get array index and check for isActive()
                int tmp;
                if ("TRUE".equals(tof_question.getCorrect_answer()))
                {
                    tmp = 0;
                } else
                {
                    tmp = 1;
                }

                if (tof_question.getRadioButton().getElements()[tmp].isActive())
                {
                    score++;
                }
            } else if (q instanceof ChoosePicture_Question)
            {
                ChoosePicture_Question cp_question = (ChoosePicture_Question) q;
                //Get left or right picture and check if isChoosen()
                if ("1".equals(cp_question.getCorrect_answer()))
                {
                    if (cp_question.getChoosePicture().getButton_left().isChoosen())
                    {
                        score++;
                    }
                } else if (cp_question.getChoosePicture().getButton_right().isChoosen())
                {
                    score++;
                }

            }
        }
    }
}
