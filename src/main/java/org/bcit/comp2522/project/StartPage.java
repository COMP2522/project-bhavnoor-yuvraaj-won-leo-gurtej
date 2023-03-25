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
    this.init();
  }

  public void init() {
    startButton = new Button(
            "Start",
            new PVector(this.width / 2, this.height / 2),
            new Color(255, 255, 255),
            new Color(0, 0, 0),
            new Color(255,0,0),
            this
    );
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
      Window gameWindow = new Window();
      gameWindow.init();
      PApplet.runSketch(new String[]{"eatBubbles"}, gameWindow);
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