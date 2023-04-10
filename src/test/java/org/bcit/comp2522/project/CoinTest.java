package org.bcit.comp2522.project;

import org.junit.jupiter.api.Test;
import processing.core.PVector;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Coin class.
 */
class CoinTest {

  @Test
  void testCollided() {
    // Create a new Coin object with a position of (0, 0) and size of 10
    Coin coin = new Coin(new PVector(0, 0), 10, null);

    // Test that the coin collides with a sprite at the same position and size
    assertTrue(coin.collided(new Sprite(new PVector(0, 0), new PVector(0, 0),
        10, 0, Color.WHITE, null)));

    // Test that the coin does not collide with a sprite at a position outside of its size
    assertFalse(coin.collided(new Sprite(new PVector(100, 100), new PVector(0, 0),
        10, 0, Color.WHITE, null)));
  }

}