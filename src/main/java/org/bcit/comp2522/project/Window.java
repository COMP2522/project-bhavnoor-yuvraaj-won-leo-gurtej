package org.bcit.comp2522.project;

import processing.core.PApplet;
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

  Wall wall;
  int numEnemies = 0;
  int minSize = 10;
  int maxSize = 20;



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
      new PVector(0,0),
      minSize + 1,
      2,
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
    print(player.direction.x + "\n");
    print(player.position + "\n");
    int keyCode = event.getKeyCode();
    switch( keyCode ) {
      case LEFT:
        // handle left
//        player.setPosition(player.getPosition().x - 20, player.getPosition().y, player.getPosition().z);
        if (player.direction.x > -10) {
          player.direction.x -= 1;
        }
        break;
      case RIGHT:
        // handle right
//        player.setPosition(player.getPosition().x + 20, player.getPosition().y, player.getPosition().z);
//        player.direction.x +=10;
        if (player.direction.x < 10) {
          player.direction.x += 1;
        }
        break;
      default:
        player.direction.x *= 0.9;
        if (player.direction.x < 5 && player.direction.x > -5){
          player.direction.x = 0;
        }

    }

  }

  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  public void draw() {

//    grav
    if (player.position.y > 100)
    player.direction.y +=0.1;


    float cameraX = -player.position.x + width/2;
    translate(cameraX, 0);

    background(0);
    for (Sprite sprite : sprites) {
      sprite.update();
      sprite.draw();
    }


    ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
    for (Sprite enemy : enemies) {
      if (Collided.collided(player, enemy)) {
        if (player.compareTo(enemy) == -1) {
          init();
        }
        if (player.compareTo(enemy) == 1) {
          toRemove.add(enemy);
          player.setSize(player.getSize() + enemy.getSize());
        }
        //toRemove.add(enemy);
        //print("added to list\n");
      }
      if (Collided.collided(wall, enemy)){
        enemy.direction.rotate(this.HALF_PI);

      }
    }
    if (player.position.y > this.height){
//      player.direction.rotate(this.HALF_PI);
//      player.direction.y = -(player.direction.y);
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