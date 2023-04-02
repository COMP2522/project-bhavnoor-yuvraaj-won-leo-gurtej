package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;


/**
 *
 */
public class Window extends PApplet implements Drawable {
  MyHashMap<Integer, Sprite> sprites;
  MyHashMap<Integer, Sprite> enemies;
  Player player;
  PImage backgroundImage;
  PImage playerImage; // image for player
  SaveState saveState;

  private boolean started = false;
  float backgroundX = 0;
  float backgroundSpeed = 1;


  Wall wall;
  int numbEnemies = 3;
  int minSize = 4;
  int maxSize = 10;

  String userDir;

  /**
   * Called once at the beginning of the program.
   */
  public void settings() {
    size(640, 360);
    if (System.getProperty("os.name").toLowerCase().startsWith("windows")) {
      userDir = System.getProperty("user.dir") + "\\src\\main\\java\\org\\bcit\\comp2522\\project\\";
    } else {
      userDir = System.getProperty("user.dir") + "/src/main/java/org/bcit/comp2522/project/";
    }
    backgroundImage = loadImage(userDir + "background.jpeg");

  }


  /**
   * Called once at the beginning of the program.
   * Initializes all objects.
   */
  public void setup() {
    this.init();
  }

  public void init() {
    print("started at beginning");

    wall = new Wall(

            new PVector(50, 200),
            new PVector(0, 0),
            minSize + 80,
            0,
            new Color(255, 3, 3),
            this);


    enemies = new MyHashMap<Integer, Sprite>();
    sprites = new MyHashMap<Integer, Sprite>();

    playerImage = loadImage(userDir + "topG.png"); //added this for player to be an image.
    player = Player.getInstance(
            new PVector(this.width / 2, this.height / 2),
            new PVector(1, 0),
            minSize + 50 ,
            1,
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
            player.setDirection(new PVector(player.getXSpeed(), 0, 0));
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

    // Move the camera to follow the player
    float cameraX = -player.position.x + width / 2;
    translate(cameraX, 0);

//    New implementation using custom hashmap class, because I am a badass
    for (Object sprite : sprites) {
      if (sprite != null){
        print("\nsprite NOT null\n");
      }
      print(sprite);
      try{
        ((Sprite)((Node)sprite).getValue()).update();
        ((Sprite)((Node)sprite).getValue()).draw();
      }catch (Exception e){
        print("\nsprite null\n");
      }

    }

////    try {
//      sprites.forEach((n) -> {
//        //cast each object to sprite and then call update and draw methods
////      n.hashCode();
//        print(n);
//        ((Sprite)(((Node)n).getValue())).update();
//        ((Sprite)(((Node)n).getValue())).draw();
//        System.out.println("executed once");
//      });
////    } catch (Exception e){
////      System.out.println("caught exception");
////    }



// todo check if this is still being used, if not remove it

//  proper x movement
//      minimum x movement
//    if (started) {
//      player.setPosition(player.getPosition().x + 2, player.getPosition().y, player.getPosition().z);
//    }


    ArrayList<Sprite> toRemove = new ArrayList<Sprite>();
    //old implementation
    /*
    for (Object n : enemies) {
      if (Collided.collided(player, (Sprite) n)) {
        print("COLLIDED");
        if (player.compareTo((Sprite)n) <= 0) {
          print("you lost!!!!!!!");
          //todo add necessary save state and server calls here
          exit();
        }

        if (player.compareTo((Sprite)n) == 1) {
          player.setSize((player.getSize() - 1));
          this.remove((Sprite) n);
          print("added enemy to remove");
          break;
        }
        break;
      }

//      if (Collided.collided(wall, player)) {
//        player.direction.rotate(this.HALF_PI);
//        if (player.getPosition().y < wall.getPosition().y) {
//          player.setPosition(player.getPosition().x, wall.getPosition().y - player.getSize() + 25, wall.getPosition().z);
//        }
//      }
    } */ //end of old implementation

//
//    new implementation starts:
    try { enemies.forEach((n) -> {
      Enemy enemy = (Enemy)(((Node)n).getValue());
      if (Collided.collided(player, enemy)) {
        print("COLLIDED");
        if (player.compareTo(enemy) <= 0) {
          this.dispose();
          GameOverPage gameOverPage = new GameOverPage(player);
          String[] gameover = new String[]{"gameover"};
          PApplet.runSketch(gameover, gameOverPage);

          //todo add necessary save state and server calls here
        }

        if (player.compareTo(enemy) == 1) {
          player.setSize((player.getSize() - 1));
          this.remove(enemy);
          print("added enemy to remove");
          throw new RuntimeException("This is how we break a homemade forEach loop");
        }
        throw new RuntimeException("This is how to break a homemade forEach loop");
      }

//      if (Collided.collided(wall, player)) {
//        player.direction.rotate(this.HALF_PI);
//        if (player.getPosition().y < wall.getPosition().y) {
//          player.setPosition(player.getPosition().x, wall.getPosition().y - player.getSize() + 25, wall.getPosition().z);
//        }
//      }
    });
  } catch (Exception e){
      System.out.println("this is how we break a loop with our custom for each B)");
    }

    //new implementation ends

    //todo change enemy to better regen
    //regen enemies as they continue through the map
    if (player.position.x % 1280 == 0) {
      MyHashMap<Integer, Enemy> newEnemies = new MyHashMap<Integer, Enemy>();
      newEnemies.addAll(createEnemies(numbEnemies));
      enemies.addAll(newEnemies);
      sprites.addAll(newEnemies);

      // save stats
      saveState.savePlayerData(player.getSize(), player.getPosition().x);
    }


    //gravity and y min/max limits
    if (player.position.y > this.height - player.size/2) {
      player.position.y = this.height - player.size/2;
      player.setDirection(new PVector(player.getXSpeed(), 0, 0));
    }
    else {
      player.gravity();
    }




    for (Sprite enemy : toRemove) {
      this.remove(enemy);
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
   * @param s the enemy.
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