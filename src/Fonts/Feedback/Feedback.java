package Fonts.Feedback;

import processing.core.PApplet;
import processing.core.PVector;

public abstract class Feedback implements FeedbackAble {

    PApplet applet;
    PVector position;
    PVector size;
    int time, actualTime;

    Feedback(PApplet applet, int time) {
        this.applet = applet;
        this.time = time;
        this.position = new PVector(0, 0);
        this.size = new PVector(50, 50);
        this.actualTime = applet.millis();
    }

    public abstract void show();

    public Feedback setSize(PVector size) {
        this.size = size;
        return this;
    }

    public Feedback setPosition(PVector position) {
        this.position = position;
        return this;
    }


}
