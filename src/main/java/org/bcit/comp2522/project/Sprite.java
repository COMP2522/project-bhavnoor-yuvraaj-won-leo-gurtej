package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Sprite implements Drawable, Collided{
  protected PVector position;
  protected PVector direction;

  protected float size;
  protected float speed;
  protected Color color;
  protected Window window;


  public Sprite(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    this.position = position;
    this.direction = direction;
    this.size = size;
    this.speed = speed;
    this.window = window;
    this.color = color;
  }

  public void bounce() {
    if (this.position.x <= 0 ||
        this.position.x >= window.width ||
        this.position.y <= 0 ||
        this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
    }
  }

  public PVector getDirection() {
    return direction.copy();
  }

  public PVector getPosition() {
    return position.copy();
  }

  public void update() {
    this.bounce();
    this.position = this.getPosition().add(this.direction.copy().mult(speed));
  }

  public float getSize() {
    return size;
  }


  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.ellipse(this.position.x, this.position.y, size, size);
    window.popStyle();
  }

  public void setDirection(PVector direction) {
    this.direction = direction;
  }



  public void setSize(float size){
    this.size = size;
  }

}
