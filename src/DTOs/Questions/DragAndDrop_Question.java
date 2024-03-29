package DTOs.Questions;

import DTOs.Question;
import GameManager.gameElements.DragAndDrop;
import GameManager.gameElements.DragAndDropElement;
import GameManager.gameElements.FixRect;
import Main.GeoQuiz;
import processing.core.PApplet;

import static processing.core.PConstants.CENTER;

public class DragAndDrop_Question extends Question
{

    private DragAndDrop dragAndDrop;

    public DragAndDrop_Question(PApplet applet, int id, String type, String region, String question_text, String correct_answer,String a1,String a2,String a3,String a4)
    {
        super(applet, id, type, region, question_text, correct_answer);

        dragAndDrop = new DragAndDrop(applet,
                new DragAndDropElement[]
                {
                    new DragAndDropElement(applet).setPosition(250, 300).setText(a1),
                    new DragAndDropElement(applet).setPosition(400, 300).setText(a2),
                    new DragAndDropElement(applet).setPosition(550, 300).setText(a3),
                    new DragAndDropElement(applet).setPosition(550, 400).setText(a4)
                },
                new FixRect(applet, 400, 150, 100, 50));
    }

    public void reset(){
        dragAndDrop.getAnswerRect().setDragAndDropElement(null);
        dragAndDrop.getSolutions()[0].setPosition(250, 300);
        dragAndDrop.getSolutions()[1].setPosition(400, 300);
        dragAndDrop.getSolutions()[2].setPosition(550, 300);
        dragAndDrop.setDraggingElement(null);
        dragAndDrop.setDragging(false);
        dragAndDrop.getAnswerRect().setOccupied(false);
    }

    public void show() {
        dragAndDrop.show();
    }

    public DragAndDrop getDragAndDrop()
    {
        return dragAndDrop;
    }

}
