package org.bcit.comp2522.project;

import processing.core.PVector;

import java.util.ArrayList;
import java.util.Arrays;

import processing.core.PApplet;

import java.util.Random;


public class CoinGroup {

  private Coin[] coins;

  private static final int COIN_SIZE = 40;

  Random ran = new Random();

  public CoinGroup(int number, PVector pos, Window window){
    coins = new Coin[number];

    for (int i = 0; i < coins.length; i++){
      coins[i] = new Coin(new PVector(pos.x, pos.y), COIN_SIZE, window);
      pos = pos.add(new PVector(COIN_SIZE, 0));
//      System.out.println(position + "vs" + coins[i].position.x);
    }
  }

  public ArrayList<Coin> getCoins(){
    for (int i =0 ; i < coins.length; i++){
      System.out.println("Coin arr:"+ coins[i].position.x);
    }

    ArrayList<Coin> list = new ArrayList<>(Arrays.asList(coins));
    for (Coin coin : list) {
      System.out.println("Coin position: " + coin.position.x);
    }
    return (list);
  }


  public CoinGroup(int number, int size){
  //todo overloaded constructor for custom coin size
  }
}
