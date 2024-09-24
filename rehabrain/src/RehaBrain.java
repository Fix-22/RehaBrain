import datamanager.DataManager;
import processing.core.PApplet;
import scenes.scenemanager.SceneManager;
import scenes.sceneslist.MainScreen;
import processing.core.PImage;

/**
 * RehaBrain
 * This is an application made to help with people with neurodegenerative diseases using cognitive stimulation
 * All the icons created by Freepik - Flaticon
 * @author Simone Cecire and Sabina Venturelli
 */
public class RehaBrain extends PApplet {
    private DataManager dataManager;
    private SceneManager sceneManager;
    private MainScreen mainScreen;
    private PImage icon;

    @Override
    public void settings() {
        size(1500, 800);
    }

    @Override
    public void setup() {
        settings();

        try {
            dataManager = new DataManager();
        }
        catch (RuntimeException e) {
            System.out.println(e);
        }
        catch (Exception e) {
            System.out.println(e);
        }

        sceneManager = new SceneManager();
        mainScreen = new MainScreen(dataManager, sceneManager, this);
        sceneManager.changeScene(mainScreen);
        icon = loadImage(System.getProperty("user.dir") + "/data/images/icon.png");
        surface.setIcon(icon);
    }

    @Override
    public void draw() {
        background(255);
        sceneManager.loop();
    }

    @Override
    public void mousePressed() {
        sceneManager.handleMousePressed();
    }

    @Override
    public void mouseDragged() {
        sceneManager.handleMouseDragged();
    }

    @Override
    public void mouseClicked() {
        sceneManager.handleMouseClicked();
    }

    @Override
    public void keyPressed() {
        sceneManager.handleKeyPressed();
    }

    public static void main(String[] args) {
        PApplet.main("RehaBrain");
    }
}