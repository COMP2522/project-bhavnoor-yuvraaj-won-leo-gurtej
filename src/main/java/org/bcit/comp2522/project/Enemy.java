package org.bcit.comp2522.project;

import processing.core.PVector;
import java.awt.*;
import static processing.core.PConstants.CLOSE;

/**
 * Public class Enemy.
 * This is enemy class.
 */
public class Enemy extends Sprite implements Comparable {

  public int sides;
  public float radius;

  public Enemy(PVector position, PVector direction, float size, float speed, Color color, int sides, float radius, Window window) {
    super(position, direction, size, speed, color, window);
    this.sides = sides;
    this.radius = radius;
  }

  /**
   * compareTo method.
   * @param o the object to be compared.
   * @return positive or negative integer based on which object is larger
   */
  //@Override
  public int compareTo(Object o) {
    if (o == null){
      throw new NullPointerException(); //null pointer exception if object is null
    }

    else if (!(this instanceof Sprite && o instanceof Sprite)){//if either of the objects in not an instance of sprite
      // we throw class cast exception
      throw new ClassCastException();
    }
    else if ((this.size - ((Sprite) o).size) < 0){
      return -1;
    }
    else if ((this.size - ((Sprite) o).size) == 0){
      return 0;
    }
    else if (((this.size - ((Sprite) o).size) > 0)){
      return 1;
    }

    throw new ClassCastException();
  }

  /**
   * equals method.
   * @param o the object to be compared
   * @return boolean t or f depending on if objects are equal
   */
  //@Override
  public boolean equals(Object o){

    if (o == null){
      throw new NullPointerException(); //null pointer exception if object is null
    }

    else if (!(this instanceof Sprite && o instanceof Sprite)){//if either of the objects in not an instance of sprite
      // we throw class cast exception
      throw new ClassCastException();
    }

    else if (!(this.size == ((Sprite)o).size)){
      return false;
    }

    return true;
  }

  @Override
  public void bounce(){
    if (    this.position.y <= 0 ||
            this.position.y >= window.height) {
      this.direction.rotate(window.HALF_PI);
  }
  }

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
//      window.vertex(x1, y1);
//      window.vertex(x2, y2);
//      window.vertex(x3, y3);
      window.triangle(x1, y1, x2, y2, x3, y3);
    } else if (sides == 4) { // draw square
//      float x1 = position.x - size;
//      float y1 = position.y - size;
//      float x2 = position.x + size;
//      float y2 = position.y - size;
//      float x3 = position.x + size;
//      float y3 = position.y + size;
//      float x4 = position.x - size;
//      float y4 = position.y + size;
//      window.vertex(x1, y1);
//      window.vertex(x2, y2);
//      window.vertex(x3, y3);
//      window.vertex(x4, y4);
        window.square(this.position.x, this.position.y, size);
    }
    window.endShape(CLOSE);
  }
}
