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

    Enemy enemy3 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 3, 0, null);
    Enemy enemy4 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        5, 1, Color.RED, 3, 0, null);
    Enemy enemy5 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 4, 0, null);
    Enemy enemy6 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.BLUE, 3, 0, null);
    Assertions.assertTrue(enemy3.compareTo(enemy4) > 0);
    Assertions.assertTrue(enemy4.compareTo(enemy3) < 0);
    Assertions.assertEquals(0, enemy3.compareTo(enemy5));
    Assertions.assertEquals(0, enemy3.compareTo(enemy3));
    Assertions.assertFalse(enemy3.compareTo(enemy6) < 0);
    Assertions.assertFalse(enemy6.compareTo(enemy3) > 0);



  }

  @Test
  void equalsTest() {
    Enemy enemy1 = new Enemy(new PVector(0, 0), new PVector(1, 0), 10, 1, Color.RED, 3, 0, null);
    Enemy enemy2 = new Enemy(new PVector(0, 0), new PVector(1, 0), 10, 1, Color.RED, 3, 0, null);
    Assertions.assertTrue(enemy1.equals(enemy2));

    Enemy enemy3 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 3, 0, null);
    Enemy enemy4 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        5, 1, Color.RED, 3, 0, null);
    Enemy enemy5 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 4, 0, null);
    Enemy enemy6 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 3, 0, null);
    Enemy enemy7 = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.BLUE, 3, 0, null);
    Assertions.assertTrue(enemy3.equals(enemy6));
    Assertions.assertFalse(enemy3.equals(enemy4));
    Assertions.assertTrue(enemy3.equals(enemy5));
    Assertions.assertTrue(enemy3.equals(enemy7));
  }

  @Test
  void compareToNullTest() {
    Enemy enemy = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 3, 0, null);
    Assertions.assertThrows(NullPointerException.class, () -> enemy.compareTo(null));
  }

  @Test
  void equalsNullTest() {
    Enemy enemy = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 3, 0, null);
    Assertions.assertThrows(NullPointerException.class, () -> enemy.equals(null));
  }

  @Test
  void compareToClassCastTest() {
    Enemy enemy = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 3, 0, null);
    Assertions.assertThrows(ClassCastException.class, () -> enemy.compareTo("not a sprite"));
  }

  @Test
  void equalsClassCastTest() {
    Enemy enemy = new Enemy(new PVector(0, 0), new PVector(1, 0),
        10, 1, Color.RED, 3, 0, null);
    Assertions.assertThrows(ClassCastException.class, () -> enemy.equals("not a sprite"));
  }



}

