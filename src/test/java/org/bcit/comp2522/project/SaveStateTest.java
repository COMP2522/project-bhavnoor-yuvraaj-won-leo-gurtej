package org.bcit.comp2522.project;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaveStateTest {

  @Test
  public void testLoadPlayerHealth() {
    SaveState saveState = new SaveState();
    saveState.savePlayerData(100, 0);
    float expectedHealth = 100;
    float actualHealth = saveState.loadPlayerHealth();
    assertEquals(expectedHealth, actualHealth);
  }

  @Test
  public void testLoadPlayerScore() {
    SaveState saveState = new SaveState();
    saveState.savePlayerData(0, 500);
    float expectedScore = 500;
    float actualScore = saveState.loadPlayerScore();
    assertEquals(expectedScore, actualScore);
  }

  @Test
  public void testLoadPlayerDataWithNoDataSaved() {
    SaveState saveState = new SaveState();
    float expectedHealth = 0;
    float actualHealth = saveState.loadPlayerHealth();
    assertEquals(expectedHealth, actualHealth);
    float expectedScore = 0;
    float actualScore = saveState.loadPlayerScore();
    assertEquals(expectedScore, actualScore);
  }

  @Test
  public void testSaveAndLoadPlayerData() {
    // Create a new SaveState object
    SaveState saveState = new SaveState();

    // Set the player data using the savePlayerData method
    saveState.savePlayerData(100.0f, 500.0f);

    // Test that the loaded player health and score are equal to the saved values
    assertEquals(100.0f, saveState.loadPlayerHealth());
    assertEquals(500.0f, saveState.loadPlayerScore());
  }
}
