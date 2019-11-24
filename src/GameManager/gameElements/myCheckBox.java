package GameManager.gameElements;

import processing.core.PApplet;

public class myCheckBox implements GameElement
{

    private PApplet applet;
    private myCheckBoxElement[] elements;

    public myCheckBox(PApplet applet)
    {
        this.applet = applet;
        elements = new myCheckBoxElement[]
        {
            new myCheckBoxElement(applet, "Dublin", 300, 200),
            new myCheckBoxElement(applet, "Madrid", 300, 300),
            new myCheckBoxElement(applet, "Dundalk", 300, 400)
        };
    }

    public myCheckBox(PApplet applet, String a1, String a2, String a3, String a4)
    {
        this.applet = applet;
        elements = new myCheckBoxElement[]
        {
            new myCheckBoxElement(applet, a1, 300, 200),
            new myCheckBoxElement(applet, a2, 300, 300),
            new myCheckBoxElement(applet, a3, 300, 400),
            new myCheckBoxElement(applet, a4, 300, 500)
    }
;
}

    public void show()
    {
        for (myCheckBoxElement element : elements)
        {
            element.show();
        }
    }

    public void resetCheckbox()
    {
        for (myCheckBoxElement element : elements)
        {
            element.setActive(false);
        }
    }

    public myCheckBoxElement[] getElements()
    {
        return elements;
    }
}
