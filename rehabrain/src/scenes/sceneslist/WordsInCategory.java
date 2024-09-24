package scenes.sceneslist;

import datamanager.DataManager;
import gui.button.Button;
import gui.imagecheckbutton.ImageCheckButton;
import gui.textlabel.TextAlign;
import gui.textlabel.TextLabel;
import processing.core.PApplet;
import scenes.Activity;
import scenes.scene.Scene;
import scenes.scenemanager.SceneManager;

import java.util.*;

/**
 * WordsInCategory
 * This class represents the activity where the user has to match images with the given category
 * @author Simone Cecire
 */
public class WordsInCategory extends Scene implements Activity {
    private String currentCategory;
    private ArrayList<ImageCheckButton> choicesCheckButtons, choicesMade;
    private HashMap<ImageCheckButton, Boolean> correctCheckButtons;
    private int choicesMadeCorrectCounter, correctChoicesCounter;
    private TextLabel category, status;
    private Button correct, toActivities, next;
    private boolean isCorrect, isNotCorrect;

    public WordsInCategory(DataManager dataManager, SceneManager sceneManager, PApplet p) {
        super(dataManager, sceneManager, p);
        choicesMadeCorrectCounter = 0;
        correctChoicesCounter = 0;
        category = new TextLabel(p.width / 2, p.height / 10, 0, 30, TextAlign.CENTER, "Categoria", p);
        status = new TextLabel(p.width / 2, p.height - p.height / 3, 0, 30, TextAlign.CENTER, "Risultato", p);
        toActivities = new Button(p.width / 50, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Attività", false, p);
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
        choicesMadeCorrectCounter = 0;

        for (ImageCheckButton icb : choicesCheckButtons) {
            if (icb.isSelected()) {
                if (correctCheckButtons.get(icb)) {
                    choicesMadeCorrectCounter++;
                }
                else {
                    isNotCorrect = true;
                    return;
                }
            }
        }

        if (choicesMadeCorrectCounter == correctChoicesCounter) isCorrect = true;
        else isNotCorrect = true;
    }

    /**
     * This method selects the correct buttons
     */
    @Override
    public void correct() {
        for (ImageCheckButton icb : choicesCheckButtons) {
            if (correctCheckButtons.get(icb)) {
                icb.setSelected(true);
            }
            else {
                icb.setSelected(false);
            }
        }
        checkCorrectness();
    }

    /**
     * This method select a new category from the pool
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

        category.setText("Categoria:\n" + currentCategory);
        category.show();

        for (ImageCheckButton icb : choicesCheckButtons) {
            icb.show();
        }

        if (isCorrect) {
            next.show();
            next.setDisabled(false);
            status.setTextColor(p.color(0, 255, 0));
            status.setText("Corretto");
            status.show();

            for (ImageCheckButton icb : choicesCheckButtons) {
                icb.setDisabled(true);
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

        for (ImageCheckButton icb : choicesCheckButtons) {
           icb.changeState();

           if (icb.isSelected()) {
               if (!choicesMade.contains(icb)) {
                   choicesMade.add(icb);
               }
           }
            else {
               if (choicesMade.contains(icb)) {
                   choicesMade.remove(icb);
               }
           }
        }

        if (choicesMade.size() >= 4) {
            checkCorrectness();
        }
    }

    /**
     * This method:
     * 1) creates an ArrayList of 5 buttons (choicesCheckButtons) that represents all the objects in the current task
     * 2) creates a HashMap (made by CheckButton as keys and Boolean as values) that represents the correctness of a single check button
     * 3) gets a HashMap (from DataManager) that contains images (made my a HashMap of NAME and PATH of the image) as key and their CATEGORY as value
     * 4) generates all the buttons and sets the corresponding to the category to guess in the HashMap made of CheckButton and Boolean
     */
    private void generateObjects() {
        currentCategory = dataManager.getRandomCategory();

        isCorrect = false;
        isNotCorrect = false;
        next.setDisabled(true);
        correctChoicesCounter = 0;
        choicesMadeCorrectCounter = 0;

        choicesMade = new ArrayList<>(0);
        choicesCheckButtons = new ArrayList<>(0);
        correctCheckButtons = new HashMap<>(0);

        HashMap<HashMap<String, String>, String> images = dataManager.getImages(5, currentCategory); // images (made by NAME and PATH) and their CATEGORY

        int x = p.width / 2 - 450, y = p.height / 2 - 100;
        int i = 0;

        for (HashMap<String, String> namePath : images.keySet()) {
            ImageCheckButton icb = new ImageCheckButton(x, y, 0.5f, 0, p.loadImage((String) namePath.values().toArray()[0]), false, false, p);
            choicesCheckButtons.add(icb);
            x += 200;
        }

        for (String category : images.values()) {
            ImageCheckButton icb = choicesCheckButtons.get(i);

            if (category.equals(currentCategory)) {
                correctCheckButtons.put(icb, true);
                correctChoicesCounter++;
            }
            else {
                correctCheckButtons.put(icb, false);
            }
            i++;
        }
    }
}
