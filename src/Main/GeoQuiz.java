package Main;

import DAOs.MyStudentDao;
import DAOs.MyTeacherDao;
import DAOs.StudentDaoInterface;
import DAOs.TeacherDaoInterface;
import DTOs.*;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
import Feedback.FeedbackAble;
import Feedback.NamePasswordNotFoundFeedBack;
import Feedback.PasscodeNotFoundFeedback;
import GameManager.GameManager;
import GameManager.gameElements.*;
import Images.ImageName;
import Languages.LanguageManager;
import Sound.SoundManager;
import UI.UIManager;
import controlP5.Button;
import controlP5.*;
import ddf.minim.Minim;
import processing.core.*;
import GameManager.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

import Images.ImageMap;

import java.time.LocalDateTime;

public class GeoQuiz extends PApplet
{

    //------------------------------------Variables and fields
    private static PasswordProcess passwordProcess = new PasswordProcess(12);
    private static ControlP5 cp5;
    private static Settings settings;
    private static TeacherDaoInterface ITeacherDao;
    private static StudentDaoInterface IStudentDao;
    private static List<FeedbackAble> feedbackList;
    private static User user;
    private static GameManager gameManager;
    private static ImageMap imageMap;
    private static SoundManager soundManager;
    private static LanguageManager languageManager;
    private static UIManager uiManager;
    private static PApplet applet;

    //------------------------------------Inner classes
    public class Settings
    {

        private PFont myFont, cyrilic;
        private Screen screen;
        private Color backgroundColor;

        public Settings()
        {
            myFont = createFont("Fonts/Times New Romance.ttf", 30);
            cyrilic = createFont("Fonts/cyrilic.ttf", 30);
            screen = Screen.LOGIN_STUDENT;
            textFont(cyrilic);
            backgroundColor = new Color(230, 87, 116);
        }

        Color getBackgroundColor()
        {
            return backgroundColor;

        }

        public PFont getCyrilic()
        {
            return cyrilic;
        }

        public PFont getMyFont()
        {
            return myFont;
        }

        public Screen getScreen()
        {
            return screen;
        }

        void setScreen(Screen win)
        {
            this.screen = win;
        }
    }
    //------------------------------------Methods given by Processing

    public static void main(String[] args)
    {
        PApplet.main("Main.GeoQuiz", args);
    }

    public void settings()
    {
        size(900, 600);
    }

    public void setup()
    {
        applet = this;
        imageMap = new ImageMap(this);
        languageManager = new LanguageManager();
        settings = new Settings();
        cp5 = new ControlP5(this);
        soundManager = new SoundManager(new Minim(this), new ControlP5(this));
        IStudentDao = new MyStudentDao();
        ITeacherDao = new MyTeacherDao();
        feedbackList = new ArrayList<>();
        cp5.setFont(settings.getMyFont());
        passwordProcess = new PasswordProcess(12);
        passwordProcess.setCurrentAttempt(LocalDateTime.now());
        uiManager = new UIManager(cp5);
        switchScreen(Screen.LOGIN_STUDENT);
    }

    public void draw()
    {
        switch (settings.getScreen())
        {
            case LOGIN_STUDENT:
                showLoginBackground();
                break;
            case MAIN_MENU_STUDENT:
                showStudentMainMenu();
                break;
            case MAIN_MENU_ADMIN:
                showLoginBackground();
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
        }

        for (FeedbackAble f : feedbackList)
        {
            f.show();
        }

        if (soundManager.isShowingMenu())
        {
            showMusicMenu();
        }

        fill(0);
        textSize(20);
        textAlign(RIGHT);
        text(settings.getScreen().toString(), width - 10, 580);

    }

    //------------------------------------Event methods given by Processing. Includes mouse and key events
    public void keyPressed()
    {
    }

