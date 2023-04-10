package org.bcit.comp2522.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import processing.core.PVector;


/**
 * The type Coin group.
 *
 * @author Bhavnoor Saroya
 */
public class CoinGroup {

  private Coin[] coins;

  private static final int COIN_SIZE = 40;

  /**
   * The Ran.
   */
  Random ran = new Random();

  /**
   * Instantiates a new Coin group.
   *
   * @param number the number
   * @param pos    the pos
   * @param window the window
   */
  public CoinGroup(int number, PVector pos, Window window) {
    coins = new Coin[number];

    for (int i = 0; i < coins.length; i++) {
      coins[i] = new Coin(new PVector(pos.x, pos.y), COIN_SIZE, window);
      pos = pos.add(new PVector(COIN_SIZE, 0));
    }
  }

  /**
   * Gets coins.
   *
   * @return the coins
   */
  public ArrayList<Coin> getCoins() {
    for (int i = 0; i < coins.length; i++) {
      System.out.println("Coin arr:" + coins[i].position.x);
    }

    ArrayList<Coin> list = new ArrayList<>(Arrays.asList(coins));
    for (Coin coin : list) {
      System.out.println("Coin position: " + coin.position.x);
    }
    return (list);
  }


  /**
   * Instantiates a new Coin group.
   *
   * @param number the number
   * @param size   the size
   */
  public CoinGroup(int number, int size) {
    //todo overloaded constructor for custom coin size
  }
}
