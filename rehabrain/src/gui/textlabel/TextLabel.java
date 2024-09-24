package gui.textlabel;

import gui.Showable;
import processing.core.PApplet;

/**
 * TextLabel
 * This class is used to display single line text
 * @author Simone Cecire
 */
public class TextLabel implements Showable {
    private TextLabelModel tlm;
    private TextLabelView tlv;

    /**
     * @param x the x position of the label
     * @param y the y position of the label
     * @param textColor the color of text inside the label
     * @param textSize the color of text inside the label
     * @param textAlign the TextAlign enum value
     * @param text the text inside the label
     * @param p the reference of PApplet
     * @throws NullPointerException if the text or the reference of TextAlign or PApplet are null
     */
    public TextLabel(float x, float y, int textColor, int textSize, TextAlign textAlign, String text, PApplet p) throws NullPointerException {
        if (textAlign == null) {
            throw new NullPointerException("Parameter text align is null");
        }
        else if (text == null) {
            throw new NullPointerException("Parameter text is null");
        }
        else if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            tlm = new TextLabelModel(x, y, textColor, textSize, textAlign, text);
            tlv = new TextLabelView(p);
        }
    }

    /**
     * This method shows the container
     */
    @Override
    public void show() {
        tlv.show(tlm);
    }

    public float getX() {
        return tlm.getX();
    }

    public void setX(float x) {
        tlm.setX(x);
    }

    public float getY() {
        return tlm.getY();
    }

    public void setY(float y) {
        tlm.setY(y);
    }

    public void setTextColor(int textColor) {
        tlm.setTextColor(textColor);
    }

    public int getTextSize() {
        return tlm.getTextSize();
    }

    public void setTextSize(int textSize) {
        tlm.setTextSize(textSize);
    }

    public TextAlign getTextAlign() {
        return tlm.getTextAlign();
    }

    public void setTextAlign(TextAlign textAlign) {
        if (textAlign != null) {
            tlm.setTextAlign(textAlign);
        }
    }

    public String getText() {
        return tlm.getText();
    }

    public void setText(String text) {
        if (text != null) {
            tlm.setText(text);
        }
    }
}
