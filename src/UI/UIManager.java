package UI;

import DTOs.Question;
import DTOs.Student;
import GameManager.Category;
import GameManager.ChooseAble;
import Images.ImageMap;
import Images.ImageName;
import Languages.Language;
import Sound.SoundName;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import controlP5.*;
import Main.GeoQuiz;
import Main.Screen;

import javax.xml.soap.Text;
import java.awt.*;
import java.util.HashMap;

import static Main.GeoQuiz.switchScreen;

public class UIManager {

    private ControlP5 cp5;
    private HashMap<Screen, Controller[]> controller;

    public UIManager(ControlP5 cp5) {
        this.cp5 = cp5;
        controller = new HashMap<>();
        uielementsCreateLogin();
        uielementsCreateSoundMenu();
        uielementsHideAll();
    }

    public void updateController(Screen oldScreen, Screen newScreen) {
        hideControllers(oldScreen);
        showControllers(newScreen);
    }

    private void showControllers(Screen showScreen) {
        for (Controller c : controller.get(showScreen)) {
            c.show();
            if (showScreen.equals(Screen.PROFILE_STUDENT)) {
                hideAchievementLabels();
                hideAvatarPictures();
            }
        }
    }

    private void hideAchievementLabels() {
        cp5.get("Achievement_TextLabel_0").hide();
        cp5.get("Achievement_TextLabel_1").hide();
        cp5.get("Achievement_TextLabel_2").hide();
        cp5.get("Achievement_TextLabel_3").hide();
        cp5.get("Achievement_TextLabel_4").hide();
        cp5.get("Achievement_TextLabel_5").hide();
        cp5.get("Achievement_TextLabel_6").hide();
        cp5.get("Achievement_TextLabel_7").hide();
        cp5.get("Achievement_TextLabel_8").hide();
        cp5.get("Achievement_TextLabel_9").hide();
    }

    private void hideAvatarPictures() {
        cp5.get("Profile_Avatar_Lion").hide();
        cp5.get("Profile_Avatar_Eagle").hide();
        cp5.get("Profile_Avatar_Zebra").hide();
        cp5.get("Profile_Avatar_Penguin").hide();
        cp5.get("Profile_Avatar_Dolphin").hide();
        cp5.get("Profile_Avatar_Coala").hide();
    }

    private void hideControllers(Screen hideScreen) {
        for (Controller c : controller.get(hideScreen)) {
            c.hide();
        }
    }

    private void uielementsHideAll() {
        cp5.getAll().forEach(ControllerInterface::hide);
    }

    public void createStudentElements() {
        uielementsCreateStudentMainMenu();
        uielementsCreateStudenProfileBackground();
        uielementsCreateStudentGameFeedback();
        uielementsCreateStudentPlaying();
        uielementsCreateStudenWork();
        uielementsCreateStudentPractise();
        uielementsCreateStudentProfile();
        uielementsHideAll();
    }

    public void createAdminElements() {
        uielementsCreateAdminChangePassword();
        uielementsCreateAdminPasscode();
        uielementsCreateAdminMainMenu();
    }

    private void expandOtherController() {
        if (GeoQuiz.getSettings().getScreen().equals(Screen.PROFILE_STUDENT)) {
            cp5.get("Profile_Student_Nickname_Change").show();
            cp5.get("Profile_Show_Background").show();
            Textfield f = (Textfield) cp5.get("Profile_Student_Nickname");
            f.setSize(320, 50);
        }
    }

    private void hideOtherController() {
        if (GeoQuiz.getSettings().getScreen().equals(Screen.PROFILE_STUDENT)) {
            cp5.get("Profile_Student_Nickname_Change").hide();
            cp5.get("Profile_Show_Background").hide();
            Textfield f = (Textfield) cp5.get("Profile_Student_Nickname");
            f.setSize(280, 50);
        }
    }

    private void hideAvatars() {
        Toggle t = (Toggle) cp5.get("Profile_Student_Avatar_Change");
        if (t.getState()) {
            cp5.get("Profile_Avatar_Zebra").hide();
            cp5.get("Profile_Avatar_Coala").hide();
            cp5.get("Profile_Avatar_Penguin").hide();
        }
    }

    private void showAvatars() {
        Toggle t = (Toggle) cp5.get("Profile_Student_Avatar_Change");
        if (t.getState()) {
            cp5.get("Profile_Avatar_Zebra").show();
            cp5.get("Profile_Avatar_Coala").show();
            cp5.get("Profile_Avatar_Penguin").show();
        }
    }

