package edu.ntnu.stud.model;

/**
 * This class represents a 2D affine transformation.
 * Contains a constructor for creating a new AffineTransform2D object,
 * and a method for transforming a 2D vector.
 * Goal: act as a model for a 2D affine transformation.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.1
 */
public class AffineTransform2D implements Transform2D {
  /**
   * The 2x2 matrix used for the linear transformation.
   */
  private final Matrix2x2 matrix;
  /**
   * The vector used for the translation.
   */
  private final Vector2D vector;

  /**
   * Constructs a new AffineTransform2D object with the given matrix and vector.
   *
   * @param matrix the 2x2 matrix to be used for the linear transformation
   * @param vector the 2D vector to be used for the translation
   */
  public AffineTransform2D(Matrix2x2 matrix, Vector2D vector) {
    this.matrix = matrix;
    this.vector = vector;
  }

  /**
   * Returns the matrix of the affine transformation.
   *
   * @return the matrix of the affine transformation
   */
  public Matrix2x2 getMatrix() {
    return matrix;
  }

  /**
   * Returns the vector of the affine transformation.
   *
   * @return the vector of the affine transformation
   */
  public Vector2D getVector() {
    return vector;
  }

  /**
   * Applies the affine transformation to a point.
   * Transforms the given point by multiplying it with the matrix and adding the vector.
   *
   * @param point the point to be transformed
   * @return the transformed point
   */
  public Vector2D transform(Vector2D point) {
    return matrix.multiply(point).add(vector);
  }

  /**
   * Returns a string representation of the affine transformation.
   *
   * @return a string representation of the affine transformation
   */
  @Override
  public String toString() {
    return matrix + ", " + vector;
  }
}
