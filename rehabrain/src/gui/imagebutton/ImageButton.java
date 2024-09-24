package gui.imagebutton;

import gui.GUIException;
import gui.Plain;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * ImageButton
 * This class is used to generate and manage image buttons
 * @author Simone Cecire
 */
public class ImageButton implements Plain {
    private ImageButtonModel ibm;
    private ImageButtonView ibv;

    /**
     * Default constructor
     * @param x the x position of the button
     * @param y the y position of the button
     * @param resizeFactor the resize factor of the image
     * @param borderColor the color of the border of the button
     * @param image the PImage reference
     * @param disabled if the button is disabled by default
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of the image or the reference of PApplet is null
     * @throws GUIException when resize
     */
    public ImageButton(float x, float y, float resizeFactor, int borderColor, PImage image, boolean disabled, PApplet p) throws NullPointerException, GUIException {
        if (image == null) {
            throw new NullPointerException("Parameter image is null");
        }
        else if (resizeFactor <= 0) {
            throw new GUIException("Resize factor is not valid");
        }
        else if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            ibm = new ImageButtonModel(x, y, resizeFactor, borderColor, image, disabled);
            ibv = new ImageButtonView(p);
        }
    }

    /**
     * This method shows the button
     */
    @Override
    public void show() {
        ibv.show(ibm);
    }

    /**
     * This method checks if the button is pressed
     * @return true if the button is pressed, otherwise false
     */
    @Override
    public boolean isPressed() {
        return ibv.isPressed(ibm);
    }

    public float getX() {
        return ibm.getX();
    }

    public float getY() {
        return ibm.getY();
    }

    /**
     * This method checks if the button is disabled
     * @return true if the button is disabled, otherwise false
     */
    public boolean isDisabled() {
        return ibm.isDisabled();
    }

    /**
     * This method ables or disables the button
     * @param disabled the value to disable or able the button
     */
    public void setDisabled(boolean disabled) {
        ibm.setDisabled(disabled);
    }
}
