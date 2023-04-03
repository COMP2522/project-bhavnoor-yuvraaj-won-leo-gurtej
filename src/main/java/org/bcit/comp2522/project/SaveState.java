package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class SaveState {

  private float health;
  private float score;

  public SaveState() {
  }

  public void savePlayerData(float health, float score) {
    this.health = health;
    this.score = score;
  }

  public float loadPlayerHealth() {
    return health;
  }

  public float loadPlayerScore() {
    return score;
  }


}
