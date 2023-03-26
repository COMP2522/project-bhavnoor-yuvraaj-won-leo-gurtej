package org.bcit.comp2522.project;

import processing.core.PVector;

import java.awt.*;

public class Coin extends Sprite implements Collided, Edible{


  public Coin(PVector position, float size, Window window) {
    super(position, new PVector(0,0,0), size, 0, new Color(255, 223, 0), window);
  }



}

