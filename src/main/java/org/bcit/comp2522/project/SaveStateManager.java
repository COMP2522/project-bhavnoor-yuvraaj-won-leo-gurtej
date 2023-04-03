package org.bcit.comp2522.project;

public class SaveStateManager {
  DatabaseHandler mongo;

  SaveState saveState;

  public void push() {
    new Thread(() -> {
      mongo.put(saveState);
    });
  }

  public void pull() {
    //TODO: IMPLEMENT THIS METHOD TO RETRIEVE PLAYER STATS FROM DB
    // WILL BE USED ON START GAME PAGE ON THE LEADERBOARD
  }
}
