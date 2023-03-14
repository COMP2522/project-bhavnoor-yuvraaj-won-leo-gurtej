package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Wall extends Sprite{
  /**/
  public Wall(PVector position, PVector direction, float size, float speed, Color color, Window window) {
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
