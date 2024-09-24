package gui.imagebutton;

import gui.GUIException;
import processing.core.PImage;

/**
 * ImageButtonModel
 * This is the model class of ImageButton
 * @author Simone Cecire
 */
class ImageButtonModel {
    private float x, y, resizeFactor;
    private int borderColor;
    private PImage image;
    private boolean disabled;

    /**
     * Default constructor
     * @param x the x position of the button
     * @param y the y position of the button
     * @param resizeFactor the resize factor of the image
     * @param borderColor the color of the border of the button
     * @param image the PImage reference
     * @param disabled if the button is disabled by default
     * @throws NullPointerException if the reference of the image is null
     * @throws GUIException when resize
     */
    ImageButtonModel(float x, float y, float resizeFactor, int borderColor, PImage image, boolean disabled) throws NullPointerException, GUIException {
        if (image == null) {
            throw new NullPointerException("Parameter image is null");
        }
        else if (resizeFactor <= 0) {
            throw new GUIException("Resize factor is not valid");
        }
        else {
            this.x = x;
            this.y = y;
            this.resizeFactor = resizeFactor;
            this.borderColor = borderColor;
            this.image = image;
            this.disabled = disabled;
            this.image.resize((int) (image.width * this.resizeFactor), (int) (image.height * this.resizeFactor));
        }
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    float getResizeFactor() {
        return resizeFactor;
    }

    int getBorderColor() {
        return borderColor;
    }

    PImage getImage() {
        return image;
    }

    boolean isDisabled() {
        return disabled;
    }

    void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
