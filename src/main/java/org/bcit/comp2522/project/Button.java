package org.bcit.comp2522.project;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;
import processing.event.KeyEvent;

import java.awt.*;
import java.util.ArrayList;

public class Button implements Drawable {
  private String label;
  private PVector position;
  private int width;
  private int height;
  private Color backgroundColor;
  private Color foregroundColor;
  private Color hoverColor;
  private PApplet applet;
  private boolean isClicked;
  private boolean isHovered;

  public Button(String label, PVector position, Color backgroundColor, Color foregroundColor, Color hoverColor, PApplet applet) {
    this.label = label;
    this.position = position;
    this.width = 100;
    this.height = 50;
    this.backgroundColor = backgroundColor;
    this.foregroundColor = foregroundColor;
    this.hoverColor = hoverColor;
    this.applet = applet;
    this.isClicked = false;
    this.isHovered = false;
  }

  public void draw() {
    applet.stroke(0);
    applet.strokeWeight(1);

    if (isHovered) {
      applet.fill(hoverColor.getRGB());
    } else {
      applet.fill(backgroundColor.getRGB());
    }

    applet.rectMode(PConstants.CENTER);
    applet.rect(position.x, position.y, width, height, 10);
    applet.fill(foregroundColor.getRGB());
    applet.textAlign(PConstants.CENTER, PConstants.CENTER);
    applet.textSize(18);
    applet.text(label, position.x, position.y);
  }

  public boolean isClicked() {
    if (applet.mousePressed) {
      float x = applet.mouseX;
      float y = applet.mouseY;
      if (x >= position.x - width / 2 && x <= position.x + width / 2 &&
              y >= position.y - height / 2 && y <= position.y + height / 2) {
        isClicked = true;
      } else {
        isClicked = false;
      }
    }
    else {
      isClicked = false;
    }

    if (applet.mouseX >= position.x - width / 2 && applet.mouseX <= position.x + width / 2 &&
            applet.mouseY >= position.y - height / 2 && applet.mouseY <= position.y + height / 2) {
      isHovered = true;
    } else {
      isHovered = false;
    }

    return isClicked;
  }
}
