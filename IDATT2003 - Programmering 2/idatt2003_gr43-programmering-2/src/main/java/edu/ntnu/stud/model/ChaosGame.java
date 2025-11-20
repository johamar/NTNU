package edu.ntnu.stud.model;

import java.util.Random;

/**
 * This class represents a chaos game.
 * Contains a constructor for creating a new ChaosGame object,
 * and a method for running a number of steps in the game.
 * Goal: act as a model for a chaos game.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.1
 */

public class ChaosGame {

  /**
   * The canvas used for drawing the chaos game.
   */
  private final ChaosCanvas canvas;
  /**
   * The description of the chaos game.
   */
  private final ChaosGameDescription description;
  /**
   * The random number generator used for the chaos game.
   */
  Random random;
  /**
   * The current point in the chaos game.
   */
  private Vector2D currentPoint;

  /**
   * Constructs a new ChaosGame object with the given description, width, and height.
   *
   * @param description the description of the chaos game
   * @param width       the width of the canvas
   * @param height      the height of the canvas
   */
  public ChaosGame(ChaosGameDescription description,
                   int width,
                   int height) {
    this.description = description;
    canvas = new ChaosCanvas(width, height, description.getMinCoords(), description.getMaxCoords());
    currentPoint = new Vector2D(0, 0);
    random = new Random();
  }

  /**
   * Returns the canvas used for the chaos game.
   *
   * @return the canvas used for the chaos game
   */
  public ChaosCanvas getCanvas() {
    return canvas;
  }

  /**
   * Returns the description of the chaos game.
   *
   * @return the description of the chaos game
   */
  public ChaosGameDescription getDescription() {
    return description;
  }

  /**
   * Runs the chaos game for the given number of steps.
   *
   * @param steps the number of steps to run the chaos game for
   */
  public void runSteps(int steps) {
    for (int i = 0; i < steps; i++) {
      int index = random.nextInt(description.getTransforms().size());
      Transform2D transform = description.getTransforms().get(index);
      currentPoint = transform.transform(currentPoint);
      canvas.putPixel(currentPoint);
    }
  }
}
