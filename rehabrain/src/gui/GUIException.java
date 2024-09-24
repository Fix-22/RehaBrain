package gui;

/**
 * DataManagerException
 * This class is used for custom RuntimeException(s) of the gui package
 * @author Simone Cecire
 */
public class GUIException extends RuntimeException {
    public GUIException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error: " + super.getMessage();
    }
}
