package datamanager;

/**
 * DataManagerException
 * This class is used for custom RuntimeException(s) of the DataManager
 * @author Simone Cecire
 */
public class DataManagerException extends RuntimeException {
    public DataManagerException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error: " + super.getMessage();
    }
}
