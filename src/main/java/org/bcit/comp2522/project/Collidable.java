package org.bcit.comp2522.project;

import processing.core.PVector;

/**
 * The Collided interface provides a method for checking
 * if two Sprite objects have collided with each other.
 */
public interface Collidable {

  /**
   * Checks if two Sprite objects have collided with each other.
   * @param a the first Sprite object
   * @param b the second Sprite object
   * @return true if the two Sprite objects have collided, false otherwise
   */
  public static boolean collided(Sprite a, Sprite b) {
    float distance = PVector.dist(a.getPosition(), b.getPosition());
    if (distance <= (a.getSize() + b.getSize())) {
      return true;
    }
    return false;
  }

}