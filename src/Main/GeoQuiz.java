package Main;

import DAOs.*;
import DTOs.*;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
import Feedback.Feedback;
import Feedback.FeedbackAble;
import Feedback.NamePasswordNotFoundFeedBack;
import Feedback.PasscodeNotFoundFeedback;
import GameManager.GameManager;
import GameManager.gameElements.*;
import Images.ImageName;
import Languages.LanguageManager;
import Sound.SoundManager;
import Teacher.TestManager.NewTestManager;
import Teacher.TestManager.QuestionRecord;
import UI.UIManager;
import controlP5.Button;
import controlP5.*;
import ddf.minim.Minim;
import processing.core.*;
import GameManager.*;
import Teacher.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import Images.ImageMap;
import processing.event.MouseEvent;

import java.time.LocalDateTime;

public class GeoQuiz extends PApplet {

    //------------------------------------Variables and fields
    private static PasswordProcess passwordProcess = new PasswordProcess(12);
    private static ControlP5 cp5;
    private static Settings settings;
    private static TeacherDaoInterface ITeacherDao;
    private static StudentDaoInterface IStudentDao;
    private static User user;
    private static GameManager gameManager;
    private static ImageMap imageMap;
    private static SoundManager soundManager;
    private static LanguageManager languageManager;
    private static UIManager uiManager;
    private static PApplet applet;
    private static TeacherManager teacherManager;


    //------------------------------------Inner classes
    public class Settings {

        private PFont myFont, cyrilic;
        private Screen screen;
        private Color backgroundColor;
        private boolean loadingApplication;
        private String loadingApplicationText;

        public Settings() {
            myFont = createFont("Fonts/Times New Romance.ttf", 30);
            cyrilic = createFont("Fonts/cyrilic.ttf", 30);
            screen = Screen.LOGIN;
            textFont(cyrilic);
            backgroundColor = new Color(230, 87, 116);
            loadingApplication = false;
            loadingApplicationText = "Loading Application";
        }

        String getLoadingApplicationText() {
            return this.loadingApplicationText;
        }

        boolean isLoadingApplication() {
            return loadingApplication;
        }

        void setLoadingApplication(boolean bool) {
            loadingApplication = bool;
        }

        void setLoadingApplicationText(String s) {
            loadingApplicationText = s;
        }

        Color getBackgroundColor() {
            return backgroundColor;

        }

        public PFont getCyrilic() {
            return cyrilic;
        }

        public PFont getMyFont() {
            return myFont;
        }

        public Screen getScreen() {
            return screen;
        }

        void setScreen(Screen win) {
            this.screen = win;
        }
    }
    //------------------------------------Methods given by Processing

    public static void main(String[] args) {
        PApplet.main("Main.GeoQuiz", args);
    }

    public void settings() {
        size(900, 600);
    }

    public void setup() {
        settings = new Settings();
        applet = this;
        soundManager = new SoundManager(new Minim(this), new ControlP5(this));
        imageMap = new ImageMap(this);
        languageManager = new LanguageManager();
        cp5 = new ControlP5(this);
        IStudentDao = new MyStudentDao();
        ITeacherDao = new MyTeacherDao();
        cp5.setFont(settings.getMyFont());
        passwordProcess = new PasswordProcess(12);
        passwordProcess.setCurrentAttempt(LocalDateTime.now());
        uiManager = new UIManager(cp5);
        switchScreen(Screen.LOGIN);
        settings.setLoadingApplication(false);
   //     thread("loadSounds");
    }

    public void loadSounds() {
        soundManager.loadSounds();
        soundManager.loadLanguages();
    }

