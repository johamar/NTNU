package edu.ntnu.stud.model;

/**
 * This interface represents a 2D transformation.
 * It provides a contract for classes that implement a 2D transformation,
 * ensuring that they have a method for transforming a 2D vector.
 * Goal: act as a model for a 2D transformation.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.1
 */
public interface Transform2D {
  /**
   * Transforms a 2D vector.
   *
   * @param point the point to be transformed
   * @return the transformed 2D vector
   */
  Vector2D transform(Vector2D point);
}
