package gui.imagecheckbutton;

import gui.GUIException;
import processing.core.PImage;

/**
 * ImageCheckButtonModel
 * This is the model class of ImageCheckButton
 * @author Simone Cecire
 */
class ImageCheckButtonModel {
    private float x, y, resizeFactor;
    private int borderColor;
    private PImage image;
    private boolean selected, disabled;

    /**
     * Default constructor
     * @param x the x position of the button
     * @param y the y position of the button
     * @param resizeFactor the resize factor of the image
     * @param borderColor the color of the border of the button
     * @param image the PImage reference
     * @param selected if the button is selected by default
     * @param disabled if the button is disabled by default
     * @throws NullPointerException if the reference of the image is null
     * @throws GUIException when resize
     */
    ImageCheckButtonModel(float x, float y, float resizeFactor, int borderColor, PImage image, boolean selected, boolean disabled) throws NullPointerException, GUIException {
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
            this.image.resize((int) (image.width * this.resizeFactor), (int) (image.height * this.resizeFactor));
            this.selected = selected;
            this.disabled = disabled;
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

    boolean isSelected() {
        return selected;
    }

    void setSelected(boolean selected) {
        this.selected = selected;
    }

    boolean isDisabled() {
        return disabled;
    }

    void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
