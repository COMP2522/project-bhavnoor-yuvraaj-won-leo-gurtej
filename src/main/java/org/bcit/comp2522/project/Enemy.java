package org.bcit.comp2522.project;

import static processing.core.PConstants.CLOSE;

import java.awt.Color;
import processing.core.PVector;


/**
 * The Enemy class represents an enemy sprite in the game.
 * It extends the Sprite class and implements the Comparable interface.
 */
public class Enemy extends Sprite implements Comparable {

  /**
   * The number of sides of the enemy polygon.
   */
  public int sides;

  /**
   * The radius of the enemy.
   */
  public float radius;

  /**
   * Constructs an enemy object with the specified position,
   * direction, size, speed, color, number of sides, radius and window.
   *
   * @param position  the position of the enemy as a PVector object
   * @param direction the direction of the enemy as a PVector object
   * @param size      the size of the enemy
   * @param speed     the speed of the enemy
   * @param color     the color of the enemy as a Color object
   * @param sides     the number of sides of the enemy polygon
   * @param radius    the radius of the enemy
   * @param window    the window in which
   *                  the enemy is drawn as a Window object
   */
  public Enemy(PVector position, PVector direction, float size,
               float speed, Color color, int sides, float radius, Window window) {
    super(position, direction, size, speed, color, window);
    this.sides = sides;
    this.radius = radius;
  }

  /**
   * Compares this enemy with the specified object for order.
   * Returns a negative integer, zero, or a positive integer as this enemy is less than,
   * equal to, or greater than the specified object.
   *
   * @param o the object to be compared
   * @return a negative integer, zero, or a positive integer
   *        as this enemy is less than, equal to, or greater than the specified object
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object is not an instance of Sprite
   */
  public int compareTo(Object o) {
    if (o == null) {
      throw new NullPointerException(); //null pointer exception if object is null
      //if either of the objects in not an instance of sprite
    } else if (!(this instanceof Sprite && o instanceof Sprite)) {
      // we throw class cast exception
      throw new ClassCastException();
    } else if ((this.size - ((Sprite) o).size) < 0) {
      return -1;
    } else if ((this.size - ((Sprite) o).size) == 0) {
      return 0;
    } else if (((this.size - ((Sprite) o).size) > 0)) {
      return 1;
    }

    throw new ClassCastException();
  }

  /**
   * Compares this enemy with the specified object for equality.
   *
   * @param o the object to be compared for equality with this enemy
   * @return true if the specified object is equal to this enemy, false otherwise
   * @throws NullPointerException if the specified object is null
   * @throws ClassCastException   if the specified object is not an instance of Sprite
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
   * Bounces the enemy off the top and bottom walls of the window.
   */
  @Override
  public void bounce() {
    if (this.position.y <= 0
            ||
            this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  /**
   * Draws the enemy in the window.
   */
  @Override
  public void draw() {
    window.fill(color.getRGB());
    window.stroke(0);
    window.strokeWeight(1);
    window.beginShape();
    if (sides == 3) { // draw triangle
      float x1 = position.x - size;
      float y1 = position.y + size;
      float x2 = position.x;
      float y2 = position.y - size;
      float x3 = position.x + size;
      float y3 = position.y + size;
      window.triangle(x1, y1, x2, y2, x3, y3);
    } else if (sides == 4) { // draw square
      window.square(this.position.x, this.position.y, size);
    }
    window.endShape(CLOSE);
    System.out.println("draw called on enemy!");
  }
}
