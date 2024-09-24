package gui.textlabel;

import processing.core.PApplet;

/**
 * TextLabelView
 * This is the view class of TextLabel
 * @author Simone Cecire
 */
class TextLabelView {
    private PApplet p;

    /**
     * Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    TextLabelView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a TextLabelModel
     * @param tlm a model to show
     */
    void show(TextLabelModel tlm) {
        p.fill(tlm.getTextColor());
        p.textSize(tlm.getTextSize());
        p.textAlign(tlm.getTextAlign().toString().equals("LEFT") ? p.LEFT : (tlm.getTextAlign().toString().equals("CENTER") ? p.CENTER : p.RIGHT));
        p.text(tlm.getText(), tlm.getX(), tlm.getY());
    }
}
