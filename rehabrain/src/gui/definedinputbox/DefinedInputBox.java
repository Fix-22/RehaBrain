package gui.definedinputbox;

import gui.GUIException;
import gui.KeyboardInputHandler;
import processing.core.PApplet;

/**
 * DefinedInputBox
 * This class is used to generate and manage input forms that contains a predefined text to complete, displaying, at start, the first and the last letter, and adding more letters as the errors made by the user increases
 * @author Simone Cecire
 */
public class DefinedInputBox implements KeyboardInputHandler {
    private DefinedInputBoxModel dibm;
    private DefinedInputBoxView dibv;

    /**
     * Default constructor
     * @param x the x position of the box
     * @param y the y position of the box
     * @param width the width of the box
     * @param height the height of the box
     * @param fillColor the color of the box
     * @param borderColor the color of the border of the box
     * @param textColor the color of text inside the box
     * @param correctText the String that contains the correct text to complete
     * @param disabled if the box is disabled by default
     * @param p the reference of PApplet
     * @throws NullPointerException if the correct text or the reference of PApplet is null
     * @throws GUIException when there are errors on dimensions
     */
    public DefinedInputBox(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String correctText, boolean disabled, PApplet p) throws NullPointerException, GUIException {
        if (correctText == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else if (width <= 0 || height <= 0) {
            throw new GUIException("Dimensions are not valid");
        }
        else if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            dibm = new DefinedInputBoxModel(x, y, width, height, fillColor, borderColor, textColor, correctText, disabled);
            dibv = new DefinedInputBoxView(p);
        }
    }

    /**
     * This method shows the box
     */
    @Override
    public void show() {
        dibv.show(dibm);
    }

    /**
     * This method handles the keyboard inputs
     */
    @Override
    public void handleInput() {
        dibv.handleInput(dibm);
    }

    /**
     * This method returns the correct text
     * @return the String that contains the correct text
     */
    public String getCorrectText() {
        return dibm.getCorrectText();
    }

    /**
     * This method returns the current text
     * @return the String that contains the current text
     */
    public String getCurrentText() {
        return dibm.getCurrentText();
    }

    /**
     * This method clears the current text
     */
    public void clearCurrentText() {
        dibm.clearCurrentText();
    }

    /**
     * This method checks the correctness of the current text
     * @return true if the current text is equal to the correct text, otherwise false
     */
    public boolean isCurrentTextCorrect() {
        return dibm.isCurrentTextCorrect();
    }

    /**
     * This method ables or disables the box
     * @param disabled the value to disable or able the box
     */
    public void setDisabled(boolean disabled) {
        dibm.setDisabled(disabled);
    }

    /**
     * This method changes the correct text
     * @param correctText the String containing the new correct text
     * @throws NullPointerException if the correct text is null
     */
    public void setCorrectText(String correctText) throws NullPointerException {
        if (correctText == null) {
            throw new NullPointerException("Correct text is null");
        }

        dibm.setCorrectText(correctText);
    }

    /**
     * This method sets a new current text
     * @param currentText the String containing the new current text
     */
    public void setCurrentText(String currentText) {
        if (currentText != null) {
            dibm.setCurrentText(currentText);
        }
    }

    /**
     * This method checks if the current text is full
     * @return true if there are no more "_" characters in the current text, otherwise false
     */
    public boolean isCurrentTextFull() {
        return !dibm.getCurrentText().contains("_");
    }

    /**
     * This method adds all the occurrences of a random character, that is present in the correct text, in the current text
     */
    public void setRandomCorrectChar() { dibm.setRandomCorrectChar();}
}
