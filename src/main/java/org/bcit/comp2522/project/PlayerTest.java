package org.bcit.comp2522.project;

//import org.testng.annotations.Test;
import org.junit.Test;
import processing.core.PImage;
import processing.core.PVector;
import java.awt.Color;

import static org.junit.Assert.*;

public class PlayerTest {

  @Test
  void testGetInstance() {
    PVector position = new PVector(50, 50);
    PVector direction = new PVector(0, 0);
    float size = 30;
    float speed = 1;
    Color color = Color.BLUE;
    PImage img = new PImage();

    Player player = Player.getInstance(position, direction, size, speed, color, img, new Window());

    assertNotNull(player);
    assertEquals(position, player.getPosition());
    assertEquals(direction, player.getDirection());
    assertEquals(size, player.getSize());
    assertEquals(speed, player.getSpeed());
    assertEquals(color, player.getColor());
    assertEquals(img, Player.imge);
  }

  @Test
  void testJumping() {
    Player player = Player.getInstance(
      new PVector(50, 50),
      new PVector(0, 0),
      30,
      1,
      Color.BLUE,
      new PImage(),
      new Window());

    assertFalse(player.isJumping());
    player.setJumping(true);
    assertTrue(player.isJumping());
  }

  @Test
  void testJumpcount() {
    Player player = Player.getInstance(
      new PVector(50, 50),
      new PVector(0, 0),
      30,
      1,
      Color.BLUE,
      new PImage(),
      new Window());

    assertEquals(0, player.getJumpcount());
    player.setJumpcount(1);
    assertEquals(1, player.getJumpcount());
  }

  /**
   * This test ensures that the Singleton is
   * returning the same instance each time getInstance() is called.
   */
  @Test
  void testInstance() {
    Player player1 = Player.getInstance(
      new PVector(50, 50),
      new PVector(0, 0),
      30,
      1,
      Color.BLUE,
      new PImage(),
      new Window());

    Player player2 = Player.getInstance(
      new PVector(60, 60),
      new PVector(5, 5),
      40,
      2,
      Color.BLACK,
      new PImage(),
      new Window());

    assertEquals(player1, player2);
  }

  /**
   * This test ensures that the Singleton is thread-safe by
   * creating multiple threads and checking that they all get the same instance.
   */
  @Test
  public void testThreadSafety() {
    Player player1 = Player.getInstance(
      new PVector(50, 50),
      new PVector(0, 0),
      30,
      1,
      Color.BLUE,
      new PImage(),
      new Window());

    Player player2 = Player.getInstance(
      new PVector(60, 60),
      new PVector(5, 5),
      40,
      2,
      Color.BLACK,
      new PImage(),
      new Window());

    assertEquals(player1, player2); // Check that instance1 and instance2 are the same object

    // Test thread safety by creating multiple threads and checking that they all get the same instance
    Runnable r = new Runnable() {
      public void run() {
        Player player3 = Player.getInstance(new PVector(70, 70), new PVector(15, 15), 45, 3, Color.RED, new PImage(), null);
        assertTrue(player1 == player3);
      }
    };

    Thread t1 = new Thread(r);
    Thread t2 = new Thread(r);
    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      fail("Thread interrupted");
    }
  }

  @Test
  public void testEqualsWithNullObject() {
    Player player1 = Player.getInstance(
      new PVector(50, 50),
      new PVector(0, 0),
      30,
      1,
      Color.BLUE,
      new PImage(),
      new Window());

    assertThrows(NullPointerException.class, () -> player1.equals(null));
  }

  @Test
  public void testEqualsWithDifferentObjectTypes() {
    Player player1 = Player.getInstance(
      new PVector(50, 50),
      new PVector(0, 0),
      30,
      1,
      Color.BLUE,
      new PImage(),
      new Window());
    Object obj = new Object();

    assertThrows(ClassCastException.class, () -> player1.equals(obj));
  }

  @Test
  public void testEqualsWithDifferentSize() {
    Player player1 = Player.getInstance(
      new PVector(50, 50),
      new PVector(0, 0),
      30,
      1,
      Color.BLUE,
      new PImage(),
      new Window());

    Player player2 = Player.getInstance(
      new PVector(60, 60),
      new PVector(5, 5),
      40,
      2,
      Color.BLACK,
      new PImage(),
      new Window());
    assertEquals(true, player1.equals(player2));
  }

//  @Test
//  void testEqualsMethod1() {
//    Player player = null;
//    Player player2 = Player.getInstance(
//      new PVector(60, 60),
//      new PVector(5, 5),
//      40,
//      2,
//      Color.BLACK,
//      new PImage(),
//      new Window());
//
//    assertEquals(new NullPointerException(), player2.equals(player));
//  }
//
//  @Test
//  void testEqualsMethod2() {
//    Player player = null;
//
//    assertEquals(new ClassCastException(), this.equals(player));
//  }

}

