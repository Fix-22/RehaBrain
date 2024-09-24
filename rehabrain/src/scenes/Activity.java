package scenes;

/**
 * Activity
 * This class represents an activity screen, such as: rhymes, rebuild word, sort actions...
 * @author Simone Cecire
 */
public interface Activity {
    /**
     * This method will be called to check the correctness of the task the user is doing in an activity
     */
    void checkCorrectness();

    /**
     * This method will be called to correct the task the user is doing in an activity
     */
    void correct();

    /**
     * This method will be called to advance to the next task in an activity
     */
    void next();
}
