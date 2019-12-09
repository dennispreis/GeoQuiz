package Teacher.TestManager;

import GameManager.Category;
import GameManager.ChooseAble;
import GameManager.TypeChooser;
import Images.ImageName;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class NewTestManager {

    private PApplet applet;
    private TypeChooser typeChooser;
    private List<QuestionRecord> questionRecordList;
    private int start, end, page, maxPage;
    private int numOfRecords, numOfQuestionsMarked, numOfRecordOnOnePage;

    public NewTestManager(PApplet applet) {
        this.applet = applet;
        typeChooser = new TypeChooser(applet);
        loadTypeChooser();
        numOfQuestionsMarked = 0;
        numOfRecords = 37;
        numOfRecordOnOnePage = 12;
        start = 0;
        end = numOfRecordOnOnePage;
        page = 0;
        maxPage = (numOfRecords / numOfRecordOnOnePage) - 1;
        questionRecordList = new ArrayList<>();
        loadQuestionRecordList();
    }

    public TypeChooser getTypeChooser() {
        return this.typeChooser;
    }

    public List<QuestionRecord> getQuestionRecordList() {
        return this.questionRecordList;
    }

    public void updateRecordActive(QuestionRecord qr) {
        if (!qr.getMyQuestionCheckBox().isActive()) {
            if (numOfQuestionsMarked != 10) {
                qr.getMyQuestionCheckBox().setActive(true);
                increaseQuestionCounter();
            }
        } else {
            qr.getMyQuestionCheckBox().setActive(false);
            decreaseQuestionCounter();
        }
        System.out.println(numOfQuestionsMarked);
    }

    private void increaseQuestionCounter() {
        numOfQuestionsMarked++;
    }

    private void decreaseQuestionCounter() {
        numOfQuestionsMarked--;
    }

    public int getNumOfQuestionsMarked() {
        return this.numOfQuestionsMarked;
    }

    public void resetMarkedQuestions() {
        for (QuestionRecord qr : questionRecordList) {
            qr.getMyQuestionCheckBox().setActive(false);
        }
        numOfQuestionsMarked = 0;
    }

    public void nextPage() {
        if (page != maxPage) {
            try {
                for (int i = start; i < end; i++) {
                    questionRecordList.get(i).setHasToBeShown(false);
                }
            } catch (Exception ignore) {
            }
            start += 15;
            end += 15;
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
            start -= 15;
            end -= 15;
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

    private void loadQuestionRecordList() {
        for (int qCounter = 0, i = 0; qCounter < numOfRecords; qCounter++, i++) {
            if (i % numOfRecordOnOnePage == 0) i = 0;
            questionRecordList.add(new QuestionRecord(applet, qCounter, 115, 202 + (30 * i)));

            if (qCounter < numOfRecordOnOnePage) {
                questionRecordList.get(qCounter).setHasToBeShown(true);
            }
        }
    }

    private void loadTypeChooser() {
        typeChooser = new TypeChooser(applet).setElements(new ChooseAble[]{
                new ChooseAble(applet, 575, 75, ImageName.PLACEHOLDER_SMALL, Category.TMP).setText(""),
                new ChooseAble(applet, 20, 150, ImageName.CATEGORY_CITIES, Category.CITIES).setText(""),
                new ChooseAble(applet, 20, 225, ImageName.CATEGORY_MOUNTAINS, Category.MOUNTAINS).setText(""),
                new ChooseAble(applet, 20, 300, ImageName.CATEGORY_WORLD, Category.WORLD).setText(""),
                new ChooseAble(applet, 20, 375, ImageName.CATEGORY_RIVERS, Category.RIVERS).setText(""),
                new ChooseAble(applet, 20, 450, ImageName.PLACEHOLDER_SMALL, Category.TMP).setText(""),
                new ChooseAble(applet, 20, 525, ImageName.PLACEHOLDER_SMALL, Category.TMP).setText("")

        });
        typeChooser.updateActiveElement(typeChooser.getElements()[0]);
    }

}
