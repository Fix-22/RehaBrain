package datamanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * DataManager
 * This class is the manager for all the data present in the application
 * @author Simone Cecire
 */
public class DataManager {
    private String profile, objectsPoolPath, actionsPoolPath, rhymesPath;
    private Random random;
    private File file;
    private ArrayList<String> objectsFileContent, actionsFileContent, rhymesFileContent;

    /**
     * Default constructor
     * It checks the existence and correctness of all the files involved in the application
     * @throws NullPointerException when the pool filename is null
     * @throws FileNotFoundException when the file is not found
     * @throws PoolException if the file is not found or a line is not valid
     */
    public DataManager() throws NullPointerException, FileNotFoundException, PoolException {
        File configFile = new File(System.getProperty("user.dir") + "/data/profiles/current.configuration");

        if (!configFile.exists()) {
            throw new FileNotFoundException("Configuration file \"current.configuration\" not found, make sure the file still exist at " + System.getProperty("user.dir") + "/data/profiles/\"");
        }

        Scanner scanner = new Scanner(configFile);

        if (scanner.hasNextLine()) {
            String start = scanner.nextLine();

            if (!start.equals("!CONFIG")) {
                throw new DataManagerException("File \"current.configuration\" is not marked as a configuration file, make sure the first line is \"!CONFIG\"");
            }
            else {
                String using = scanner.nextLine();
                String[] usingSplitted = using.split(" ");

                if (usingSplitted.length == 2) {
                    if (usingSplitted[0].equals("using")) {
                        File test = new File(System.getProperty("user.dir") + "/data/profiles/" + usingSplitted[1]);

                        if (test.exists()) {
                            profile = usingSplitted[1];
                        }
                        else {
                            throw new FileNotFoundException("Directory \"" + System.getProperty("user.dir") + "/data/profiles/" + usingSplitted[1] + "\" doesn't exist");
                        }
                    }
                    else {
                        throw new DataManagerException("Error at line 2 of \"current.configuration\", make sure that the line is \"using {name of directory to use as pool}\"");
                    }
                }
                else {
                    throw new DataManagerException("Error at line 2 of \"current.configuration\", make sure that the line is \"using {name of directory to use as pool}\"");
                }
            }
        }
        scanner.close();

        objectsPoolPath = System.getProperty("user.dir") + "/data/profiles/" + profile + "/objects.pool";
        actionsPoolPath = System.getProperty("user.dir") + "/data/profiles/" + profile + "/actions.pool";
        rhymesPath = System.getProperty("user.dir") + "/data/profiles/" + profile + "/rhymes.pool";

        objectsFileContent = new ArrayList<>(0);
        actionsFileContent = new ArrayList<>(0);
        rhymesFileContent = new ArrayList<>(0);

        fillFileContent(objectsPoolPath, objectsFileContent);
        fillFileContent(actionsPoolPath, actionsFileContent);
        fillFileContent(rhymesPath, rhymesFileContent);

    }

    /**
     * This method fills an ArrayList with the content of a file
     * @param currentPoolPath the pool file path
     * @param fileContent the ArrayList to fill
     * @throws NullPointerException when the pool filename is null
     * @throws FileNotFoundException when the file is not found
     * @throws PoolException if the file is not found or a line is not valid
     */
    private void fillFileContent(String currentPoolPath, ArrayList<String> fileContent) throws NullPointerException, FileNotFoundException, PoolException {
        if (currentPoolPath == null) {
            throw new NullPointerException("Path of pull is null");
        }
        else {
            file = new File(currentPoolPath);

            if (!file.exists()) {
                throw new FileNotFoundException("File \"" + file + "\" not found");
            }
            else {
                random = new Random();
                Scanner scanner = new Scanner(file);

                if (scanner.hasNextLine()) {
                    String start = scanner.nextLine();

                    if (!start.equals("!POOL") && !start.equals("!LIST") && !start.equals("!WORDS-POOL")) {
                        throw new PoolException("This file is not a pool or a list file");
                    }
                    else {
                        int lineCount = 2;

                        if (start.equals("!POOL")) {
                            while (scanner.hasNextLine()) {
                                String data = scanner.nextLine();

                                if (data.split(" ").length != 3) {
                                    throw new PoolException("Line " + lineCount + " of \"" + currentPoolPath + "\" is not valid");
                                }
                                else {
                                    fileContent.add(data);
                                }
                                lineCount++;
                            }
                        }
                        else if (start.equals("!WORDS-POOL") || start.equals("!LIST")) {
                            while (scanner.hasNextLine()) {
                                fileContent.add(scanner.nextLine().replaceAll("#", "\n")); // the # in the file is used to create another line
                            }
                        }
                    }
                }
                scanner.close();
            }
        }
    }

