package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 * Lab-02 starter code.
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 *
 * @author paul_bucci
 *
 */
public class Window extends PApplet implements Drawable{
  ArrayList<Sprite> sprites;
  ArrayList<Sprite> enemies;
  Player player;
  PImage backgroundImage;

  private boolean started = false;
  float backgroundX = 0;
  float backgroundSpeed = 1;


  Wall wall;
  int numEnemies = 10;
  int minSize = 4;
  int maxSize = 10;



  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
    String userDir = System.getProperty("user.dir") + "\\src\\main\\java\\org\\bcit\\comp2522\\project\\";
    backgroundImage = loadImage(userDir + "blue.jpg");
  }


  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    this.init();
  }

  public void init() {

    wall = new Wall(

      new PVector(50,200),
      new PVector(0,0),
      minSize + 80,
      0,
      new Color(255,3,3),
      this);


    enemies = new ArrayList<Sprite>();
    sprites = new ArrayList<Sprite>();
    player = Player.getInstance(
      new PVector(this.width/2,this.height/2),
      new PVector(1,0),
      minSize + 10,
      0,
      new Color(0,255,0),
      this);

    //refactored player to singleton

    for (int i = 0; i < numEnemies; i++) {
      enemies.add(new Sprite(
        new PVector(random(0, this.width), random(0, this.height)),
        new PVector(random(-1, 1), random(-1,1)),
        random(minSize, maxSize),
        random(0,2),
        new Color(255, 0, 0),
        this
      ));
    }
    sprites.addAll(enemies);
    sprites.add(player);
    sprites.add(wall);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    started = true;
//    print(System.getProperty("os.name"));
    print("\nSpeed: " + player.speed);
    print("\npos: " + player.position);
    int keyCode = event.getKeyCode();
    switch( keyCode ) {
      case LEFT:
        // handle left
        player.setPosition(player.getPosition().x - 20, player.getPosition().y, player.getPosition().z);
        break;
      case RIGHT:
        // handle right
        player.setPosition(player.getPosition().x + 20, player.getPosition().y, player.getPosition().z);
        break;
      case ' ': {
        if (!player.isJumping()){
          player.setPosition(player.getPosition().x, player.getPosition().y - 100, player.getPosition().z);
          player.setJumping(true);
        }
        break;
      }


    }

  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {
      // Update the background position
      backgroundX -= backgroundSpeed;

      // Draw the background image repeatedly in a loop
      float x = backgroundX;
      while (x < width) {
        image(backgroundImage, x, 0);
        x += backgroundImage.width;
      }

      // Move the camera to follow the player
      float cameraX = -player.position.x + width/2;
      translate(cameraX, 0);

      for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }

//      gravity
      if (player.isJumping()) {
      player.setPosition(player.getPosition().x, player.getPosition().y + 2, player.getPosition().z);
    }

//      minimum x movement
      if (started){
//        player.setPosition(player.getPosition().x + 2, player.getPosition().y, player.getPosition().z);
      }



    ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
    for (Sprite enemy : enemies) {
      if (Collided.collided(player, enemy)) {
        if (player.compareTo(enemy) == -1) {
          init();
        }
        if (player.compareTo(enemy) == 1) {
          toRemove.add(enemy);
          player.setSize(player.getSize() + 15); //enemy.getSize());
          print("enemy hit!");
        }
        //toRemove.add(enemy);
        //print("added to list\n");
      }
      if (Collided.collided(wall, enemy)){
        enemy.direction.rotate(this.HALF_PI);

      }
    }
    if (player.position.y > this.height - 10){
//      player.direction.rotate(this.HALF_PI);
//      player.direction.y = -(player.direction.y);
        player.position.y = this.height - 10;
        player.setJumping(false);
    }


    for (Sprite enemy : toRemove) {
      this.remove(enemy);
    }

  }









  /**
   * remove method to remove the enemy from both arraylists.
   * @param s the enemy.
   */
  public void remove(Sprite s) {
    enemies.remove(s);
    sprites.remove(s);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}