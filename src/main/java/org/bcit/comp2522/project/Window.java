package org.bcit.comp2522.project;

import org.bson.Document;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


/**
 * Window class, this is a general manager class to manage everything visible.
 * @author Bhavnoor Saroya
 * @author Yuvraaj Chouhan
 *
 */
public class Window extends PApplet implements Drawable {
  /**
   * The Sprites.
   */
  MyHashMap<Integer, Sprite> sprites;
  /**
   * The Enemies.
   */
  MyHashMap<Integer, Sprite> enemies;

  /**
   * The New coins.
   */
  MyHashMap<Integer, Sprite> newCoins = new MyHashMap<>();
  /**
   * The Player.
   */
  Player player;
  /**
   * The Background image.
   */
  PImage backgroundImage;
  /**
   * The Player image.
   */
  PImage playerImage;
  /**
   * The Save state.
   */
  SaveState saveState;

  SoundHandler musicPlayer;

  private boolean started = false;
  /**
   * The Background x.
   */
  float backgroundX = 0;
  /**
   * The Background speed.
   */
  float backgroundSpeed = 1;


  /**
   * The Wall.
   */
  Wall wall;
  /**
   * The Numb enemies.
   */
  int numbEnemies = 3;
  /**
   * The Min size.
   */
  int minSize = 4;
  /**
   * The Max size.
   */
  int maxSize = 10;

  /**
   * The Coin count.
   */
  int coinCount;


