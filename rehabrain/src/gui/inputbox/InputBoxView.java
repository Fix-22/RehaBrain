package gui.inputbox;

import processing.core.PApplet;

/**
 * InputBoxView
 * This is the view class of InputBox
 * @author Simone Cecire
 */
class InputBoxView {
    private PApplet p;

    /**
     * Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    InputBoxView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a InputBoxModel
     * @param ibm a model to show
     */
    void show(InputBoxModel ibm) {
        p.rectMode(p.CORNER);
        p.fill(ibm.getFillColor());
        p.stroke(ibm.getBorderColor());
        p.strokeWeight(ibm.getHeight() / (ibm.isSelected() ? 10 : 15));
        p.rect(ibm.getX(), ibm.getY(), ibm.getWidth(), ibm.getHeight());
        ibm.getRelatedButton().show();
        p.fill(ibm.getTextColor());
        p.textSize(20);
        p.text(ibm.getText(), ibm.getX() + ibm.getWidth() / 2, ibm.getY() + ibm.getHeight() / 2);
    }

    /**
     * This method checks if the box is pressed, then changes its state (from not selected to selected and vice versa)
     * @param ibm a model to interact with
     */
    void changeState(InputBoxModel ibm) {
        if (p.mouseX >= ibm.getX() && p.mouseX <= ibm.getX() + ibm.getWidth() && p.mouseY >= ibm.getY() && p.mouseY <= ibm.getY() + ibm.getHeight() && p.mousePressed && !ibm.isDisabled()) {
            ibm.setSelected(!ibm.isSelected());
        }
    }

    /**
     * This method handles the keyboard inputs
     * @param ibm a model to interact with
     */
    void handleInput(InputBoxModel ibm) {
        if (ibm.isSelected()) {
            if (p.key == p.ENTER) { // if the key is ENTER then disables the box
                ibm.setSelected(false);
            }
            else if (p.key == p.BACKSPACE) { // if the key is backspace
                ibm.removeLastCharFromText(); // removes character
            }
            else if (Character.isLetter(p.key) && p.textWidth(ibm.getText()) < ibm.getWidth() - p.textAscent() * 4){ // if the key is a letter and the length of the current text is shorter than the box
                ibm.addCharToText(Character.toLowerCase(p.key)); // adds character
            }
        }
    }

    /**
     * This method checks if the related button is pressed
     * @param ibm a model to interact with
     * @return true if the related button is pressed, otherwise false
     */
    boolean isRelatedButtonPressed(InputBoxModel ibm) {
        return ibm.getRelatedButton().isPressed();
    }
}
