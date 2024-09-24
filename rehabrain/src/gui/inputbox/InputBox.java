package gui.inputbox;

import gui.Plain;
import gui.GUIException;
import gui.KeyboardInputHandler;
import processing.core.PApplet;

/**
 * InputBox
 * This class is used to generate and manage input forms
 * @author Simone Cecire
 */
public class InputBox implements KeyboardInputHandler {
    private InputBoxModel ibm;
    private InputBoxView ibv;

    /**
     * @param x the x position of the box
     * @param y the y position of the box
     * @param width the width of the box
     * @param height the height of the box
     * @param fillColor the color of the box
     * @param borderColor the color of the border of the box
     * @param textColor the color of text inside the box
     * @param text the String that contains the text written by the user
     * @param selected if the box is disabled by default
     * @param disabled if the box is disabled by default
     * @param relatedButton the button that when pressed
     * @param p the reference of PApplet
     * @throws NullPointerException if the text or the reference of the related button or PApplet are null
     * @throws GUIException when there are errors on dimensions
     */
    public InputBox(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String text, boolean selected, boolean disabled, Plain relatedButton, PApplet p) throws NullPointerException, GUIException {
        if (text == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else if (width <= 0 || height <= 0) {
            throw new GUIException("Dimensions are not valid");
        }
        else if (relatedButton == null) {
            throw new NullPointerException("No related button");
        }
        else if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            ibm = new InputBoxModel(x, y, width, height, fillColor, borderColor, textColor, text, selected, disabled, relatedButton);
            ibv = new InputBoxView(p);
        }
    }

    /**
     * This method shows the box
     */
    @Override
    public void show() {
        ibv.show(ibm);
    }

    /**
     * This method checks if the box is pressed, then changes its state (from not selected to selected and vice versa)
     * @return
     */
    public void changeState() {
        ibv.changeState(ibm);
    }

    /**
     * This method handles the keyboard inputs
     */
    @Override
    public void handleInput() {
        ibv.handleInput(ibm);
    }

    /**
     * This method checks if the related button is pressed
     * @return true if the related button is pressed, otherwise false
     */
    public boolean isRelatedButtonPressed() {
        return ibv.isRelatedButtonPressed(ibm);
    }

    /**
     * This method returns the text
     * @return the String that contains the text
     */
    public String getText() {
        return ibm.getText();
    }

    /**
     * This method clears the text
     */
    public void clearText() {
        ibm.clearText();
    }
}
