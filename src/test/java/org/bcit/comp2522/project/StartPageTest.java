package org.bcit.comp2522.project;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StartPageTest {
  private StartPage startPage;

  @BeforeEach
  public void setUp() {
    startPage = new StartPage();

    startPage.init();
  }

  @Test
  public void testStartButtonNotNull() {
    assertNotNull(startPage.startButton);
  }

  @Test
  public void testStartButtonPosition() {
    int expectedX = startPage.width / 2;
    int expectedY = startPage.height / 2;
    assertEquals(expectedX, startPage.startButton.getPosition().x);
    assertEquals(expectedY, startPage.startButton.getPosition().y);
  }

  @Test
  public void testStartButtonLabel() {
    assertEquals("Start", startPage.startButton.getLabel());
  }


  @Test
  public void testMousePressedWithStartButtonNotClicked() {
    // Mock mouse coordinates outside of the button's bounding box
    startPage.mouseX = -100;
    startPage.mouseY = -100;

    startPage.mousePressed();

    assertNull(startPage.gameWindow);
  }

  @Test
  public void testMousePressedWithStartButtonClicked() {
    // Mock mouse coordinates inside the button's bounding box
    startPage.mouseX = (int) startPage.startButton.getPosition().x;
    startPage.mouseY = (int) startPage.startButton.getPosition().y;

    startPage.mousePressed();

  }

}

