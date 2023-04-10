package org.bcit.comp2522.project;

import java.awt.*;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;


/**
 * Button class.
 */
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

  /**
   * Instantiates a new Button.
   *
   * @param label           the label
   * @param position        the position
   * @param backgroundColor the background color
   * @param foregroundColor the foreground color
   * @param hoverColor      the hover color
   * @param applet          the applet
   */
  public Button(
          String label,
          PVector position,
          Color backgroundColor,
          Color foregroundColor,
          Color hoverColor,
          PApplet applet) {
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

  /**
   * Draw method.
   */
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

  /**
   * Is clicked boolean.
   *
   * @return the boolean
   */
  public boolean isClicked() {
    if (applet.mousePressed) {
      float x = applet.mouseX;
      float y = applet.mouseY;
      if (x >= position.x - width / 2 && x <= position.x + width / 2
              && y >= position.y - height / 2
              && y <= position.y + height / 2) {
        isClicked = true;
      } else {
        isClicked = false;
      }
    } else {
      isClicked = false;
    }

    if (applet.mouseX >= position.x - width / 2 && applet.mouseX <= position.x + width / 2
            && applet.mouseY >= position.y - height / 2
            && applet.mouseY <= position.y + height / 2) {
      isHovered = true;
    } else {
      isHovered = false;
    }

    return isClicked;
  }

  /**
   * Gets label.
   *
   * @return the label
   */
  public String getLabel() {
    return label;
  }

  /**
   * Gets position.
   *
   * @return the position
   */
  public PVector getPosition() {
    return position;
  }

  /**
   * Gets width.
   *
   * @return the width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Sets width.
   *
   * @param width the width
   */
  public void setWidth(int width) {
    this.width = width;
  }

  /**
   * Gets height.
   *
   * @return the height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Sets height.
   *
   * @param height the height
   */
  public void setHeight(int height) {
    this.height = height;
  }

  /**
   * Gets background color.
   *
   * @return the background color
   */
  public Color getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * Gets foreground color.
   *
   * @return the foreground color
   */
  public Color getForegroundColor() {
    return foregroundColor;
  }

  /**
   * Gets hover color.
   *
   * @return the hover color
   */
  public Color getHoverColor() {
    return hoverColor;
  }

  /**
   * Gets color.
   *
   * @return the color
   */
  public Color getColor() {
    if (isHovered) {
      return hoverColor;
    } else {
      return backgroundColor;
    }
  }

  /**
   * Gets text color.
   *
   * @return the text color
   */
  public Color getTextColor() {
    if (isHovered) {
      return hoverColor;
    } else {
      return foregroundColor;
    }
  }

}
