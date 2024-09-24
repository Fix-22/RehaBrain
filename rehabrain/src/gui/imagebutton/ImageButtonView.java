package gui.imagebutton;

import processing.core.PApplet;

/**
 * ImageButtonView
 * This is the view class of ImageButton
 * @author Simone Cecire
 */
class ImageButtonView {
    private PApplet p;

    /**
     Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    ImageButtonView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a ImageButtonModel
     * @param ibm a model to show
     */
    void show(ImageButtonModel ibm) {
        p.image(ibm.getImage(), ibm.getX() + ibm.getImage().height / 15, ibm.getY() + ibm.getImage().height / 15);
    }

    /**
     * This method checks if the button is pressed
     * @param ibm a model to interact with
     * @return true if the button is pressed, otherwise false
     */
    boolean isPressed(ImageButtonModel ibm) {
        return p.mouseX >= ibm.getX() && p.mouseX <= ibm.getX() + ibm.getImage().width && p.mouseY >= ibm.getY() && p.mouseY <= ibm.getY() + ibm.getImage().height && p.mousePressed && !ibm.isDisabled();
    }
}
