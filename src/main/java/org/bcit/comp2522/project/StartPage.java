package org.bcit.comp2522.project;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


/**
 * Start page for the game.
 * Displays a "Start" button that launches the game window.
 *
 * @author YoungWon Kim
 */
public class StartPage extends PApplet implements Drawable {
  /**
   * The Start button.
   */
  public Button startButton;
  /**
   * The Game window.
   */
  public Window gameWindow;

  /**
   * The background image for the start page.
   */
  private PImage backgroundImage;

  /**
   * The user directory for the image file.
   */

  private static String userDir;

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
  }

  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      userDir = System.getProperty("user.dir")
        + "\\src\\main\\java\\org\\bcit\\comp2522\\project\\";
    } else {
      userDir = System.getProperty("user.dir") + "/src/main/java/org/bcit/comp2522/project/";
    }
    backgroundImage = loadImage("images/bg2.png");
    this.init();
  }

  /**
   * Initializes the start button.
   */
  public void init() {
    startButton = new Button(
            "Start",
            new PVector(this.width / 2, this.height / 2),
            new Color(255, 255, 255),
            new Color(0, 0, 0),
            new Color(255, 0, 0),
            this
    );
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(backgroundImage);
    startButton.draw();
  }

  /**
   * Called whenever the mouse is pressed.
   * Launches the game window when the Start button is clicked.
   */
  @Override
  public void mousePressed() {
    if (startButton.isClicked()) {
      // Start the game window
      gameWindow = new Window();
      gameWindow.init();
      PApplet.runSketch(new String[]{"eatBubbles"}, gameWindow);
    }
  }

  /**
   * Main function drives the class.
   *
   * @param passedArgs unused
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"StartPage"};
    StartPage startPage = new StartPage();
    PApplet.runSketch(appletArgs, startPage);
  }
}