  /**
   * The User dir.
   */
  public static String userDir;

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
  }


  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      userDir = System.getProperty("user.dir") + "\\src\\main\\java\\org\\bcit\\comp2522\\project\\";
    } else {
      userDir = System.getProperty("user.dir") + "/src/main/java/org/bcit/comp2522/project/";
    }
    backgroundImage = loadImage("images/background.jpeg");
    playerImage = loadImage("images/topG.png"); //added this for player to be an image.
    this.init();
  }

  /**
   * Init.
   */
  public void init() {
    print("started at beginning");
    new Thread(()->musicPlayer = SoundHandler.getInstance()).start();
//    musicPlayer = SoundHandler.getInstance();
    wall = new Wall(

            new PVector(50, 200),
            new PVector(0, 0),
            minSize + 80,
            0,
            new Color(255, 3, 3),
            this);


    enemies = new MyHashMap<>();
    sprites = new MyHashMap<>();

    
    player = Player.getInstance(
            new PVector(this.width / 2, this.height / 2),
            new PVector(1, 0),
            minSize + 50 ,
            5,
            1,
            new Color(0, 255, 0),
            playerImage,
            this);



    ArrayList<Sprite> en = createEnemies(numbEnemies);
    enemies.addAll(en);//add enemies that were created into enemies arraylist
    sprites.addAll(enemies);
    sprites.add(player);
    sprites.add(wall);

    saveState = new SaveState();
  }

  @Override //todo improve the code of this function

  public void keyPressed(KeyEvent event) {
    started = true;
    print("\nsize: " + player.getSize());
    print("\npos: " + player.position);
    int keyCode = event.getKeyCode();
    switch (keyCode) {

      case ' ', UP, 'W', 'w': {
        if (player.getDirection().y > 0)
            player.setDirection(new PVector(player.getspeedX(), 0, 0));
        player.fly();
        break;
      }
    }

  }


  /**
   * Called on every frame. Updates scene object
   * state and redraws the scene. Drawings appear
   * in order of function calls.
   */
  @SuppressWarnings("unchecked")
  public void draw() {

    // Update the background position
    backgroundX -= backgroundSpeed;

    // Draw the background image repeatedly in a loop
    float x = backgroundX;
    while (x < width) {
      image(backgroundImage, x, 0);
      x += backgroundImage.width;
    }
    textSize(16);
    textAlign(LEFT, TOP);
    fill(255);
    text("Health: " + player.getSize(), 10, 10);
    text("Score: " + player.getPosition().x, 10, 30);
    text("Coins: " + coinCount, 10, 50);

    // Move the camera to follow the player
    float cameraX = -player.position.x + width / 2;
    translate(cameraX, 0);

//    New implementation using custom hashmap class, because I am a badass
    for (Object sprite : sprites) {
      if (sprite != null){
      }
      print(sprite);
      try{
        ((Sprite)((Node)sprite).getValue()).update();
        ((Sprite)((Node)sprite).getValue()).draw();
      }catch (Exception e){
      }

    }

//    new implementation starts:
    try { enemies.forEach((n) -> {
      Enemy enemy = (Enemy)(((Node)n).getValue());
      if (Collidable.collided(player, enemy)) {
        print("COLLIDED");
        if (player.compareTo(enemy) <= 0) {
          this.dispose();
          //todo add necessary save state and server calls here


          saveState.savePlayerData(0, saveState.loadPlayerScore());
          try {
            saveState.createJSON(System.getProperty("user.name"), saveState.loadPlayerHealth(), saveState.loadPlayerScore());
          } catch (IOException e) {
            System.out.println(e);
            System.out.println("savetoJson failed");
          }

          SaveStateManager stateManager = new SaveStateManager();
          stateManager.push();

          System.out.println("health: " + saveState.loadPlayerHealth());
          System.out.println("score: "  + saveState.loadPlayerScore());
          System.out.println("TOP PLAYERS:");
          Document[] topThreePlayers = DatabaseHandler.getInstance().getTopThreePlayersByScore();

          try {
            for (int i=0; i<topThreePlayers.length; i++) {
              System.out.println(topThreePlayers[i].toJson());
            }
          } catch (Exception e){
            //This is here in the even that there are less than three players
          }


          System.out.println("about to stop");
          exit();
          System.out.println("broken how tf");
        }

        if (player.compareTo(enemy) == 1) {
          player.setSize((player.getSize() - 10));
          this.remove(enemy);
          print("added enemy to remove");
          throw new RuntimeException("This is how we break a homemade forEach loop");
        }
        throw new RuntimeException("This is how to break a homemade forEach loop");
      }
    });
  } catch (Exception e){
      System.out.println("loop broken");
    }

    try {  newCoins.forEach((n) -> {
      Coin coin = (Coin)(((Node)n).getValue());
      print("looping through coins\n");
//      float dist = PVector.dist(coin.getPosition(), player.getPosition());
//      float dist = PVector.dist(coin.getPosition(), player.getPosition());
      print("\nplayer:", player.position, "coin:", coin.position);
      if (coin.collided(player)){
        System.out.println("collidedcoin");
        coinCount++;
        int key =  Integer.valueOf(coin.hashCode());
        System.out.println("\nremoved obj" + newCoins.remove(key).getValue());
        sprites.remove(key);
        System.out.println("\ncalled remove on coin");
      }
    });
    } catch (Exception e){
      print("caught exeption e");
    }




    //new implementation ends

    //todo change enemy to better regen
    //regen enemies as they continue through the map
    if (player.position.x % 1280 == 0) {
      MyHashMap<Integer, Enemy> newEnemies = new MyHashMap<>();
      newEnemies.addAll(createEnemies(numbEnemies));
      enemies.addAll(newEnemies);
      sprites.addAll(newEnemies);


      newCoins.addAll((ArrayList)new CoinGroup(
              (int)random(1, 10),
              new PVector(player.position.x + this.width,
              random(0, this.height)), this).getCoins());

//      newCoins.forEach((n)-> print("coin", ((Coin)((Node)(n)).getValue())));

      sprites.addAll(newCoins);

      // save stats
      saveState.savePlayerData(player.getSize(), player.getPosition().x);
    }


    //gravity and y min/max limits
    if (player.position.y > this.height - player.size/2) {
      player.position.y = this.height - player.size/2;
      player.setDirection(new PVector(player.getspeedX(), 0, 0));
    }
    else {
      player.gravity();
    }

  }

  private ArrayList createEnemies(int numEnemies) {
    ArrayList<Enemy> enems = new ArrayList<>();
    for (int i = 0; i < numEnemies; i++) {
      Enemy j = new Enemy(
              new PVector(random(player.position.x + 320, player.position.x + 320 + this.width), random(0, this.height)),
              new PVector(random(-1, 1), random(-1, 1)),
              10,
              random(0, 2),
              new Color(255, 0, 0),
              (int) random(3, 5),
              10,
              this
      );
      enems.add(j);
    }
    numbEnemies++;
    return enems;
  }


  /**
   * remove method to remove the enemy from both arraylists.
   *
   * @param s the sprite to remove.
   */
  public void remove(Sprite s) {
    int key = Integer.valueOf(s.hashCode());
    enemies.remove(key);
    sprites.remove(key);
  }

  /**
   * Main function.
   *
   * @param passedArgs arguments from command line
   */
  public static void main(String[] passedArgs) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}