    public void manageControllerView() {
        if (GeoQuiz.getSoundManager().isShowingMenu()) {
            GeoQuiz.getSoundManager().showMenu();
            hideOtherController();
        } else {
            GeoQuiz.getSoundManager().hideMenu();
            expandOtherController();
        }
    }

    //-------CHANGE LANGUAGE

    private void changeLoginLanguage() {
        //LOGIN
        cp5.get("Magic").setLabel(GeoQuiz.getLanguageManager().getString("magic"));
        cp5.get("Login_Login").setLabel(GeoQuiz.getLanguageManager().getString("login"));
        cp5.get("Login_Name").setLabel(GeoQuiz.getLanguageManager().getString("name"));
        cp5.get("Login_Password").setLabel(GeoQuiz.getLanguageManager().getString("password"));
    }

    private void changeAdminMenuLanguage() {
        //ADMIN MAIN MENU
        cp5.get("Change_Password").setLabel(GeoQuiz.getLanguageManager().getString("Change_Password"));
        cp5.get("Change_Password_Student").setLabel(GeoQuiz.getLanguageManager().getString("Change_Password_Student"));
        cp5.get("View_Student_Progress").setLabel(GeoQuiz.getLanguageManager().getString("View_Student_Progress"));
        cp5.get("Change_Password").setLabel(GeoQuiz.getLanguageManager().getString("Change_Password"));

        //ADMIN PASSCODE
        cp5.get("Passcode").setLabel(GeoQuiz.getLanguageManager().getString("passcode"));
        cp5.get("Change_Password_Check").setLabel(GeoQuiz.getLanguageManager().getString("Change_Password_Check"));

        //ADMIN CHANGE PASSWORD
        cp5.get("Change_Password_Name").setLabel(GeoQuiz.getLanguageManager().getString("name"));
        cp5.get("Change_Password_Password").setLabel(GeoQuiz.getLanguageManager().getString("password"));
        cp5.get("Change_Password_Change").setLabel(GeoQuiz.getLanguageManager().getString("change"));
    }

    private void changeStudentMenuLanguage() {
        //STUDENT PROFILE
        Textlabel l;
        for (int i = 0; i < 10; i++) {
            l = (Textlabel) cp5.get("Achievement_TextLabel_" + i);
            l.setText(GeoQuiz.getLanguageManager().getString("achievement_" + i));
        }

        //STUDENT PRACTISE
        ChooseAble[] tmp = GeoQuiz.getGameManager().getCategoryChooser().getElements();
        tmp[0].setText(GeoQuiz.getLanguageManager().getString("city"));
        tmp[1].setText(GeoQuiz.getLanguageManager().getString("mountain"));
        tmp[2].setText(GeoQuiz.getLanguageManager().getString("river"));
        tmp[3].setText(GeoQuiz.getLanguageManager().getString("world"));
        tmp[4].setText(GeoQuiz.getLanguageManager().getString("island"));
        tmp[5].setText(GeoQuiz.getLanguageManager().getString("lake"));

        tmp = GeoQuiz.getGameManager().getLevelChooser().getElements();
        tmp[0].setText(GeoQuiz.getLanguageManager().getString("easy"));
        tmp[1].setText(GeoQuiz.getLanguageManager().getString("medium"));
        tmp[2].setText(GeoQuiz.getLanguageManager().getString("hard"));

        //STUDENT GAME FEEDBACK
        cp5.get("Practise_Student_Game_Feedback_Back").setLabel(GeoQuiz.getLanguageManager().getString("back_to_menu"));

        //STUDENT PLAY
        changeQuestionLanguage();

    }

    private void changeQuestionLanguage(){
        if (GeoQuiz.getGameManager().isPlaying()) {
            for (Question q : GeoQuiz.getGameManager().getQuestions()) {
                q.setQuestion_text(GeoQuiz.getLanguageManager().getQuestionsString(Integer.toString(q.getId())));
            }
        }
    }


    //-------CONTROLLER GENERATOR

