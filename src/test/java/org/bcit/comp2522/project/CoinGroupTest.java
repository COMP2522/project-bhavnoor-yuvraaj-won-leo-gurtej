package org.bcit.comp2522.project;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.core.PVector;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CoinGroupTest {

  private CoinGroup coinGroup;

  @BeforeEach
  public void setUp() {
    Window window = new Window();
    PVector pos = new PVector(0, 0);
    coinGroup = new CoinGroup(5, pos, window);
  }

  @Test
  public void testGetCoins() {
    ArrayList<Coin> coins = coinGroup.getCoins();
    assertNotNull(coins);
    assertEquals(5, coins.size());

    for (Coin coin : coins) {
      assertNotNull(coin);
      assertNotNull(coin.position);
    }
  }

}