    /**
     * This method extract n words from the words file
     * @param n number of words to extract
     * @return an ArrayList of words extracted
     * @throws DataManagerException when the number of words is not valid
     */
    public ArrayList<String> getWords(int n) throws DataManagerException {
        if (n > objectsFileContent.size()) {
            throw new DataManagerException("File is too short (" + objectsFileContent.size() + " lines) to get " + n + " words from it");
        }
        else if (n <= 0) {
            throw new DataManagerException("Parameter 'n' is too little");
        }

        ArrayList<Integer> generated = new ArrayList<>(0);
        ArrayList<String> words = new ArrayList<>(0);

        for (int i = 0; i < n; i++) {
            int idx = random.nextInt(0, objectsFileContent.size());

            while (generated.contains(idx)) {
                idx = random.nextInt(0, objectsFileContent.size());
            }
            generated.add(idx);

            words.add(objectsFileContent.get(idx).split(" ")[0]);
        }

        return words;
    }

    /**
     * This method returns n images path
     * @param n number of images to return
     * @return a HashMap made of NAME and PATH of the images
     * @throws DataManagerException when the number of images is not valid
     */
    public HashMap<String, String> getImages(int n) throws DataManagerException {
        if (n > objectsFileContent.size()) {
            throw new DataManagerException("File is too short (" + objectsFileContent.size() + " lines) to get " + n + " words from it");
        }
        else if (n <= 0) {
            throw new DataManagerException("Parameter 'n' is too little");
        }

        ArrayList<Integer> generated = new ArrayList<>(0);
        HashMap<String, String> imagesPath = new HashMap<>(0);

        for (int i = 0; i < n; i++) {
            int idx = random.nextInt(0, objectsFileContent.size());

            while (generated.contains(idx)) {
                idx = random.nextInt(0, objectsFileContent.size());
            }
            generated.add(idx);

            imagesPath.put(objectsFileContent.get(idx).split(" ")[0], objectsFileContent.get(idx).split(" ")[1]);
        }

        return imagesPath;
    }

    /**
     * This method extract n actions from the actions file
     * @param category the actions' category
     * @return an ArrayList of actions extracted
     * @throws NullPointerException when category is null
     * @throws ListException when category is not present in the file
     * @throws PoolException when there are errors on the pool file
     */
    public ArrayList<String> getActions(String category) throws NullPointerException, ListException, PoolException {
        if (category == null) {
            throw new NullPointerException("Parameter category is null");
        }

        ArrayList<String> listFileContent = new ArrayList<>(0);

        try {
            fillFileContent(System.getProperty("user.dir") + "/data/profiles/" + profile + "/actions/" + category + ".list", listFileContent);
        }
        catch (FileNotFoundException e) {
            throw new ListException("No such category named \"" + category + "\"");
        }
        catch (PoolException e) {
            throw new PoolException(e.getMessage());
        }

        return listFileContent;
    }

    /**
     * This method extract n words of a specific category
     * @param n the number of words
     * @param category the category of the words
     * @return an ArrayList of words extracted
     * @throws DataManagerException if there are errors on the number of words to extract
     * @throws NullPointerException if the category is null
     */
    public ArrayList<String> getWords(int n, String category) throws DataManagerException, NullPointerException {
        if (n > objectsFileContent.size()) {
            throw new DataManagerException("File is too short (" + objectsFileContent.size() + " lines) to get " + n + " words from it");
        }
        else if (n <= 0) {
            throw new DataManagerException("Parameter 'n' is too little");
        }
        else if (category == null) {
            throw new NullPointerException("Parameter category is null");
        }

        ArrayList<String> words = new ArrayList<>(0);

        for (String s : objectsFileContent) {
            String[] splitted = s.split(" ");

            if (splitted[2].equals(category)) {
                words.add(splitted[0]);
            }
        }

        if (n > words.size()) {
            throw new DataManagerException("There are" + (words.size() > 0 ? " only " + words.size() + " words of category \"" + category + "\"" : "n't any words of category \"" + category + "\""));
        }

        Collections.shuffle(words);

        ArrayList<String> out = (ArrayList<String>) words.clone();

        for (int i = n; i < out.size(); i++) {
            out.remove(i);
        }

        return out;
    }

