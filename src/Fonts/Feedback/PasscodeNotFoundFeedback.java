package Fonts.Feedback;

import processing.core.PApplet;

import static processing.core.PConstants.CORNER;

public class PasscodeNotFoundFeedback extends Feedback {

    public PasscodeNotFoundFeedback(PApplet applet, int time) {
        super(applet, time);
    }

    public void show() {
        if (applet.millis() < actualTime + time) {
            applet.fill(applet.color(125, 100));
            applet.rect(position.x, position.y, size.x, size.y);
            applet.fill(0);
            applet.textSize(20);
            applet.textAlign(CORNER);
            applet.text("Passcode", position.x-80, position.y+2);
            applet.text("not found!",  position.x-80, position.y+20);
        }
    }

}