    public void mousePressed()
    {
        if (settings.getScreen().equals(Screen.PLAYING))
        {
            Question question = gameManager.getActualQuestion();
            if (question.getClass().equals(DragAndDrop_Question.class))
            {
                DragAndDrop_Question dad = (DragAndDrop_Question) question;
                for (DragAndDropElement element : dad.getDragAndDrop().getSolutions())
                {
                    if (element.isMouseWithIn())
                    {
                        dad.getDragAndDrop().setDraggingElement(element);
                        dad.getDragAndDrop().setDragging(true);
                    }
                }
            } else if (question.getClass().equals(ChoosePicture_Question.class))
            {
                ChoosePicture_Question cp_question = (ChoosePicture_Question) question;
                ChoosePicture cp = cp_question.getChoosePicture();
                if (cp.getButton_left().isMouseWithIn())
                {
                    cp.getButton_left().setChoosen(true);
                    cp.getButton_right().setChoosen(false);
                } else if (cp.getButton_right().isMouseWithIn())
                {
                    cp.getButton_left().setChoosen(false);
                    cp.getButton_right().setChoosen(true);
                }
            } else if (question.getClass().equals(TrueOrFalse_Question.class))
            {
                TrueOrFalse_Question tof_question = (TrueOrFalse_Question) question;
                for (RadioButtonElement element : tof_question.getRadioButton().getElements())
                {
                    if (element.isMouseWithIn())
                    {
                        tof_question.getRadioButton().selectElement(element);
                    }
                }
            } else if (question.getClass().equals(Multiplichoice_Question.class))
            {
                Multiplichoice_Question mp_question = (Multiplichoice_Question) question;
                for (myCheckBoxElement element : mp_question.getCheckBox().getElements())
                {
                    if (element.isMouseWithIn())
                    {
                        if (element.isActive())
                        {
                            element.setActive(false);
                        } else
                        {
                            element.setActive(true);
                        }
                    }
                }
            }
        }

        if (settings.getScreen().equals(Screen.PRACTISE_STUDENT))
        {
            for (ChooseAble ca : gameManager.getCategoryChooser().getElements())
            {
                if (ca.isMouseWithIn())
                {
                    gameManager.getCategoryChooser().updateActiveElement(ca);
                    break;
                }
            }
            for (ChooseAble ca : gameManager.getLevelChooser().getElements())
            {
                if (ca.isMouseWithIn())
                {
                    gameManager.getLevelChooser().updateActiveElement(ca);
                    break;
                }
            }
        }

        DropdownList dList = (DropdownList) soundManager.getSoundMenu().get("Sound_Language");

        if (soundManager.isShowingMenu())
        {
            if (!mouseWithIn(650, 10, 170, 140) && !mouseWithIn(840, 10, 50, 50))
            {
                if (!dList.isOpen())
                {
                    soundManager.updateShowMenu();
                    soundManager.hideMenu();
                    uiManager.manageControllerView();
                }
            }
        }
    }

    public void mouseReleased()
    {
        if (settings.getScreen().equals(Screen.PLAYING))
        {
            Question question = gameManager.getActualQuestion();
            if (question.getClass().equals(DragAndDrop_Question.class))
            {
                DragAndDrop_Question dad_question = (DragAndDrop_Question) question;
                if (dad_question.getDragAndDrop().isDragging())
                {
                    if (dad_question.getDragAndDrop().isMouseInAnswerRect())
                    {
                        if (!dad_question.getDragAndDrop().getAnswerRect().isOccupied() && dad_question.getDragAndDrop().getDraggingElement() != null)
                        {
                            dad_question.getDragAndDrop().getDraggingElement().setPosition(dad_question.getDragAndDrop().getAnswerRect().getX(), dad_question.getDragAndDrop().getAnswerRect().getY());
                            dad_question.getDragAndDrop().getAnswerRect().setOccupied(true);
                            dad_question.getDragAndDrop().getAnswerRect().setDragAndDropElement(dad_question.getDragAndDrop().getDraggingElement());
                        }
                    } else if (dad_question.getDragAndDrop().getDraggingElement() != null)
                    {
                        if (dad_question.getDragAndDrop().getDraggingElement() != null)
                        {
                            dad_question.getDragAndDrop().getDraggingElement().setPosition(dad_question.getDragAndDrop().getDraggingElement().getPosition().x, dad_question.getDragAndDrop().getDraggingElement().getPosition().y);
                            dad_question.getDragAndDrop().getAnswerRect().setOccupied(false);
                            dad_question.getDragAndDrop().getAnswerRect().setDragAndDropElement(null);
                        }
                    }
                }
                dad_question.getDragAndDrop().setDraggingElement(null);
            } else if (question.getClass().equals(ChoosePicture.class))
            {

            }
        }
    }

    //------------------------------------Own Methods.
    private boolean mouseWithIn(float x1, float y1, float x2, float y2)
    {
        return (mouseX > x1 && mouseX < x1 + x2 && mouseY > y1 && mouseY < y1 + y2);
    }

