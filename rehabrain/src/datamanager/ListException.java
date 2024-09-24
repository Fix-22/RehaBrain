package datamanager;

/**
 * ListException
 * This class is used for custom RuntimeException(s) in list files
 * @author Simone Cecire
 */
public class ListException extends RuntimeException {
    public ListException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error: " + super.getMessage();
    }
}
