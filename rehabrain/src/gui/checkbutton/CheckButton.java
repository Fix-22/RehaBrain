package gui.checkbutton;

import gui.GUIException;
import gui.Selectable;
import processing.core.PApplet;

/**
 * CheckButton
 * This class is used to generate and manage plain check buttons
 * @author Simone Cecire
 */
public class CheckButton implements Selectable {
    private CheckButtonModel cbm;
    private CheckButtonView cbv;

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
     * @param selected if the button is selected by default
     * @param disabled if the button is disabled by default
     * @param p the reference of PApplet
     * @throws NullPointerException if the text or the reference of PApplet is null
     * @throws GUIException when there are errors on dimensions
     */
    public CheckButton(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String text, boolean selected, boolean disabled, PApplet p) throws NullPointerException, GUIException {
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
            cbm = new CheckButtonModel(x, y, width, height, fillColor, borderColor, textColor, text, selected, disabled);
            cbv = new CheckButtonView(p);
        }
    }

    /**
     * This method shows the button
     */
    @Override
    public void show() {
        cbv.show(cbm);
    }

    /**
     * This method checks if the button is pressed
     * @return true if the button is pressed, otherwise false
     */
    @Override
    public boolean isPressed() {
        return cbv.isPressed(cbm);
    }

    @Override
    public boolean isSelected() {
        return cbm.isSelected();
    }

    public void setSelected(boolean selected) {
        cbm.setSelected(selected);
    }

    /**
     * This method checks if the button is selected
     * @return true if the button is selected, otherwise false
     */
    @Override
    public void changeState() {
        cbv.changeState(cbm);
    }

    /**
     * This method checks if the button is disabled
     * @return true if the button is disabled, otherwise false
     */
    public boolean isDisabled() {
        return cbm.isDisabled();
    }

    /**
     * This method ables or disables the button
     * @param disabled the value to disable or able the button
     */
    public void setDisabled(boolean disabled) {
        cbm.setDisabled(disabled);
    }

    /**
     * This method returns the text inside the button
     * @return the String containing the
     */
    public String getText() {
        return cbm.getText();
    }
}