    /**
     * This method returns a random category selected from the pool file
     * @return the String containing a random category
     */
    public String getRandomCategory() {
        return objectsFileContent.get(random.nextInt(0, objectsFileContent.size())).split(" ")[2];
    }

    /**
     * This method returns a random category for actions selected from the pool file of actions
     * @return the String containing a random category
     */
    public String getRandomActionCategory() {
        return actionsFileContent.get(random.nextInt(0, actionsFileContent.size()));
    }

    /**
     * This method returns a HashMap (of length n and full at 75% by correct category images) made of a HashMap that contains the name and the path of the file and the category of the image and name
     * @param n the number of images to return
     * @param category the category of the images
     * @return a HashMap made of the name and path of the images and the category of the images
     * @throws DataManagerException if there are errors on the number of images
     * @throws NullPointerException if the category is null
     */
    public HashMap<HashMap<String, String>, String> getImages(int n, String category) throws DataManagerException, NullPointerException {
        if (n > objectsFileContent.size()) {
            throw new DataManagerException("File is too short (" + objectsFileContent.size() + " lines) to get " + n + " words from it");
        }
        else if (n <= 0) {
            throw new DataManagerException("Parameter 'n' is too little");
        }
        else if (category == null) {
            throw new NullPointerException("Parameter category is null");
        }

        HashMap<HashMap<String, String>, String> imagesWithCategory = new HashMap<>(0);

        for (String s : objectsFileContent) {
            String[] splitted = s.split(" ");

            if (imagesWithCategory.size() < Math.round(n * 0.75) && splitted[2].equals(category)) {
                HashMap<String, String> images = new HashMap<>(0);
                images.put(splitted[0], splitted[1]);

                imagesWithCategory.put(images, splitted[2]);
            }
            else if (imagesWithCategory.size() >= Math.round(n * 0.75) && imagesWithCategory.size() < n && !splitted[2].equals(category)) {
                HashMap<String, String> images = new HashMap<>(0);
                images.put(splitted[0], splitted[1]);

                imagesWithCategory.put(images, splitted[2]);
            }
        }

        if (n > imagesWithCategory.size()) {
            throw new DataManagerException("There are" + (imagesWithCategory.size() > 0 ? " only " + imagesWithCategory.size() + " words of category \"" + category + "\"" : "n't any words of category \"" + category + "\""));
        }

        return imagesWithCategory;
    }

    /**
     * This method extract a random rhyme from the rhymes file and returns it
     * @return a String that contains the rhyme
     */
    public String getRhyme() {
        return rhymesFileContent.get(random.nextInt(0, rhymesFileContent.size()));
    }

    /**
     * This method checks if the rhyme is present in the word
     * @param rhyme the String of a rhyme to check
     * @param word the String of a word to check
     * @return true if there is a rhyme, otherwise false
     * @throws NullPointerException if the rhyme or the word are null
     * @throws DataManagerException if the rhyme is not present in the rhymes file
     */
    public boolean rhymeInWord(String rhyme, String word) throws NullPointerException, DataManagerException {
        if (rhyme == null) {
            throw new NullPointerException("Rhyme is null");
        }
        else if (word == null) {
            throw new NullPointerException("Word is null");
        }
        else if (!rhymesFileContent.contains(rhyme)) {
            throw new DataManagerException("The rhyme is not contained in the rhyme list");
        }

        if (rhyme.equals(word) || word.length() < 4) {
            return false;
        }

        return word.substring(word.length() - 4, word.length()).equals(rhyme.substring(rhyme.length() - 4, rhyme.length()));
    }
}