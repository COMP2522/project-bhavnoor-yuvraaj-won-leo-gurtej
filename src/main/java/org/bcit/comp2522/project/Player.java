package org.bcit.comp2522.project;

import java.awt.Color;
import processing.core.PImage;
import processing.core.PVector;

/**
 * The Player class represents a player object in a game.
 * It extends the Sprite class and implements the Comparable
 * interface.
 */
public class Player extends Sprite implements Comparable {

  /**
   * The single instance of the Player class.
   */
  public static Player singleInstance;

  /**
   * The image of the player.
   */
  static PImage imge;


  /**
   * The x speed of the player.
   */
  private static int speedX;

  /**
   * Constructs a new Player object with the specified position,
   * direction, size, speed, speedX, color, and window.
   *
   * @param position the position of the player.
   * @param direction the direction of the player.
   * @param size the size of the player.
   * @param speed the speed of the player.
   * @param speedX the x speed of the player.
   * @param color the color of the player.
   * @param window the window in which the player is displayed.
   */
  private Player(PVector position, PVector direction, float size,
                 float speed, int speedX, Color color, Window window) {
    super(position, direction, size, speed, color, window);
    this.speedX = speedX;
  }


  /**
   * Returns the single instance of the Player class with the specified
   * position, direction, size, speed, speedX,
   * color, image, and window. If the single instance is null,
   * creates a new Player object with the specified parameters.
   *
   * @param position the position of the player.
   * @param direction the direction of the player.
   * @param size the size of the player.
   * @param speed the speed of the player.
   * @param speedX the x speed of the player.
   * @param color the color of the player.
   * @param img the image of the player.
   * @param window the window in which the player is displayed.
   * @return the single instance of the Player class.
   */
  public static Player getInstance(PVector position, PVector direction, float size, float speed,
                                   int speedX, Color color, PImage img, Window window) {
    if (singleInstance == null) {
      singleInstance = new Player(position, direction, size, speed, speedX, color, window);
    }
    imge = img;
    return singleInstance;
  }

  /**
   * Compares the size of the player sprite with another object's size.
   *
   * @param o the object to be compared.
   * @return a negative integer, zero, or a positive integer as this
   *        player sprite is smaller than, equal to, or larger than the specified object.
   * @throws NullPointerException if the specified object is null.
   * @throws ClassCastException if the specified object is not an instance of Sprite.
   */
  public int compareTo(Object o) {
    System.out.println(this.size - (((Sprite) o).size));
    if (o == null) {
      throw new NullPointerException(); //null pointer exception if object is null
      //if either of the objects in not an instance of sprite
    } else if (!(this instanceof Sprite && o instanceof Sprite)) {
      // we throw class cast exception
      throw new ClassCastException();
    } else if ((this.size - ((Sprite) o).size) < 0) {
      System.out.println("CompareTo: " + -1);
      return -1;
    } else if ((this.size - ((Sprite) o).size) == 0) {
      System.out.println("CompareTo: " + 0);
      return 0;
    } else if (((this.size - ((Sprite) o).size) > 0)) {
      System.out.println("CompareTo: " + 1);
      return 1;
    }

    throw new ClassCastException();
  }

  /**
   * equals method.
   *
   * @param o the object to be compared
   * @return boolean t or f depending on if objects are equal
   * @throws NullPointerException if the object passed in is null
   * @throws ClassCastException if either this object
   *        or the object passed in is not an instance of Sprite
   */
  public boolean equals(Object o) {

    if (o == null) {
      throw new NullPointerException(); //null pointer exception if object is null
      //if either of the objects in not an instance of sprite
    } else if (!(this instanceof Sprite && o instanceof Sprite)) {
      // we throw class cast exception
      throw new ClassCastException();
    } else if (!(this.size == ((Sprite) o).size)) {
      return false;
    }

    return true;
  }

  /**
   * Draws the sprite on the screen.
   */
  @Override
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.circle(this.position.x, this.position.y, size);

    window.image(imge, this.position.x - this.size / 2,
            this.position.y - this.size / 2, this.size, this.size);

    window.popStyle();
  }


  /**
   * Makes the player fly upwards.
   */
  public void fly() {     //overloaded method to support future programmers and my own team
    fly(1);
  }


  /**
   * Makes the player fly upwards by the specified amount.
   *
   * @param up the amount to make the player fly upwards
   */
  public void fly(int up) { //dev should pass in how much they want player to fly upward
    if (getDirection().y > -5) {

      setDirection(getDirection().add(new PVector(0, -up, 0)));
    }
  }


  /**
   * Applies gravity to the player.
   */
  public void gravity() {
    if (this.getDirection().y < 35) {
      setDirection(getDirection().add(new PVector(0, (float) 0.1, 0)));
    }
  }

  /**
   * Gets the X speed.
   *
   * @return the X speed
   */
  public int getspeedX() {
    return speedX;
  }

}