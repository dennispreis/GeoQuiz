package Main;

import DAOs.MyStudentDao;
import DAOs.MyTeacherDao;
import DAOs.StudentDaoInterface;
import DAOs.TeacherDaoInterface;
import DTOs.HistoryRecord;
import DTOs.Question;
import DTOs.Questions.ChoosePicture_Question;
import DTOs.Questions.DragAndDrop_Question;
import DTOs.Questions.Multiplichoice_Question;
import DTOs.Questions.TrueOrFalse_Question;
import DTOs.Student;
import DTOs.Teacher;
import DTOs.User;
import Fonts.Feedback.FeedbackAble;
import Fonts.Feedback.NamePasswordNotFoundFeedBack;
import Fonts.Feedback.PasscodeNotFoundFeedback;
import GameManager.GameManager;
import GameManager.gameElements.*;
import Images.ImageName;
import com.sun.org.apache.xpath.internal.operations.Mult;
import controlP5.Button;
import controlP5.*;
import processing.core.*;

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
    private static DragAndDrop dragAndDrop;
    private static ChoosePicture choosePicture;
    private static ImageMap imageMap;

    //------------------------------------Inner classes
    class Settings
    {

        private PFont myFont;
        private Screen screen;
        private Color backgroundColor;

        Settings()
        {
            myFont = createFont("Fonts/Times New Romance.ttf", 30);
            screen = Screen.LOGIN_STUDENT;
            textFont(myFont);
            backgroundColor = new Color(230, 87, 116);
        }

        Color getBackgroundColor()
        {
            return backgroundColor;

        }

        public PFont getMyFont()
        {
            return myFont;
        }

        Screen getScreen()
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
        settings = new Settings();
        cp5 = new ControlP5(this);
        IStudentDao = new MyStudentDao();
        ITeacherDao = new MyTeacherDao();
        feedbackList = new ArrayList<>();
        cp5.setFont(settings.getMyFont());
        switchScreen(Screen.LOGIN_STUDENT);
        passwordProcess = new PasswordProcess(12);
        passwordProcess.setCurrentAttempt(LocalDateTime.now());
        imageMap = new ImageMap(this);

        dragAndDrop = new DragAndDrop(this,
                new DragAndDropElement[]
                {
                    new DragAndDropElement(this).setPosition(100, 20).setText("1"),
                    new DragAndDropElement(this).setPosition(150, 20).setText("2")
                },
                new FixRect(this, 200, 200, 50, 50));
        choosePicture = new ChoosePicture(this, imageMap.getImage(ImageName.PLACEHOLDER_SMALL), imageMap.getImage(ImageName.PLACEHOLDER_SMALL));

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
                showTeacherMainMenu();
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
                showLoginBackground();
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

    private void switchScreen(Screen targetScreen)
    {
        uielementsRemoveAll();
        switch (targetScreen)
        {
            case LOGIN_STUDENT:
                uielementsCreateLogin();
                break;
            case MAIN_MENU_STUDENT:
                uielementsCreateStudentMainMenu();
                break;
            case MAIN_MENU_ADMIN:
                uielementsCreateAdminMainMenu();
                break;
            case CHANGE_PASSWORD_ADMIN_PASSCODE:
                uielementsCreateAdminPasscode();
                break;
            case CHANGE_PASSWORD_ADMIN:
                uielementsCreateAdminChangePassword();
                break;
            case CHANGE_PASSWORD_STUDENT_ADMIN:
                //uielementsCreateAdminChangeStudentPassword();
                break;
            case VIEW_STUDENT_PROGRESS_ADMIN:
                //uielementsCreateAdminViewStudentProgress();
                break;
            case PROFILE_STUDENT:
                uielementsCreateStudentProfile();
                break;
            case PRACTISE_STUDENT:
                uielementsCreateStudentPractise();
                break;
            case WORK_STUDENT:
                break;
            case PROFILE_SHOW_BACKGROUND:
                uielementsCreateStudenProfileBackground();
                break;
            case PLAYING:
                uielementsCreateStudentPlaying();
                break;
            case PRACTISE_STUDENT_GAME_FEEDBACK:
                uielementsCreateStudentGameFeedback();
                break;
        }
        settings.setScreen(targetScreen);
    }

    public static PImage getImage(ImageName name)
    {
        return imageMap.getImage(name);
    }

    //------------------------------------Show Methods
    private void showPlaying()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_PLAY));
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
        textSize(30);
        textAlign(CENTER, TOP);
    }

    private void showStudentProfile()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        textSize(20);
        fill(255);
        textAlign(CORNER);
        stroke(255);
        line(365, 205, 685, 205);
        text("Category", 375, 200);
        text("Level", 500, 200);
        text("Date", 590, 200);
        HistoryRecord[] history = ((Student) user).getProfileHistory().getFiveRecords();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < history.length; i++)
        {
            if (history[i] != null)
            {
                text(history[i].getCategory().name(), 375, 225 + 30 * i);
                text(history[i].getLevel().name(), 500, 225 + 30 * i);
                text((formatter.format(history[0].getDate())), 590, 225 + 30 * i);
            }
        }
        textAlign(LEFT, TOP);
        text("Click to change avatar", 130, 300);

        image(imageMap.getImage(imageMap.getImageName(((Student) user).getAvatar())), 100, 100);
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
        text("Work", 200, 420);
        text("Practise", 450, 420);
        text("Profile", 700, 420);
    }

    private void showStudentPractise()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));

        fill(100, 125);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(300, 325, 275, 100);
        rect(600, 325, 275, 100);

        fill(0);
        textSize(30);
        textAlign(CENTER, CENTER);
        text("Category", 300, 300);
        text("Level", 600, 300);

        text(gameManager.getCategory().name(), 300, 350);
        text(gameManager.getLevel().name(), 600, 350);

        imageMode(CENTER);
        image(imageMap.getImage(ImageName.CATEGORY_CITIES), 300, 150);
        image(imageMap.getImage(ImageName.LEVEL_EASY), 600, 150);
        imageMode(CORNER);

    }

    private void showStudentWork()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));

    }

    private void showChangePasswordBackground()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("Change Password", 450, 100);
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

    private void showStudentProfileBackground()
    {
        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
    }

    //------------------------------------Methods to create/Remove UIElements
    private void uielementsCreateLogin()
    {

        cp5.addButton("Magic").setPosition(570, 420).setSize(100, 50);
//---------
        cp5.addToggle("Login_Role")
                .setPosition(10, 10)
                .setSize(20, 70)
                .setView((theGraphics, c)
                        -> 
                        {
                            theGraphics.fill(Color.BLACK.getRGB());
                            theGraphics.rect(-2, -2, c.getWidth() + 4, c.getHeight() + 4);
                            theGraphics.fill(Color.WHITE.getRGB());
                            int h = c.getHeight() / 2;
                            theGraphics.rect(0, c.getState() ? h : 0, c.getWidth(), h);
                });

        cp5.addLabel("Student")
                .setPosition(30, 5)
                .setColor(Color.WHITE.getRGB());
        cp5.addLabel("Teacher")
                .setPosition(30, 45)
                .setColor(Color.WHITE.getRGB());

        Color col = Color.decode("#7974fc");

        cp5.addTextfield("Login_Name")
                .setPosition(355, 220)
                .setSize(200, 50)
                .setColorBackground(col.getRGB())
                .setColorForeground(Color.WHITE.getRGB())
                .setAutoClear(false)
                .setLabel("Name")
                .getCaptionLabel()
                .setPaddingY(-90);

        cp5.addTextfield("Login_Password")
                .setPosition(355, 320)
                .setSize(200, 50)
                .setColorBackground(col.getRGB())
                .setColorForeground(Color.WHITE.getRGB())
                .setPasswordMode(true)
                .setAutoClear(false)
                .setLabel("Password")
                .getCaptionLabel()
                .setPaddingY(-90);

        cp5.addButton("Login_Login")
                .setPosition(355, 420)
                .setSize(200, 50)
                .setLabel("Login");

    }

    private void uielementsCreateStudentMainMenu()
    {
        cp5.addButton("Main_Menu_Student_Work").
                setPosition(100, 200).
                setSize(200, 200).
                setLabel("Test").
                setImage(imageMap.getImage(ImageName.STUDENT_WORK));
        cp5.addButton("Main_Menu_Student_Practise").
                setPosition(350, 200).
                setSize(200, 200).
                setLabel("Practise").
                setImage(imageMap.getImage(ImageName.STUDENT_PRACTISE));
        cp5.addButton("Main_Menu_Student_Profile").
                setPosition(600, 200).
                setSize(200, 200).
                setLabel("Profile").
                setImage(imageMap.getImage(ImageName.STUDENT_PROFILE));
        cp5.addButton("Main_Menu_Student_Logout").
                setPosition(20, 20).
                setSize(200, 50).
                setImage(imageMap.getImage(ImageName.LOGOUT));
    }

    private void uielementsCreateAdminMainMenu()
    {

        cp5.addButton("Change_Password")
                .setPosition(255, 220)
                .setSize(400, 50)
                .setLabel("Change Password");
        cp5.addButton("Change_Password_Student")
                .setPosition(255, 320)
                .setSize(400, 50)
                .setLabel("Change Student Password");
        cp5.addButton("View_Student_Progress")
                .setPosition(255, 420)
                .setSize(400, 50)
                .setLabel("View Student Progress");
        cp5.addButton("Main_Menu_Student_Logout")
                .setPosition(20, 20)
                .setSize(200, 50)
                .setImage(imageMap.getImage(ImageName.LOGOUT));
    }

    private void uielementsCreateAdminPasscode()
    {
        Color col = Color.decode("#7974fc");

        cp5.addGroup("Passcode_Check")
                .setBackgroundColor(color(1, 160))
                .setPosition(300, 200)
                .setSize(300, 300)
                .hideBar();

        cp5.addTextfield("Passcode")
                .setGroup("Passcode_Check")
                .setPosition(45, 140)
                .setSize(220, 40)
                .setColorBackground(col.getRGB())
                .setColorForeground(Color.WHITE.getRGB())
                .setPasswordMode(true)
                .setAutoClear(false)
                .setLabel("Passcode")
                .getCaptionLabel()
                .setPaddingY(-90);

        cp5.addButton("Change_Password_Check")
                .setGroup("Passcode_Check")
                .setPosition(45, 240)
                .setSize(220, 40)
                .setLabel("Check");

        cp5.addButton("Main_Menu_Student_Logout").
                setPosition(20, 20).
                setSize(200, 50).
                setImage(imageMap.getImage(ImageName.LOGOUT));

    }

    private void uielementsCreateAdminChangePassword()
    {
        Color col = Color.decode("#7974fc");
        String name = cp5.get(Textfield.class, "Login_Name").getText();

        cp5.addTextfield("Change_Password_Name")
                .setText(name)
                .lock()
                .setPosition(355, 220)
                .setSize(200, 50)
                .setColorBackground(col.getRGB())
                .setColorForeground(Color.WHITE.getRGB())
                .setAutoClear(false)
                .setLabel("Name")
                .getCaptionLabel()
                .setPaddingY(-90);

        cp5.addTextfield("Change_Password_Password")
                .setPosition(355, 320)
                .setSize(200, 50)
                .setColorBackground(col.getRGB())
                .setColorForeground(Color.WHITE.getRGB())
                .setPasswordMode(true)
                .setAutoClear(false)
                .setLabel("Password")
                .getCaptionLabel()
                .setPaddingY(-90);

        cp5.addButton("Change_Password_Change")
                .setPosition(355, 420)
                .setSize(200, 50)
                .setLabel("Change");

        cp5.addButton("Main_Menu_Student_Logout").
                setPosition(20, 20).
                setSize(200, 50).
                setImage(imageMap.getImage(ImageName.LOGOUT));
    }

    private void uielementsCreateStudentProfile()
    {
        cp5.addToggle("Profile_Student_Avatar_Change").setPosition(100, 300).setSize(25, 25).
                setLabel("");

        cp5.addButton("Profile_Show_Background").setPosition(700, 175).setSize(100, 50).setLabel("Map");

        cp5.addButton("Profile_Avatar_Lion").setPosition(100, 350).setSize(50, 50).setImage(imageMap.getImage(ImageName.AVATAR_LION_SMALL)).hide();
        cp5.addButton("Profile_Avatar_Dolphin").setPosition(160, 350).setSize(50, 50).setImage(imageMap.getImage(ImageName.AVATAR_DOLPHIN_SMALL)).hide();
        cp5.addButton("Profile_Avatar_Eagle").setPosition(220, 350).setSize(50, 50).setImage(imageMap.getImage(ImageName.AVATAR_EAGLE_SMALL)).hide();
        cp5.addButton("Profile_Avatar_Zebra").setPosition(100, 410).setSize(50, 50).setImage(imageMap.getImage(ImageName.AVATAR_ZEBRA_SMALL)).hide();
        cp5.addButton("Profile_Avatar_Coala").setPosition(160, 410).setSize(50, 50).setImage(imageMap.getImage(ImageName.AVATAR_COALA_SMALL)).hide();
        cp5.addButton("Profile_Avatar_Penguin").setPosition(220, 410).setSize(50, 50).setImage(imageMap.getImage(ImageName.AVATAR_PENGUIN_SMALL)).hide();

        cp5.addButton("Profile_Student_Nickname_Change").setPosition(700, 100).setSize(100, 50).
                setLabel("Save");

        cp5.addTextfield("Profile_Student_Nickname").setPosition(365, 100).setSize(320, 50).
                setText(((Student) user).getNickname()).setLabel("");

        cp5.addButton("Profile_Student_Back").setPosition(20, 20).setSize(100, 100).
                setImage(imageMap.getImage(ImageName.LOGOUT));

        cp5.addButton("Profile_Student_Achievement_0").setPosition(80, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_0").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_0").hide());

        cp5.addButton("Profile_Student_Achievement_1").setPosition(160, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_1").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_1").hide());

        cp5.addButton("Profile_Student_Achievement_2").setPosition(240, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_2").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_2").hide());

        cp5.addButton("Profile_Student_Achievement_3").setPosition(320, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_3").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_3").hide());

        cp5.addButton("Profile_Student_Achievement_4").setPosition(400, 500).
                setSize(50, 50).setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_4").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_4").hide());

        cp5.addButton("Profile_Student_Achievement_5").setPosition(480, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_5").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_5").hide());

        cp5.addButton("Profile_Student_Achievement_6").setPosition(560, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_6").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_6").hide());

        cp5.addButton("Profile_Student_Achievement_7").setPosition(640, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_7").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_7").hide());

        cp5.addButton("Profile_Student_Achievement_8").setPosition(720, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_8").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_8").hide());

        cp5.addButton("Profile_Student_Achievement_9").setPosition(800, 500).setSize(50, 50).
                setImage(imageMap.getImage(ImageName.PLACEHOLDER_SMALL)).
                onEnter(callbackEvent -> cp5.getController("Achievement_Label_9").show()).
                onLeave(callbackEvent -> cp5.getController("Achievement_Label_9").hide());

        cp5.addLabel("Achievement_Label_0").setPosition(0, 0).setSize(100, 100).setText("Achievement_0").hide();
        cp5.addLabel("Achievement_Label_1").setPosition(0, 0).setText("Achievement_1").hide();
        cp5.addLabel("Achievement_Label_2").setPosition(0, 0).setText("Achievement_2").hide();
        cp5.addLabel("Achievement_Label_3").setPosition(0, 0).setText("Achievement_3").hide();
        cp5.addLabel("Achievement_Label_4").setPosition(0, 0).setText("Achievement_4").hide();
        cp5.addLabel("Achievement_Label_5").setPosition(0, 0).setText("Achievement_5").hide();
        cp5.addLabel("Achievement_Label_6").setPosition(0, 0).setText("Achievement_6").hide();
        cp5.addLabel("Achievement_Label_7").setPosition(0, 0).setText("Achievement_7").hide();
        cp5.addLabel("Achievement_Label_8").setPosition(0, 0).setText("Achievement_8").hide();
        cp5.addLabel("Achievement_Label_9").setPosition(0, 0).setText("Achievement_9").hide();
    }

    private void uielementsCreateStudentPractise()
    {
        cp5.addButton("Practise_Student_Back").setPosition(20, 20).setSize(100, 100).
                setImage(imageMap.getImage(ImageName.LOGOUT));

        cp5.addButton("Practise_Student_Go").setPosition(775, 275).setSize(100, 100).
                setImage(imageMap.getImage(ImageName.GO));

        cp5.addButton("Practise_Category_Left").setPosition(155, 250).setSize(50, 50).setImage(imageMap.getImage(ImageName.ARROW_LEFT));
        cp5.addButton("Practise_Category_Right").setPosition(345, 250).setSize(50, 50).setImage(imageMap.getImage(ImageName.ARROW_RIGHT));
        cp5.addButton("Practise_Level_Left").setPosition(475, 250).setSize(50, 50).setImage(imageMap.getImage(ImageName.ARROW_LEFT));
        cp5.addButton("Practise_Level_Right").setPosition(625, 250).setSize(50, 50).setImage(imageMap.getImage(ImageName.ARROW_RIGHT));

    }

    private void uielementsCreateStudentPlaying()
    {
        cp5.addButton("Practise_Student_Playing_Back").setPosition(20, 20).setSize(200, 50).
                setImage(imageMap.getImage(ImageName.LOGOUT));
        cp5.addButton("Practise_Student_Playing_Next").setPosition(775, 275).setSize(100, 100).
                setImage(imageMap.getImage(ImageName.ARROW_RIGHT));
    }

    private void uielementsCreateStudenProfileBackground()
    {
        cp5.addButton("Profile_Student_Background_Back").setPosition(20, 20).setSize(100, 100).
                setImage(imageMap.getImage(ImageName.LOGOUT));
    }

    private void uielementsCreateStudentGameFeedback()
    {
        cp5.addButton("Practise_Student_Game_Feedback_Back").setPosition(345, 450).setSize(210, 50).setLabel("Back to Menu");
    }

    private void uielementsRemoveAll()
    {
        feedbackList.removeAll(feedbackList);
        cp5.getAll().forEach(ControllerInterface::hide);
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
                    .setPosition(new PVector(750, 350))
                    .setSize(new PVector(170, 50)));

        } else
        {
            if (isTeacher)
            {

                hashedPassword = ITeacherDao.getHash(name);
                bruteForceResult = passwordProcess.bruteForceCheck(name, password, hashedPassword);

                if (bruteForceResult == 1)
                {

                    ID = ITeacherDao.getAccountId(name);
                    createUserInstance(ID, true);
                    switchScreen(Screen.MAIN_MENU_ADMIN);
                } else if (bruteForceResult == -1 && !(ITeacherDao.getAttempt(name) < 5))
                {
                    cp5.get(Button.class, "Login_Login").hide();
                } else
                {
                    feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000)
                            .setPosition(new PVector(750, 350))
                            .setSize(new PVector(170, 50)));
                }

            } else
            {
                ID = IStudentDao.getAccountId(name, password);
                if (ID == -1)
                {
                    feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000)
                            .setPosition(new PVector(750, 350))
                            .setSize(new PVector(170, 50)));
                } else
                {
                    createUserInstance(ID, false);

                    switchScreen(Screen.MAIN_MENU_STUDENT);
                }
            }

            gameManager = new GameManager(this);

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
                    .setPosition(new PVector(750, 350))
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
                        .setPosition(new PVector(750, 350))
                        .setSize(new PVector(170, 50)));
            }
        } catch (NumberFormatException ex)
        {
            feedbackList.add(new PasscodeNotFoundFeedback(this, 5000)
                    .setPosition(new PVector(750, 350))
                    .setSize(new PVector(170, 50)));

        }
    }

    public void Change_Password()
    {
        switchScreen(Screen.CHANGE_PASSWORD_ADMIN_PASSCODE);
    }

    public void Change_Password_Change()
    {
        String name = cp5.get(Textfield.class, "Login_Name").getText();
        String password = cp5.get(Textfield.class, "Change_Password_Password").getText();
        String hash = passwordProcess.hash(password);

        if (password.isEmpty())
        {
            feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000)
                    .setPosition(new PVector(750, 350))
                    .setSize(new PVector(170, 50)));
        } else
        {
            ITeacherDao.setHash(name, hash);
            ITeacherDao.setAttempt(name, 0);
            switchScreen(Screen.LOGIN_STUDENT);
        }

    }

    private void showTeacherMainMenu()
    {
        String name = ((Teacher) user).getName();
        String withUpper = name.substring(0, 1).toUpperCase() + name.substring(1, name.length());

        background(imageMap.getImage(ImageName.BACKGROUND_GREEN));
        fill(100, 120);
        stroke(0);
        strokeWeight(2);
        rectMode(CENTER);
        rect(width / 2, height / 2, 500, 400);
        textSize(60);
        textAlign(CENTER, TOP);
        fill(255);
        text("Welcome " + withUpper + "!", 450, 100);
        textSize(30);
        textAlign(CENTER, TOP);
    }

    public void Login_Admin_Back()
    {
        switchScreen(Screen.LOGIN_STUDENT);
    }

    public void Main_Menu_Student_Logout()
    {
        switchScreen(Screen.LOGIN_STUDENT);
    }

    public void Main_Menu_Student_Profile()
    {
        switchScreen(Screen.PROFILE_STUDENT);
    }

    public void Main_Menu_Student_Work()
    {
        switchScreen(Screen.WORK_STUDENT);
    }

    public void Main_Menu_Student_Practise()
    {
        switchScreen(Screen.PRACTISE_STUDENT);
    }

    public void Profile_Student_Back()
    {
        switchScreen(Screen.MAIN_MENU_STUDENT);
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

    public void Practise_Student_Back()
    {
        switchScreen(Screen.MAIN_MENU_STUDENT);
    }

    public void Practise_Category_Left()
    {
        gameManager.decreaseCategory();
    }

    public void Practise_Category_Right()
    {
        gameManager.increaseCategory();
    }

    public void Practise_Level_Left()
    {
        gameManager.decreaseLevel();
    }

    public void Practise_Level_Right()
    {
        gameManager.increaseLevel();
    }

    public void Practise_Student_Go()
    {
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

    public void Profile_Show_Background()
    {
        switchScreen(Screen.PROFILE_SHOW_BACKGROUND);
    }

    public void Profile_Student_Background_Back()
    {
        switchScreen(Screen.PROFILE_STUDENT);
    }

    public void Practise_Student_Playing_Back()
    {
        switchScreen(Screen.MAIN_MENU_STUDENT);
        gameManager.setActuallQuestionIndex(0);
    }

    public void Practise_Student_Game_Feedback_Back()
    {
        switchScreen(Screen.MAIN_MENU_STUDENT);
        gameManager.setActuallQuestionIndex(0);
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
