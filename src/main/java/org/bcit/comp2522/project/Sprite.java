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

  //todo change 0 to player radius
  public void bounce() {
    if (this.position.y <= 0) {
      this.position.y = 0;
    } else if ( this.position.y >= window.height) {
      this.position.y = window.height;

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
    System.out.println("update called!");
  }

  public float getSize() {
    return size;
  }

  public void setPosition(float x, float y, float z) {
    this.position = new PVector(x, y, z);
  }

  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.circle(this.position.x, this.position.y, size);
    window.popStyle();
    System.out.println("draw called and ran!");
  }

  public void setDirection(PVector direction) {
    System.out.println(direction);
    this.direction = direction;
  }



  public void setSize(float size){
    this.size = size;
  }


}
