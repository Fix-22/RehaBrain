package gui.imagecheckbutton;

import gui.GUIException;
import gui.Selectable;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * ImageCheckButton
 * This class is used to generate and manage image check buttons
 * @author Simone Cecire
 */
public class ImageCheckButton implements Selectable {
    private ImageCheckButtonModel icbm;
    private ImageCheckButtonView icbv;

    /**
     * Default constructor
     * @param x the x position of the button
     * @param y the y position of the button
     * @param resizeFactor the resize factor of the image
     * @param borderColor the color of the border of the button
     * @param image the PImage reference
     * @param selected if the button is selected by default
     * @param disabled if the button is disabled by default
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of the image or the reference of PApplet is null
     * @throws GUIException when resize
     */
    public ImageCheckButton(float x, float y, float resizeFactor, int borderColor, PImage image, boolean selected, boolean disabled, PApplet p) throws NullPointerException, GUIException {
        if (image == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else if (resizeFactor <= 0) {
            throw new GUIException("Resize factor is not valid");
        }
        else if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            icbm = new ImageCheckButtonModel(x, y, resizeFactor, borderColor, image, selected, disabled);
            icbv = new ImageCheckButtonView(p);
        }
    }

    /**
     * This method shows the button
     */
    @Override
    public void show() {
        icbv.show(icbm);
    }

    /**
     * This method checks if the button is pressed
     * @return true if the button is pressed, otherwise false
     */
    @Override
    public boolean isPressed() {
        return icbv.isPressed(icbm);
    }

    @Override
    public boolean isSelected() {
        return icbm.isSelected();
    }

    public void setSelected(boolean selected) {
        icbm.setSelected(selected);
    }

    /**
     * This method checks if the button is pressed, then changes its state (from not selected to selected and vice versa)
     * @return true if the button is selected, otherwise false
     */
    @Override
    public void changeState() {
        icbv.changeState(icbm);
    }

    /**
     * This method checks if the button is disabled
     * @return true if the button is disabled, otherwise false
     */
    public boolean isDisabled() {
        return icbm.isDisabled();
    }

    /**
     * This method ables or disables the button
     * @param disabled the value to disable or able the button
     */
    public void setDisabled(boolean disabled) {
        icbm.setDisabled(disabled);
    }

    public float getX() {
        return icbm.getX();
    }

    public float getY() {
        return icbm.getY();
    }
}
