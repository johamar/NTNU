package edu.ntnu.stud.model;

import java.util.List;

/**
 * This class represents a chaos game description.
 * Contains a constructor for creating a new ChaosGameDescription object,
 * and methods for getting the minimum and maximum coordinates,
 * as well as the list of transformations.
 * Goal: act as a model for a chaos game description.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.2
 */
public class ChaosGameDescription {

  /**
   * The minimum and maximum coordinates of the chaos game.
   */
  private final Vector2D minCoords;
  private final Vector2D maxCoords;

  /**
   * The list of transformations used for the chaos game.
   */
  private final List<Transform2D> transforms;

  /**
   * Constructs a new ChaosGameDescription object with the given minimum and maximum coordinates.
   *
   * @param minCoords  the minimum coordinates of the chaos game
   * @param maxCoords  the maximum coordinates of the chaos game
   * @param transforms the list of transformations used for the chaos game
   */
  public ChaosGameDescription(Vector2D minCoords,
                              Vector2D maxCoords,
                              List<Transform2D> transforms) {
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.transforms = transforms;
  }

  /**
   * Returns the minimum coordinates of the chaos game.
   *
   * @return the minimum coordinates of the chaos game
   */
  public Vector2D getMinCoords() {
    return minCoords;
  }

  /**
   * Returns the maximum coordinates of the chaos game.
   *
   * @return the maximum coordinates of the chaos game
   */
  public Vector2D getMaxCoords() {
    return maxCoords;
  }

  /**
   * Returns the list of transformations used for the chaos game.
   *
   * @return the list of transformations used for the chaos game
   */
  public List<Transform2D> getTransforms() {
    return transforms;
  }
}