    private void uielementsCreateLogin() {

        Color col = Color.decode("#7974fc");

        controller.put(Screen.LOGIN_STUDENT, new Controller[]{

                cp5.addButton("Magic").setPosition(570, 420).setSize(100, 50),

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
                }),

                cp5.addButton("Login_Login")
                        .setPosition(355, 420)
                        .setSize(200, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("login"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.getSoundManager().triggerSound(SoundName.TEST_SOUND);
                }),

                cp5.addTextfield("Login_Name")
                        .setPosition(355, 220)
                        .setSize(200, 50)
                        .setColorBackground(col.getRGB())
                        .setColorForeground(Color.WHITE.getRGB())
                        .setAutoClear(false)
                        .setLabel(GeoQuiz.getLanguageManager().getString("name")),

                cp5.addTextfield("Login_Password")
                        .setPosition(355, 320)
                        .setSize(200, 50)
                        .setColorBackground(col.getRGB())
                        .setColorForeground(Color.WHITE.getRGB())
                        .setPasswordMode(true)
                        .setAutoClear(false)
                        .setLabel(GeoQuiz.getLanguageManager().getString("password"))
        });

        Textfield tf;
        tf = (Textfield) cp5.get("Login_Name");
        tf.getCaptionLabel().setPaddingY(-90);
        tf = (Textfield) cp5.get("Login_Password");
        tf.getCaptionLabel().setPaddingY(-90);
    }

    private void uielementsCreateStudentMainMenu() {

        controller.put(Screen.MAIN_MENU_STUDENT, new Controller[]{
                cp5.addButton("Main_Menu_Student_Work").
                        setPosition(100, 200).
                        setSize(200, 200).
                        setLabel("Test").
                        setImage(ImageMap.getImage(ImageName.STUDENT_WORK)).onClick(callbackEvent -> {
                    switchScreen(Screen.WORK_STUDENT);
                }),
                cp5.addButton("Main_Menu_Student_Practise").
                        setPosition(350, 200).
                        setSize(200, 200).
                        setLabel("Practise").
                        setImage(ImageMap.getImage(ImageName.STUDENT_PRACTISE)).onClick(callbackEvent -> {
                    switchScreen(Screen.PRACTISE_STUDENT);
                }),
                cp5.addButton("Main_Menu_Student_Profile").
                        setPosition(600, 200).
                        setSize(200, 200).
                        setLabel("Profile").
                        setImage(ImageMap.getImage(ImageName.STUDENT_PROFILE)).onClick(callbackEvent -> {
                    switchScreen(Screen.PROFILE_STUDENT);
                }),
                cp5.addButton("Main_Menu_Student_Logout").
                        setPosition(20, 20).
                        setSize(200, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.LOGIN_STUDENT);
                })
        });
    }

    private void uielementsCreateAdminMainMenu() {
        controller.put(Screen.MAIN_MENU_ADMIN, new Controller[]{

                cp5.addButton("Change_Password")
                        .setPosition(255, 220)
                        .setSize(400, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("Change_Password")),

                cp5.addButton("Change_Password_Student")
                        .setPosition(255, 320)
                        .setSize(400, 50)
                        .setLabel("Change Student Password"),

                cp5.addButton("View_Student_Progress")
                        .setPosition(255, 420)
                        .setSize(400, 50)
                        .setLabel("View Student Progress"),

                cp5.addButton("Main_Menu_Student_Logout")
                        .setPosition(20, 20)
                        .setSize(200, 50)
                        .setImage(ImageMap.getImage(ImageName.LOGOUT))
        });
    }

    private void uielementsCreateAdminPasscode() {
        Color col = Color.decode("#7974fc");

        controller.put(Screen.CHANGE_PASSWORD_ADMIN, new Controller[]{

                cp5.addTextfield("Passcode")
                        .setPosition(45, 140)
                        .setSize(220, 40)
                        .setColorBackground(col.getRGB())
                        .setColorForeground(Color.WHITE.getRGB())
                        .setPasswordMode(true)
                        .setAutoClear(false)
                        .setLabel(GeoQuiz.getLanguageManager().getString("passcode")), //label yPadding -90

                cp5.addButton("Change_Password_Check")
                        .setPosition(45, 240)
                        .setSize(220, 40)
                        .setLabel(GeoQuiz.getLanguageManager().getString("Change_Password_Check")),

                cp5.addButton("Main_Menu_Student_Logout").
                        setPosition(20, 20).
                        setSize(200, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT))

        });
    }

    private void uielementsCreateAdminChangePassword() {
        Color col = Color.decode("#7974fc");
        String name = cp5.get(Textfield.class, "Login_Name").getText();

        controller.put(Screen.CHANGE_PASSWORD_ADMIN, new Controller[]{
                cp5.addTextfield("Change_Password_Name")
                        .setText(name)
                        .lock()
                        .setPosition(355, 220)
                        .setSize(200, 50)
                        .setColorBackground(col.getRGB())
                        .setColorForeground(Color.WHITE.getRGB())
                        .setAutoClear(false)
                        .setLabel(GeoQuiz.getLanguageManager().getString("name")), //Captionlabel yPadding -90

                cp5.addTextfield("Change_Password_Password")
                        .setPosition(355, 320)
                        .setSize(200, 50)
                        .setColorBackground(col.getRGB())
                        .setColorForeground(Color.WHITE.getRGB())
                        .setPasswordMode(true)
                        .setAutoClear(false)
                        .setLabel(GeoQuiz.getLanguageManager().getString("password")), // Captionlabel yPadding -90

                cp5.addButton("Change_Password_Change")
                        .setPosition(355, 420)
                        .setSize(200, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("change")),

                cp5.addButton("Main_Menu_Student_Logout").
                        setPosition(20, 20).
                        setSize(200, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)),
        });
    }

    private void uielementsCreateStudenWork() {
        controller.put(Screen.WORK_STUDENT, new Controller[]{
                cp5.addButton("Work_Student_Back").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_STUDENT);
                })
        });
    }

    private void uielementsCreateStudentProfile() {
        controller.put(Screen.PROFILE_STUDENT, new Controller[]{

                cp5.addToggle("Profile_Student_Avatar_Change").setPosition(100, 300).setSize(25, 25).setLabel(""),

                cp5.addButton("Profile_Show_Background").setPosition(770, 100).setSize(50, 50).
                        setLabel("Map").onClick(callbackEvent -> {
                    switchScreen(Screen.PROFILE_SHOW_BACKGROUND);
                }).setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL)),

                cp5.addButton("Profile_History_Next").setPosition(770, 375).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                    Student stu = (Student) GeoQuiz.getUser();
                    if ((stu.getProfileHistory().getActualPage() < stu.getProfileHistory().getMaxPages()))
                        stu.getProfileHistory().increaseRange();
                }),
                cp5.addButton("Profile_History_Last").setPosition(630, 375).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                    Student stu = (Student) GeoQuiz.getUser();
                    if (stu.getProfileHistory().getStart() != 0) stu.getProfileHistory().decreaseRange();
                }),

                cp5.addButton("Profile_Avatar_Lion").setPosition(100, 350).setSize(50, 50).setImage(ImageMap.getImage(ImageName.AVATAR_LION_SMALL)).hide(),
                cp5.addButton("Profile_Avatar_Dolphin").setPosition(160, 350).setSize(50, 50).setImage(ImageMap.getImage(ImageName.AVATAR_DOLPHIN_SMALL)).hide(),
                cp5.addButton("Profile_Avatar_Eagle").setPosition(220, 350).setSize(50, 50).setImage(ImageMap.getImage(ImageName.AVATAR_EAGLE_SMALL)).hide(),
                cp5.addButton("Profile_Avatar_Zebra").setPosition(100, 410).setSize(50, 50).setImage(ImageMap.getImage(ImageName.AVATAR_ZEBRA_SMALL)).hide(),
                cp5.addButton("Profile_Avatar_Coala").setPosition(160, 410).setSize(50, 50).setImage(ImageMap.getImage(ImageName.AVATAR_COALA_SMALL)).hide(),
                cp5.addButton("Profile_Avatar_Penguin").setPosition(220, 410).setSize(50, 50).setImage(ImageMap.getImage(ImageName.AVATAR_PENGUIN_SMALL)).hide(),

                cp5.addButton("Profile_Student_Nickname_Change").setPosition(700, 100).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL)),

                cp5.addTextfield("Profile_Student_Nickname").setPosition(365, 100).setSize(320, 50).
                        setText(((Student) GeoQuiz.getUser()).getNickname()).setLabel(""),

                cp5.addButton("Profile_Student_Back").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_STUDENT);
                }),


                cp5.addButton("Profile_Student_Achievement_0").setPosition(80, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_0)).
                        onEnter(callbackEvent -> {
                            cp5.getController("Achievement_TextLabel_0").show();
                            hideAvatars();
                        }).
                        onLeave(callbackEvent -> {
                    cp5.getController("Achievement_TextLabel_0").hide();
                    showAvatars();
                }),
                cp5.addTextlabel("Achievement_TextLabel_0").setPosition(80, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_0")).hide(),


                cp5.addButton("Profile_Student_Achievement_1").setPosition(160, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_1)).
                        onEnter(callbackEvent -> {
                            cp5.getController("Achievement_TextLabel_1").show();
                            hideAvatars();
                        }).
                        onLeave(callbackEvent -> {
                    cp5.getController("Achievement_TextLabel_1").hide();
                    showAvatars();
                }),
                cp5.addLabel("Achievement_TextLabel_1").setPosition(160, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_1")).hide(),

                cp5.addButton("Profile_Student_Achievement_2").setPosition(240, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_2)).
                        onEnter(callbackEvent -> {
                            cp5.getController("Achievement_TextLabel_2").show();
                            hideAvatars();
                        }).
                        onLeave(callbackEvent -> {
                    cp5.getController("Achievement_TextLabel_2").hide();
                    showAvatars();
                }),
                cp5.addLabel("Achievement_TextLabel_2").setPosition(240, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_2")).hide(),

                cp5.addButton("Profile_Student_Achievement_3").setPosition(320, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_3)).
                        onEnter(callbackEvent -> {
                            cp5.getController("Achievement_TextLabel_3").show();
                            hideAvatars();
                        }).
                        onLeave(callbackEvent -> {
                    cp5.getController("Achievement_TextLabel_3").hide();
                    showAvatars();
                }),
                cp5.addLabel("Achievement_TextLabel_3").setPosition(320, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_3")).hide(),

                cp5.addButton("Profile_Student_Achievement_4").setPosition(400, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_4)).
                        onEnter(callbackEvent -> cp5.getController("Achievement_TextLabel_4").show()).
                        onLeave(callbackEvent -> cp5.getController("Achievement_TextLabel_4").hide()),
                cp5.addLabel("Achievement_TextLabel_4").setPosition(400, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_4")).hide(),

                cp5.addButton("Profile_Student_Achievement_5").setPosition(480, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_5)).
                        onEnter(callbackEvent -> cp5.getController("Achievement_TextLabel_5").show()).
                        onLeave(callbackEvent -> cp5.getController("Achievement_TextLabel_5").hide()),
                cp5.addLabel("Achievement_TextLabel_5").setPosition(480, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_5")).hide(),

                cp5.addButton("Profile_Student_Achievement_6").setPosition(560, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_6)).
                        onEnter(callbackEvent -> cp5.getController("Achievement_TextLabel_6").show()).
                        onLeave(callbackEvent -> cp5.getController("Achievement_TextLabel_6").hide()),
                cp5.addLabel("Achievement_TextLabel_6").setPosition(560, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_6")).hide(),

                cp5.addButton("Profile_Student_Achievement_7").setPosition(640, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_7)).
                        onEnter(callbackEvent -> cp5.getController("Achievement_TextLabel_7").show()).
                        onLeave(callbackEvent -> cp5.getController("Achievement_TextLabel_7").hide()),
                cp5.addLabel("Achievement_TextLabel_7").setPosition(640, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_7")).hide(),

                cp5.addButton("Profile_Student_Achievement_8").setPosition(720, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_8)).
                        onEnter(callbackEvent -> cp5.getController("Achievement_TextLabel_8").show()).
                        onLeave(callbackEvent -> cp5.getController("Achievement_TextLabel_8").hide()),
                cp5.addLabel("Achievement_TextLabel_8").setPosition(720, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_8")).hide(),

                cp5.addButton("Profile_Student_Achievement_9").setPosition(800, 500).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.ACHIEV_9)).
                        onEnter(callbackEvent -> cp5.getController("Achievement_TextLabel_9").show()).
                        onLeave(callbackEvent -> cp5.getController("Achievement_TextLabel_9").hide()),
                cp5.addLabel("Achievement_TextLabel_9").setPosition(800, 450)
                        .setText(GeoQuiz.getLanguageManager().getString("achievement_9")).hide()
        });
    }

    private void uielementsCreateStudentPractise() {
        String[] cats = new String[Category.values().length];
        int idx = 0;
        for (Category c : Category.values()) {
            cats[idx++] = c.name();
        }

        controller.put(Screen.PRACTISE_STUDENT, new Controller[]{

                cp5.addButton("Practise_Student_Back").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_STUDENT);
                }),

                cp5.addButton("Practise_Student_Go").setPosition(775, 275).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.GO)).onClick(callbackEvent -> {
                    GeoQuiz.getGameManager().setChoosenCategory();
                    GeoQuiz.getGameManager().setChoosenLevel();
                    GeoQuiz.getGameManager().createQuestions();
                    GeoQuiz.getGameManager().setPlaying(true);
                    GeoQuiz.getSoundManager().updateBackgroundPlaying(false);
                    changeQuestionLanguage();
                    switchScreen(Screen.PLAYING);
                })
        });
    }

    private void uielementsCreateStudentPlaying() {
        controller.put(Screen.PLAYING, new Controller[]{
                cp5.addButton("Practise_Student_Playing_Back").setPosition(20, 20).setSize(200, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT))
                        .onClick(callbackEvent -> {
                    GeoQuiz.getGameManager().setPlaying(false);
                    Toggle t = (Toggle) GeoQuiz.getSoundManager().getSoundMenu().get("Sound_PlayPause");
                    if (t.getState()) GeoQuiz.getSoundManager().updateBackgroundPlaying(true);
                }),

                cp5.addButton("Practise_Student_Playing_Next").setPosition(775, 275).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.ARROW_RIGHT))
        });
    }

    private void uielementsCreateStudenProfileBackground() {
        controller.put(Screen.PROFILE_SHOW_BACKGROUND, new Controller[]{
                cp5.addButton("Profile_Student_Background_Back").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.PROFILE_STUDENT);
                })
        });
    }

    private void uielementsCreateStudentGameFeedback() {
        controller.put(Screen.PRACTISE_STUDENT_GAME_FEEDBACK, new Controller[]{
                cp5.addButton("Practise_Student_Game_Feedback_Back")
                        .setPosition(345, 450).setSize(210, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("back_to_menu"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.getGameManager().setPlaying(false);
                    Toggle t = (Toggle) GeoQuiz.getSoundManager().getSoundMenu().get("Sound_PlayPause");
                    if (t.getState()) GeoQuiz.getSoundManager().updateBackgroundPlaying(true);
                })
        });
    }

    private void uielementsCreateSoundMenu() {
        String[] languageList = new String[Language.values().length];
        int idx = 0;
        for (Language l : Language.values()) {
            languageList[idx++] = l.getName();
        }

        controller.put(Screen.PRACTISE_STUDENT, new Controller[]{

                GeoQuiz.getSoundManager().getSoundMenu().addButton("Sound_Menu").setPosition(840, 10).setSize(50, 50).setImage(ImageMap.getImage(ImageName.SETTING))
                        .onClick(callbackEvent -> {
                    GeoQuiz.getSoundManager().updateShowMenu();
                    manageControllerView();
                }),

                GeoQuiz.getSoundManager().getSoundMenu().addToggle("Sound_PlayPause").setPosition(660, 20).setSize(25, 25)
                        .hide().setLabelVisible(false).setState(GeoQuiz.getSoundManager().isBackgroundPlaying())
                        .onClick(callbackEvent -> {
                    Toggle t = (Toggle) callbackEvent.getController();
                    GeoQuiz.getSoundManager().updateBackgroundPlaying(t.getState());
                }),

                GeoQuiz.getSoundManager().getSoundMenu().addSlider("Sound_Volume").setPosition(660, 75).setSize(150, 20).
                        hide().setValue(GeoQuiz.getSoundManager().getVolume()).
                        setLabelVisible(false).setRange(0, 100).onChange(callbackEvent -> {
                    Slider s = (Slider) callbackEvent.getController();
                    GeoQuiz.getSoundManager().setVolume(s.getValue());
                }),

                GeoQuiz.getSoundManager().getSoundMenu().addDropdownList("Sound_Language").
                        setPosition(660, 120).setSize(150, 100).setBarHeight(20).setItemHeight(20)
                        .setLabel(GeoQuiz.getLanguageManager().getActiveLanguage().getName()).addItems(languageList).hide().close()
                        .onClick(callbackEvent -> {
                    DropdownList dList = (DropdownList) callbackEvent.getController();
                    GeoQuiz.getLanguageManager().setActiveLanguage(dList.getCaptionLabel().getText());
                    if (GeoQuiz.getSettings().getScreen().equals(Screen.LOGIN_STUDENT)) {
                        changeLoginLanguage();
                    } else {
                        changeStudentMenuLanguage();
                    }
                })
        });
    }

}