package GameManager.gameElements;

import processing.core.PApplet;

public class RadioButton implements GameElement
{

    private PApplet applet;
    private RadioButtonElement[] elements;
    private RadioButtonElement activeElement;

    public RadioButton(PApplet applet)
    {
        this.applet = applet;
        elements = new RadioButtonElement[]
        {
            new RadioButtonElement(applet, "TRUE", 300, 300),
            new RadioButtonElement(applet, "FALSE", 300, 400)
        };
        activeElement = null;
    }
    public void selectElement(RadioButtonElement ele) {
        if (this.activeElement == null) {
            if (ele != null) {
                this.activeElement = ele;
                this.activeElement.setActive(true);
            }
        } else {
            this.activeElement.setActive(false);
            this.activeElement = ele;
            if (ele != null)
            {
                this.activeElement.setActive(true);
            }
        }
    }

    public RadioButtonElement[] getElements()
    {
        return elements;
    }

    public void show()
    {
        for (RadioButtonElement ele : elements)
        {
            ele.show();
        }
    }

}
