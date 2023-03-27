package org.bcit.comp2522.project;

import processing.core.PApplet;
import processing.core.PVector;
import java.awt.*;

public class GameOver extends PApplet implements Drawable {

    private Button retryButton;
    private Player player;

    public GameOver(Player player) {
        this.player = player;
    }

    public void init() {
        retryButton = new Button(
                "Retry",
                new PVector(this.width / 2, this.height * 3 / 4),
                new Color(255, 255, 255),
                new Color(0, 0, 0),
                new Color(255, 0, 0),
                this
        );
    }

    public void settings() {
        size(640, 360);
    }

    public void setup() {
        this.init();
    }

    public void draw() {
        background(0);
        textAlign(CENTER, CENTER);
        textSize(32);
        fill(255);
        text("GAME OVER", width/2, height/4);
        textSize(24);
        text("Your score: " + player.getPosition().x, width/2, height/2);
        retryButton.draw();
    }

    public void mousePressed() {
        if (retryButton.isClicked()) {
            // Restart the game
            String[] appletArgs = new String[]{"eatBubbles"};
            Window eatBubbles = new Window();
            eatBubbles.init();
            PApplet.runSketch(appletArgs, eatBubbles);
            this.dispose();
        }
    }

    public void keyPressed() {
        // Do nothing
    }

}
