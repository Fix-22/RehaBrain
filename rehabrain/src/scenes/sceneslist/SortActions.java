package scenes.sceneslist;

import datamanager.DataManager;
import gui.button.Button;
import gui.checkbutton.CheckButton;
import gui.textlabel.TextAlign;
import gui.textlabel.TextLabel;
import processing.core.PApplet;
import scenes.Activity;
import scenes.scene.Scene;
import scenes.scenemanager.SceneManager;
import java.util.*;

/**
 * SortActions
 * This class represents the activity where the user has to reorder some given actions
 * @author Simone Cecire
 */
public class SortActions extends Scene implements Activity {
    private String currentCategory;
    private TextLabel category, status;
    private ArrayList<CheckButton> choicesCheckButtons, choicesMade;
    private ArrayList<TextLabel> numbers;
    private ArrayList<String> correctChoices, currentChoices;
    private Button correct, toActivities, next;
    private boolean isCorrect, isNotCorrect;
    private int counter;

    public SortActions(DataManager dataManager, SceneManager sceneManager, PApplet p) {
        super(dataManager, sceneManager, p);
        category = new TextLabel(p.width / 2, p.height / 10, 0, 30, TextAlign.CENTER, "Categoria", p);
        status = new TextLabel(p.width / 2, p.height / 1.25f, 0, 30, TextAlign.CENTER, "Azione", p);
        toActivities = new Button(p.width / 50, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Attività", false, p);
        next = new Button(p.width - p.width / 50 - p.width / 5, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(0, 206, 80), p.color(0, 206, 80), 255, "Prossimo", true, p);
        correct = new Button(p.width - p.width / 50 - p.width / 10, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(255, 149, 18), p.color(255, 149, 18), 255, "Correggi", false, p);
        isCorrect = false;
        isNotCorrect = false;

        selectActions();
    }

    /**
     * This method checks if the sequence of check buttons pressed is correct
     */
    @Override
    public void checkCorrectness() {
        if (currentChoices.size() != correctChoices.size()) {
            isNotCorrect = true;
            return;
        }

        isCorrect = true;

        for (int i = 0; i < currentChoices.size(); i++) {
            if (!currentChoices.get(i).equals(correctChoices.get(i))) {
                isCorrect = false;
                isNotCorrect = true;
                break;
            }
        }
    }

    /**
     * This method sets the correct sequence of check buttons
     */
    @Override
    public void correct() {
        if (isCorrect) return;
        counter = 0;

        choicesMade.clear();
        currentChoices.clear();

        for (CheckButton cb : choicesCheckButtons) {
            cb.setSelected(false);
        }

        for (String correctChoice : correctChoices) {
            for (CheckButton cb : choicesCheckButtons) {
                if (cb.getText().equals(correctChoice)) {
                    cb.setSelected(true);
                    counter++;
                    numbers.get(choicesCheckButtons.indexOf(cb)).setText(Integer.toString(counter));
                    break;
                }
            }
        }

        isCorrect = true;
        isNotCorrect = false;
    }

    /**
     * This method selects the new sequence of actions
     */
    @Override
    public void next() {
        selectActions();
    }

    /**
     * This method handles the visualization of all GUI elements in the activity
     */
    @Override
    public void loop() {
        correct.show();
        toActivities.show();

        p.fill(0);
        p.textAlign(p.CENTER);
        category.setText("Azione:\n" + currentCategory.replaceAll("_", " ").toUpperCase());
        category.show();

        for (CheckButton cb : choicesCheckButtons) {
            cb.show();
        }

        for (TextLabel number : numbers) {
            number.show();
        }

        if (isCorrect) {
            next.show();
            next.setDisabled(false);
            status.setTextColor(p.color(0, 255, 0));
            status.setText("Corretto");
            status.show();

            for (CheckButton cb : choicesCheckButtons) cb.setDisabled(true);
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

        if (!isCorrect) {
            for (CheckButton cb : choicesCheckButtons) {
                cb.changeState();

                if (cb.isSelected()) {
                    if (!choicesMade.contains(cb)) {
                        currentChoices.add(cb.getText());
                        choicesMade.add(cb);
                        counter++;
                        numbers.get(choicesCheckButtons.indexOf(cb)).setText(Integer.toString(counter));
                    }
                }
                else {
                    if (choicesMade.contains(cb)) {
                        currentChoices.remove(cb.getText());
                        choicesMade.remove(cb);
                        counter--;
                        numbers.get(choicesCheckButtons.indexOf(cb)).setText("");
                    }
                }
            }
        }

        if (choicesMade.size() == 4) { // if all the check buttons are selected
            checkCorrectness();
        }
    }

    /**
     * This method:
     * 1) creates an ArrayList of 4 buttons (choicesCheckButtons) that represents all the actions in the current task
     * 2) creates an ArrayList of CheckButton (choicesMade) that represents the order in which the cehck buttons are selected
     * 3) gets an ArrayList of String (from DataManager) that represents all the ORDERED actions based on a category
     * 4) creates all the CheckButtons (corresponding to the actions but with their position shuffled)
     */
    private void selectActions() {
        currentCategory = dataManager.getRandomActionCategory();

        isCorrect = false;
        isNotCorrect = false;
        next.setDisabled(true);

        choicesCheckButtons = new ArrayList<>(0);
        choicesMade = new ArrayList<>(0);
        numbers = new ArrayList<>(0); // ArrayList of TextLabel used to show the action number under a CheckButton
        counter = 0;

        currentChoices = new ArrayList<>(0);
        correctChoices = dataManager.getActions(currentCategory);

        int x = p.width / 2 - 430, y = p.height / 2 - 50;
        ArrayList<Integer> xs = new ArrayList<>(0);
        int j = 0;

        for (int i = 0; i < correctChoices.size(); i++) {
            xs.add(x);
            x += 220;
        }
        Collections.shuffle(xs);

        for (String action : correctChoices) {
            numbers.add(new TextLabel(xs.get(j) + 100, y + 150, 0, 25, TextAlign.CENTER, "", p));
            CheckButton cb = new CheckButton(xs.get(j), y, 200, 100, p.color(255, 255, 255), 0,0, action,false, false, p);
            choicesCheckButtons.add(cb);
            j++;
        }
    }
}
