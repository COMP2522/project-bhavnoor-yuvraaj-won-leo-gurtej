package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


/**
 *
 * Runs the applet for the Lab-02 bouncing
 * balls starter code.
 * Based on code from Keith Peters demonstrating
 * multiple-object collision.
 *
 *
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
  int numbEnemies = 3;
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
    print("started at beginning");

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
      minSize + 40,
      0,
      new Color(0,255,0),
      this);

    //refactored player to singleton


    enemies.addAll(createEnemies(numbEnemies));//add enemies that were created into enemies arraylist
    sprites.addAll(enemies);
    sprites.add(player);
    sprites.add(wall);
  }

  @Override
  public void keyPressed(KeyEvent event) {
    started = true;
//    print(System.getProperty("os.name"));
    print("\nsize: " + player.getSize());
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
//          player.setPosition(player.getPosition().x, player.getPosition().y - 100, player.getPosition().z);
          player.setJumping(true);
          player.setJumpcount(1);
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
    textSize(16);
    textAlign(LEFT, TOP);
    fill(255);
    text("Health: " + player.getSize(), 10, 10);
    text("Score: " + player.getPosition().x, 10, 30);

      // Move the camera to follow the player
      float cameraX = -player.position.x + width/2;
      translate(cameraX, 0);

      for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }

//      gravity
      if (player.isJumping()) {
        if (player.getJumpcount() > 0){
          player.setPosition(player.getPosition().x, player.getPosition().y -(player.getSize()/2), player.getPosition().z);
          player.setJumpcount(player.getJumpcount() + 10);
        }

        if (player.getJumpcount() >= 100){
          player.setJumpcount(0);
        }
    }

//      ensure gravity movement
        player.setPosition(player.getPosition().x, player.getPosition().y + 2, player.getPosition().z);


//      minimum x movement
      if (started){
        player.setPosition(player.getPosition().x + 2, player.getPosition().y, player.getPosition().z);
      }



    ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
    for (Sprite enemy : enemies) {
      if (Collided.collided(player, enemy)) {
        print("COLLIDED");
        if (player.compareTo(enemy) <= 0) {
          print("you lost!!!!!!!");
          exit();
        }

        if (player.compareTo(enemy) == 1){
          player.setSize((player.getSize() - 1));
//          toRemove.add(enemy);
          this.remove(enemy);
          print("added enemy to remove");
          break;
        }

          break;

//        print("here");
//       if (player.compareTo(enemy) > 0) {
////        player.setSize(player.getSize() > 50 ? player.getSize(): player.getSize() - 2); //enemy.getSize()); //player should later increase by 1, size capped at 50 for now
//          player.setSize(player.getSize() -2);
//          print("enemy hit!");
//        }
//       print("end hit data");
      }
      if (Collided.collided(wall, player)){
        player.direction.rotate(this.HALF_PI);
        if (player.getPosition().y < wall.getPosition().y){
          player.setPosition(player.getPosition().x, wall.getPosition().y - player.getSize() + 25, wall.getPosition().z);
        }

      }
    }

    //regen enemies as they continue through the map
    if (player.position.x % 1280 == 0){
      ArrayList<Enemy> newEnemies = new ArrayList<Enemy>();
      newEnemies.addAll(createEnemies(numbEnemies));
      enemies.addAll(newEnemies);
      sprites.addAll(newEnemies);
    }


    if (player.position.y > this.height - 10){
//      player.direction.rotate(this.HALF_PI);
//      player.direction.y = -(player.direction.y);
        player.position.y = this.height - 10;

    }

    if (player.position.y == this.height - 10){
      player.setJumping(false);
    }


    for (Sprite enemy : toRemove) {
      this.remove(enemy);
    }

  }

  private ArrayList createEnemies(int numEnemies) {
    ArrayList<Enemy> enems = new ArrayList<Enemy>();
    for (int i = 0; i < numEnemies; i++) {
      Enemy j = new Enemy(
              new PVector(random(player.position.x + 320, player.position.x + 320  + this.width), random(0, this.height)),
              new PVector(random(-1, 1), random(-1,1)),
              10,
              random(0,2),
              new Color(255, 0, 0),
              (int)random(3, 5),
              10,
              this
      );
      enems.add(j);
    }
    numbEnemies++;
    return enems;
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