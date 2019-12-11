package Teacher.TestManager;

import Main.GeoQuiz;
import processing.core.PApplet;

import static processing.core.PConstants.CORNER;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.TOP;

public class MyQuestionCheckBox {

    private PApplet applet;
    private float xPos, yPos, xSize, ySize;
    private boolean isActive, isShowing;

    public MyQuestionCheckBox(PApplet applet, float x, float y) {
        this.applet = applet;
        this.xPos = x;
        this.yPos = y;
        this.xSize = 25;
        this.ySize = 25;
        this.isActive = false;
        this.isShowing = true;
    }

    public void show() {
        if (isShowing) {
            applet.stroke(0);
            applet.strokeWeight(2);
            applet.fill(applet.color(30, 30, 150));
            applet.rectMode(CORNER);
            applet.rect(xPos, yPos, xSize, ySize);
            applet.textSize(20);
            applet.fill(255);
            applet.textAlign(LEFT, TOP);
            applet.text(isActive ? "X" : "", xPos + 7, yPos + 4);
        }
    }

    public MyQuestionCheckBox setPosition(float x, float y) {
        if (isShowing) {
            this.xPos = x;
            this.yPos = y;
        }
        return this;
    }

    public void setIsShowing(boolean bool) {
        this.isShowing = bool;
    }

    public boolean isShowing() {
        return this.isShowing;
    }

    public void setActive(boolean bool) {
        if (isShowing) {
            this.isActive = bool;
        }
    }

    public boolean isActive() {
        return this.isActive;
    }

    public boolean isMouseWithIn() {
        return (applet.mouseX > xPos && applet.mouseX < xPos + xSize && applet.mouseY > yPos && applet.mouseY < yPos + ySize);
    }
}
