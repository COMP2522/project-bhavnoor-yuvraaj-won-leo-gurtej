package org.bcit.comp2522.project;

public class Coin implements Edible{

  private boolean isEaten;

  public Coin() {
    this.isEaten = false;
  }

  @Override
  public void eaten() {
    this.isEaten = true;
  }

  public boolean isEaten() {
    return this.isEaten;
  }

}
