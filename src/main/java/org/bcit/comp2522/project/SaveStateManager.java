package org.bcit.comp2522.project;

import com.mongodb.client.MongoDatabase;

/**
 * The SaveStateManager class manages the
 * saving and loading of player data to/from a database.
 * It has methods to push the player's current data
 * to the database and pull the player's saved data from the database.
 */
public class SaveStateManager {

  /**
   * The handler for the database where the player data is saved.
   */
  DatabaseHandler databaseHandler;

  MongoDatabase mongoDatabase;

  /**
   * The current SaveState object for the player.
   */
  SaveState saveState;

  public SaveStateManager() {
    databaseHandler = new DatabaseHandler();
    mongoDatabase = databaseHandler.database;
  }

  /**
   * Pushes the player's current SaveState data to the database.
   * This method runs on a separate thread to prevent blocking the UI thread.
   */
  public void push() {
    new Thread(() -> {
      //mongo.put(saveState);
      databaseHandler.saveToDB(mongoDatabase);
    }).start();
  }

  /**
   * Retrieves the player's saved SaveState data from the database.
   * This method will be used on the start game page for the leaderboard.
   */
  public void pull() {
    //TODO: IMPLEMENT THIS METHOD TO RETRIEVE PLAYER STATS FROM DB
    // WILL BE USED ON START GAME PAGE ON THE LEADERBOARD
  }
}
