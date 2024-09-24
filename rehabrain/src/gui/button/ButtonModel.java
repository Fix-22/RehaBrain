package gui.button;

import gui.GUIException;

/**
 * ButtonModel
 * This is the model class of Button
 * @author Simone Cecire
 */
class ButtonModel {
    private float x, y, width, height;
    private int fillColor, borderColor, textColor;
    private String text;
    private boolean disabled;

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
     * @throws NullPointerException if the text is null
     * @throws GUIException when there are errors on dimensions
     */
    ButtonModel(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String text, boolean disabled) throws NullPointerException, GUIException {
        if (text == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else if (width <= 0 || height <= 0) {
            throw new GUIException("Dimensions are not valid");
        }
        else {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.fillColor = fillColor;
            this.borderColor = borderColor;
            this.textColor = textColor;
            this.text = text;
            this.disabled = disabled;
        }
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
    }

    float getWidth() {
        return width;
    }

    float getHeight() {
        return height;
    }

    int getFillColor() {
        return fillColor;
    }

    int getBorderColor() {
        return borderColor;
    }

    int getTextColor() {
        return textColor;
    }

    String getText() {
        return text;
    }

    boolean isDisabled() {
        return disabled;
    }

    void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}