package gui.checkbutton;

import gui.GUIException;

/**
 * CheckButtonModel
 * This is the model class of CheckButton
 * @author Simone Cecire
 */
class CheckButtonModel {
    private float x, y, width, height;
    private int fillColor, borderColor, textColor;
    private String text;
    private boolean selected, disabled;

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
     * @throws NullPointerException if the text is null
     * @throws GUIException when there are errors on dimensions
     */
    CheckButtonModel(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String text, boolean selected, boolean disabled) throws NullPointerException, GUIException {
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
