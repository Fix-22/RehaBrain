package gui.multilinetextcontainer;

import processing.core.PApplet;

/**
 * MultilineTextContainerView
 * This is the view class of MultilineTextContainer
 * @author Simone Cecire
 */
class MultilineTextContainerView {
    private PApplet p;

    /**
     * Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    MultilineTextContainerView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a MultilineTextContainerModel
     * @param mtcm a model to show
     */
    void show(MultilineTextContainerModel mtcm) {
        p.fill(mtcm.getTextColor());
        p.textAlign(p.CENTER);

        float x = mtcm.getX();
        float y = mtcm.getY();

        for (String w : mtcm.getWords()) {
            p.text(w, x, y);
            y += p.textAscent() * 1.5;
        }
    }
}
