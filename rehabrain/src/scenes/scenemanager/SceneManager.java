package scenes.scenemanager;

import scenes.scene.Scene;
import java.util.ArrayList;

/**
 * SceneManger
 * This class is used to interact and change various scenes
 * @author Simone Cecire
 */
public class SceneManager {
    private Scene currentScene;

    /**
     * Default constructor
     * Always use method "changeScene" to set the first scene
     */
    public SceneManager() {}

    /**
     * This method changes the current scene
     * @param scene the Scene to set as the current
     */
    public void changeScene(Scene scene) {
        if (scene != null) {
            currentScene = scene;
        }
    }

    /**
     * This method has to be called in the draw() method in the main sketch
     */
    public void loop() {
        currentScene.loop();
    }

    /**
     * This method has to be called in the mousePressed() method in the main sketch
     */
    public void handleMousePressed() {
        currentScene.handleMousePressed();
    }

    /**
     * This method has to be called in the mouseDragged() method in the main sketch
     */
    public void handleMouseDragged() {
        currentScene.handleMouseDragged();
    }

    /**
     * This method has to be called in the mouseClicked() method in the main sketch
     */
    public void handleMouseClicked() {
        currentScene.handleMouseClicked();
    }

    /**
     * This method has to be called in the keyPressed() method in the main sketch
     */
    public void handleKeyPressed() {
        currentScene.handleKeyPressed();
    }
}
