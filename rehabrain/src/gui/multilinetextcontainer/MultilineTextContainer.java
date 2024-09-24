package gui.multilinetextcontainer;

import gui.Showable;
import processing.core.PApplet;
import java.util.ArrayList;

/**
 * MultilineTextContainer
 * This class is used to display different text in multiple lines
 * @author Simone Cecire
 */
public class MultilineTextContainer implements Showable {
    private MultilineTextContainerModel mtcm;
    private MultilineTextContainerView mtcv;

    /**
     * @param x the x position of the container
     * @param y the y position of the container
     * @param fillColor the color of the container
     * @param borderColor the color of the border of the container
     * @param textColor the color of text inside the container
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    public MultilineTextContainer(float x, float y, int fillColor, int borderColor, int textColor, PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            mtcm = new MultilineTextContainerModel(x, y, fillColor, borderColor, textColor);
            mtcv = new MultilineTextContainerView(p);
        }
    }

    /**
     * This method shows the container
     */
    @Override
    public void show() {
        mtcv.show(mtcm);
    }

    /**
     * This method adds a line with a new word
     * @param word the String containing the new line
     */
    public void addWord(String word) {
        if (word != null) {
            mtcm.addWord(word);
        }
    }

    /**
     * This method returns a copy of all the lines in the container
     * @return an ArrayList with every line
     */
    public ArrayList<String> getWords() {
        return mtcm.getWords();
    }

    /**
     * This method clears all the lines and empties the ArrayList
     */
    public void clear() {
        mtcm.clearWords();
    }
}
