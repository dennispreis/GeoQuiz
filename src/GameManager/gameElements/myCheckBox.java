package GameManager.gameElements;

import processing.core.PApplet;

public class myCheckBox implements GameElement {

    private PApplet applet;
    private myCheckBoxElement[] elements;

    public myCheckBox(PApplet applet, String s1, String s2, String s3, String s4) {
        this.applet = applet;
        elements = new myCheckBoxElement[]{
            new myCheckBoxElement(applet, s1, 300, 200),
                    new myCheckBoxElement(applet, s2, 300, 300),
                    new myCheckBoxElement(applet, s3, 300, 400),
                    new myCheckBoxElement(applet, s4, 300, 500)
        };
    }


    public void show() {
        for (myCheckBoxElement element : elements) {
            element.show();
        }
    }

    public void resetCheckbox() {
        for (myCheckBoxElement element : elements) {
            element.setActive(false);
        }
    }

    public myCheckBoxElement[] getElements() {
        return elements;
    }
}
