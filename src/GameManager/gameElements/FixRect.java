package GameManager.gameElements;

import processing.core.PApplet;

public class FixRect {

    private PApplet applet;
    private float x, y;
    private float xS, yS;

    private boolean isOccupied;

    public FixRect(PApplet applet, float x, float y, float xS, float yS) {
        this.applet = applet;
        this.x = x;
        this.y = y;
        this.xS = xS;
        this.yS = yS;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    float getXsize() {
        return xS;
    }

    float getYsize() {
        return yS;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    void show(){
        applet.fill(200, 100);
        applet.stroke(0);
        applet.strokeWeight(3);
        applet.rect(x, y, xS, yS);
    }
}
