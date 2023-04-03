package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Coin extends Sprite implements Collided {


  public Coin(PVector position, float size, Window window) {
    super(position, new PVector(0,0,0), size, 0, new Color(255, 223, 0), window);
  }

  public boolean collided(Sprite a){
    float dist = PVector.dist(a.getPosition(), this.getPosition());
    float size = this.size + a.size;
//    if (pos.x - this.position.x <= size){
//      if (pos.y - this.position.y <= size){
//        return true;
//      }
//    }

    if (dist <= size){
      return true;
    }

    return false;
  }



}

