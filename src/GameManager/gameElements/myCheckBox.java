package GameManager.gameElements;

import processing.core.PApplet;

public class myCheckBox implements GameElement {

    private PApplet applet;
    private myCheckBoxElement[] elements;

    public myCheckBox(PApplet applet){
        this.applet = applet;
        elements = new myCheckBoxElement[]{
            new myCheckBoxElement(applet, "Dublin", 300, 200),
                new myCheckBoxElement(applet, "Madrid", 300, 300),
                new myCheckBoxElement(applet, "Dundalk", 300, 400)
        };
    }

    public void show(){
        for(myCheckBoxElement element : elements){
            element.show();
        }
    }

    public void resetCheckbox(){
        for(myCheckBoxElement element : elements){
            element.setActive(false);
        }
    }

    public myCheckBoxElement[] getElements() {
        return elements;
    }
}
