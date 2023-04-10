package org.bcit.comp2522.project;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import org.bson.Document;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;


/**
 * Window class, this is a general manager class to manage everything visible.
 *
 * @author Bhavnoor Saroya
 * @author Yuvraaj Chouhan
 * @author Paul Bucci - again we took some inspiration from lab02
 */
public class Window extends PApplet implements Drawable {
  /**
   * The Sprites.
   */
  private MyHashMap<Integer, Sprite> sprites;
  /**
   * The Enemies.
   */
  private MyHashMap<Integer, Sprite> enemies;

  /**
   * The New coins.
   */
  private MyHashMap<Integer, Sprite> newCoins = new MyHashMap<>();
  /**
   * The Player.
   */
  private Player player;
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

  /**
   * The Music player.
   */
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
   * The Coin count.
   */
  int coinCount;


  /**
   * The User dir.
   */
  public static String userDir;

  /**
   * Custom ThreadPool. Thats how cool we are.
   */
  CustomThreadPool threadPool;

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
      userDir = System.getProperty("user.dir")
              + "\\src\\main\\java\\org\\bcit\\comp2522\\project\\";
    } else {
      userDir = System.getProperty("user.dir")
              + "/src/main/java/org/bcit/comp2522/project/";
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
    new Thread(() -> musicPlayer = SoundHandler.getInstance()).start();
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
            minSize + 50,
            5,
            1,
            new Color(0, 255, 0),
            playerImage,
            this);

    threadPool = CustomThreadPool.getInstance(4);

    ArrayList<Sprite> en = createEnemies(numbEnemies);
    enemies.addAll(en); //add enemies that were created into enemies arraylist
    sprites.addAll(enemies);
    sprites.add(player);
    sprites.add(wall);

    saveState = new SaveState();
  }

  @Override

  public void keyPressed(KeyEvent event) {
    started = true;
    print("\nsize: " + player.getSize());
    print("\npos: " + player.position);
    int keyCode = event.getKeyCode();
    switch (keyCode) {

      case ' ', UP, 'W', 'w': {
        if (player.getDirection().y > 0) {
          player.setDirection(new PVector(player.getspeedX(), 0, 0));
        }
        player.fly();
        break;
      }
      default:
        //no default case
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

    for (Object sprite : sprites) {
      print(sprite);

      ((Sprite) ((Node) sprite).getValue()).update();
      ((Sprite) ((Node) sprite).getValue()).draw();


    }


//    new implementation starts:
    try { enemies.forEach((n) -> {
      Enemy enemy = (Enemy)(((Node)n).getValue());
      if (Collidable.collided(player, enemy)) {
        print("COLLIDED");
        if (player.compareTo(enemy) <= 0) {
          String[] appletArgs = new String[]{"gameover"};
          GameOverPage startPage = new GameOverPage(player,this);
          PApplet.runSketch(appletArgs, startPage);

          this.dispose();
          //todo add necessary save state and server calls here


            saveState.savePlayerData(0, saveState.loadPlayerScore());
            try {
              saveState.createJSON(System.getProperty("user.name"),
                      saveState.loadPlayerHealth(), saveState.loadPlayerScore());
            } catch (IOException e) {
              System.out.println(e);
              System.out.println("savetoJson failed");
            }

            SaveStateManager stateManager = new SaveStateManager();
            stateManager.push();

            System.out.println("health: " + saveState.loadPlayerHealth());
            System.out.println("score: " + saveState.loadPlayerScore());
            System.out.println("TOP PLAYERS:");
            Document[] topThreePlayers = DatabaseHandler.getInstance().getTopThreePlayersByScore();

            try {
              for (int i = 0; i < topThreePlayers.length; i++) {
                System.out.println(topThreePlayers[i].toJson());
              }
            } catch (Exception e) {
              //This is here in the event that there are less than three players
            }

            exit();
          }

          if (player.compareTo(enemy) == 1) {

            player.setSize((player.getSize() - 10));
            try {
              threadPool.submit(()->this.remove(enemy));
            } catch (InterruptedException e) {
              //because checkstyle requires the try catch
            }

            throw new RuntimeException("Only way to break a forEach inside an anonymous func");
          }
          throw new RuntimeException("Only way to break a forEach inside an anonymous func");
        }
        if (player.getPosition().x - enemy.getPosition().x > 350) {
          this.remove(enemy);
        }
      });
    } catch (Exception e) {
      System.out.println("loop broken");
    }

    try {
      newCoins.forEach((n) -> {
        Coin coin = (Coin) (((Node) n).getValue());
        if (coin.collided(player)) {
          coinCount++;
          int key = Integer.valueOf(coin.hashCode());
          new Thread(()->sprites.remove(key)).start();
        }
      });
    } catch (Exception e) {
      print("caught exeption e");
    }

    //Enemy Reger
    if (player.position.x % 1280 == 0) {
      MyHashMap<Integer, Enemy> newEnemies = new MyHashMap<>();
      newEnemies.addAll(createEnemies(numbEnemies));
      enemies.addAll(newEnemies);
      sprites.addAll(newEnemies);


      newCoins.addAll((ArrayList) new CoinGroup(
              (int) random(1, 10),
              new PVector(player.position.x + this.width,
                      random(0, this.height)), this).getCoins());


      sprites.addAll(newCoins);

      // save stats
      saveState.savePlayerData(player.getSize(), player.getPosition().x);
    }


    //gravity and y min/max limits
    if (player.position.y > this.height - player.size / 2) {
      player.position.y = this.height - player.size / 2;
      player.setDirection(new PVector(player.getspeedX(), 0, 0));
    } else {
      player.gravity();
    }

  }

  private ArrayList createEnemies(int numEnemies) {
    ArrayList<Enemy> enems = new ArrayList<>();
    for (int i = 0; i < numEnemies; i++) {
      Enemy j = new Enemy(
              new PVector(random(player.position.x + 320,
                      player.position.x + 320 + this.width),
                      random(0, this.height)),
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
   * @param args arguments from command line
   */
  public static void main(String[] args) {
    String[] appletArgs = new String[]{"eatBubbles"};
    Window eatBubbles = new Window();
    PApplet.runSketch(appletArgs, eatBubbles);
  }
}