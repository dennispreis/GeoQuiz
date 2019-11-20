package GameManager.gameElements;

import processing.core.PApplet;

public class DragAndDrop implements GameElement{

    private PApplet applet;
    private DragAndDropElement[] solutions;
    private DragAndDropElement draggingElement;
    private boolean isDragging;
    private FixRect answerRect;

    public DragAndDrop(PApplet applet, DragAndDropElement[] elements, FixRect fixRect) {
        this.applet = applet;
        this.answerRect = fixRect;
        this.solutions = elements;
    }

    public DragAndDropElement getDraggingElement() {
        return this.draggingElement;
    }

    public boolean isDragging() {
        return isDragging;
    }

    public void setDragging(boolean dragging) {
        isDragging = dragging;
    }

    public void setDraggingElement(DragAndDropElement ele) {
        this.draggingElement = ele;
    }

    public DragAndDropElement[] getSolutions() {
        return solutions;
    }

    public FixRect getAnswerRect(){
        return answerRect;
    }

    public boolean isMouseInAnswerRect() {
        return (applet.mouseX < answerRect.getX() + answerRect.getXsize()
                && applet.mouseX > answerRect.getX()
                && applet.mouseY < answerRect.getY() + answerRect.getYsize()
                && applet.mouseY > answerRect.getY());
    }

    public void show() {



        for (DragAndDropElement element : solutions) {
            element.show();
        }

        answerRect.show();

    }
}
