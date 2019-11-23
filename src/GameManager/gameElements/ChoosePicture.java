package GameManager.gameElements;
import processing.core.PApplet;
import processing.core.PImage;

import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;

public class ChoosePicture implements GameElement {

    private PApplet applet;
    private myImageButton button_left, button_right;

    public ChoosePicture(PApplet applet, PImage image, PImage image2){
        this.applet = applet;
        button_left = new myImageButton(applet, 300, 350, image);
        button_right = new myImageButton(applet, 600, 350, image2);
    }

    public myImageButton getButton_left(){
        return button_left;
    }

    public myImageButton getButton_right(){
        return button_right;
    }



    public void show(){
        button_left.show();
        button_right.show();
    }

    public class myImageButton{

        PApplet applet;
        float x, y;
        float xS, yS;
        PImage imageName;
        boolean choosen;

        myImageButton(PApplet applet, float x, float y, PImage image){
            this.applet = applet;
            this.x = x;
            this.y = y;
            this.imageName = image;
            this.xS = image.width;
            this.yS = image.height;
            this.choosen = false;
        }

        public void show(){
            applet.imageMode(CENTER);
            applet.image(imageName, x, y);
            if(this.choosen){
                applet.rectMode(CENTER);
                applet.noFill();
                applet.stroke(0);
                applet.strokeWeight(4);
                applet.rect(x, y, xS, yS);
            }
        }

        public boolean isChoosen(){
            return this.choosen;
        }

        public void setChoosen(boolean bool){
            this.choosen = bool;
        }

        public boolean isMouseWithIn(){
            return (applet.mouseX > x-xS/2 && applet.mouseX < x + xS/2 && applet.mouseY > y-yS/2 && applet.mouseY < y + yS/2);
        }
    }

}

