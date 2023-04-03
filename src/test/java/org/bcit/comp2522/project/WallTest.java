package org.bcit.comp2522.project;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.awt.*;

public class WallTest {
  private Wall wall;

  @BeforeEach
  public void setUp() {
    PVector position = new PVector(0, 0);
    PVector direction = new PVector(1, 0);
    float size = 10;
    float speed = 0;
    Color color = Color.BLACK;
    Window window = new Window();

    wall = new Wall(position, direction, size, speed, color, window);
  }

  @Test
  public void testConstructor() {
    Assertions.assertEquals(new PVector(0, 0), wall.getPosition());
    Assertions.assertEquals(new PVector(1, 0), wall.getDirection());
    Assertions.assertEquals(10, wall.getSize());
    Assertions.assertEquals(0, wall.getSpeed());
    Assertions.assertEquals(Color.BLACK, wall.getColor());
  }


}
