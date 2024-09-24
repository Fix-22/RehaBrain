package gui;

/**
 * Plain
 * This interface is used to represent all types of plain buttons in the application
 * @author Simone Cecire
 */
public interface Plain extends Showable {
    /**
     * This method is used to check if the button is pressed
     * @return true if the button is pressed, otherwise false
     */
    boolean isPressed();
}
