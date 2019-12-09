package GameManager;

import processing.core.PApplet;

public class TypeChooser {

    private PApplet applet;
    private ChooseAble[] elements;
    private ChooseAble activeElement;

    public TypeChooser(PApplet applet) {
        this.applet = applet;
    }

    public void show() {
        for (ChooseAble ca : elements) {
            ca.show();
        }
    }

    public TypeChooser setElements(ChooseAble[] arr) {
        this.elements = arr;
        return this;
    }

    public ChooseAble[] getElements() {
        return elements;
    }

    public ChooseAble getActiveElement() {
        return this.activeElement;
    }

    public void updateActiveElement(ChooseAble ca) {
        if (this.activeElement == null) {
            this.activeElement = ca;
            this.activeElement.setActive(true);
        }
        else {
            this.activeElement.setActive(false);
            this.activeElement = ca;
            this.activeElement.setActive(true);
        }
    }
}