    public void draw() {
        switch (settings.getScreen()) {
            case LOGIN:
                showLoginBackground();
                break;
            case MAIN_MENU_STUDENT:
                showStudentMainMenu();
                break;
            case MAIN_MENU_ADMIN:
                showAdminMainMenu();
                break;
            case CHANGE_PASSWORD_ADMIN_PASSCODE:
                showChangePasswordPasscodeBackground();
                break;
            case CHANGE_PASSWORD_ADMIN:
                showChangePasswordBackground();
                break;
            case CHANGE_PASSWORD_STUDENT_ADMIN:
                showChangePasswordBackground();
                break;
            case VIEW_STUDENT_PROGRESS_ADMIN:
                showStudentPracticeView();
                break;
            case PROFILE_STUDENT:
                showStudentProfile();
                break;
            case PRACTISE_STUDENT:
                showStudentPractise();
                break;
            case WORK_STUDENT:
                showStudentWork();
                break;
            case PROFILE_SHOW_BACKGROUND:
                showStudentProfileBackground();
                break;
            case PLAYING:
                showPlaying();
                break;
            case PRACTISE_STUDENT_GAME_FEEDBACK:
                showPractiseGameFeedback();
                break;
            case CREATE_NEW_TEST:
                showNewTestBackground();
                break;
            case CREATE_NEW_QUESTION:
                showNewQuestionBackground();
                break;
            case SHOW_STUDENT_PROGRESS:
                showAdminStudentProccess();
                break;
            case SHOW_STUDENT_TEST:
                showAdminStudentTest();
                break;
            case ADMIN_STUDENTS:
                showChangeStudentPasswordBackground();
                break;
        }

        if (soundManager.isShowingMenu()) {
            showMusicMenu();
        }

        fill(0);
        textSize(20);
        textAlign(RIGHT);
        text(settings.getScreen().toString(), width - 10, 580);

    }

    //------------------------------------Event methods given by Processing. Includes mouse and key events

    public void mousePressed() {
        if (settings.getScreen().equals(Screen.PLAYING)) {
            Question question = gameManager.getActualQuestion();
            if (question.getClass().equals(DragAndDrop_Question.class)) {
                DragAndDrop_Question dad = (DragAndDrop_Question) question;
                for (DragAndDropElement element : dad.getDragAndDrop().getSolutions()) {
                    if (element.isMouseWithIn()) {
                        dad.getDragAndDrop().setDraggingElement(element);
                        dad.getDragAndDrop().setDragging(true);
                    }
                }
            } else if (question.getClass().equals(ChoosePicture_Question.class)) {
                ChoosePicture_Question cp_question = (ChoosePicture_Question) question;
                ChoosePicture cp = cp_question.getChoosePicture();
                if (cp.getButton_left().isMouseWithIn()) {
                    cp.getButton_left().setChoosen(true);
                    cp.getButton_right().setChoosen(false);
                } else if (cp.getButton_right().isMouseWithIn()) {
                    cp.getButton_left().setChoosen(false);
                    cp.getButton_right().setChoosen(true);
                }
            } else if (question.getClass().equals(TrueOrFalse_Question.class)) {
                TrueOrFalse_Question tof_question = (TrueOrFalse_Question) question;
                for (RadioButtonElement element : tof_question.getRadioButton().getElements()) {
                    if (element.isMouseWithIn()) {
                        tof_question.getRadioButton().selectElement(element);
                    }
                }
            } else if (question.getClass().equals(Multiplichoice_Question.class)) {
                Multiplichoice_Question mp_question = (Multiplichoice_Question) question;
                for (myCheckBoxElement element : mp_question.getCheckBox().getElements()) {
                    if (element.isMouseWithIn()) {
                        if (element.isActive()) {
                            element.setActive(false);
                        } else {
                            element.setActive(true);
                        }
                    }
                }
            }
        }

        if (settings.getScreen().equals(Screen.PRACTISE_STUDENT)) {
            for (ChooseAble ca : gameManager.getCategoryChooser().getElements()) {
                if (ca.isMouseWithIn()) {
                    gameManager.getCategoryChooser().updateActiveElement(ca);
                    break;
                }
            }
        }

        if (settings.getScreen().equals(Screen.CREATE_NEW_TEST)) {
            NewTestManager tm = teacherManager.getTestManager();
            for (ChooseAble ca : tm.getTypeChooser().getElements()) {
                if (ca.isMouseWithIn()) {
                    tm.getTypeChooser().updateActiveElement(ca);
                    tm.setActiveCategory(ca);

                }
            }

            if (!tm.getTypeChooser().getElements()[0].isActive()) {
                for (QuestionRecord qr : tm.getPages().get(tm.getActiveCategory()).getQuestionRecordList()) {
                    if (qr.getMyQuestionCheckBox().isMouseWithIn() && qr.isHasToBeShown()) {
                        tm.getPages().get(tm.getActiveCategory()).updateRecordActive(qr);
                    }
                }
            }
        }

        DropdownList dList = (DropdownList) soundManager.getSoundMenu().get("Sound_Language");

        if (soundManager.isShowingMenu()) {
            if (!mouseWithIn(650, 10, 170, 140) && !mouseWithIn(840, 10, 50, 50)) {
                if (!dList.isOpen()) {
                    soundManager.updateShowMenu();
                    soundManager.hideMenu();
                    uiManager.manageControllerView();
                }
            }
        }
    }

