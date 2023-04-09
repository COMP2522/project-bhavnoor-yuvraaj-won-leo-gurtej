package org.bcit.comp2522.project;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The SaveState class represents the data saved for a player in a game.
 * It contains information about the player's health and score.
 */
public class SaveState {

  /**
   * The health of the player.
   */
  private float health;

  /**
   * The score of the player.
   */
  private float score;

  /**
   * The name of the player.
   */
  private String name;

  /**
   * Constructs a SaveState object.
   */
  public SaveState() {
  }

  /**
   * Saves the player's health and score.
   *
   * @param health the health of the player
   * @param score the score of the player
   */
  public void savePlayerData(float health, float score) {
    this.health = health;
    this.score = score;
  }

  /**
   * Returns the health of the player.
   *
   * @return the health of the player
   */
  public float loadPlayerHealth() {
    return health;
  }

  /**
   * Returns the score of the player.
   *
   * @return the score of the player
   */
  public float loadPlayerScore() {
    return score;
  }

  public void createJSON(String name, float health, float score) throws IOException {
    JSONObject playerStats = new JSONObject();
    playerStats.put("Name", name);
    playerStats.put("Score", score);
    playerStats.put("Health", health);
    System.out.println("JSON object created: " + playerStats);

    try {
      // write to JSON file
      File jsonFile = new File("player-stats.json");
      FileWriter fileWriter = new FileWriter(jsonFile, true);

      fileWriter.write(playerStats.toJSONString());
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws IOException {
    SaveState saveState = new SaveState();
    saveState.createJSON("Pauline", 0, 1000);

  }


}
