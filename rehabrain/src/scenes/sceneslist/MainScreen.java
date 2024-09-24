package scenes.sceneslist;

import datamanager.DataManager;
import gui.button.Button;
import gui.textlabel.TextAlign;
import gui.textlabel.TextLabel;
import processing.core.PApplet;
import processing.core.PImage;
import scenes.scene.Scene;
import scenes.scenemanager.SceneManager;

/**
 * MainScreen
 * This class represents the main screen
 * @author Simone Cecire
 */
public class MainScreen extends Scene {
    private TextLabel title, credits;
    private Button activities, quit;
    private PImage logo;

    public MainScreen(DataManager dataManager, SceneManager sceneManager, PApplet p) {
        super(dataManager, sceneManager, p);
        title = new TextLabel(p.width / 2, p.height / 2 - 80, 0, 80, TextAlign.CENTER, "RehaBrain", p);
        credits = new TextLabel(p.width - p.width / 6, p.height - p.height / 20, 0, 20, TextAlign.CENTER, "All the icons created by Freepik - Flaticon", p);
        activities = new Button(p.width / 2 - p.width / 5, p.height / 2 + p.height / 7, p.width / 10, p.height / 10, p.color(52, 204, 204), p.color(52, 204, 204), 255, "Attività", false, p);
        quit = new Button(p.width / 2 + (p.width / 7) / 2, p.height / 2 + p.height / 7, p.width / 10, p.height / 10, p.color(240, 60, 0), p.color(240, 60, 0), 255, "Esci", false, p);
        logo = p.loadImage(System.getProperty("user.dir") + "/data/images/logo.png");
        logo.resize(640, 360);
    }

    /**
     * This method handles the visualization of all GUI elements in the scene
     */
    @Override
    public void loop() {
        //title.show();
        credits.show();
        activities.show();
        quit.show();
        p.imageMode(p.CENTER);
        p.image(logo, p.width / 2, p.height / 2 - 100);
        p.imageMode(p.CORNER);
    }

    /**
     * This method handles all the buttons' inputs
     */
    @Override
    public void handleMousePressed() {
        if (activities.isPressed()) {
            changeScene(new ActivitiesScreen(dataManager, sceneManager, p));
        }
        else if (quit.isPressed()) {
            stop();
        }
    }
}
