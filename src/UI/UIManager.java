package UI;

import DAOs.MyPaperDao;
import DAOs.MyQuestionDao;
import DAOs.MyTestDao;
import DAOs.QuestionDaoInterface;
import DAOs.TestDaoInterface;
import DTOs.Question;
import DTOs.Student;
import DTOs.Teacher;
import DTOs.Test;
import GameManager.Category;
import GameManager.ChooseAble;
import GameManager.TypeChooser;
import Images.ImageMap;
import Images.ImageName;
import Languages.Language;
import controlP5.*;
import Main.GeoQuiz;
import Main.Screen;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Main.GeoQuiz.switchScreen;

public class UIManager {

    private ControlP5 cp5;
    private HashMap<Screen, Controller[]> controller;

    public UIManager(ControlP5 cp5) {
        this.cp5 = cp5;
        controller = new HashMap<>();
        uielementsCreateLogin();
        uielementsCreateSettingsMenu();
        uielementsHideAll();
    }

    public void updateController(Screen oldScreen, Screen newScreen) {
        hideControllers(oldScreen);
        showControllers(newScreen);
    }

    private void showControllers(Screen showScreen) {
        System.out.println(controller.get(showScreen));
        System.out.println(controller.size());
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
        uielementsCreateStudentWork();
        uielementsCreateStudentPractise();
        uielementsCreateStudentProfile();
        uielementsHideAll();
    }

