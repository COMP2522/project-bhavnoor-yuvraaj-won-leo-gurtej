package org.bcit.comp2522.project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

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

    // 'write' function calls
    writeInPlayerStats(playerStats);
    writeInGameLogs(playerStats);



//    try {
//      // read existing data from file
//      JSONArray existingData;
//      try (FileReader fileReader = new FileReader("player-stats.josn")) {
//        existingData = (JSONArray) new JSONParser().parse(fileReader);
//      } catch (ParseException | FileNotFoundException e) {
//        existingData = new JSONArray();
//      }
//
//      // add the new object to existing data array
//      existingData.add(playerStats);
//
//      // write the updated array to JSON file
//      File jsonFile = new File("player-stats.json");
//
////      try (FileWriter fileWriter = new FileWriter("player-stats.json", true)) {
////        fileWriter.write(existingData.toJSONString());
////      }
////    } catch (IOException | ClassCastException e) {
////      e.printStackTrace();
////    }
//
//      FileWriter fileWriter = new FileWriter(jsonFile, true);
//      if (jsonFile.length() > 0) { // file already exists and has content
//        fileWriter.write(",\n"); // add comma to separate objects
//      }
//      fileWriter.write(existingData.toJSONString());
//      fileWriter.write("\n");
//      //fileWriter.write(playerStats.toJSONString());
//      fileWriter.flush();
//      fileWriter.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
  }

  public void writeInPlayerStats(JSONObject playerStats){
    try {
      // write to JSON file
      File jsonFile = new File("player-stats.json");
      FileWriter fileWriter = new FileWriter(jsonFile); // append flag set to false

      fileWriter.write(playerStats.toJSONString());
      fileWriter.write("\n"); // add newline character to separate objects
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void writeInGameLogs(JSONObject playerStats) {

    try {
      // read existing data from file
      JSONArray existingData;
      try (FileReader fileReader = new FileReader("game-logs.json")) {
        existingData = (JSONArray) new JSONParser().parse(fileReader);
      } catch (ParseException | FileNotFoundException e) {
        existingData = new JSONArray();
      }

      // add the new object to existing data array
      existingData.add(playerStats);

      // write the updated array to JSON file
      File jsonFile = new File("game-logs.json");

      FileWriter fileWriter = new FileWriter(jsonFile, false); // overwrite the file
      fileWriter.write(existingData.toJSONString() + "\n");
      fileWriter.write("\n");
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException | ClassCastException e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) throws IOException {
    SaveState saveState = new SaveState();
    saveState.createJSON("Paul1", 0, 1000);
    saveState.createJSON("Paul2", 0, 1000);
    saveState.createJSON("Paul3", 0, 1000);
    saveState.createJSON("Paul4", 0, 1000);

  }


}
