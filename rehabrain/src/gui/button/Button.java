package gui.button;

import gui.GUIException;
import gui.Plain;
import processing.core.PApplet;

/**
 * Button
 * This class is used to generate and manage plain buttons
 * @author Simone Cecire
 */
public class Button implements Plain {
    private ButtonModel bm;
    private ButtonView bv;

    /**
     * Default constructor
     * @param x the x position of the button
     * @param y the y position of the button
     * @param width the width of the button
     * @param height the height of the button
     * @param fillColor the color of the button
     * @param borderColor the color of the border of the button
     * @param textColor the color of text inside the button
     * @param text the String tha contains the text inside the button
     * @param disabled if the button is disabled by default
     * @param p the reference of PApplet
     * @throws NullPointerException if the text or the reference of PApplet is null
     * @throws GUIException when there are errors on dimensions
     */
    public Button(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String text, boolean disabled, PApplet p) throws NullPointerException, GUIException {
        if (text == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else if (width <= 0 || height <= 0) {
            throw new GUIException("Dimensions are not valid");
        }
        else if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            bm = new ButtonModel(x, y, width, height, fillColor, borderColor, textColor, text, disabled);
            bv = new ButtonView(p);
        }
    }

    /**
     * This method shows the button
     */
   @Override
    public void show() {
        bv.show(bm);
    }

    /**
     * This method checks if the button is pressed
     * @return true if the button is pressed, otherwise false
     */
    @Override
    public boolean isPressed() {
        return bv.isPressed(bm);
    }

    /**
     * This method checks if the button is disabled
     * @return true if the button is disabled, otherwise false
     */
    public boolean isDisabled() {
        return bm.isDisabled();
    }

    /**
     * This method ables or disables the button
     * @param disabled the value to disable or able the button
     */
    public void setDisabled(boolean disabled) {
        bm.setDisabled(disabled);
    }
}
