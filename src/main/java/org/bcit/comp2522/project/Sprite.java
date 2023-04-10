package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

/**
 * Sprite superclass, player, enemy, coin all inherit from sprite
 *
 * @author Yuvraaj Chouhan
 * @author Bhavnoor Saroya
 * @author Paul Bucci - we took some of it from lab02 code :)
 */
public class Sprite implements Drawable, Collidable {
  /**
   * The Position.
   */
  protected PVector position;
  /**
   * The Direction.
   */
  protected PVector direction;

  /**
   * The Size.
   */
  protected float size;
  /**
   * The Speed.
   */
  protected float speed;
  /**
   * The Color.
   */
  protected Color color;
  /**
   * The Window.
   */
  protected Window window;


  /**
   * Instantiates a new Sprite.
   *
   * @param position  the position
   * @param direction the direction
   * @param size      the size
   * @param speed     the speed
   * @param color     the color
   * @param window    the window
   */
  public Sprite(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
    this.color = color;
  }

  /**
   * Bounce.
   */
  public void bounce() {
    if (this.position.y <= 0) {
      this.position.y = 0;
    } else if (this.position.y >= window.height) {
      this.position.y = window.height;

    }
  }

  /**
   * Gets direction.
   *
   * @return the direction
   */
  public PVector getDirection() {
    return direction.copy();
  }

  /**
   * Gets position.
   *
   * @return the position
   */
  public PVector getPosition() {
    return position.copy();
  }

  /**
   * Update.
   */
  public void update() {
    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
    System.out.println("update called!");
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public float getSize() {
    return size;
  }

  /**
   * Sets position.
   *
   * @param x the x
   * @param y the y
   * @param z the z
   */
  public void setPosition(float x, float y, float z) {
    this.position = new PVector(x, y, z);
  }

  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.circle(this.position.x, this.position.y, size);
    window.popStyle();
//    System.out.println("draw called and ran!");
  }

  /**
   * Sets direction.
   *
   * @param direction the direction
   */
  public void setDirection(PVector direction) {
    System.out.println(direction);
    this.direction = direction;
  }


  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(float size) {
    this.size = size;
  }


}