    public void mouseReleased() {
        if (settings.getScreen().equals(Screen.PLAYING)) {
            Question question = gameManager.getActualQuestion();
            if (question.getClass().equals(DragAndDrop_Question.class)) {
                DragAndDrop_Question dad_question = (DragAndDrop_Question) question;
                if (dad_question.getDragAndDrop().isDragging()) {
                    if (dad_question.getDragAndDrop().isMouseInAnswerRect()) {
                        if (!dad_question.getDragAndDrop().getAnswerRect().isOccupied() && dad_question.getDragAndDrop().getDraggingElement() != null) {
                            dad_question.getDragAndDrop().getDraggingElement().setPosition(dad_question.getDragAndDrop().getAnswerRect().getX(), dad_question.getDragAndDrop().getAnswerRect().getY());
                            dad_question.getDragAndDrop().getAnswerRect().setOccupied(true);
                            dad_question.getDragAndDrop().getAnswerRect().setDragAndDropElement(dad_question.getDragAndDrop().getDraggingElement());
                        }
                    } else if (dad_question.getDragAndDrop().getDraggingElement() != null) {
                        if (dad_question.getDragAndDrop().getDraggingElement() != null) {
                            dad_question.getDragAndDrop().getDraggingElement().setPosition(dad_question.getDragAndDrop().getDraggingElement().getPosition().x, dad_question.getDragAndDrop().getDraggingElement().getPosition().y);
                            dad_question.getDragAndDrop().getAnswerRect().setOccupied(false);
                            dad_question.getDragAndDrop().getAnswerRect().setDragAndDropElement(null);
                        }
                    }
                }
                dad_question.getDragAndDrop().setDraggingElement(null);
            } else if (question.getClass().equals(ChoosePicture.class)) {

            }
        }
    }

    public void mouseWheel(MouseEvent event) {

        if (settings.getScreen().equals(Screen.CREATE_NEW_TEST)) {
            if (!teacherManager.getTestManager().getTypeChooser().getElements()[0].isActive()) {
                if (event.getCount() == 1) {
                    teacherManager.getTestManager().getPages().get(teacherManager.getTestManager().getActiveCategory()).nextPage();
                } else if (event.getCount() == -1) {
                    teacherManager.getTestManager().getPages().get(teacherManager.getTestManager().getActiveCategory()).lastPage();
                }
            }
        }
    }


    //------------------------------------Own Methods.
    private boolean mouseWithIn(float x1, float y1, float x2, float y2) {
        return (mouseX > x1 && mouseX < x1 + x2 && mouseY > y1 && mouseY < y1 + y2);
    }

    private static void createUserInstance(int ID, boolean isTeacher) {
        if (isTeacher) {
            user = ITeacherDao.createTeacherUser(ID);
        } else {
            user = IStudentDao.createStudentUser(ID);
        }
    }

    public static void switchScreen(Screen targetScreen) {
        uiManager.updateController(settings.getScreen(), targetScreen);
        if (soundManager.isShowingMenu()) {
            soundManager.showMenu();
        }
        settings.setScreen(targetScreen);
    }

