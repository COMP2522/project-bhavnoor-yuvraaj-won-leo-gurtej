package org.bcit.comp2522.project;

import processing.core.PImage;
import processing.core.PVector;

import java.awt.*;


public class Player extends Sprite implements Comparable{

  public static Player singleInstance;

  static PImage imge;

  private static int maxSpeed;

  private static int xSpeed;

  private Player(PVector position, PVector direction, float size, float speed, int xSpeed, Color color, Window window) {
    super(position, direction, size, speed, color, window);
    this.xSpeed = xSpeed;
  }

  public static Player getInstance(PVector position, PVector direction, float size, float speed,int xSpeed, Color color, PImage img, Window window){
    if (singleInstance == null) {
      singleInstance = new Player(position, direction, size, speed, xSpeed, color, window);
    }
    imge = img;
    return singleInstance;
  }

  /**
   * compareTo method.
   * @param o the object to be compared.
   * @return positive or negative integer based on which object is larger
   */
  //@Override
  public int compareTo(Object o) {
    System.out.println(this.size - (((Sprite)o).size));
    if (o == null){
      throw new NullPointerException(); //null pointer exception if object is null
    }

    else if (!(this instanceof Sprite && o instanceof Sprite)){//if either of the objects in not an instance of sprite
      // we throw class cast exception
      throw new ClassCastException();
    }
    else if ((this.size - ((Sprite) o).size) < 0){
      System.out.println("CompareTo: " +  -1);
      return -1;
    }
    else if ((this.size - ((Sprite) o).size) == 0){
      System.out.println("CompareTo: " +  0);
      return 0;
    }
    else if (((this.size - ((Sprite) o).size) > 0)){
      System.out.println("CompareTo: " +  1);
      return 1;
    }

    throw new ClassCastException();
  }

  /**
   * equals method.
   * @param o the object to be compared
   * @return boolean t or f depending on if objects are equal
   */
  //@Override
  public boolean equals(Object o){

    if (o == null){
      throw new NullPointerException(); //null pointer exception if object is null
    }

    else if (!(this instanceof Sprite && o instanceof Sprite)){//if either of the objects in not an instance of sprite
      // we throw class cast exception
      throw new ClassCastException();
    }

    else if (!(this.size == ((Sprite)o).size)){
      return false;
    }

    return true;
  }



  @Override
  public void draw() {
    window.pushStyle();
    window.fill(this.color.getRed(), this.color.getGreen(), this.color.getBlue());
    window.circle(this.position.x, this.position.y, size);

    window.image(imge, this.position.x - this.size/2, this.position.y - this.size/2, this.size, this.size);

    window.popStyle();
  }

  //overloaded method to support future programmers and my own team
  public void fly() {
    fly(3);
  }

  public void fly(int up) { //dev should pass in how much they want player to fly upward
    if (getDirection().y > - 8)
    setDirection(getDirection().add(new PVector(0, -up, 0)));
  }


  public static int getMaxSpeed() {
    return maxSpeed;
  }

  public static void setMaxSpeed(int maxSpeed) {
    Player.maxSpeed = maxSpeed;
  }

  public void gravity() {
    if (this.getDirection().y < 40)
    setDirection(getDirection().add(new PVector(0, (float) 0.25, 0)));
  }

  public int getXSpeed() {
    return xSpeed;
  }
}