    public void createAdminElements() {
        uielementsCreateAdminChangePassword();
        uielementsCreateAdminPasscode();
        uielementsCreateAdminMainMenu();
        uielementsCreateShowStudent_Progress();
        uielementsCreateShowStudent_Test();
        uielementsCreateCreateNewTest();
        uielementsCreateChangeStudentPassword();
        uielementsCreateCreateNewQuestion();
        uielementsCreateShowTestList();
        uielementsCreateShowOneTest();
        uielementsHideAll();
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

    private void changeAvatarVisibility(boolean state) {
        if (state) {
            cp5.get("Profile_Avatar_Lion").show();
            cp5.get("Profile_Avatar_Eagle").show();
            cp5.get("Profile_Avatar_Dolphin").show();
            cp5.get("Profile_Avatar_Zebra").show();
            cp5.get("Profile_Avatar_Penguin").show();
            cp5.get("Profile_Avatar_Coala").show();

        } else {
            cp5.get("Profile_Avatar_Lion").hide();
            cp5.get("Profile_Avatar_Eagle").hide();
            cp5.get("Profile_Avatar_Dolphin").hide();
            cp5.get("Profile_Avatar_Zebra").hide();
            cp5.get("Profile_Avatar_Coala").hide();
            cp5.get("Profile_Avatar_Penguin").hide();
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

    private void clearPasswordData() {
        Textfield tf = (Textfield) cp5.get("Login_Password");
        tf.clear();
    }

    //-------CHANGE LANGUAGE
    private void changeLoginLanguage() {
        //LOGIN
        cp5.get("Login_Login").setLabel(GeoQuiz.getLanguageManager().getString("login"));
        cp5.get("Login_Name").setLabel(GeoQuiz.getLanguageManager().getString("name"));
        cp5.get("Login_Password").setLabel(GeoQuiz.getLanguageManager().getString("password"));
    }

    private void changeAdminMenuLanguage() {
        //ADMIN MAIN MENU
        cp5.get("Change_Password").setLabel(GeoQuiz.getLanguageManager().getString("Change_Password"));
        cp5.get("Admin_Students").setLabel(GeoQuiz.getLanguageManager().getString("student"));
        cp5.get("View_Student_Progress").setLabel(GeoQuiz.getLanguageManager().getString("View_Student_Progress"));
        cp5.get("Change_Password").setLabel(GeoQuiz.getLanguageManager().getString("Change_Password"));

        //ADMIN PASSCODE
        cp5.get("Passcode").setLabel(GeoQuiz.getLanguageManager().getString("passcode"));
        cp5.get("Change_Password_Check").setLabel(GeoQuiz.getLanguageManager().getString("Change_Password_Check"));

    }

    private void changeStudentMenuLanguage() {
        //STUDENT PROFILE
        if (!GeoQuiz.getUser().isTeacher()) {
            Textlabel l;
            for (int i = 0; i < 10; i++) {
                l = (Textlabel) cp5.get("Achievement_TextLabel_" + i);
                l.setText(GeoQuiz.getLanguageManager().getString("achievement_" + i));
            }

            //STUDENT PRACTISE
            ChooseAble[] tmp = GeoQuiz.getGameManager().getCategoryChooser().getElements();
            tmp[0].setText(GeoQuiz.getLanguageManager().getString("cities"));
            tmp[1].setText(GeoQuiz.getLanguageManager().getString("mountains"));
            tmp[2].setText(GeoQuiz.getLanguageManager().getString("rivers"));
            tmp[3].setText(GeoQuiz.getLanguageManager().getString("world"));
            tmp[4].setText(GeoQuiz.getLanguageManager().getString("islands"));
            tmp[5].setText(GeoQuiz.getLanguageManager().getString("lakes"));

            /*
            tmp = GeoQuiz.getGameManager().getLevelChooser().getElements();
            tmp[0].setText(GeoQuiz.getLanguageManager().getString("easy"));
            tmp[1].setText(GeoQuiz.getLanguageManager().getString("medium"));
            tmp[2].setText(GeoQuiz.getLanguageManager().getString("hard"));
            */

            //STUDENT GAME FEEDBACK
            cp5.get("Practise_Student_Game_Feedback_Back").setLabel(GeoQuiz.getLanguageManager().getString("back_to_menu"));
        }
        //STUDENT PLAY
        changeQuestionLanguage();

    }

    private void changeQuestionLanguage() {
        if (!GeoQuiz.getUser().isTeacher() && GeoQuiz.getGameManager().isPlaying()) {
            for (Question q : GeoQuiz.getGameManager().getQuestions()) {
                q.setQuestion_text(GeoQuiz.getLanguageManager().getQuestionsString(Integer.toString(q.getId())));
            }
            Textarea ta = (Textarea) GeoQuiz.getGameManager().getCp5().get("Question_Textarea");
            ta.setText(GeoQuiz.getLanguageManager().getQuestionsString(Integer.toString(GeoQuiz.getGameManager().getActualQuestion().getId())));
        }
    }

    //-------CONTROLLER GENERATOR
    private void uielementsCreateLogin() {

        Color col = Color.decode("#7974fc");

        controller.put(Screen.LOGIN, new Controller[]{

                cp5.addButton("Login_Login")
                        .setPosition(355, 420)
                        .setSize(200, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("login"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.loginVerification();
                    clearPasswordData();
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
                        .setLabel(GeoQuiz.getLanguageManager().getString("password")),

                cp5.addButton("Student").setPosition(330, 520).setSize(120, 50)
                        .onClick(callbackEvent -> {
                    Textfield tf = (Textfield) cp5.get("Login_Name");
                    tf.setText("max");
                    tf = (Textfield) cp5.get("Login_Password");
                    tf.setText("123456");
                }),
                cp5.addButton("Teacher").setPosition(465, 520).setSize(120, 50)
                        .onClick(callbackEvent -> {
                    Textfield tf = (Textfield) cp5.get("Login_Name");
                    tf.setText("peter");
                    tf = (Textfield) cp5.get("Login_Password");
                    tf.setText("password");
                })
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
                            ((Student)GeoQuiz.getUser()).updateProfileHistory();
                    switchScreen(Screen.PROFILE_STUDENT);
                }),
                cp5.addButton("Main_Menu_Student_Logout").
                        setPosition(20, 20).
                        setSize(200, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.LOGIN);
                })
        });
    }

    private void uielementsCreateAdminMainMenu() {
        controller.put(Screen.MAIN_MENU_ADMIN, new Controller[]{

                cp5.addButton("Change_Password")
                        .setPosition(255, 180)
                        .setSize(400, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("Change_Password"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.switchScreen(Screen.CHANGE_PASSWORD_ADMIN_PASSCODE);
                }),

                cp5.addButton("Admin_Students")
                        .setPosition(255, 240)
                        .setSize(400, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("student"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.switchScreen(Screen.ADMIN_STUDENTS);
                }),

                cp5.addButton("View_Student_Progress")
                        .setPosition(255, 300)
                        .setSize(400, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("View_Student_Progress"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.switchScreen(Screen.SHOW_STUDENT_PROGRESS);
                }),
                
                cp5.addButton("View_Student_Test")
                        .setPosition(255, 360)
                        .setSize(400, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("View_Student_Test"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.switchScreen(Screen.SHOW_STUDENT_TEST);
                }),

                cp5.addButton("Create_New_Test")
                        .setPosition(255, 420)
                        .setSize(400, 50)
                        .setLabel("Create Tests")
                        .onClick(callbackEvent -> {
                              try{GeoQuiz.getTeacherManager().updateQuestionList();}catch(NullPointerException e){}
                            GeoQuiz.getTeacherManager().getTestManager().getTypeChooser().updateActiveElement(GeoQuiz.getTeacherManager().getTestManager().getTypeChooser().getElements()[0]);
                    GeoQuiz.switchScreen(Screen.CREATE_NEW_TEST);
                }),
                cp5.addButton("Create_New_Question")
                        .setPosition(255, 480)
                        .setSize(400, 50)
                        .setLabel("Create Question")
                        .onClick(callbackEvent -> {
                     GeoQuiz.switchScreen(Screen.CREATE_NEW_QUESTION);
                }),
                cp5.addButton("Main_Menu_Admin_Logout")
                        .setPosition(20, 20)
                        .setSize(200, 50)
                        .setImage(ImageMap.getImage(ImageName.LOGOUT))
                        .onClick(callbackEvent -> {
                    GeoQuiz.switchScreen(Screen.LOGIN);
                })
        });
    }

    private void uielementsCreateAdminPasscode() {
        Color col = Color.decode("#7974fc");

        controller.put(Screen.CHANGE_PASSWORD_ADMIN_PASSCODE, new Controller[]{

                cp5.addTextfield("Passcode")
                        .setPosition(355, 220)
                        .setSize(200, 50)
                        .setColorBackground(col.getRGB())
                        .setColorForeground(Color.WHITE.getRGB())
                        .setPasswordMode(true)
                        .setAutoClear(false)
                        .setLabel(GeoQuiz.getLanguageManager().getString("passcode")),

                cp5.addButton("Change_Password_Check")
                        .setPosition(355, 300)
                        .setSize(200, 50)
                        .setLabel(GeoQuiz.getLanguageManager().getString("Change_Password_Check"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.checkForPasswordChange();
                }),

                cp5.addButton("Passcode_Admin_Logout").
                        setPosition(20, 20).
                        setSize(200, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT))
                        .onClick(callbackEvent -> {
                    GeoQuiz.switchScreen(Screen.MAIN_MENU_ADMIN);
                })
        });

        Textfield tf = (Textfield) cp5.get("Passcode");
        tf.getCaptionLabel().setPaddingY(-90);
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
                        .setLabel(GeoQuiz.getLanguageManager().getString("change"))
                        .onClick(callbackEvent -> {
                    GeoQuiz.changePassword();
                }),

                cp5.addButton("Change_Admin_Password_Logout").
                        setPosition(20, 20).
                        setSize(200, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT))
                        .onClick(callbackEvent -> {
                    GeoQuiz.switchScreen(Screen.MAIN_MENU_ADMIN);
                }),
        });

        Textfield tf = (Textfield) cp5.get("Change_Password_Name");
        tf.getCaptionLabel().setPaddingY(-90);
        tf = (Textfield) cp5.get("Change_Password_Password");
        tf.getCaptionLabel().setPaddingY(-90);

    }

    private void uielementsCreateStudentWork() {
        
        TestDaoInterface ITestDao = new MyTestDao();
        List<Test> testList = ITestDao.getAllTest();
        Controller[] controlArray = new Controller[testList.size()+1];
        controlArray[0] = cp5.addButton("Work_Student_Back").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_STUDENT);
                });
        int id = GeoQuiz.getUser().getId();
        for(int i = 0 ; i < testList.size();i++)
        {
            int test_id = testList.get(i).getTest_id();
            controlArray[i+1] = cp5.addButton("Attempt"+i).setPosition(650,200+30*i).setSize(15,15).setLabel(" ").onClick(
            callBackEvent ->{
                    GeoQuiz.getGameManager().setChoosenCategory();
                    GeoQuiz.getGameManager().createQuestions(id,test_id);
                    GeoQuiz.getGameManager().setPlaying(true);
                    GeoQuiz.getSoundManager().updateBackgroundPlaying(false);
                    changeQuestionLanguage();
                    switchScreen(Screen.PLAYING);
            }
            );
        }
        
        controller.put(Screen.WORK_STUDENT, controlArray);

    }

    private void uielementsCreateShowTestList(){
        TestDaoInterface ITestDao = new MyTestDao();
        List<Test> testList = ITestDao.getAllTest();
        Controller[] controlArray = new Controller[testList.size()+1];
        controlArray[0] = cp5.addButton("Test_List_Back").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.CREATE_NEW_TEST);
                });
        int id = GeoQuiz.getUser().getId();
        for(int i = 0 ; i < testList.size();i++)
        {
            int test_id = testList.get(i).getTest_id();
            controlArray[i+1] = cp5.addButton("Attempt"+i).setPosition(650,200+30*i).setSize(15,15).setLabel(" ").onClick(
            callBackEvent ->{
                    GeoQuiz.getTestQuestionList().setQuestionList(test_id);;
                    changeQuestionLanguage();
                    switchScreen(Screen.SHOW_ONE_TEST);
            }
            );
        }
        
        controller.put(Screen.TEST_LIST, controlArray);
    }
    
    private void uielementsCreateShowOneTest(){
      controller.put(Screen.SHOW_ONE_TEST, new Controller[]{
                cp5.addButton("Admin_Show_One_Logout").setPosition(20, 20).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.CREATE_NEW_TEST);
                })
        });
    }
    
    private void uielementsCreateStudentProfile() {
        controller.put(Screen.PROFILE_STUDENT, new Controller[]{

                //---------General ui elements

                cp5.addToggle("Profile_Student_Avatar_Change").setPosition(100, 300).setSize(25, 25).setLabel("")
                        .onChange(callbackEvent -> {
                    Toggle t = (Toggle) callbackEvent.getController();
                    changeAvatarVisibility(t.getState());
                }),

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

                cp5.addButton("Profile_Student_Nickname_Change").setPosition(700, 100).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                    Textfield tf = (Textfield) cp5.get("Profile_Student_Nickname");
                    ((Student) GeoQuiz.getUser()).setNickname(tf.getText());
                    GeoQuiz.getIStudentDao().saveStudentNickName((Student) GeoQuiz.getUser());
                }),

                cp5.addTextfield("Profile_Student_Nickname").setPosition(365, 100).setSize(320, 50).
                        setText(((Student) GeoQuiz.getUser()).getNickname()).setLabel(""),

                cp5.addButton("Profile_Student_Back").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_STUDENT);
                }),

                //---------Profile avatars

                cp5.addButton("Profile_Avatar_Lion").setPosition(100, 350).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.AVATAR_LION_SMALL)).hide()
                        .onClick(callbackEvent -> {
                    ((Student) GeoQuiz.getUser()).setAvatar(ImageName.AVATAR_LION.name());
                    GeoQuiz.getIStudentDao().saveStudentAvatar((Student) GeoQuiz.getUser());
                }),
                cp5.addButton("Profile_Avatar_Dolphin").setPosition(160, 350).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.AVATAR_DOLPHIN_SMALL)).hide().onClick(callbackEvent -> {
                    ((Student) GeoQuiz.getUser()).setAvatar(ImageName.AVATAR_DOLPHIN.name());
                    GeoQuiz.getIStudentDao().saveStudentAvatar((Student) GeoQuiz.getUser());
                }),
                cp5.addButton("Profile_Avatar_Eagle").setPosition(220, 350).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.AVATAR_EAGLE_SMALL)).hide().onClick(callbackEvent -> {
                    ((Student) GeoQuiz.getUser()).setAvatar(ImageName.AVATAR_EAGLE.name());
                    GeoQuiz.getIStudentDao().saveStudentAvatar((Student) GeoQuiz.getUser());
                }),
                cp5.addButton("Profile_Avatar_Zebra").setPosition(100, 410).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.AVATAR_ZEBRA_SMALL)).hide().onClick(callbackEvent -> {
                    ((Student) GeoQuiz.getUser()).setAvatar(ImageName.AVATAR_ZEBRA.name());
                    GeoQuiz.getIStudentDao().saveStudentAvatar((Student) GeoQuiz.getUser());
                }),
                cp5.addButton("Profile_Avatar_Coala").setPosition(160, 410).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.AVATAR_COALA_SMALL)).hide().onClick(callbackEvent -> {
                    ((Student) GeoQuiz.getUser()).setAvatar(ImageName.AVATAR_COALA.name());
                    GeoQuiz.getIStudentDao().saveStudentAvatar((Student) GeoQuiz.getUser());
                }),
                cp5.addButton("Profile_Avatar_Penguin").setPosition(220, 410).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.AVATAR_PENGUIN_SMALL)).hide().onClick(callbackEvent -> {
                    ((Student) GeoQuiz.getUser()).setAvatar(ImageName.AVATAR_PENGUIN.name());
                    GeoQuiz.getIStudentDao().saveStudentAvatar((Student) GeoQuiz.getUser());
                }),

                //---------Achievements

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

                cp5.addButton("Practise_Student_Go").setPosition(400, 350).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.GO)).onClick(callbackEvent -> {
                    GeoQuiz.getGameManager().setChoosenCategory();
                    GeoQuiz.getGameManager().createQuestions();
                    GeoQuiz.getGameManager().setPlaying(true);
                    GeoQuiz.getGameManager().updateQuestionText();
                    GeoQuiz.getSoundManager().updateBackgroundPlaying(false);
                    changeQuestionLanguage();
                    switchScreen(Screen.PLAYING);
                })

        });
    }

    private void uielementsCreateStudentPlaying() {
        controller.put(Screen.PLAYING, new Controller[]{
                cp5.addButton("Practise_Student_Playing_Back").setPosition(20, 20).setSize(200, 50)
                        .setImage(ImageMap.getImage(ImageName.LOGOUT))
                        .onClick(callbackEvent -> {
                    GeoQuiz.getGameManager().setPlaying(false);
                    Toggle t = (Toggle) GeoQuiz.getSoundManager().getSoundMenu().get("Sound_PlayPause");
                    if (t.getState()) GeoQuiz.getSoundManager().updateBackgroundPlaying(true);

                    GeoQuiz.getGameManager().setPlaying(false);
                    GeoQuiz.getGameManager().setActuallQuestionIndex(0);
                    switchScreen(Screen.MAIN_MENU_STUDENT);
                }),
                cp5.addButton("Pratice_Student_Read_Question").setPosition(840, 70).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                    GeoQuiz.getSoundManager().triggerLanguageAudio();
                }),

                cp5.addButton("Practise_Student_Playing_Next").setPosition(775, 275).setSize(100, 100)
                        .setImage(ImageMap.getImage(ImageName.ARROW_RIGHT))
                        .onClick(callbackEvent -> {
                    if (!GeoQuiz.getGameManager().nextQuestion()) {
                        switchScreen(Screen.PRACTISE_STUDENT_GAME_FEEDBACK);
                        GeoQuiz.getGameManager().loadPractiseFeedback();
                    }
                })
        });
    }

    private void uielementsCreateStudenProfileBackground() {
        controller.put(Screen.PROFILE_SHOW_BACKGROUND, new Controller[]
                {
                        cp5.addButton("Profile_Student_Background_Back").setPosition(20, 20).setSize(100, 100).
                                setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent
                                ->
                        {
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
                    Toggle t = (Toggle) GeoQuiz.getSoundManager().getSoundMenu().get("Sound_PlayPause");
                    if (t.getState()) GeoQuiz.getSoundManager().updateBackgroundPlaying(true);

                    GeoQuiz.getGameManager().setActuallQuestionIndex(0);
                    GeoQuiz.getGameManager().setPlaying(false);
                    GeoQuiz.getGameManager().reset();
                    switchScreen(Screen.MAIN_MENU_STUDENT);

                })
        });
    }

    private void uielementsCreateSettingsMenu() {
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
                        }).setState(false),

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
                    changeLoginLanguage();
                    if (!GeoQuiz.getSettings().getScreen().equals(Screen.LOGIN)) {
                        if (GeoQuiz.getUser().isTeacher()) {
                            changeAdminMenuLanguage();
                        } else {
                            changeStudentMenuLanguage();
                        }
                    }
                })
        });
    }

    private void uielementsCreateCreateNewTest() {
      
        controller.put(Screen.CREATE_NEW_TEST, new Controller[]{
                cp5.addButton("Admin_Create_Test_Logout").setPosition(20, 20).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_ADMIN);
                }),
                cp5.addTextfield("Admin_Create_Test_TestName").setPosition(100, 75).setSize(300, 50)
                        .setLabel("Test name").setAutoClear(false),
                cp5.addButton("Admin_Create_Test_CreateTest").setPosition(425, 75).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                    Textfield tf = (Textfield) cp5.get("Admin_Create_Test_TestName");
                    if (GeoQuiz.getTeacherManager().getTestManager().getMarkedQuestions().size() == 10 && !tf.getText().isEmpty()) {
                        MyTestDao myDao = new MyTestDao();
                        myDao.addTest(tf.getText(), GeoQuiz.getTeacherManager().getTestManager().getQuestionList());
                        cp5.addButton("dslkdjsalkdjsadjsaklj").setPosition(225,150).setSize(450,300).setLabel("Test Created")
                                .onClick(callBackEvent->{
                        cp5.getController("dslkdjsalkdjsadjsaklj").remove();
                        ((Textfield)cp5.get("Admin_Create_Test_TestName")).setText("");
                        GeoQuiz.getTeacherManager().getTestManager().resetMarkedQuestions();
                    });
                  //  new FeedBackPop(GeoQuiz.getApplet(),1000).show();
                    }
                }),
                cp5.addButton("Admin_Create_Test_resetTest").setPosition(500, 75).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                        ((Textfield)cp5.get("Admin_Create_Test_TestName")).setText("");
                    GeoQuiz.getTeacherManager().getTestManager().resetMarkedQuestions();
                }),
                cp5.addButton("TestList").setPosition(725,75).setSize(100,50).onClick(
                callbackEvent -> {
                       GeoQuiz.switchScreen(Screen.TEST_LIST);
                }
                )
        });
        Textfield tf = (Textfield) cp5.get("Admin_Create_Test_TestName");
        tf.getCaptionLabel().setPaddingY(-90);
    }

    private void uielementsCreateChangeStudentPassword() {
        controller.put(Screen.ADMIN_STUDENTS, new Controller[]{
                cp5.addButton("Admin_Change_Student_Password").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_ADMIN);
                })
        });
    }

    private void uielementsCreateShowStudent_Progress() {
        Teacher teach = (Teacher) GeoQuiz.getUser();
        Controller[] controlArray = new Controller[teach.getClassList().size() + 3];
        controlArray[0] = cp5.addButton("Admin_Show_Student_Progress").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_ADMIN);
                });
        controlArray[1] =  cp5.addButton("Profile_History_Next ").setPosition(770, 520).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                        
                    if ((teach.getProfileHistory().getActualPage() < teach.getProfileHistory().getMaxPages()))
                    {   
                        teach.getProfileHistory().increaseRange();
                    }
                });
        controlArray[2] = cp5.addButton("Profile_History_Last ").setPosition(630, 520).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                    if (teach.getProfileHistory().getStart() != 0) teach.getProfileHistory().decreaseRange();
                });
        for(int i = 0 ; i < teach.getClassList().size();i++)
        {
            String class_name = teach.getClassList().get(i).getName();
            controlArray[i+3] = cp5.addButton(class_name+" ").setPosition(85+100*i,75).setSize(80,40).onClick(
            callbackEvent -> {
                System.out.println("Class "+class_name+" Selected");
                teach.setProfileHistory(class_name);
            }
            );
        }
        
        controller.put(Screen.SHOW_STUDENT_PROGRESS, controlArray);
    }
    
    private void uielementsCreateShowStudent_Test() {
        Teacher teach = (Teacher)GeoQuiz.getUser();
        Controller[] controlArray = new Controller[teach.getClassList().size()+3];
        controlArray[0] = cp5.addButton("Admin_Show_Student_Test").setPosition(20, 20).setSize(100, 100).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                    switchScreen(Screen.MAIN_MENU_ADMIN);
                });
        controlArray[1] =  cp5.addButton("Profile_History_Next").setPosition(770, 520).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                        
                    if ((teach.getProfileHistory().getActualPage() < teach.getProfileHistory().getMaxPages()))
                    {   
                        teach.getProfileHistory().increaseRange();
                    }
                });
        controlArray[2] = cp5.addButton("Profile_History_Last").setPosition(630, 520).setSize(50, 50)
                .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                .onClick(callbackEvent -> {
                    if (teach.getProfileHistory().getStart() != 0) teach.getProfileHistory().decreaseRange();
                });
        for (int i = 0; i < teach.getClassList().size(); i++) {
            String class_name = teach.getClassList().get(i).getName();
            controlArray[i + 3] = cp5.addButton(class_name).setPosition(85 + 100 * i, 75).setSize(80, 40).onClick(
                    callbackEvent -> {
                        System.out.println("Class " + class_name + " Selected");
                        teach.setProfileHistory(class_name);
                    }
            );
        }

        
        controller.put(Screen.SHOW_STUDENT_TEST, controlArray);

    }
    
    private void uielementsCreateCreateNewQuestion(){
        int choice =0;    
                     
        controller.put(Screen.CREATE_NEW_QUESTION, new Controller[]{
                cp5.addButton("Admin_Create_Question_Logout").setPosition(20, 20).setSize(50, 50).
                        setImage(ImageMap.getImage(ImageName.LOGOUT)).onClick(callbackEvent -> {
                      this.clearQuestionButton();
                    switchScreen(Screen.MAIN_MENU_ADMIN);
                }),
                cp5.addTextfield("Admin_Create_Question_QuestionText").setPosition(100, 75).setSize(700, 50)
                        .setLabel("Question Text").setAutoClear(false),
                cp5.addButton("Admin_Create_Question_MultipleChoice").setPosition(75,400).setSize(300,50).setLabel("MultipleChoice")
                    .onClick(callBackEvent->{
                        this.clearQuestionButton();
                       cp5.addTextfield("Tf1").setPosition(400,200).setSize(300,50).setLabel("Answer 1");
                        cp5.addTextfield("Tf2").setPosition(400,300).setSize(300,50).setLabel("Answer 2");
                        cp5.addTextfield("Tf3").setPosition(400,400).setSize(300,50).setLabel("Answer 3");
                        cp5.addTextfield("Tf4").setPosition(400,500).setSize(300,50).setLabel("Answer 4");
                        cp5.addRadioButton("Ca1").setPosition(725,200).setSize(50,50).addItem("√",1).addItem("√ ",2).addItem("√  ",3).addItem("√   ",4).setSpacingRow(50);
                    }),
                cp5.addButton("Admin_Create_Question_TrueORFalse").setPosition(75,250).setSize(300,50).setLabel("TrueFalse")
                    .onClick(callBackEvent->{
                        
                        this.clearQuestionButton();
                        cp5.addRadioButton("Tf9").setPosition(400,200).setSize(100,100).addItem("TRUE", 1).addItem("FALSE",2);
                    }),
                cp5.addButton("Admin_Create_Question_Drag And Drop").setPosition(75,325).setSize(300,50).setLabel("DragAndDrop")
                    .onClick(callBackEvent->{
                        
                        this.clearQuestionButton();
                        cp5.addTextfield("Tf5").setPosition(400,200).setSize(300,50).setLabel("Answer 1");
                        cp5.addTextfield("Tf6").setPosition(400,300).setSize(300,50).setLabel("Answer 2");
                        cp5.addTextfield("Tf7").setPosition(400,400).setSize(300,50).setLabel("Answer 3");
                        cp5.addTextfield("Tf8").setPosition(400,500).setSize(300,50).setLabel("Answer 4");
                        cp5.addRadioButton("Ca2").setPosition(725,200).setSize(50,50).addItem("√ ",1).addItem("√  ",2).addItem("√   ",3).addItem("√",4).setSpacingRow(50); 
                    }),
                cp5.addButton("Admin_Create_Question_CreateQuestion").setPosition(825, 75).setSize(50, 50)
                        .setImage(ImageMap.getImage(ImageName.PLACEHOLDER_SMALL))
                        .onClick(callbackEvent -> {
                    Textfield tf = (Textfield) cp5.get("Admin_Create_Question_QuestionText");
                    Textfield a1=null,a2=null,a3=null,a4=null;
                    String correct_answer = "";
                    int questionType=0;
                    try{
                        a1 = (Textfield) cp5.get("Tf1");
                        a2 = (Textfield) cp5.get("Tf2");
                        a3 = (Textfield) cp5.get("Tf3");
                        a4 = (Textfield) cp5.get("Tf4");
                        for(int i = 1 ; i <= 5;i++)
                        {
                            if(((RadioButton)cp5.get("Ca1")).getState(i))
                            {
                                correct_answer = Integer.toString(i);
                            }
                        }
                        questionType = 1;
                    }catch(NullPointerException e)
                    {
                        try{
                            a1 = (Textfield) cp5.get("Tf5");
                            a2 = (Textfield) cp5.get("Tf6");
                            a3 = (Textfield) cp5.get("Tf7");
                            a4 = (Textfield) cp5.get("Tf8");
                             for(int i = 1 ; i <= 5;i++)
                            {
                            if(((RadioButton)cp5.get("Ca2")).getState(i))
                            {
                                correct_answer =  ((Textfield) cp5.get("Tf"+(4+i))).getText();
                            }
                            }
                              questionType = 2;
                        }
                        catch(NullPointerException e1)
                        {
                                correct_answer = Boolean.toString(((RadioButton) cp5.get("Tf9")).getState("TRUE"));
                             questionType = 3;
                        }
                    }
                    finally{
                        if (!tf.getText().isEmpty()) 
                        {
                            QuestionDaoInterface IQuestionDao = new MyQuestionDao();
                            switch(questionType)
                            {
                                case 1:
                                    IQuestionDao.addMCQuestion("world", "World", tf.getText(), correct_answer, a1.getText(), a2.getText(), a2.getText(), a2.getText());
                                    break;
                                case 2:    
                                    IQuestionDao.addDDQuestion("world", "World", tf.getText(), correct_answer, a1.getText(), a2.getText(), a2.getText(), a2.getText());
                                    break;
                                case 3:
                                    IQuestionDao.addTFQuestion("world", "World", tf.getText(), correct_answer);
                                    break;
                            }
                            cp5.addButton("vnueuhdhasdskjsahdsakj").setPosition(225,150).setSize(450,300).setLabel("Question Created")
                                .onClick(callBackEvent->{
                            cp5.getController("vnueuhdhasdskjsahdsakj").remove();
                            });
                    }
                        else{
                            cp5.addButton("cuqwqhejnd").setPosition(225,150).setSize(450,300).setLabel("Please input QuestionText")
                                .onClick(callBackEvent->{
                            cp5.getController("cuqwqhejnd").remove();
                            });
                        }
                    }
                    }),
                
        });
        Textfield tf = (Textfield) cp5.get("Admin_Create_Question_QuestionText");
        tf.getCaptionLabel().setPaddingY(-90);
    }

    private void clearQuestionButton(){

        try{cp5.getController("Tf1").remove();}catch(NullPointerException e){}
        try{cp5.getController("Tf2").remove();}catch(NullPointerException e){}
        try{cp5.getController("Tf3").remove();}catch(NullPointerException e){}
        try{cp5.getController("Tf4").remove();}catch(NullPointerException e){}
        try{cp5.getController("Tf5").remove();}catch(NullPointerException e){}
        try{cp5.getController("Tf6").remove();}catch(NullPointerException e){}
        try{cp5.getController("Tf7").remove();}catch(NullPointerException e){}
        try{cp5.getController("Tf8").remove();}catch(NullPointerException e){}
        try{cp5.getGroup("Tf9").remove();}catch(NullPointerException e){}
        try{cp5.getGroup("Ca1").remove();}catch(NullPointerException e){}
        try{cp5.getGroup("Ca2").remove();}catch(NullPointerException e){}
        
    }
}

