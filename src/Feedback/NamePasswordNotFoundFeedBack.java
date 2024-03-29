package Feedback;

import processing.core.PApplet;

import static processing.core.PConstants.CORNER;

public class NamePasswordNotFoundFeedBack extends Feedback {

    public NamePasswordNotFoundFeedBack(PApplet applet, int time) {
        super(applet, time);
    }

    public void show() {
        if (applet.millis() < actualTime + time) {
            applet.fill(applet.color(125, 100));
            applet.rect(position.x, position.y, size.x, size.y);
            applet.fill(0);
            applet.textSize(20);
            applet.textAlign(CORNER);
            applet.text("Name or Password", position.x+5, position.y+size.y/2);
            applet.text("not found!", position.x+5, position.y+size.y-5);
        }
    }

}
