package org.bcit.comp2522.project;

import processing.core.PVector;

public interface Collided {
  public static boolean collided(Sprite a, Sprite b) {
    float distance = PVector.dist(a.getPosition(), b.getPosition());
    if (distance <= (a.getSize() + b.getSize())) {
      return true;
    }
    return false;
  }
}
