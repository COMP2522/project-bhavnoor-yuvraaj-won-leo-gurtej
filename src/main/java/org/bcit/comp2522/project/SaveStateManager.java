package org.bcit.comp2522.project;

/**
 * The SaveStateManager class manages the
 * saving and loading of player data to/from a database.
 * It has methods to push the player's current data
 * to the database and pull the player's saved data from the database.
 *
 * @author Gurtej Malik
 */
public class SaveStateManager {

  /**
   * The handler for the database where the player data is saved.
   */
  DatabaseHandler databaseHandler;

  /**
   * Constructor to create SaveStateManager object.
   */
  public SaveStateManager() {
  }

  /**
   * Pushes the player's current SaveState data to the database.
   * This method runs on a separate thread to prevent blocking the UI thread.
   */
  public void push() {
    databaseHandler = DatabaseHandler.getInstance();
    databaseHandler.saveToDb();
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
