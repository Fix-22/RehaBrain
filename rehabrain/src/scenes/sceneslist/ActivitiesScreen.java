package scenes.sceneslist;

import datamanager.DataManager;
import gui.button.Button;
import processing.core.PApplet;
import scenes.scene.Scene;
import scenes.scenemanager.SceneManager;

/**
 * ActivitiesScreen
 * This class represents the screen where the user will select the wanted activity
 * @autor Simone Cecire
 */
public class ActivitiesScreen extends Scene {
    private Button wordToObject, wordsInCategory, rhymes, rebuildWord, sortActions, toMainScreen, exit;

    public ActivitiesScreen(DataManager dataManager, SceneManager sceneManager, PApplet p) {
        super(dataManager, sceneManager, p);
        wordToObject = new Button(p.width / 8, p.height / 2 - p.height / 3, p.width / 10, p.height / 10, p.color(51, 204, 204), p.color(51, 204, 204), 255, "Abbinamento\noggetto-parola", false, p);
        wordsInCategory = new Button(p.width / 2 - p.width / 14, p.height / 2 - p.height / 3, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Raggruppamento\nper categoria", false, p);
        sortActions = new Button(p.width - p.width / 3.5f, p.height / 2 - p.height / 3, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Ordina le\nazioni", false, p);
        rhymes = new Button(p.width / 8 + p.width / 7, p.height / 2, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Rime", false, p);
        rebuildWord = new Button(p.width / 2 + p.width / 14, p.height / 2, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Ricostruzione\nparola", false, p);
        toMainScreen = new Button(p.width - p.width / 8, p.height - p.height / 4, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Schermata\nprincipale", false, p);
        exit = new Button(p.width - p.width / 8, p.height - p.height / 8, p.width / 10, p.height / 10, p.color(240, 60, 0), p.color(240, 60, 0), 255, "Esci", false, p);
    }

    /**
     * This method handles the visualization of all GUI elements in the scene
     */
    @Override
    public void loop() {
        wordToObject.show();
        wordsInCategory.show();
        sortActions.show();
        rhymes.show();
        rebuildWord.show();
        toMainScreen.show();
        exit.show();
    }

    /**
     * This method handles all the buttons' inputs
     */
    @Override
    public void handleMousePressed() {
        if (wordToObject.isPressed()) {
            changeScene(new WordToObject(dataManager, sceneManager, p));
        }
        else if (wordsInCategory.isPressed()) {
            changeScene(new WordsInCategory(dataManager, sceneManager, p));
        }
        else if (sortActions.isPressed()) {
            changeScene(new SortActions(dataManager, sceneManager, p));
        }
        else if (rhymes.isPressed()) {
            changeScene(new Rhymes(dataManager, sceneManager, p));
        }
        else if (rebuildWord.isPressed()) {
            changeScene(new RebuildWord(dataManager, sceneManager, p));
        }
        else if (toMainScreen.isPressed()) {
            changeScene(new MainScreen(dataManager, sceneManager, p));
        }
        else if (exit.isPressed()) {
            stop();
        }
    }
}