    public static void loginVerification() {

        String hashedPassword;
        String name = cp5.get(Textfield.class, "Login_Name").getText();
        String password = cp5.get(Textfield.class, "Login_Password").getText();
        //boolean isTeacher = cp5.get(Toggle.class, "Login_Role").getState();
        boolean userPreset = false;
        boolean isTeacher = false;
        int bruteForceResult;
        int ID = -1;

        for (String s : IStudentDao.getStudentUsernames()) {
            if (s.equals(name)) {
                userPreset = true;
            }
        }
        if (!userPreset) {
            for (String s : ITeacherDao.getTeacherUsernames()) {
                if (s.equals(name)) {
                    isTeacher = true;
                    userPreset = true;
                }
            }
        }
        System.out.println(userPreset);
        if (!userPreset || name.isEmpty() || password.isEmpty()) {
            //Feedback
        } else {
            if (isTeacher) {

                hashedPassword = ITeacherDao.getHash(name);
                bruteForceResult = passwordProcess.bruteForceCheck(name, password, hashedPassword);
                System.out.println(bruteForceResult);
                if (bruteForceResult == 1) {
                    ID = ITeacherDao.getAccountId(name);
                    createUserInstance(ID, true);
                    user.setTeacher(true);
                    System.out.println("Hello");
                    uiManager.createAdminElements();
                    teacherManager = new TeacherManager(applet, cp5);
                    switchScreen(Screen.MAIN_MENU_ADMIN);
                } else if (bruteForceResult == -1 && !(ITeacherDao.getAttempt(name) < 5)) {
                    cp5.get(Button.class, "Login_Login").hide();
                } else {
                    //Feedback
                }
            } else {
                ID = IStudentDao.getAccountId(name, password);
                if (ID == -1)
                {
                    (new NamePasswordNotFoundFeedBack(applet, 5000))
                            .setPosition(new PVector(750, 350))
                            .setSize(new PVector(170, 50)).show();
                }
                else
                {
                    createUserInstance(ID, false);
                    user.setTeacher(false);
                    uiManager.createStudentElements();
                    gameManager = new GameManager(applet, cp5,ID);
                    switchScreen(Screen.MAIN_MENU_STUDENT);
                }
            }
        }
    }

    public static void changePassword() {
        String name = cp5.get(Textfield.class, "Login_Name").getText();
        String password = cp5.get(Textfield.class, "Change_Password_Password").getText();
        String hash = passwordProcess.hash(password);

        if (password.isEmpty()) {
            //Feedback
        } else {
            ITeacherDao.setHash(name, hash);
            ITeacherDao.setAttempt(name, 0);
            switchScreen(Screen.LOGIN);
        }

    }

    public static void checkForPasswordChange() {
        try {

            String name = cp5.get(Textfield.class, "Login_Name").getText();
            String passcodeText = cp5.get(Textfield.class, "Passcode").getText();
            int passcode;

            if (!passcodeText.isEmpty()) {
                passcode = Integer.parseInt(passcodeText);
            } else {
                passcode = -1;
            }

            if (passcode == ITeacherDao.getPassCode(name)) {
                switchScreen(Screen.CHANGE_PASSWORD_ADMIN);
            } else {
                //Feedback
            }
        } catch (NumberFormatException ex) {
            //Feedback

        }
    }

    public static String getShortString(String s) {
        if (s.length() >= 30) {
            s = s.substring(0, 30);
            int idx = 29;
            while (s.toCharArray()[idx] != ' ') {
                s = s.substring(0, idx);
                idx--;
            }
            s += "...";
        }
        return s;
    }

    //------------------------------------Show Methods
    private void showPlaying() {
        background(ImageMap.getImage(ImageName.BACKGROUND_PLAY));
        gameManager.show();
        if (settings.getScreen().equals(Screen.PLAYING)) {
            Question question = gameManager.getActualQuestion();
            if (question.getClass().equals(DragAndDrop_Question.class)) {
                DragAndDrop_Question dad_question = (DragAndDrop_Question) question;
                if (dad_question.getDragAndDrop().isDragging() && dad_question.getDragAndDrop().getDraggingElement() != null) {
                    dad_question.getDragAndDrop().getDraggingElement().updatePos(mouseX, mouseY);
                }
                dad_question.getDragAndDrop().show();
            } else if (question.getClass().equals(ChoosePicture_Question.class)) {
                ChoosePicture_Question cp_question = (ChoosePicture_Question) question;
                cp_question.show();
            } else if (question.getClass().equals(TrueOrFalse_Question.class)) {
                TrueOrFalse_Question tof_question = (TrueOrFalse_Question) question;
                tof_question.show();
            } else if (question.getClass().equals(Multiplichoice_Question.class)) {
                Multiplichoice_Question mp_question = (Multiplichoice_Question) question;
                mp_question.show();
            }
        }
    }

    private void showPractiseGameFeedback() {
        background(imageMap.getImage(ImageName.BACKGROUND_PLAY));
        gameManager.showPractiseFeedback();
    }

