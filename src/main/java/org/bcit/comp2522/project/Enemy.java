package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Enemy extends Sprite implements Comparable{
  public Enemy(PVector position, PVector direction, float size, float speed, Color color, Window window) {
    super(position, direction, size, speed, color, window);
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
    else if ((this.size - ((Sprite) o).size) ==0){
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


}
