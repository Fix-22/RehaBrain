package gui.multilinetextcontainer;

import java.util.ArrayList;

/**
 * MultilineTextContainerModel
 * This is the model class of MultilineTextContainer
 */
class MultilineTextContainerModel {
    private float x, y;
    private int fillColor, borderColor, textColor;
    private ArrayList<String> words;

    /**
     * @param x the x position of the container
     * @param y the y position of the container
     * @param fillColor the color of the container
     * @param borderColor the color of the border of the container
     * @param textColor the color of text inside the container
     */
    MultilineTextContainerModel(float x, float y, int fillColor, int borderColor, int textColor) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.textColor = textColor;
        words = new ArrayList<>(0);
    }

    float getX() {
        return x;
    }

    float getY() {
        return y;
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

    ArrayList<String> getWords() {
        return (ArrayList<String>) words.clone();
    }

    void addWord(String word) {
        if (word != null) {
            words.add(word);
        }
    }

    void clearWords() {
        words.clear();
    }
}
