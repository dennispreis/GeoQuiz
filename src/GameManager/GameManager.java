package GameManager;

import DAOs.MyPaperDao;
import DAOs.PaperDaoInterface;
import DTOs.Question;
import DTOs.Questions.*;
import java.util.List;
import processing.core.PApplet;

public class GameManager
{

    private PaperDaoInterface IPaperDao;
    private Category category;
    private Level level;
    private Question[] questions;
    private Question actualQuestion;
    private PApplet applet;
    private int actuallQuestionIndex;
    private List<Question> questionsTmp;

    public GameManager(PApplet applet)
    {
        this.IPaperDao = new MyPaperDao();
        this.applet = applet;
        this.category = Category.MOUNTAINS;
        this.level = Level.EASY;
//        questionsTmp = IPaperDao.getRandPaper(applet);
//        System.out.println(questionsTmp.size());
//        questions = new Question[questionsTmp.size()];
//        for (int i = 0; i < questionsTmp.size(); i++)
//        {
//            questions[i] = questionsTmp.get(i);
//        }
//        System.out.println(questions.length);
        questions = new Question[]
        {
            new DragAndDrop_Question(applet, 0,
            "DragAndDrop",
            "Region",
            "What is the Capital of Ireland?",
            "Dublin"),
            new ChoosePicture_Question(applet, 1,
            "ChoosePicture",
            "Region",
            "Which of these is Paris?",
            "answerText"),
            new TrueOrFalse_Question(applet, 2,
            "TrueOrFalse",
            "Region",
            "Which of this countries are in europa?",
            "answerText"
            )
        };
        System.out.println(questions.length);
        actuallQuestionIndex = 0;
        actualQuestion = questions[actuallQuestionIndex];
//        actualQuestion = questions.get(actuallQuestionIndex);
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

    public void nextQuestion()
    {
        if (actuallQuestionIndex != questions.length - 1)
        {
            actuallQuestionIndex++;
            actualQuestion = questions[actuallQuestionIndex];
        }

    }

    public void setActuallQuestionIndex(int idx)
    {
        actuallQuestionIndex = idx;
        actualQuestion = questions[actuallQuestionIndex];
    }
}
