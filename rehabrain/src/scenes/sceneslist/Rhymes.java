package scenes.sceneslist;

import datamanager.DataManager;
import gui.button.Button;
import gui.inputbox.InputBox;
import gui.multilinetextcontainer.MultilineTextContainer;
import gui.textlabel.TextAlign;
import gui.textlabel.TextLabel;
import processing.core.PApplet;
import scenes.Activity;
import scenes.scene.Scene;
import scenes.scenemanager.SceneManager;

/**
 * Rhymes
 * This class represents the activity where the user has to find some words that rhymes with a given other word
 * @author Simone Cecire
 */
public class Rhymes extends Scene implements Activity {
    private String currentRhyme;
    private TextLabel rhyme, rhymeCount;
    private Button toActivities, next;
    private InputBox inputBox;
    private MultilineTextContainer multilineContainer;
    private int maxRhymesCount, currentRhymesCount;

    public Rhymes(DataManager dataManager, SceneManager sceneManager, PApplet p) {
        super(dataManager, sceneManager, p);
        rhyme = new TextLabel(p.width / 2, p.height / 10, 0, 30, TextAlign.CENTER, "Rima", p);
        rhymeCount = new TextLabel(p.width / 2, p.height / 5, 0, 20, TextAlign.CENTER, "Rima", p);
        toActivities = new Button(p.width / 50, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Attività", false, p);
        next = new Button(p.width - p.width / 50 - p.width / 10, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(0, 206, 80), p.color(0, 206, 80), 255, "Prossimo", true, p);
        inputBox = new InputBox(p.width / 2 - 200, p.height / 3.5f, 300, 50, 255, 0, 0, "", false, false, new Button(p.width / 2 + 104, p.height / 3.5f, 100, 50, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Aggiungi", false, p), p);
        multilineContainer = new MultilineTextContainer(p.width / 2.5f, p.height / 2, 0, 0, 0, p);
        selectRhyme();
    }

    /**
     * This method checks if the pressed button is the correct one
     */
    @Override
    public void checkCorrectness() {
        float y = p.height / 2;

        for (String w : multilineContainer.getWords()) {
            TextLabel status = new TextLabel(p.width / 1.6f, y, 0, 20, TextAlign.CENTER, "", p);

            if (dataManager.rhymeInWord(currentRhyme, w)) {
                status.setTextColor(p.color(0, 255, 0));
                status.setText("Corretto");
            }
            else {
                status.setTextColor(p.color(255, 0, 0));
                status.setText("Non esatto, riprova");
            }

            status.show();
            y += p.textAscent() * 1.5;
        }
    }

    /**
     * This method is empty because it is not possible to search for all the rhymes of a word
     */
    @Override
    public void correct() {}

    /**
     * This method select a new rhyme from the pool
     */
    @Override
    public void next() {
        selectRhyme();
    }

    /**
     * This method handles the visualization of all GUI elements in the activity
     */
    @Override
    public void loop() {
        toActivities.show();
        inputBox.show();
        multilineContainer.show();
        rhyme.setText("Rima attuale:\n" + currentRhyme);
        rhymeCount.setText("Rima " + currentRhymesCount + " di " + maxRhymesCount);
        rhymeCount.show();
        rhyme.show();

        checkCorrectness();

        if (currentRhymesCount < maxRhymesCount) {
            currentRhymesCount = multilineContainer.getWords().size();
        }
        else {
            next.show();
            next.setDisabled(false);
        }
    }

    /**
     * This method handles all the buttons' inputs
     */
    @Override
    public void handleMousePressed() {
        inputBox.changeState();

        if (next.isPressed()) {
            next();
        }
        else if (toActivities.isPressed()) {
            changeScene(new ActivitiesScreen(dataManager, sceneManager, p));
        }
        else if (inputBox.isRelatedButtonPressed() && currentRhymesCount < maxRhymesCount && inputBox.getText() != "" && !multilineContainer.getWords().contains(inputBox.getText())) {
            multilineContainer.addWord(inputBox.getText());
            inputBox.clearText();
        }
    }

    /**
     * This method handles all the keyboard's inputs
     */
    @Override
    public void handleKeyPressed() {
        inputBox.handleInput();
    }

    /**
     * This method selects a new random rhyme (from DataManager) and clears the previous written rhymes
     */
    public void selectRhyme() {
        currentRhyme = dataManager.getRhyme();
        maxRhymesCount = 5;
        currentRhymesCount = 0;
        multilineContainer.clear();
    }
}
