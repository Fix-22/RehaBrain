package scenes.sceneslist;

import datamanager.DataManager;
import gui.button.Button;
import gui.definedinputbox.DefinedInputBox;
import gui.textlabel.TextAlign;
import gui.textlabel.TextLabel;
import processing.core.PApplet;
import scenes.Activity;
import scenes.scene.Scene;
import scenes.scenemanager.SceneManager;

/**
 * RebuildWord
 * This class represents the activity where the user has to guess a word by its given first and last letter
 * @author Simone Cecire
 */
public class RebuildWord extends Scene implements Activity {
    private String currentWord;
    private TextLabel status;
    private Button correct, toActivities, next;
    private DefinedInputBox definedInputBox;

    public RebuildWord(DataManager dataManager, SceneManager sceneManager, PApplet p) {
        super(dataManager, sceneManager, p);
        status = new TextLabel(p.width / 2, p.height / 2 + 50, 0, 30, TextAlign.CENTER, "Parola", p);
        toActivities = new Button(p.width / 50, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Attività", false, p);
        next = new Button(p.width - p.width / 50 - p.width / 5, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(0, 206, 80), p.color(0, 206, 80), 255, "Prossimo", true, p);
        correct = new Button(p.width - p.width / 50 - p.width / 10, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(255, 149, 18), p.color(255, 149, 18), 255, "Correggi", false, p);
        definedInputBox = new DefinedInputBox(p.width / 2 - 250, p.height / 3.5f, 500, 100, 255, 0, 0, "", false, p);
        selectWord();
    }

    /**
     * This method checks if the correct word is written
     */
    @Override
    public void checkCorrectness() {
        if (definedInputBox.isCurrentTextCorrect()) {
            definedInputBox.setDisabled(true);
            status.setTextColor(p.color(0, 255, 0));
            status.setText("Corretto");
            status.show();
            next.setDisabled(false);
            next.show();
        }
        else if (!definedInputBox.isCurrentTextCorrect() && definedInputBox.isCurrentTextFull()) {
            definedInputBox.setRandomCorrectChar();
        }
    }

    /**
     * This method sets the current text equal to the correct text
     */
    @Override
    public void correct() {
        definedInputBox.setCurrentText(definedInputBox.getCorrectText());
    }

    /**
     * This method selects another word
     */
    @Override
    public void next() {
        selectWord();
        next.setDisabled(true);
        definedInputBox.setDisabled(false);
    }

    /**
     * This method handles the visualization of all GUI elements in the activity
     */
    @Override
    public void loop() {
        correct.show();
        toActivities.show();
        definedInputBox.show();

        checkCorrectness();
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
    }

    /**
     * This method handles all the keyboard's inputs
     */
    @Override
    public void handleKeyPressed() {
        definedInputBox.handleInput();
    }

    /**
     * This method select a new word to guess (from DataManager) and sets it as DefinedInputBox's correct word
     */
    public void selectWord() {
        currentWord = dataManager.getWords(1).get(0);
        definedInputBox.setCorrectText(currentWord);
    }
}
