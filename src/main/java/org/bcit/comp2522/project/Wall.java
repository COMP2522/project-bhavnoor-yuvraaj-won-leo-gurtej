package org.bcit.comp2522.project;

import java.awt.*;
import processing.core.PVector;

/**
 * The type Wall, used once in beginning, could be used more later.
 *
 * @author Bhavnoor Saroya
 * @author Yuvraaj Chouhan
 */
public class Wall extends Sprite {
  /**
   * Instantiates a new Wall.
   *
   * @param position  the position
   * @param direction the direction
   * @param size      the size
   * @param speed     the speed
   * @param color     the color
   * @param window    the window
   */
  /**/
  public Wall(PVector position,
              PVector direction,
              float size,
              float speed,
              Color color,
              Window window) {
    super(position, direction, size, speed, color, window);
  }

  @Override
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.square(this.position.x, this.position.y, size);
    window.popStyle();
  }


}


