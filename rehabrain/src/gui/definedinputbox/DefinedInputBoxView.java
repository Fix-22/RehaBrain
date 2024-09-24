package gui.definedinputbox;

import processing.core.PApplet;

/**
 * DefinedInputBoxView
 * This is the model view of DefinedInputBox
 * @author Simone Cecire
 */
class DefinedInputBoxView {
    private PApplet p;

    /**
     * Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    DefinedInputBoxView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a DefinedInputBoxModel
     * @param dibm a model to show
     */
    void show(DefinedInputBoxModel dibm) {
        p.fill(dibm.getTextColor());
        p.textSize(p.height / 3.5f);
        p.text(dibm.getCurrentText(), dibm.getX() + dibm.getWidth() / 2, dibm.getY() + dibm.getHeight() / 2);
    }

    /**
     * This method handles the keyboard inputs
     * @param dibm a model to interact with
     */
    void handleInput(DefinedInputBoxModel dibm) {
        if (!dibm.isDisabled()) {
            if (p.key == p.BACKSPACE) { // if the key is backspace
                dibm.removeLastCharFromCurrentText(); // removes character
            }
            else if (Character.isLetter(p.key)){ // if the key is a letter
                dibm.addCharToCurrentText(Character.toUpperCase(p.key)); // adds character
            }
        }
    }
}
