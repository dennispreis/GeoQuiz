package Teacher;

import Teacher.TestManager.NewTestManager;
import processing.core.PApplet;

public class TeacherManager {

    private PApplet applet;
    private NewTestManager testManager;

    public TeacherManager(PApplet applet){
        this.applet = applet;
        this.testManager = new NewTestManager(applet);
    }

    public NewTestManager getTestManager(){
        return this.testManager;
    }

}