    private void createUserInstance(int ID, boolean isTeacher)
    {
        if (isTeacher)
        {
            user = ITeacherDao.createTeacherUser(ID);
        } else
        {
            user = IStudentDao.createStudentUser(ID);
        }
    }

    public static void switchScreen(Screen targetScreen)
    {
        uiManager.updateController(settings.getScreen(), targetScreen);
        if (soundManager.isShowingMenu())
        {
            soundManager.showMenu();
        }
        settings.setScreen(targetScreen);
    }

    public static PImage getImage(ImageName name)
    {
        return ImageMap.getImage(name);
    }

    public static Settings getSettings()
    {
        return settings;
    }

    public static GameManager getGameManager()
    {
        return gameManager;
    }

    public static UIManager getUiManager()
    {
        return uiManager;
    }

    public static SoundManager getSoundManager()
    {
        return soundManager;
    }

    public static LanguageManager getLanguageManager()
    {
        return languageManager;
    }

    public static User getUser()
    {
        return user;
    }

    //------------------------------------Show Methods
    private void showPlaying()
    {
        background(ImageMap.getImage(ImageName.BACKGROUND_PLAY));
        gameManager.show();
        if (settings.getScreen().equals(Screen.PLAYING))
        {
            Question question = gameManager.getActualQuestion();
            if (question.getClass().equals(DragAndDrop_Question.class))
            {
                DragAndDrop_Question dad_question = (DragAndDrop_Question) question;
                if (dad_question.getDragAndDrop().isDragging() && dad_question.getDragAndDrop().getDraggingElement() != null)
                {
                    dad_question.getDragAndDrop().getDraggingElement().updatePos(mouseX, mouseY);
                }
                dad_question.getDragAndDrop().show();
            } else if (question.getClass().equals(ChoosePicture_Question.class))
            {
                ChoosePicture_Question cp_question = (ChoosePicture_Question) question;
                cp_question.show();
            } else if (question.getClass().equals(TrueOrFalse_Question.class))
            {
                TrueOrFalse_Question tof_question = (TrueOrFalse_Question) question;
                tof_question.show();
            } else if (question.getClass().equals(Multiplichoice_Question.class))
            {
                Multiplichoice_Question mp_question = (Multiplichoice_Question) question;
                mp_question.show();
            }
        }
    }

