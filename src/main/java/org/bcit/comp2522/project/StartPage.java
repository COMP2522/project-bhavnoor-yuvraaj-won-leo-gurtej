package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;


/**
 * Start page for the game.
 * Displays a "Start" button that launches the game window.
 */
public class StartPage extends PApplet implements Drawable {
  private Button startButton;
  private Window gameWindow;

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
    startButton = new Button(
            "Start",
            new PVector(this.width / 2, this.height / 2),
            new Color(255, 255, 255),
            new Color(0, 0, 0),
            new Color(255,0,0),
            this
    );
    gameWindow = new Window();
  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
    background(0);
    startButton.draw();
  }

  @Override
  public void mousePressed() {
    if (startButton.isClicked()) {
      // Start the game window
      gameWindow.init();
      String[] appletArgs = new String[]{"eatBubbles"};
      PApplet.runSketch(appletArgs, gameWindow);
    }
  }

  @Override
  public void keyPressed(KeyEvent event) {
    // Do nothing
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"StartPage"};
    StartPage startPage = new StartPage();
    PApplet.runSketch(appletArgs, startPage);
  }
}
