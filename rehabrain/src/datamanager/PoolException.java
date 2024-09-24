package datamanager;

/**
 * PoolException
 * This class is used for custom RuntimeException(s) in pool files
 */
public class PoolException extends RuntimeException {
    public PoolException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Error: " + super.getMessage();
    }
}
