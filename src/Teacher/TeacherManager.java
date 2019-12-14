package Teacher;

import Teacher.TestManager.NewTestManager;
import controlP5.ControlP5;
import processing.core.PApplet;

public class TeacherManager {

    private PApplet applet;
    private NewTestManager testManager;

    public TeacherManager(PApplet applet, ControlP5 cp5){
        this.applet = applet;
        this.testManager = new NewTestManager(applet, cp5);
    }

    public NewTestManager getTestManager(){
        return this.testManager;
    }

    public void updateQuestionList(){
        this.testManager.loadPages();
    }
}
