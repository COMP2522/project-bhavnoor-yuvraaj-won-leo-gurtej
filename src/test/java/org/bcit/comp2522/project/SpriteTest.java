package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Sprite class.
 */
public class SpriteTest {
  private Window window;
  private Sprite sprite;

  /**
   * Initialises Window and Sprite objects object before each test.
   */
  @BeforeEach
  public void setup() {
    window = new Window();
    sprite = new Sprite(new PVector(0, 0), new PVector(1, 1), 10, 2, Color.BLACK, window);
  }

  @Test
  public void testGetPosition() {
    PVector position = sprite.getPosition();
    assertNotNull(position);
    assertEquals(0, position.x);
    assertEquals(0, position.y);
  }

  @Test
  public void testGetDirection() {
    PVector direction = sprite.getDirection();
    assertNotNull(direction);
    assertEquals(1, direction.x);
    assertEquals(1, direction.y);
  }

  @Test
  public void testSetPosition() {
    sprite.setPosition(10, 20, 0);
    PVector position = sprite.getPosition();
    assertNotNull(position);
    assertEquals(10, position.x);
    assertEquals(20, position.y);
  }

  @Test
  public void testSetDirection() {
    sprite.setDirection(new PVector(-1, 0));
    PVector direction = sprite.getDirection();
    assertNotNull(direction);
    assertEquals(-1, direction.x);
    assertEquals(0, direction.y);
  }

  @Test
  public void testGetSize() {
    float size = sprite.getSize();
    assertEquals(10, size);
  }

  @Test
  public void testSetSize() {
    sprite.setSize(20);
    float size = sprite.getSize();
    assertEquals(20, size);
    sprite.setSize(5);
    size = sprite.getSize();
    assertEquals(5, size);
    sprite.setSize(10);
    size = sprite.getSize();
    assertEquals(10, size);
    sprite.setSize(17);
    size = sprite.getSize();
    assertEquals(17, size);

  }

}