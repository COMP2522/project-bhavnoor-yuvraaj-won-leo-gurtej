package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import processing.event.KeyEvent;
import java.awt.*;
import java.util.ArrayList;

public class GameOverPage extends PApplet implements Drawable {
  private Button retryButton;
  Player player;

  public GameOverPage (Player player){
    this.player = player;
  }
  public void settings() {
    size(640, 360);
  }

  public void setup() {
    retryButton = new Button(
            "Retry",
            new PVector(this.width / 2, this.height - 100),
            new Color(255, 255, 255),
            new Color(0, 0, 0),
            new Color(255,0,0),
            this
    );
  }

  public void draw() {
    background(0);
    textSize(32);
    textAlign(CENTER);
    fill(255, 255, 255);
    text("Game Over", width/2, height/3);
    text("Score: " + player.getPosition().x, width/2, height/2);
    retryButton.draw();
  }

  public void mousePressed() {
    if (retryButton.isClicked()) {
      // Restart the game
      Window gameWindow = new Window();
      gameWindow.init();
      PApplet.runSketch(new String[]{"eatBubbles"}, gameWindow);
    }
  }

  public void keyPressed(KeyEvent event) {
    // Do nothing
  }

}