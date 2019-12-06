package GameManager;

import Images.ImageName;
import Main.GeoQuiz;
import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class ChooseAble {

    private PApplet applet;
    private float xPos, yPos, xSize, ySize;
    private String text;
    private PImage image;
    private boolean isActive;
    private GameProperty gameProperty;

    public ChooseAble(PApplet applet, float x, float y, ImageName name, GameProperty prop) {
        this.applet = applet;
        this.xPos = x;
        this.yPos = y;
        this.xSize = 50;
        this.ySize = 50;
        this.image = GeoQuiz.getImage(name);
        this.image.resize((int) xSize, (int) ySize);
        this.text = "";
        this.isActive = false;
        this.gameProperty = prop;
    }

    public void show(){
        if(this.isActive){
            applet.fill(200, 100);
            applet.stroke(2);
            applet.strokeWeight(4);
            applet.rectMode(CORNER);
            applet.rect(xPos-xSize/5, yPos-ySize/5, xSize+xSize/2.5f, ySize+ySize/2.5f);
        }
        applet.imageMode(CORNER);
        applet.image(image, xPos, yPos);
        applet.textAlign(CENTER, CENTER);
        applet.fill(255);
        applet.text(this.text, xPos + xSize/2, yPos + ySize*1.5f);

    }

    public GameProperty getGameProperty(){
        return this.gameProperty;
    }

    public boolean isMouseWithIn(){
        return (applet.mouseX > xPos && applet.mouseX < xPos+ySize && applet.mouseY > yPos && applet.mouseY < yPos+ySize);
    }

    public ChooseAble setText(String t) {
        this.text = t;
        return this;
    }

    public void setActive(boolean bool) {
        this.isActive = bool;
    }

    public boolean isActive() {
        return this.isActive;
    }

}
