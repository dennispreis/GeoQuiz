package GameManager.gameElements;

import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

import static processing.core.PConstants.CENTER;

public class DragAndDropElement implements Dragable {

    private PApplet p;
    private PVector position;
    private PVector size;
    private Color col;
    private boolean isDragging;
    private PVector draggingPosition;
    private String text;

    public DragAndDropElement(PApplet p) {
        this.p = p;
        position = new PVector(0, 0);
        draggingPosition = position;
        size = new PVector(50, 50);
        col = new Color(400);
        text = "";
    }

    public void show() {
        p.stroke(0);
        p.strokeWeight(this.isMouseWithIn() || this.isDragging ? 8 : 3);
        p.fill(col.getRGB());
        p.rect(this.position.x, this.position.y, this.size.x, this.size.y);
        p.textSize(20);
        p.textAlign(CENTER, CENTER);
        p.fill(255);
        p.text(text, position.x+size.x/2, position.y+size.y/2);
    }

    public String getText() {
        return text;
    }

    public DragAndDropElement setText(String text) {
        this.text = text;
        return this;
    }

    public void setDrag(boolean bool) {
        this.isDragging = bool;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public DragAndDropElement updatePos(float x, float y) {
        this.draggingPosition.x = x;
        this.draggingPosition.y = y;
        return this;
    }

    public DragAndDropElement setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        return this;
    }

    public PVector getPosition() {
        return this.position;
    }

    public boolean isMouseWithIn() {
        return (p.mouseX > this.position.x && p.mouseX < this.position.x + this.size.x
                && p.mouseY > this.position.y && p.mouseY < this.position.y + this.size.y);
    }


}
