package edu.ntnu.stud.model;

/**
 * This class represents a 2x2 matrix.
 * Contains a constructor for creating a new Matrix2x2 object,
 * and a method for multiplying the matrix with a 2D vector.
 * Goal: act as a model for a 2x2 matrix.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.1
 */
public class Matrix2x2 {
  /**
   * The elements of the first row of the matrix.
   */
  private final double a00;
  private final double a01;
  /**
   * The elements of the second row of the matrix.
   */
  private final double a10;
  private final double a11;

  /**
   * Constructs a new Matrix2x2 object with the given elements.
   *
   * @param a00 the element in the first row and first column
   * @param a01 the element in the first row and second column
   * @param a10 the element in the second row and first column
   * @param a11 the element in the second row and second column
   */
  public Matrix2x2(double a00, double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  /**
   * Returns the element in the first row and first column of the matrix.
   *
   * @return the element in the first row and first column of the matrix
   */
  public double getA00() {
    return a00;
  }

  /**
   * Returns the element in the first row and second column of the matrix.
   *
   * @return the element in the first row and second column of the matrix
   */
  public double getA01() {
    return a01;
  }

  /**
   * Returns the element in the second row and first column of the matrix.
   *
   * @return the element in the second row and first column of the matrix
   */
  public double getA10() {
    return a10;
  }

  /**
   * Returns the element in the second row and second column of the matrix.
   *
   * @return the element in the second row and second column of the matrix
   */
  public double getA11() {
    return a11;
  }

  /**
   * Multiplies the matrix with a 2D vector.
   *
   * @param vector the vector to be multiplied with the matrix
   * @return a new 2D vector representing the result of the multiplication
   */
  public Vector2D multiply(Vector2D vector) {
    return new Vector2D(
        a00 * vector.getX0() + a01 * vector.getX1(),
        a10 * vector.getX0() + a11 * vector.getX1()
    );
  }

  /**
   * Returns a string representation of the matrix.
   * The string representation is in the format "a00, a01, a10, a11".
   *
   * @return a string representation of the matrix
   */
  @Override
  public String toString() {
    return a00 + ", " + a01 + ", " + a10 + ", " + a11;
  }
}
