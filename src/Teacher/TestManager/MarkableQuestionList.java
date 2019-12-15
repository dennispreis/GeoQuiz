package Teacher.TestManager;

import DTOs.Question;
import Main.GeoQuiz;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarkableQuestionList {

    private PApplet applet;
    private List<QuestionRecord> questionRecordList;
    private List<Question> questionList;

    private int start, end, page, maxPage;
    private int numOfRecords, numOfRecordOnOnePage;
    private Map<Integer,String> answers;

    MarkableQuestionList(PApplet applet, List<Question> questionList) {
        this.applet = applet;
        start = 0;
        numOfRecordOnOnePage = 12;
        this.questionList = questionList;
        setQuestionList();
        numOfRecords = questionRecordList.size();
        end = numOfRecordOnOnePage;
        page = 0;
        maxPage = (((int) Math.ceil((double) numOfRecords / numOfRecordOnOnePage)) - 1);
    }

    MarkableQuestionList(PApplet applet, List<Question> questionList,Map<Integer,String> answers) {
        this.applet = applet;
        start = 0;
        numOfRecordOnOnePage = 12;
        this.questionList = questionList;
        this.answers = answers;
        setQuestionListWithAnswer();
        numOfRecords = questionRecordList.size();
        end = numOfRecordOnOnePage;
        page = 0;
        maxPage = (((int) Math.ceil((double) numOfRecords / numOfRecordOnOnePage)) - 1);
    }
    
    private void setQuestionList() {
        if (questionList == null) System.out.println("null");
        questionRecordList = new ArrayList<>();
        System.out.println(questionList.size());
        for (int i = 0, j = 0; i < questionList.size(); i++, j++) {
            if (j == numOfRecordOnOnePage) j = 0;
            questionRecordList.add(new QuestionRecord(applet, questionList.get(i), i, 120, 200 + 30 * j));
            if (i < numOfRecordOnOnePage) questionRecordList.get(i).setHasToBeShown(true);
        }
    }

    private void setQuestionListWithAnswer()
    {
         if (questionList == null) System.out.println("null");
        questionRecordList = new ArrayList<>();
        System.out.println(questionList.size());
        for (int i = 0, j = 0; i < questionList.size(); i++, j++) {
            if (j == numOfRecordOnOnePage) j = 0;
            questionRecordList.add(new QuestionRecord(applet, questionList.get(i), i, 120, 200 + 30 * j,answers.get(questionList.get(i).getId())));
            if (i < numOfRecordOnOnePage) questionRecordList.get(i).setHasToBeShown(true);
        }
    }
    
    public void show() {
        applet.rect(100, 150, 300, 425);
        applet.stroke(255);
        applet.line(105, 185, 395, 185);
        applet.textSize(20);
        applet.fill(255);
        applet.text(GeoQuiz.getLanguageManager().getString("questionText"), 150, 165);
        applet.text((page + 1) + " / " + (maxPage + 1), 350, 165);
        try {
            for (int i = start, y = 0; i < end; i++, y++) {
                questionRecordList.get(i).show();
            }
        } catch (Exception ignore) {
        }
    }

    public void updateRecordActive(QuestionRecord qr) {
        if (!qr.getMyQuestionCheckBox().isActive()) {
            if (GeoQuiz.getTeacherManager().getTestManager().getNumOfMarkedQuestions() != 10) {
                qr.getMyQuestionCheckBox().setActive(true);
                GeoQuiz.getTeacherManager().getTestManager().addMarkedQuestion(qr);
                increaseQuestionCounter();
            }
        } else {
            qr.getMyQuestionCheckBox().setActive(false);
            GeoQuiz.getTeacherManager().getTestManager().removeMarkedQuestion(qr);
            decreaseQuestionCounter();
        }
    }

    private void increaseQuestionCounter() {
        GeoQuiz.getTeacherManager().getTestManager().increaseMarkedQuestions();
    }

    private void decreaseQuestionCounter() {
        GeoQuiz.getTeacherManager().getTestManager().decreaseMarkedQuestions();
    }

    void resetMarkedQuestions() {
        for (QuestionRecord qr : questionRecordList) {
            qr.getMyQuestionCheckBox().setActive(false);
        }
        GeoQuiz.getTeacherManager().getTestManager().setMarkedQuestions(0);
    }

    public void nextPage() {
        if (page != maxPage) {
            try {
                for (int i = start; i < end; i++) {
                    questionRecordList.get(i).setHasToBeShown(false);
                }
            } catch (Exception ignore) {
            }
            start += numOfRecordOnOnePage;
            end += numOfRecordOnOnePage;
            page++;
            try {
                for (int i = start; i < end; i++) {
                    questionRecordList.get(i).setHasToBeShown(true);
                }
            } catch (Exception ignore) {
            }
        }
    }

    public void lastPage() {
        if (page != 0) {
            try {
                for (int i = start; i < end; i++) {
                    questionRecordList.get(i).setHasToBeShown(false);
                }
            } catch (Exception ignore) {
            }
            start -= numOfRecordOnOnePage;
            end -= numOfRecordOnOnePage;
            page--;
            try {
                for (int i = start; i < end; i++) {
                    questionRecordList.get(i).setHasToBeShown(true);
                }
            } catch (Exception ignore) {
            }
        }
    }

    public int getStart() {
        return this.start;
    }

    public int getEnd() {
        return this.end;
    }

    public int getPage() {
        return this.page;
    }

    public int getMaxPage() {
        return this.maxPage;
    }

    public List<QuestionRecord> getQuestionRecordList() {
        return this.questionRecordList;
    }
}