    private void showLoginBackground() {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, height / 2, 300, 400);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("GeoQuiz!", 450, 110);
    }

    private void showStudentPracticeView() {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, height / 2, width - 10, height - 10);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("Practice History", 220, 10);
    }

    private void showStudentProfile() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CORNER);
        rect(360, 175, 465, 180);

        textSize(20);
        fill(255);
        textAlign(CORNER);
        stroke(255);
        line(365, 205, 820, 205);
        text(languageManager.getString("category"), 380, 200);
        text(languageManager.getString("score"), 525, 200);
        text(languageManager.getString("date"), 665, 200);

        Student stu = (Student) user;

        List<HistoryRecord> history = stu.getProfileHistory().getHistoryRecord();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            for (int i = stu.getProfileHistory().getStart(), historyIndex = 0; i < stu.getProfileHistory().getEnd(); i++, historyIndex++) {
                if (history.get(i) != null) {
                    text(languageManager.getString(history.get(i).getCategory().name().toLowerCase()), 380, 225 + 30 * historyIndex);
                    text(history.get(i).getScore(), 525, 225 + 30 * historyIndex);
                    text((formatter.format(history.get(i).getDate())), 665, 225 + 30 * historyIndex);
                }
            }
        } catch (IndexOutOfBoundsException ignore) {
        }
        textAlign(CENTER, CENTER);
        text((stu.getProfileHistory().getActualPage() + 1) + " / " + (stu.getProfileHistory().getMaxPages() + 1), 725, 400);

        textAlign(LEFT, TOP);
        text(languageManager.getString("click_to_change_avatar"), 130, 300);

        image(ImageMap.getImage(ImageMap.getImageName(((Student) user).getAvatar())), 100, 100);
    }

    private void showStudentMainMenu() {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 125);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, height / 2, 750, 325);
        textSize(20);
        textAlign(CENTER, TOP);
        fill(255);
        text(languageManager.getString("work"), 200, 420);
        text(languageManager.getString("practise"), 450, 420);
        text(languageManager.getString("profile"), 700, 420);
    }

    private void showStudentPractise() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        gameManager.showGameChoosing();
        fill(100, 125);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, 130, languageManager.getString("category").length() * 30, 50);
        //rect(width / 2, 330, languageManager.getString("level").length() * 30, 50);
        fill(255);
        textSize(50);
        textAlign(CENTER, CENTER);
        text(languageManager.getString("category"), width / 2, 125);
        //text(languageManager.getString("level"), width / 2, 325);
    }

    private void showAdminMainMenu() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, height / 2, 450, 600);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("GeoQuiz!", 450, 110);
    }

    private void showAdminStudentProccess() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CORNER);
        rect(10, 120, width - 15, height - 200);

        textSize(30);
        fill(255);
        textAlign(CORNER);
        text(languageManager.getString("Student_Practice_List--Class"), 200, 50);

        textSize(25);
        stroke(255);
        line(12, 180, width - 8, 180);
        text(languageManager.getString("student_name"), 50, 170);
        text(languageManager.getString("category"), 250, 170);
         text(languageManager.getString("score"), 450, 170);
        text(languageManager.getString("date"), 650, 170);
        textSize(20);

        Teacher teach = (Teacher)user;
        
        if(teach.getProfileHistory()!=null)
        {
        List<HistoryRecord> history = teach.getProfileHistory().getHistoryRecord();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            for (int i = teach.getProfileHistory().getStart(), historyIndex = 0; i < teach.getProfileHistory().getEnd(); i++, historyIndex++)
            {
                if (history.get(i) != null)
                {
                    text(history.get(i).getStudent_name(),50,215 + 30*historyIndex);
                    text(languageManager.getString(history.get(i).getCategory().name().toLowerCase()), 250, 215 + 30 * historyIndex);
                     text(history.get(i).getScore(),450,215+30*historyIndex);
                    text((formatter.format(history.get(i).getDate())), 650, 215 + 30 * historyIndex);
                }

            }
            } catch (IndexOutOfBoundsException ignore) {
            }
      
            textAlign(CENTER, CENTER);
            text((teach.getProfileHistory().getActualPage() + 1) + " / " + (teach.getProfileHistory().getMaxPages() + 1), 725, 540);
        }
    
       
    }
      
    private void showAdminStudentTest(){
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CORNER);
        rect(10, 120, width-15, height-200);

        textSize(30);
        fill(255);
        textAlign(CORNER);
        text(languageManager.getString("Student_Test_List--Class"),200,50);
     
        textSize(25);
        stroke(255);
        line(12, 180, width-8, 180);
        text(languageManager.getString("student_name"),50,170);
        text(languageManager.getString("test_name"),250, 170);
        text(languageManager.getString("test_id"), 410, 170);
        text(languageManager.getString("score"),550,170);
        text(languageManager.getString("date"), 665, 170);
        textSize(20);
        Teacher teach = (Teacher)user;
        if(teach.getClassList()==null)
        {
        List<HistoryRecord> history = teach.getProfileHistory().getHistoryRecord();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            for (int i = teach.getProfileHistory().getStart(), historyIndex = 0; i < teach.getProfileHistory().getEnd(); i++, historyIndex++)
            {
                if (history.get(i) != null)
                {
                    text(history.get(i).getStudent_name(),50,215 + 30*historyIndex);
                    text(history.get(i).getTest_name(), 250, 215 + 30 * historyIndex);
                    text(history.get(i).getRecord_id(), 410, 215 + 30 * historyIndex);
                    text(history.get(i).getScore(),550,215+30*historyIndex);
                    text((formatter.format(history.get(i).getDate())), 665, 215 + 30 * historyIndex);
                }
            }
            
            
            
        } catch (IndexOutOfBoundsException ignore)
        {
        }
        textAlign(CENTER, CENTER);
        text((teach.getProfileHistory().getActualPage()+1) + " / " + (teach.getProfileHistory().getMaxPages()+1), 725, 540);
        }
    }

    private void showStudentWork() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CORNER);
        rect(10, 120, width - 15, height - 200);

        textSize(30);
        fill(255);
        textAlign(CORNER);
        text(languageManager.getString("TestList"), 200, 50);

        textSize(25);
        stroke(255);
        line(12, 180, width - 8, 180);
        text(languageManager.getString("test_id"), 50, 170);
        text(languageManager.getString("test_name"), 250, 170);
        text(languageManager.getString("date_close"), 410, 170);
        text(languageManager.getString("attempt"), 650, 170);
        textSize(20);
        TestDaoInterface ITestDao = new MyTestDao();
        List<Test> testList =  ITestDao.getAllTest();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            for (int i = 0;i< testList.size(); i++)
            {
                if (testList.get(i) != null)
                {
                    text(testList.get(i).getTest_id(),50,215 + 30*i);
                    text(testList.get(i).getTest_name(), 250, 215 + 30 * i);
                    text("Date",410,215+30*i);
//                    text((formatter.format(testList.get(i).getDate())), 665, 215 + 30 * i);
//                    text("Attempt",650,215+30*i);
                }
            }
            
            
            
        } catch (IndexOutOfBoundsException ignore)
        {
        }
        textAlign(CENTER, CENTER);
        
    }

    private void showChangePasswordBackground() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, height / 2, user.isTeacher() ? 450 : 300, 400);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text(languageManager.getString("Change_Password"), 450, 100);
        textSize(30);
        textAlign(CENTER, TOP);
    }

    private void showChangePasswordPasscodeBackground() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, 250, user.isTeacher() ? 450 : 300, 300);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("Passcode Check", width / 2, 110);
        textSize(30);
        textAlign(CENTER, TOP);
    }

    private void showMusicMenu() {
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CORNER);
        rect(650, 10, 170, 140);

        textSize(25);
        fill(255);
        textAlign(LEFT, TOP);
        text(languageManager.getString("sound"), 700, 20);
        text(languageManager.getString("volume"), 660, 50);
        text(languageManager.getString("language"), 660, 95);
    }

    private void showStudentProfileBackground() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
    }

    private void showChangeStudentPasswordBackground() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
    }

    private void showNewTestBackground() {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        teacherManager.getTestManager().show();
    }
    
    private void showNewQuestionBackground(){
         background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
    }

    //-------------------------------------Get Instances

    public static PImage getImage(ImageName name) {
        return ImageMap.getImage(name);
    }

    public static Settings getSettings() {
        return settings;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static UIManager getUiManager() {
        return uiManager;
    }

    public static SoundManager getSoundManager() {
        return soundManager;
    }

    public static LanguageManager getLanguageManager() {
        return languageManager;
    }

    public static User getUser() {
        return user;
    }

    public static StudentDaoInterface getIStudentDao() {
        return IStudentDao;

    }

    public static TeacherManager getTeacherManager() {
        return teacherManager;
    }

    public static ControlP5 getCP5() {
        return cp5;
    }

    public static PApplet getApplet() {
        return applet;
    }
}
