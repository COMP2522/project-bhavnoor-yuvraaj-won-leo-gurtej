package org.bcit.comp2522.project;

import processing.core.PVector;

public class Coin implements Edible{

  private boolean isEaten;
  private PVector position;
  private float size;

  public Coin(PVector position, float size) {
    this.isEaten = false;
    this.position = position;
    this.size = size;
  }

  @Override
  public void eaten() {
    this.isEaten = true;
  }

  public boolean isEaten() {
    return this.isEaten;
  }

  public boolean checkCollision(Player player) {
    float distance = PVector.dist(this.position, player.getPosition());
    return distance < (this.getSize() + player.getSize()) / 2;
  }

  public float getSize() {
    return size;
  }

  public PVector getPosition() {
    return this.position;
  }

}
