package org.bcit.comp2522.project;

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


}
