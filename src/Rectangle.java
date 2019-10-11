import processing.core.PApplet;
import processing.core.PVector;

import java.awt.*;

public class Rectangle implements Dragable{

    private PApplet p;
    private PVector position;
    private PVector size;
    private Color col;
    boolean isDragging;

    Rectangle(PApplet p){
        this.p = p;
        position = new PVector(500, 200);
        size = new PVector(50, 50);
        col = new Color(400);
    }

    public void show(){
        p.stroke(0);
        p.strokeWeight(this.isMouseWithIn() || this.isDragging ? 8 : 3);
        p.fill(col.getRGB());
        p.rect(this.position.x, this.position.y, this.size.x, this.size.y);
    }

    void setDrag(boolean bool){
        this.isDragging = bool;
    }

    void updatePos(PVector pos){
        this.position = pos;
    }

    public boolean isMouseWithIn(){
        return (p.mouseX > this.position.x && p.mouseX < this.position.x + this.size.x
                && p.mouseY > this.position.y && p.mouseY < this.position.y + this.size.y);
    }

}
