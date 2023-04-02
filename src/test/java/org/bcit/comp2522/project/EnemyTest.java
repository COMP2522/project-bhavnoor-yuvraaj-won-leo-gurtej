package org.bcit.comp2522.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import processing.core.PVector;
import processing.core.PApplet;
import java.awt.Color;



class EnemyTest {

  @Test
  void compareToTest() {
    Enemy enemy1 = new Enemy(new PVector(0, 0), new PVector(1, 0), 10, 1, Color.RED, 3, 0, null);
    Enemy enemy2 = new Enemy(new PVector(0, 0), new PVector(1, 0), 5, 1, Color.RED, 3, 0, null);
    Assertions.assertTrue(enemy1.compareTo(enemy2) > 0);
  }

  @Test
  void equalsTest() {
    Enemy enemy1 = new Enemy(new PVector(0, 0), new PVector(1, 0), 10, 1, Color.RED, 3, 0, null);
    Enemy enemy2 = new Enemy(new PVector(0, 0), new PVector(1, 0), 10, 1, Color.RED, 3, 0, null);
    Assertions.assertTrue(enemy1.equals(enemy2));
  }



}

