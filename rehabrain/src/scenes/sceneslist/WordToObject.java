package scenes.sceneslist;

import datamanager.DataManager;
import gui.button.Button;
import gui.imagebutton.ImageButton;
import gui.textlabel.TextAlign;
import gui.textlabel.TextLabel;
import processing.core.PApplet;
import scenes.Activity;
import scenes.scene.Scene;
import scenes.scenemanager.SceneManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * WordToObject
 * This class represents the activity where the user has to match an image with its corresponding word
 * @author Simone Cecire
 */
public class WordToObject extends Scene implements Activity {
    private String currentWord;
    private ArrayList<ImageButton> choicesButtons;
    private ImageButton correctButton;
    private TextLabel word, status;
    private Button correct, toActivities, next;
    private boolean isCorrect, isNotCorrect;

    public WordToObject(DataManager dataManager, SceneManager sceneManager, PApplet p) {
        super(dataManager, sceneManager, p);
        word = new TextLabel(p.width / 2, p.height / 10, 0, 30, TextAlign.CENTER, "Parola", p);
        status = new TextLabel(p.width / 2, p.height - p.height / 3, 0, 30, TextAlign.CENTER, "", p);
        toActivities = new Button(p.width / 50, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(51, 204, 204), p.color(51, 204, 204), 255, "Attività", false, p);
        next = new Button(p.width - p.width / 50 - p.width / 5, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(0, 206, 80), p.color(0, 206, 80), 255, "Prossimo", true, p);
        correct = new Button(p.width - p.width / 50 - p.width / 10, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(255, 149, 18), p.color(255, 149, 18), 255, "Correggi", false, p);
        isCorrect = false;
        isNotCorrect = false;
        generateObjects();
    }

    /**
     * This method checks if the pressed button is the correct one
     */
    @Override
    public void checkCorrectness() {
        for (ImageButton ib : choicesButtons) {
            if (ib.isPressed()) {
                status.setX(ib.getX() + 80);
                status.setY(ib.getY() + 250);

                if (ib.equals(correctButton)) {
                    isCorrect = true;
                }
                else {
                    isNotCorrect = true;
                }
            }
        }
    }

    /**
     * This method selects the correct button
     */
    @Override
    public void correct() {
        for (ImageButton ib : choicesButtons) {
            if (ib.equals(correctButton)) {
                status.setX(ib.getX() + 80);
                status.setY(ib.getY() + 250);
                isCorrect = true;
            }
        }
    }

    /**
     * This method select a new word from the pool
     */
    @Override
    public void next() {
        generateObjects();
    }

    /**
     * This method handles the visualization of all GUI elements in the activity
     */
    @Override
    public void loop() {
        correct.show();
        toActivities.show();

        word.setText("Parola:\n" + currentWord);
        word.show();

        for (ImageButton ib : choicesButtons) {
            ib.show();
        }

        if (isCorrect) {
            next.show();
            next.setDisabled(false);
            status.setTextColor(p.color(0, 255, 0));
            status.setText("Corretto");
            status.show();

            for (ImageButton ib : choicesButtons) {
                ib.setDisabled(true);
            }
        }
        else if (isNotCorrect) {
            status.setTextColor(p.color(255, 0, 0));
            status.setText("Non esatto,\nriprova");
            status.show();
        }
    }

    /**
     * This method handles all the buttons' inputs
     */
    @Override
    public void handleMousePressed() {
        if (next.isPressed()) {
            next();
        }
        else if (correct.isPressed()) {
            correct();
        }
        else if (toActivities.isPressed()) {
            changeScene(new ActivitiesScreen(dataManager, sceneManager, p));
        }

        checkCorrectness();
    }

    /**
     * This method:
     * 1) creates an ArrayList of 5 buttons (choicesButtons) that represents all the objects in the current task
     * 2) gets a HashMap (from DataManager) that contains the NAME linked PATH of the objects' images
     * 3) iterates over the keys of the HashMap (the names of the images) and selects a random name (by having pregenerated a random index) to set as the current word
     * 4) generates all the buttons and sets the one corresponding to the word to guess in a variable (correctButton)
     */
    private void generateObjects() {
        isCorrect = false;
        isNotCorrect = false;
        next.setDisabled(true);

        currentWord = null;
        choicesButtons = new ArrayList<>(0);

        Random random = new Random();
        HashMap<String, String> imagesNamePath = dataManager.getImages(4);

        int x = p.width / 2 - 380, y = p.height / 2 - 100;
        int i = 0, j = 0;
        int idx = random.nextInt(0, 4); // the index to select the random word

        for (String name : imagesNamePath.keySet()) {
            if (i == idx) {
                currentWord = name;
                break;
            }
            i++;
        }

        for (String path : imagesNamePath.values()) {
            ImageButton ib = new ImageButton(x, y, 0.5f, 0, p.loadImage(path), false, p);
            choicesButtons.add(ib);

            if (j == idx) {
                correctButton = ib;
            }
            x += 220;
            j++;
        }
    }
}