    private void showPractiseGameFeedback()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_PLAY));
        gameManager.showPractiseFeedback();
    }

    private void showLoginBackground()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, height / 2, 300, 400);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("GeoQuiz!", 450, 100);
    }

    private void showStudentPracticeView()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width/2, height/2, width-10, height-10);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("Practice History", 220, 10);
    }

    private void showStudentProfile()
    {
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
        text(languageManager.getString("level"), 525, 200);
        text(languageManager.getString("date"), 665, 200);

        Student stu = (Student) user;

        List<HistoryRecord> history = stu.getProfileHistory().getHistoryRecord();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            for (int i = stu.getProfileHistory().getStart(), historyIndex = 0; i < stu.getProfileHistory().getEnd(); i++, historyIndex++)
            {
                if (history.get(i) != null)
                {
                    text(languageManager.getString(history.get(i).getCategory().name().toLowerCase()), 380, 225 + 30 * historyIndex);
                    text(languageManager.getString(history.get(i).getLevel().name().toLowerCase()), 525, 225 + 30 * historyIndex);
                    text((formatter.format(history.get(i).getDate())), 665, 225 + 30 * historyIndex);
                }
            }
        } catch (IndexOutOfBoundsException ignore)
        {
        }
        textAlign(CENTER, CENTER);
        text((stu.getProfileHistory().getActualPage() + 1) + " / " + (stu.getProfileHistory().getMaxPages() + 1), 725, 400);

        textAlign(LEFT, TOP);
        text(languageManager.getString("click_to_change_avatar"), 130, 300);

        image(ImageMap.getImage(ImageMap.getImageName(((Student) user).getAvatar())), 100, 100);
    }

    private void showStudentMainMenu()
    {
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

    private void showStudentPractise()
    {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        gameManager.showGameChoosing();
        fill(100, 125);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, 130, languageManager.getString("category").length() * 30, 50);
        rect(width / 2, 330, languageManager.getString("level").length() * 30, 50);
        fill(255);
        textSize(50);
        textAlign(CENTER, CENTER);
        text(languageManager.getString("category"), width / 2, 125);
        text(languageManager.getString("level"), width / 2, 325);
    }

    private void showStudentWork()
    {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
    }

    private void showChangePasswordBackground()
    {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text(languageManager.getString("Change_Password"), 450, 100);
        textSize(30);
        textAlign(CENTER, TOP);
    }

    private void showChangePasswordPasscodeBackground()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("Passcode Check", 450, 100);
        textSize(30);
        textAlign(CENTER, TOP);
    }

    private void showMusicMenu()
    {
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

    private void showStudentProfileBackground()
    {
        background(ImageMap.getImage(ImageName.BACKGROUND_GREEN));
    }

    //------------------------------------Anonymous methods for ControlP5-UIElements
    public void Magic()
    {

        boolean isTeacher = cp5.get(Toggle.class, "Login_Role").getState();
        if (isTeacher)
        {
            Textfield myT = (Textfield) cp5.get("Login_Name");
            myT.setText("peter");
            myT = (Textfield) cp5.get("Login_Password");
            myT.setText("password");
        } else
        {
            Textfield myT = (Textfield) cp5.get("Login_Name");
            myT.setText("max");
            myT = (Textfield) cp5.get("Login_Password");
            myT.setText("123456");
        }
    }

    public boolean Login_Role()
    {

        boolean isTeacher;
        boolean flg = cp5.get(Toggle.class, "Login_Role").getState();
        if (flg)
        {
            cp5.addButton("Password_Management")
                    .setPosition(355, 500)
                    .setSize(200, 100)
                    .setLabel("Forgot\nPassword");
            isTeacher = true;

        } else
        {
            isTeacher = false;
            cp5.getController("Password_Management").setVisible(false);
            cp5.get(Button.class, "Login_Login").show();
        }
        return isTeacher;
    }

    public void Login_Login()
    {

        String hashedPassword;
        String name = cp5.get(Textfield.class, "Login_Name").getText();
        String password = cp5.get(Textfield.class, "Login_Password").getText();
        boolean isTeacher = cp5.get(Toggle.class, "Login_Role").getState();
        boolean userPreset = false;
        int bruteForceResult;
        int ID = -1;

        if (isTeacher)
        {
            for (String user : ITeacherDao.getTeacherUsernames())
            {
                userPreset = name.equals(user);
            }

        } else
        {
            for (String user : IStudentDao.getStudentUsernames())
            {
                userPreset = name.equals(user);
            }
        }

        if (!userPreset || name.isEmpty() || password.isEmpty())
        {
            feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000)
                    .setPosition(new PVector(650, 200))
                    .setSize(new PVector(170, 50)));

        } else if (isTeacher)
        {

            hashedPassword = ITeacherDao.getHash(name);
            bruteForceResult = passwordProcess.bruteForceCheck(name, password, hashedPassword);

            if (bruteForceResult == 1)
            {

                ID = ITeacherDao.getAccountId(name);
                createUserInstance(ID, true);
                uiManager.createAdminElements();
                switchScreen(Screen.MAIN_MENU_ADMIN);
            } else if (bruteForceResult == -1 && !(ITeacherDao.getAttempt(name) < 5))
            {
                cp5.get(Button.class, "Login_Login").hide();
            } else
            {
                feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000)
                        .setPosition(new PVector(650, 200))
                        .setSize(new PVector(170, 50)));
            }

        } else
        {
            ID = IStudentDao.getAccountId(name, password);
            createUserInstance(ID, false);
            uiManager.createStudentElements();
            gameManager = new GameManager(this,user.getId());
            switchScreen(Screen.MAIN_MENU_STUDENT);
        }
    }

    public void Password_Management()
    {
        String name = cp5.get(Textfield.class, "Login_Name").getText();
        boolean userPreset = false;

        for (String user : ITeacherDao.getTeacherUsernames())
        {
            userPreset = name.equals(user);
        }

        if (!userPreset || name.isEmpty())
        {
            feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000)
                    .setPosition(new PVector(650, 200))
                    .setSize(new PVector(170, 50)));
        } else
        {
            switchScreen(Screen.CHANGE_PASSWORD_ADMIN_PASSCODE);
        }
    }

    public void Change_Password_Check()
    {
        try
        {

            String name = cp5.get(Textfield.class, "Login_Name").getText();
            String passcodeText = cp5.get(Textfield.class, "Passcode").getText();
            int passcode;

            if (!passcodeText.isEmpty())
            {
                passcode = Integer.parseInt(passcodeText);
            } else
            {
                passcode = -1;
            }

            if (passcode == ITeacherDao.getPassCode(name))
            {
                switchScreen(Screen.CHANGE_PASSWORD_ADMIN);
            } else
            {
                feedbackList.add(new PasscodeNotFoundFeedback(this, 5000)
                        .setPosition(new PVector(650, 200))
                        .setSize(new PVector(170, 50)));
            }
        } catch (NumberFormatException ex)
        {
            feedbackList.add(new PasscodeNotFoundFeedback(this, 5000)
                    .setPosition(new PVector(650, 200))
                    .setSize(new PVector(170, 50)));

        }
    }

    public void Change_Password_Change()
    {
        String name = cp5.get(Textfield.class, "Login_Name").getText();
        String password = cp5.get(Textfield.class, "Change_Password_Password").getText();
        String hash = passwordProcess.hash(password);

        if (password.isEmpty())
        {
            feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000)
                    .setPosition(new PVector(650, 200))
                    .setSize(new PVector(170, 50)));
        } else
        {
            ITeacherDao.setHash(name, hash);
            ITeacherDao.setAttempt(name, 0);
            switchScreen(Screen.LOGIN_STUDENT);
        }

    }

    public void Profile_Student_Nickname_Change()
    {
        //SAVE NEW NICKNAME TO DATABASE FROM TEXTFIELD CONTROLLER
        //RENEW THE USERNICKNAME

        Textfield tf = (Textfield) cp5.get("Profile_Student_Nickname");
        ((Student) user).setNickname(tf.getText());
        IStudentDao.saveStudentNickName((Student) user);
    }

    public void Profile_Student_Avatar_Change(boolean theFlag)
    {
        if (theFlag)
        {
            cp5.get("Profile_Avatar_Lion").show();
            cp5.get("Profile_Avatar_Eagle").show();
            cp5.get("Profile_Avatar_Dolphin").show();
            cp5.get("Profile_Avatar_Zebra").show();
            cp5.get("Profile_Avatar_Penguin").show();
            cp5.get("Profile_Avatar_Coala").show();

        } else
        {
            cp5.get("Profile_Avatar_Lion").hide();
            cp5.get("Profile_Avatar_Eagle").hide();
            cp5.get("Profile_Avatar_Dolphin").hide();
            cp5.get("Profile_Avatar_Zebra").hide();
            cp5.get("Profile_Avatar_Coala").hide();
            cp5.get("Profile_Avatar_Penguin").hide();
        }
    }

    public void Practise_Student_Go()
    {
        gameManager.setPlaying(true);
        switchScreen(Screen.PLAYING);
    }

    public void Practise_Student_Playing_Next()
    {
        if (!gameManager.nextQuestion())
        {
            switchScreen(Screen.PRACTISE_STUDENT_GAME_FEEDBACK);
            gameManager.loadPractiseFeedback();
        }
    }

    public void Practise_Student_Playing_Back()
    {
        switchScreen(Screen.MAIN_MENU_STUDENT);
        gameManager.setPlaying(false);
        gameManager.setActuallQuestionIndex(0);
    }

    public void Practise_Student_Game_Feedback_Back()
    {
        switchScreen(Screen.MAIN_MENU_STUDENT);
        gameManager.setActuallQuestionIndex(0);
        gameManager.setPlaying(false);
        gameManager.reset();
    }

    public void Profile_Avatar_Lion()
    {
        ((Student) user).setAvatar(ImageName.AVATAR_LION.name());
        boolean tf = IStudentDao.saveStudentAvatar((Student) user);

    }

    public void Profile_Avatar_Eagle()
    {
        ((Student) user).setAvatar(ImageName.AVATAR_EAGLE.name());
        boolean tf = IStudentDao.saveStudentAvatar((Student) user);

    }

    public void Profile_Avatar_Zebra()
    {
        ((Student) user).setAvatar(ImageName.AVATAR_ZEBRA.name());
        IStudentDao.saveStudentAvatar((Student) user);
    }

    public void Profile_Avatar_Dolphin()
    {
        ((Student) user).setAvatar(ImageName.AVATAR_DOLPHIN.name());
        IStudentDao.saveStudentAvatar((Student) user);
    }

    public void Profile_Avatar_Coala()
    {
        ((Student) user).setAvatar(ImageName.AVATAR_COALA.name());
        IStudentDao.saveStudentAvatar((Student) user);
    }

    public void Profile_Avatar_Penguin()
    {
        ((Student) user).setAvatar(ImageName.AVATAR_PENGUIN.name());
        IStudentDao.saveStudentAvatar((Student) user);
    }
}
