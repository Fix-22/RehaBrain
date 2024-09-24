package gui.imagecheckbutton;

import processing.core.PApplet;

/**
 * ImageCheckButtonView
 * This is the view class of ImageCheckButton
 * @author Simone Cecire
 */
class ImageCheckButtonView {
    private PApplet p;

    /**
     Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    ImageCheckButtonView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a ImageCheckButtonModel
     * @param icbm a model to show
     */
    void show(ImageCheckButtonModel icbm) {
        p.noFill();
        p.stroke(icbm.getBorderColor());
        p.strokeWeight(icbm.getImage().height / (icbm.isSelected() ? 10 : 15));
        p.rect(icbm.getX() - 30, icbm.getY() - 30, icbm.getImage().width + 30, icbm.getImage().height + 30);
        p.image(icbm.getImage(), icbm.getX() - 15, icbm.getY() - 15);
    }

    /**
     * This method checks if the button is pressed
     * @param icbm a model to interact with
     * @return true if the button is pressed, otherwise false
     */
    boolean isPressed(ImageCheckButtonModel icbm) {
        return p.mouseX >= icbm.getX() && p.mouseX <= icbm.getX() + icbm.getImage().width && p.mouseY >= icbm.getY() && p.mouseY <= icbm.getY() + icbm.getImage().height && p.mousePressed && !icbm.isDisabled();
    }

    /**
     * This method changes the state of selected if the button is pressed
     * @param icbm a model to interact with
     * @return true if the button is selected, otherwise false
     */
    void changeState(ImageCheckButtonModel icbm) {
        if (isPressed(icbm)) {
            icbm.setSelected(!icbm.isSelected());
        }
    }
}
