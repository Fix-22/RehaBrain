package gui;

/**
 * Selectable
 * This interface is used to represent all types of check buttons in the application
 * @author Simone Cecire
 */
public interface Selectable extends Showable {
    /**
     * This method is used to check if the check button is pressed
     * @return true if the check button is pressed, otherwise false
     */
    boolean isPressed();

    /**
     * This method is used to return the state of selected
     * @return true if the check button is selected, otherwise false
     */
    boolean isSelected();

    /**
     * This method checks if the button is pressed, then changes its state (from not selected to selected and vice versa)
     */
    void changeState();
}