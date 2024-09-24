package scenes.scene;

import datamanager.DataManager;
import processing.core.PApplet;
import scenes.scenemanager.SceneManager;

/**
 * Scene
 * This class is used to represent different scenes in the application
 * By overriding and defining the methods inside this class, the SceneManager can change scenes without the need of lots of code
 * @author Simone Cecire
 */
public abstract class Scene {
    protected DataManager dataManager;
    protected SceneManager sceneManager;
    protected PApplet p;

    /**
     * Default constructor
     * @param dataManager the reference to the DataManger associated with this scene
     * @param sceneManager the reference to the SceneManger associated with this scene
     * @param p the reference of the PApplet
     * @throws NullPointerException if the reference of DataManger, SceneManger or PApplet are null
     */
    public Scene(DataManager dataManager, SceneManager sceneManager, PApplet p) throws NullPointerException {
        if (dataManager == null) {
            throw new NullPointerException("Reference to data manager is null");
        }
        else if (sceneManager == null) {
            throw new NullPointerException("Reference to scene manager is null");
        }
        else if (p == null) {
            throw new NullPointerException("PApplet is null");
        }
        else {
            this.dataManager = dataManager;
            this.sceneManager = sceneManager;
            this.p = p;
        }
    }

    /**
     * This method is will be looped inside the draw() method of the main sketch (via same method of SceneManger)
     * This method MUST be implemented to use the Scene-SceneManager pattern
     */
    public abstract void loop();

    /**
     * This method will be in the mousePressed() method in the main sketch (via same method of SceneManger)
     */
    public void handleMousePressed() {}

    /**
     * This method will be in the mouseDragged() method in the main sketch (via same method of SceneManger)
     */
    public void handleMouseDragged() {}

    /**
     * This method will be in the mouseClicked() method in the main sketch (via same method of SceneManger)
     */
    public void handleMouseClicked() {}

    /**
     * This method will be in the keyPressed() method in the main sketch (via same method of SceneManger)
     */
    public void handleKeyPressed() {}

    /**
     * This method changes the scene to a new scene (via same method of SceneManger)
     * @param scene the new Scene to go to
     */
    public final void changeScene(Scene scene) {
        sceneManager.changeScene(scene);
    }

    /**
     * This method stops the scene and the sketch
     */
    public final void stop() {
        p.exit();
    }
}
