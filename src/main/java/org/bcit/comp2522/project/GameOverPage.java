package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;

public class GameOverPage extends PApplet implements Drawable {
  private Button exit;
  Player player;
  Window window;
  PImage backgroundImage;
  String userDir;

  public GameOverPage (Player player, Window window){
    this.player = player;
    this.window = window;
  }
  public void settings() {
    size(640, 360);
  }

  public void setup() {
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      userDir = System.getProperty("user.dir") + "\\src\\main\\java\\org\\bcit\\comp2522\\project\\";
    } else {
      userDir = System.getProperty("user.dir") + "/src/main/java/org/bcit/comp2522/project/";
    }
    backgroundImage = loadImage("images/bg2.png");
    exit = new Button(
            "exit",
            new PVector(this.width / 2, this.height - 100),
            new Color(255, 255, 255),
            new Color(0, 0, 0),
            new Color(255,0,0),
            this
    );
  }

  public void draw() {
    background(backgroundImage);
    textSize(32);
    textAlign(CENTER);
    fill(255, 0, 0);
    text("Game Over", width/2, height/2-35);
    text("Score: " + player.getPosition().x, width/2, height/2);
    text("coin " + window.coinCount, width/2, height/2+35);
    exit.draw();
  }

  public void mousePressed() {
    if (exit.isClicked()) {
      // Restart the game
      exit();
    }
  }

  public Button getRetryButton() {
    return exit;
  }


  public void keyPressed(KeyEvent event) {
    // Do nothing
  }

}