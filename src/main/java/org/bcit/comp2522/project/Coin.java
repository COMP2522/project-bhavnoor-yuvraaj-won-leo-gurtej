package org.bcit.comp2522.project;

import java.awt.*;
import processing.core.PVector;

/**
 * The type Coin.
 *
 * @author Bhavnoor Saroya
 */
public class Coin extends Sprite implements Collidable {


  /**
   * Instantiates a new Coin.
   *
   * @param position the position
   * @param size     the size
   * @param window   the window
   */
  public Coin(PVector position, float size, Window window) {
    super(position, new PVector(0, 0, 0), size, 0, new Color(255, 223, 0), window);
  }

  /**
   * Collided boolean.
   *
   * @param a the a
   * @return the boolean
   */
  public boolean collided(Sprite a) {
    float dist = PVector.dist(a.getPosition(), this.getPosition());
    float size = this.size + a.size;

    if (dist <= size) {
      return true;
    }

    return false;
  }


}

