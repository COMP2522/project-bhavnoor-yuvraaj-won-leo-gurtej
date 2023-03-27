package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

  private Player player;

  @BeforeEach
  public void setUp() {
    PVector position = new PVector(100, 100);
    PVector direction = new PVector(1, 0);
    float size = 50;
    float speed = 5;
    Color color = Color.RED;
    PImage playerImage = new PImage(); // create a new image
    Window window = new Window();
    player = Player.getInstance(position, direction, size, speed, color, playerImage, window); // Pass the image to the getInstance() method
  }

  @Test
  void isJumping() {
    assertFalse(player.isJumping());
    player.setJumping(true);
    assertTrue(player.isJumping());
  }

  @Test
  void getJumpcount() {
    assertEquals(0, player.getJumpcount());
    player.setJumpcount(10);
    assertEquals(10, player.getJumpcount());
  }

  @Test
  void testCompareTo() {
    // Creating two players with different sizes
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 1);
    float smallerSize = 40;
    float largerSize = 60;
    float speed = 10;
    Color color = Color.RED;
    PImage image = new PImage();
    Window window = new Window();
    Player smallerPlayer = Player.getInstance(position, direction, smallerSize, speed, color, image, window);
    Player largerPlayer = Player.getInstance(position, direction, largerSize, speed, color, image, window);


    float size = 50;
    Player player = Player.getInstance(position, direction, size, speed, color, image, window);

    assertEquals(0, player.compareTo(smallerPlayer));
    assertEquals(0, player.compareTo(player));
    assertEquals(0, player.compareTo(largerPlayer));

  }


  @Test
  void testEquals() {
    // Creating a player with the same size as the player in setUp()
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 1);
    float size = 50;
    float speed = 5;
    Color color = Color.RED;
    PImage image = new PImage();
    Window window = new Window();
    Player otherPlayer = Player.getInstance(position, direction, size, speed, color, image, window);

    assertTrue(player.equals(player));
    assertTrue(player.equals(otherPlayer));
    assertThrows(NullPointerException.class, () -> {
      player.equals(null);
    });

    assertThrows(ClassCastException.class, () -> {
      player.equals("not a sprite");
    });
  }
}




