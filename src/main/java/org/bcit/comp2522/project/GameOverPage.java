package org.bcit.comp2522.project;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

/**
 * The GameOverPage class represents the game over screen
 * that is displayed when the player loses the game.
 * It implements the Drawable interface to be drawn on the screen.
 */
public class GameOverPage extends PApplet implements Drawable {
  /**
   * A button that allows the player to exit the game.
   */
  private Button exit;

  /**
   * The player object that represents the user's character in the game.
   */
  private Player player;

  /**
   * The window object that represents the game's graphical user interface.
   */
  private Window window;


  /**
   * The background image of the game over page.
   */
  private PImage backgroundImage;

  /**
   * The file path to the directory where the game's resources are stored.
   */
  private String userDir;

  /**
   * Constructs a GameOverPage object with the given player and window.
   *
   * @param player the player object representing the player in the game
   * @param window the window object representing the game window
   */
  public GameOverPage(Player player, Window window) {
    this.player = player;
    this.window = window;
  }

  /**
   * Sets the size of the window.
   */
  public void settings() {
    size(640, 360);
  }

  /**
   * Loads the background image and creates the exit button.
   */
  public void setup() {
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      userDir = System.getProperty("user.dir")
        + "\\src\\main\\java\\org\\bcit\\comp2522\\project\\";
    } else {
      userDir = System.getProperty("user.dir") + "/src/main/java/org/bcit/comp2522/project/";
    }
    backgroundImage = loadImage("images/bg2.png");
    exit = new Button(
            "exit",
            new PVector(this.width / 2, this.height - 100),
            new Color(255, 255, 255),
            new Color(0, 0, 0),
            new Color(255, 0, 0),
            this
    );
  }

  /**
   * Draws the game over screen with the player's score and coin count,
   * as well as the exit button.
   */
  public void draw() {
    background(backgroundImage);
    textSize(32);
    textAlign(CENTER);
    fill(255, 0, 0);
    text("Game Over", width / 2, height / 2 - 35);
    text("Score: " + player.getPosition().x, width / 2, height / 2);
    text("coin " + window.coinCount, width / 2, height / 2 + 35);
    exit.draw();
  }

  /**
   * Checks if the exit button has been clicked and exits the program if it has.
   */
  public void mousePressed() {
    if (exit.isClicked()) {
      // Restart the game
      exit();
    }
  }

  /**
   * Does nothing in this class.
   *
   * @param event the KeyEvent object representing the key that was pressed
   */
  public void keyPressed(KeyEvent event) {
    // Do nothing
  }

}