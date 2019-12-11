package GameManager.gameElements;

import processing.core.PApplet;

import java.awt.*;

import static controlP5.ControlP5Constants.LEFT;
import static processing.core.PConstants.CENTER;

public class myCheckBoxElement {


    private PApplet applet;
    private String text;
    private float x, y;
    private float xS, yS;
    private boolean isActive;
    private Color col;

    public myCheckBoxElement(PApplet applet, String text, float x, float y) {
        this.applet = applet;
        this.text = text;
        this.x = x;
        this.y = y;
        xS = 50;
        yS = 50;
        this.col = new Color(400);
    }

    public void setActive(boolean bool) {
        this.isActive = bool;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public String getText(){
        return this.text;
    }

    public void show() {
        applet.fill(col.getRGB());
        applet.stroke(0);
        applet.strokeWeight(1);
        applet.rectMode(CENTER);
        applet.rect(x, y, xS, yS);
        applet.textSize(30);
        applet.textAlign(LEFT, CENTER);
        applet.text(this.text, x + xS, y);
        if (this.isActive) {
            applet.textAlign(CENTER, CENTER);
            applet.fill(200);
            applet.text("X", x, y);
        }
    }

    public boolean isMouseWithIn() {
        return (applet.mouseX > x - xS / 2 && applet.mouseX < x + xS / 2 && applet.mouseY > y - yS / 2 && applet.mouseY < y + yS / 2);
    }
}
