package gui.button;

import processing.core.PApplet;

/**
 * ButtonView
 * This is the view class of Button
 * @author Simone Cecire
 */
class ButtonView {
    private PApplet p;

    /**
     * Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    ButtonView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a ButtonModel
     * @param bm a model to show
     */
    void show(ButtonModel bm) {
        p.rectMode(p.CORNER);
        p.fill(bm.getFillColor());
        p.stroke(bm.getBorderColor());
        p.strokeWeight(bm.getHeight() / 15);
        p.rect(bm.getX(), bm.getY(), bm.getWidth(), bm.getHeight());
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(bm.getHeight() / 4.5f);
        p.fill(bm.getTextColor());
        p.text(bm.getText(), bm.getX() + bm.getWidth() / 2 , bm.getY() + bm.getHeight() / 2);
    }

    /**
     * This method checks if the button is pressed
     * @param bm a model to interact with
     * @return true if the button is pressed, otherwise false
     */
    boolean isPressed(ButtonModel bm) {
        return p.mouseX >= bm.getX() && p.mouseX <= bm.getX() + bm.getWidth() && p.mouseY >= bm.getY() && p.mouseY <= bm.getY() + bm.getHeight() && p.mousePressed && !bm.isDisabled();
    }
}
