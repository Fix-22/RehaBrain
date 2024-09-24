package gui.definedinputbox;

import gui.GUIException;
import java.util.Random;

/**
 * DefinedInputBoxModel
 * This is the model class of DefinedInputBox
 * @author Simone Cecire
 */
class DefinedInputBoxModel {
    private float x, y, width, height;
    private int fillColor, borderColor, textColor;
    private String correctText, currentText;
    private char[] generatedLetters; // this is the array representation of the characters added by the program present in the current text
    private boolean disabled;
    private int currentIdx;
    private Random random;

    /**
     * Default constructor
     * @param x the x position of the box
     * @param y the y position of the box
     * @param width the width of the box
     * @param height the height of the box
     * @param fillColor the color of the box
     * @param borderColor the color of the border of the box
     * @param textColor the color of text inside the box
     * @param correctText the String that contains the correct text to complete
     * @param disabled if the box is disabled by default
     * @throws NullPointerException if the correct text is null
     * @throws GUIException when there are errors on dimensions
     */
    DefinedInputBoxModel(float x, float y, float width, float height, int fillColor, int borderColor, int textColor, String correctText, boolean disabled) throws NullPointerException, GUIException {
        if (correctText == null) {
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
            this.correctText = correctText;
            this.disabled = disabled;
            setCorrectText(correctText);
            random = new Random();
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

    String getCorrectText() {
        return correctText;
    }
    String getCurrentText() {
        return currentText;
    }

    void setCurrentText(String currentText) {
        if (currentText != null) {
            this.currentText = currentText;
        }
    }

    /**
     * This method checks if the letter in input was added by the program after an error
     * @param letter a character to check
     * @return true if it was generated, otherwise false
     */
    private boolean isLetterGenerated(char letter) {
        for (int i = 0; i < generatedLetters.length; i++) {
            if (generatedLetters[i] == letter) return true;
        }
        return false;
    }

    /**
     * This method adds a character to the current text
     * @param c the character to add
     */
    void addCharToCurrentText(char c) {
        if (currentIdx < correctText.length() - 1) {
            if (currentText.charAt(currentIdx) != '_') { // checks for previous correct chars adds
                currentIdx = currentText.indexOf('_');
            }
            currentText = currentText.substring(0, currentIdx) + c + currentText.substring(currentIdx + 1); // adds the char
            currentIdx++;
        }
    }

    /**
     * This method removes the last character, added by the user, not the program, from the current text
     */
    void removeLastCharFromCurrentText() {
        if (currentIdx > 1) {
            if (isLetterGenerated(currentText.charAt(currentIdx - 1))) { // if the letter is generated
                for (int i = currentIdx - 1; i > 0; i--) { // then search the first occurrence of a blank char ('\u0000')
                    if (generatedLetters[i] == '\u0000') {
                        currentIdx = i; // and set the position of that occurrence as the current index
                        break;
                    }
                }
            }
            else currentIdx--;

            currentText = currentText.substring(0, currentIdx) + "_" + currentText.substring(currentIdx + 1); // removes the char
        }
    }

    /**
     * This method clears the current text
     */
    void clearCurrentText() {
        setCorrectText(correctText);
    }

    boolean isDisabled() {
        return disabled;
    }

    void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    boolean isCurrentTextCorrect() {
        return currentText.equals(correctText);
    }

    /**
     * This method sets the correct text
     * @param correctText the String that contains the new correct text
     * @throws NullPointerException if the correct text is null
     */
    void setCorrectText(String correctText) throws NullPointerException {
        if (correctText == null) {
            throw new NullPointerException("Correct text is null");
        }

        this.correctText = correctText;
        currentText = "";
        currentIdx = 1;

        if (correctText.length() > 0) {
            // set the first and last generated letters
            generatedLetters = new char[this.correctText.length()];
            generatedLetters[0] = this.correctText.charAt(0);
            generatedLetters[generatedLetters.length - 1] = correctText.charAt(correctText.length() - 1);

            for (int i = 0; i < this.correctText.length(); i++) {
                if (i == 0 || i == this.correctText.length() - 1) {
                    this.currentText += this.correctText.charAt(i);
                }
                else {
                    this.currentText += this.correctText.length() > i ? "_" : this.correctText.charAt(i);
                }
            }
        }
    }

    /**
     * This method adds all the occurrences of a random character, that is present in the correct text, in the current text
     */
    void setRandomCorrectChar() {
        char correctChar = correctText.charAt(random.nextInt(1, correctText.length() - 2)); // random correct char

        for (int i = 1; i < correctText.length() - 1; i++) {
            if (correctText.charAt(i) == correctChar) { // if the correct char equals the generated correct char
                currentText = currentText.substring(0, i) + correctChar + currentText.substring(i + 1); // adds it in the current text
                generatedLetters[i] = correctChar; // adds it to the generated letters
            }
            else if (correctText.charAt(i) != generatedLetters[i]) { // if the correct char doesn't equal to a generated letter
                currentText = currentText.substring(0, i) + "_" + currentText.substring(i + 1); // adds a "_"
            }
        }
        currentIdx = currentText.indexOf('_');
    }
}
