package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;
import processing.core.PVector;
import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Button class.
 */
public class ButtonTest {

  private Button button;
  private PApplet applet;

  /**
   * Initialises button and PApplet object before each test.
   */
  @BeforeEach
  public void setup() {
    applet = new PApplet();
    button = new Button("Test Button", new PVector(100, 100), Color.BLUE, Color.WHITE, Color.GREEN, applet);
  }

  @Test
  public void testButtonConstructor() {
    assertEquals("Test Button", button.getLabel());
    assertEquals(new PVector(100, 100), button.getPosition());
    assertEquals(100, button.getWidth());
    assertEquals(50, button.getHeight());
    assertEquals(Color.BLUE, button.getBackgroundColor());
    assertEquals(Color.WHITE, button.getForegroundColor());
    assertEquals(Color.GREEN, button.getHoverColor());
  }

  @Test
  public void testIsClicked() {
    // simulate a click within the button bounds
    applet.mousePressed = true;
    applet.mouseX = 100;
    applet.mouseY = 100;
    assertTrue(button.isClicked());

    // simulate a click within the button bounds
    applet.mousePressed = true;
    applet.mouseX = 75;
    applet.mouseY = 75;
    assertTrue(button.isClicked());

    // simulate a click outside the button bounds
    applet.mouseX = 500;
    assertFalse(button.isClicked());

    // simulate a click outside the button bounds
    applet.mouseX = 200;
    assertFalse(button.isClicked());

    // simulate a click with the button bounds, but with mouse not pressed
    applet.mousePressed = false;
    assertFalse(button.isClicked());

    // test with a larger button
    Button button2 = new Button("Test Button 2", new PVector(200, 200),
        Color.RED, Color.BLACK, Color.YELLOW, applet);
    button2.setWidth(200);
    button2.setHeight(100);
    applet.mouseX = 250;
    applet.mouseY = 200;
    assertFalse(button2.isClicked());
    applet.mouseX = 300;
    assertFalse(button2.isClicked());

    // test with a smaller button
    Button button3 = new Button("Test Button 3", new PVector(300, 300),
        Color.GREEN, Color.WHITE, Color.BLUE, applet);
    button3.setWidth(50);
    button3.setHeight(25);
    applet.mouseX = 300;
    applet.mouseY = 300;
    assertFalse(button3.isClicked());
    applet.mouseX = 325;
    applet.mouseY = 312;
    assertFalse(button3.isClicked());
  }

}