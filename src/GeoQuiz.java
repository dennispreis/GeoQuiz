import Feedback.FeedbackAble;
import Feedback.NamePasswordNotFoundFeedBack;
import controlP5.*;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;

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
            myFont = createFont("Times New Romance.ttf", 30);
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
            case LOGIN_ADMIN:
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

        tmp.put("Background", loadImage("background.JPG"));

        return tmp;
    }

    //------------------------------------Event methods given by Processing. Includes mouse and key events

    public void keyPressed() {
    }

    public void mousePressed() {
    }

    //------------------------------------Own Methods.

    private void createUserInstance(int ID){
        user = dbConnector.createStudentUser(ID);
        println(user.getUserName());
    }

    private void switchScreen(Screen targetScreen) {
        uielementsRemoveAll();
        switch (targetScreen) {
            case LOGIN_STUDENT:
                uielementsCreateStudenLogin();
                break;
            case LOGIN_ADMIN:
                uielementsCreateStaffLogin();
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
        // background(color(20, 180, 230));
        background(images.get("Background"));
        stroke(0);
        strokeWeight(2);
        fill(255, 100);
        rect(180, -5, 240, height + 5);
        fill(0);
        textSize(60);
        textAlign(CORNER);
        text("GeoQuiz!", 195, 130);
    }

    private void showStudentProfile() {
        background(images.get("Background"));
        textSize(30);
        fill(0);
        text("TESTING PROFILE", width / 2, height / 2);
    }

    //------------------------------------Methods to create/Remove UIElements

    private void uielementsCreateStudenLogin() {
        cp5.addTextfield("Login_Student_Name").setPosition(200, 200).setSize(200, 50).setColorLabel(0).setAutoClear(false).setLabel("Name");
        cp5.addTextfield("Login_Student_Password").setPosition(200, 300).setSize(200, 50).setColorLabel(0).setPasswordMode(true).setAutoClear(false).setLabel("Password");
        cp5.addButton("Login_Student_Login").setPosition(200, 400).setSize(200, 50).setLabel("Login");
        cp5.addButton("Login_Student_Admin").setPosition(200, 460).setSize(200, 50).setLabel("Staff/Admin");
        cp5.addButton("SUPERLOGIN").setPosition(200, 540).setSize(200, 50);
    }

    private void uielementsCreateStaffLogin() {
        cp5.addTextfield(" Login_Admin_Name").setPosition(200, 200).setSize(200, 50).setColorLabel(0).setAutoClear(false).setLabel("Name");
        cp5.addTextfield("Login_Admin_Password").setPosition(200, 300).setSize(200, 50).setColorLabel(0).setPasswordMode(true).setAutoClear(false).setLabel("Password");
        cp5.addButton("Login_Admin_Login").setPosition(200, 400).setSize(200, 50).setLabel("login");
        cp5.addButton("Login_Admin_Back").setPosition(200, 460).setSize(200, 50).setLabel("Back");
    }

    private void uielementsCreateStudentMainMenu() {
        cp5.addButton("Main_Menu_Student_Work").setPosition(200, 200).setSize(200, 50).setLabel("Work");
        cp5.addButton("Main_Menu_Student_Practise").setPosition(200, 270).setSize(200, 50).setLabel("Practise");
        cp5.addButton("Main_Menu_Student_Profile").setPosition(200, 340).setSize(200, 50).setLabel("Profile");
        cp5.addButton("Main_Menu_Student_Logout").setPosition(200, 410).setSize(200, 50).setLabel("Logout");
    }

    private void uielementsCreateAdminMainMenu() {
    }

    private void uielementsRemoveAll() {
        cp5.getAll().forEach(ControllerInterface::remove);
    }


    //------------------------------------Anonymous methods for ControlP5-UIElements

    public void Login_Student_Login() {

        String name = cp5.get(Textfield.class, "Login_Student_Name").getText();
        String password = cp5.get(Textfield.class, "Login_Student_Password").getText();
        int ID = dbConnector.getAccountId(name, password);

        if (name.equals("")) {
            println("name leer");
        } else if (password.equals("")) {
            println("password leer");
        } else if (ID != -1) {
            createUserInstance(ID);
            switchScreen(Screen.MAIN_MENU_STUDENT);
        } else {
            feedbackList.add(new NamePasswordNotFoundFeedBack(this, 5000).setPosition(new PVector(450, 200)).setSize(new PVector(170, 50)));
        }

    }

    public void Login_Student_Admin() {
        switchScreen(Screen.LOGIN_ADMIN);
    }

    public void Login_Admin_Back() {
        switchScreen(Screen.LOGIN_STUDENT);
    }

    public void Main_Menu_Student_Logout() {
        switchScreen(Screen.LOGIN_STUDENT);
    }

    public void Main_Menu_Student_Profile() { switchScreen(Screen.PROFILE_STUDENT); }

    public void SUPERLOGIN() {
        switchScreen(Screen.MAIN_MENU_STUDENT);
    }

    //------------------------------------Thread methods


}
