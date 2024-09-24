package gui.checkbutton;

import processing.core.PApplet;

/**
 * CheckButtonView
 * This is the view class of CheckButton
 * @author Simone Cecire
 */
class CheckButtonView {
    private PApplet p;

    /**
     * Default constructor
     * @param p the reference of PApplet
     * @throws NullPointerException if the reference of PApplet is null
     */
    CheckButtonView(PApplet p) throws NullPointerException {
        if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.p = p;
        }
    }

    /**
     * This method shows a CheckButtonModel
     * @param cbm a model to show
     */
    void show(CheckButtonModel cbm) {
        p.rectMode(p.CORNER);
        p.fill(p.red(cbm.getFillColor()) - (cbm.isSelected() ? 30 : 0), p.green(cbm.getFillColor()) - (cbm.isSelected() ? 30 : 0), p.blue(cbm.getFillColor()) - (cbm.isSelected() ? 30 : 0));
        p.stroke(cbm.getBorderColor());
        p.strokeWeight(cbm.getHeight() / (cbm.isSelected() ? 10 : 15));
        p.rect(cbm.getX(), cbm.getY(), cbm.getWidth(), cbm.getHeight());
        p.textAlign(p.CENTER, p.CENTER);
        p.textSize(cbm.getHeight() / 5.5f);
        p.fill(cbm.getTextColor());
        p.text(cbm.getText(), cbm.getX() + cbm.getWidth() / 2, cbm.getY() + cbm.getHeight() / 2 - (p.textAscent() * cbm.getText().chars().filter(c -> c == '\n').count()) / 5); // this lambda function is used to get the number of character \n to match the y position of the text based on their number
    }

    /**
     * This method checks if the button is pressed
     * @param cbm a model to interact with
     * @return true if the button is pressed, otherwise false
     */
    boolean isPressed(CheckButtonModel cbm) {
        return p.mouseX >= cbm.getX() && p.mouseX <= cbm.getX() + cbm.getWidth() && p.mouseY >= cbm.getY() && p.mouseY <= cbm.getY() + cbm.getHeight() && p.mousePressed && !cbm.isDisabled();
    }

    /**
     * This method checks if the button is pressed, then changes its state (from not selected to selected and vice versa)
     * @param cbm a model to interact with
     */
    void changeState(CheckButtonModel cbm) {
        if (isPressed(cbm)) {
            cbm.setSelected(!cbm.isSelected());
        }
    }
}
