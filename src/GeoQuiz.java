import Feedback.FeedbackAble;
import Feedback.NamePasswordNotFoundFeedBack;
import controlP5.*;
import processing.core.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeoQuiz extends PApplet {

    //------------------------------------Variables and fields
    private static ControlP5 cp5;
    private static Settings settings;
    private static DBConnector dbConnector;
    private static List<FeedbackAble> feedbackList;
    private static Map<String, PImage> images;
    private static User user;

    //------------------------------------Inner classes
    class Settings {

        private PFont myFont;
        private Screen screen;

        Settings() {
            myFont = createFont("Fonts/Times New Romance.ttf", 30);
            screen = Screen.LOGIN_STUDENT;
            textFont(myFont);
        }

        PFont getMyFont() {
            return myFont;
        }

        Screen getScreen() {
            return screen;
        }

        void setScreen(Screen win) {
            this.screen = win;
        }
    }
    //------------------------------------Methods given by Processing

    public static void main(String[] args) {
        PApplet.main("GeoQuiz", args);
    }

    public void settings() {
        size(900, 600);
    }

    public void setup() {
        settings = new Settings();
        cp5 = new ControlP5(this);
        dbConnector = new DBConnector();
        feedbackList = new ArrayList<>();
        images = loadImages();
        cp5.setFont(settings.getMyFont());
        switchScreen(Screen.LOGIN_STUDENT);


    }

    public void draw() {
        switch (settings.getScreen()) {
            case LOGIN_STUDENT:
                showLoginBackground();
                break;
            case MAIN_MENU_STUDENT:
                showLoginBackground();
                break;
            case MAIN_MENU_ADMIN:
                showLoginBackground();
                break;
            case PROFILE_STUDENT:
                showStudentProfile();
                break;
        }

        for (FeedbackAble f : feedbackList) {
            f.show();
        }

        fill(0);
        textSize(20);
        textAlign(RIGHT);
        text(settings.getScreen().toString(), width - 10, 580);
    }

    //------------------------------------Initialising methods.

    private Map<String, PImage> loadImages() {
        Map<String, PImage> tmp = new HashMap<>();

        tmp.put("Background", loadImage("Images/background.JPG"));
        tmp.put("Logout", loadImage("Images/logout.png"));

        return tmp;
    }

    //------------------------------------Event methods given by Processing. Includes mouse and key events

    public void keyPressed() {
    }

    public void mousePressed() {
    }

    //------------------------------------Own Methods.

    private void createUserInstance(int ID, boolean isTeacher) {
        user = isTeacher ? dbConnector.createTeacherUser(ID) : dbConnector.createStudentUser(ID);
    }

    private void switchScreen(Screen targetScreen) {
        uielementsRemoveAll();
        switch (targetScreen) {
            case LOGIN_STUDENT:
                uielementsCreateLogin();
                break;
            case MAIN_MENU_STUDENT:
                uielementsCreateStudentMainMenu();
                break;
            case MAIN_MENU_ADMIN:
                uielementsCreateAdminMainMenu();
                break;
        }
        settings.setScreen(targetScreen);
    }

    private void showLoginBackground() {
        background(images.get("Background"));
        stroke(0);
        strokeWeight(2);
        fill(255, 100);
        rect(180, -5, 240, height + 5);
        fill(0);
        textSize(60);
        textAlign(CORNER);
        text("GeoQuiz!", 195, 130);
        textSize(30);
        textAlign(LEFT, TOP);
    }

    private void showStudentProfile() {
        background(images.get("Background"));
        textSize(30);
        fill(0);
        text("TESTING PROFILE", width / 2, height / 2);
    }

    //------------------------------------Methods to create/Remove UIElements

    private void uielementsCreateLogin() {
        cp5.addTextfield("Login_Name").setPosition(200, 200).setSize(200, 50).setColorLabel(0).setAutoClear(false).setLabel("Name").getCaptionLabel().setPaddingY(-80);
        cp5.addTextfield("Login_Password").setPosition(200, 300).setSize(200, 50).setColorLabel(0).setPasswordMode(true).setAutoClear(false).setLabel("Password").getCaptionLabel().setPaddingY(-80);
        cp5.addButton("Login_Login").setPosition(200, 380).setSize(200, 50).setLabel("Login");
        cp5.addToggle("Login_Role")
                .setPosition(202, 440)
                .setSize(20, 50)
                .setView((theGraphics, c) -> {
                    CColor col = c.getColor();
                    theGraphics.fill(col.getBackground());
                    theGraphics.rect(-2, -2, c.getWidth()+4, c.getHeight()+4);
                    theGraphics.fill(col.getForeground());
                    int h = c.getHeight() / 2;
                    theGraphics.rect(0, c.getState() ? h : 0, c.getWidth(), h);
                    //c.getCaptionLabel().draw(theGraphics, 0, 0, c);
                })
        ;
        cp5.addLabel("Student").setPosition(232, 440).setColor(Color.BLACK.getRGB());
        cp5.addLabel("Teacher").setPosition(232, 460).setColor(Color.BLACK.getRGB());
    }

    private void uielementsCreateStudentMainMenu() {
        cp5.addButton("Main_Menu_Student_Work").setPosition(200, 200).setSize(200, 50).setLabel("Test");
        cp5.addButton("Main_Menu_Student_Practise").setPosition(200, 270).setSize(200, 50).setLabel("Practise");
        cp5.addButton("Main_Menu_Student_Profile").setPosition(200, 340).setSize(200, 50).setLabel("Profile");
        cp5.addButton("Main_Menu_Student_Logout").setPosition(20, 20).setSize(200, 50).setImage(images.get("Logout"));
    }

    private void uielementsCreateAdminMainMenu() {
    }

    private void uielementsRemoveAll() {
        cp5.getAll().forEach(ControllerInterface::remove);
    }


    //------------------------------------Anonymous methods for ControlP5-UIElements

    public void Login_Login() {

        String name = cp5.get(Textfield.class, "Login_Name").getText();
        String password = cp5.get(Textfield.class, "Login_Password").getText();

        boolean isTeacher = cp5.get(Toggle.class, "Login_Role").getState();

        int ID = dbConnector.getAccountId(name, password, isTeacher);

        if (name.equals("")) {
            println("name leer");
        } else if (password.equals("")) {
            println("password leer");
        } else if (ID != -1) {
            createUserInstance(ID, isTeacher);
            switchScreen(isTeacher ? Screen.MAIN_MENU_ADMIN : Screen.MAIN_MENU_STUDENT);
        } else {
            feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000).setPosition(new PVector(450, 200)).setSize(new PVector(170, 50)));
        }

    }

    public void Login_Admin_Back() {
        switchScreen(Screen.LOGIN_STUDENT);
    }

    public void Main_Menu_Student_Logout() {
        switchScreen(Screen.LOGIN_STUDENT);
    }

    public void Main_Menu_Student_Profile() {
        switchScreen(Screen.PROFILE_STUDENT);
    }
}
