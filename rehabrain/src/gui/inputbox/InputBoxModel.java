package gui.inputbox;

import gui.Plain;
import gui.GUIException;

/**
 * InputBoxModel
 * This is the model class of InputBox
 * @author Simone Cecire
 */
class InputBoxModel {
    private float x, y, width, height;
    private int fillColor, borderColor, textColor;
    private String text;
    private boolean selected, disabled;
    private Plain relatedButton;

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
     * @throws NullPointerException if the text or the reference of the related button are null
     * @throws GUIException when there are errors on dimensions
     */
    InputBoxModel(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String text, boolean selected, boolean disabled, Plain relatedButton) throws NullPointerException, GUIException {
        if (text == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else if (width <= 0 || height <= 0) {
            throw new GUIException("Dimensions are not valid");
        }
        else if (relatedButton == null) {
            throw new NullPointerException("No related button");
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
            this.relatedButton = relatedButton;
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

    /**
     * This method adds a character to the text
     * @param c the character to add
     */
    void addCharToText(char c) {
        text += c;
    }

    /**
     * This method removes the last character from the text
     */
    void removeLastCharFromText() {
        if (text.length() > 0) {
            text = text.substring(0, text.length() - 1);
        }
    }

    /**
     * This method clears the text
     */
    void clearText() {
        text = "";
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

    Plain getRelatedButton() {
        return relatedButton;
    }
}
