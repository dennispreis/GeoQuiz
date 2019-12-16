package Teacher.TestManager;

import DAOs.MyQuestionDao;
import DTOs.Question;
import DTOs.Questions.DragAndDrop_Question;
import GameManager.Category;
import GameManager.ChooseAble;
import GameManager.TypeChooser;
import Images.ImageName;
import Main.GeoQuiz;
import controlP5.ControlP5;
import controlP5.Textarea;
import controlP5.Textlabel;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.TOP;

public class NewTestManager {

    private ControlP5 cp5;
    private PApplet applet;
    private TypeChooser typeChooser;
    private List<QuestionRecord> markedQuestions;
    private int numOfMarkedQuestions;
    private HashMap<Category, MarkableQuestionList> pages;
    private Category activeCategory;

    public NewTestManager(PApplet applet, ControlP5 cp5) {
        this.applet = applet;
        this.cp5 = cp5;
        typeChooser = new TypeChooser(applet);
        loadTypeChooser();
        markedQuestions = new ArrayList<>();
        pages = new HashMap<>(6);
        loadPages();
    }

    public void show() {
        typeChooser.show();
        applet.rectMode(CORNER);
        applet.stroke(0);
        applet.strokeWeight(2);
        applet.fill(100, 120);
        applet.textAlign(LEFT, TOP);
        if (typeChooser.getElements()[0].isActive()) {
            applet.rect(100, 150, 300, 425);
            applet.stroke(255);
            applet.line(105, 185, 395, 185);
            applet.textSize(20);
            applet.fill(255);
            applet.text(GeoQuiz.getLanguageManager().getString("questionText"), 150, 165);
            for (int i = 0; i < markedQuestions.size(); i++) {
                markedQuestions.get(i).show();
            }
        } else {
            pages.get(activeCategory).show();
        }
    }

    public List<Question> getQuestionList() {
        List<Question> tmp = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tmp.add(markedQuestions.get(i).getQuestion());
        }
        return tmp;
    }

    void addMarkedQuestion(QuestionRecord question) {
        QuestionRecord clone = question.Clone();
        markedQuestions.add(clone);
        markedQuestions.get(markedQuestions.size() - 1).getMyQuestionCheckBox().setIsShowing(false);
        markedQuestions.get(markedQuestions.indexOf(clone)).setPosition(120, 200 + 30 * (markedQuestions.size() - 1));
    }

    void removeMarkedQuestion(QuestionRecord question) {
        for (int i = 0; i < markedQuestions.size(); i++) {
            if (markedQuestions.get(i).getQuestion().getId() == question.getQuestion().getId()) {
                markedQuestions.remove(i);
                if (markedQuestions.size() > i) {
                    for (int j = i; j < markedQuestions.size(); j++) {
                        markedQuestions.get(j).setPosition(120, 200 + (30 * j));
                    }
                }
                break;
            }
        }
    }

    public List<QuestionRecord> getMarkedQuestions() {
        return markedQuestions;
    }

    public HashMap<Category, MarkableQuestionList> getPages() {
        return pages;
    }

    public Category getActiveCategory() {
        return activeCategory;
    }

    public void setActiveCategory(ChooseAble ca) {
        if (ca.getGameProperty().getName().equals("cities")) this.activeCategory = Category.CITIES;
        if (ca.getGameProperty().getName().equals("mountains")) this.activeCategory = Category.MOUNTAINS;
        if (ca.getGameProperty().getName().equals("rivers")) this.activeCategory = Category.RIVERS;
        if (ca.getGameProperty().getName().equals("world")) this.activeCategory = Category.WORLD;
        if (ca.getGameProperty().getName().equals("islands")) this.activeCategory = Category.ISLANDS;
        if (ca.getGameProperty().getName().equals("lakes")) this.activeCategory = Category.LAKES;
    }

    public TypeChooser getTypeChooser() {
        return this.typeChooser;
    }

    int getNumOfMarkedQuestions() {
        return numOfMarkedQuestions;
    }

    void increaseMarkedQuestions() {
        this.numOfMarkedQuestions++;
    }

    void decreaseMarkedQuestions() {
        this.numOfMarkedQuestions--;
    }

    void setMarkedQuestions(int value) {
        this.numOfMarkedQuestions = value;
    }

    public void resetMarkedQuestions() {
        for (MarkableQuestionList mqList : pages.values()) {
            mqList.resetMarkedQuestions();
        }
        markedQuestions.clear();
    }

    public void loadPages() {
        MyQuestionDao myQuestionDao = new MyQuestionDao();
        pages.put(Category.CITIES, new MarkableQuestionList(applet, myQuestionDao.getAllQuestionByType(applet, "cities")));
        pages.put(Category.MOUNTAINS, new MarkableQuestionList(applet, myQuestionDao.getAllQuestionByType(applet, "mountains")));
        pages.put(Category.RIVERS, new MarkableQuestionList(applet, myQuestionDao.getAllQuestionByType(applet, "rivers")));
        pages.put(Category.WORLD, new MarkableQuestionList(applet, myQuestionDao.getAllQuestionByType(applet, "world")));
        pages.put(Category.ISLANDS, new MarkableQuestionList(applet, myQuestionDao.getAllQuestionByType(applet, "islands")));
        pages.put(Category.LAKES, new MarkableQuestionList(applet, myQuestionDao.getAllQuestionByType(applet, "lakes")));
    }

    private void loadTypeChooser() {
        typeChooser = new TypeChooser(applet).setElements(new ChooseAble[]{
                new ChooseAble(applet, 575, 75, ImageName.PLACEHOLDER_SMALL, Category.TMP).setText(""),
                new ChooseAble(applet, 20, 150, ImageName.CATEGORY_CITIES, Category.CITIES).setText(""),
                new ChooseAble(applet, 20, 225, ImageName.CATEGORY_MOUNTAINS, Category.MOUNTAINS).setText(""),
                new ChooseAble(applet, 20, 300, ImageName.CATEGORY_WORLD, Category.WORLD).setText(""),
                new ChooseAble(applet, 20, 375, ImageName.CATEGORY_RIVERS, Category.RIVERS).setText(""),
                new ChooseAble(applet, 20, 450, ImageName.PLACEHOLDER_SMALL, Category.ISLANDS).setText(""),
                new ChooseAble(applet, 20, 525, ImageName.PLACEHOLDER_SMALL, Category.LAKES).setText("")

        });
        typeChooser.updateActiveElement(typeChooser.getElements()[0]);
    }

}
