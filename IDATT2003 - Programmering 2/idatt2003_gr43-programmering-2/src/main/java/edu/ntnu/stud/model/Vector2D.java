package edu.ntnu.stud.model;

/**
 * This class represents a 2D vector.
 * Contains a constructor for creating a new Vector2D object,
 * and methods for getting and setting the components of the vector,
 * as well as adding and subtracting two vectors.
 * Goal: act as a model for a 2D vector.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.1
 */
public class Vector2D {
  /**
   * The components of the vector.
   */
  private double x0;
  private double x1;

  /**
   * Constructs a new Vector2D object with the given components.
   *
   * @param x0 the first component of the vector
   * @param x1 the second component of the vector
   */
  public Vector2D(double x0, double x1) {
    this.x0 = x0;
    this.x1 = x1;
  }

  /**
   * Returns the first component of the vector.
   *
   * @return the first component of the vector
   */
  public double getX0() {
    return x0;
  }

  /**
   * Sets the first component of the vector.
   *
   * @param x0 the new value of the first component of the vector
   */
  public void setX0(double x0) {
    this.x0 = x0;
  }

  /**
   * Returns the second component of the vector.
   *
   * @return the second component of the vector
   */
  public double getX1() {
    return x1;
  }

  /**
   * Sets the second component of the vector.
   *
   * @param x1 the new value of the second component of the vector
   */
  public void setX1(double x1) {
    this.x1 = x1;
  }

  /**
   * Adds the given vector to this vector.
   *
   * @param v the vector to be added
   * @return a new vector representing the sum of the two vectors
   */
  public Vector2D add(Vector2D v) {
    return new Vector2D(x0 + v.x0, x1 + v.x1);
  }

  /**
   * Subtracts the given vector from this vector.
   *
   * @param v the vector to be subtracted
   * @return a new vector representing the difference of the two vectors
   */
  public Vector2D subtract(Vector2D v) {
    return new Vector2D(x0 - v.x0, x1 - v.x1);
  }


  /**
   * Returns a string representation of the vector.
   * The string representation is in the format "x0, x1".
   *
   * @return a string representation of the vector
   */
  @Override
  public String toString() {
    return x0 + ", " + x1;
  }

  /**
   * Returns a hash code value for the vector.
   *
   * @param obj the object to be compared
   * @return true if the object is equal to this vector, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Vector2D vector2D = (Vector2D) obj;
    return Double.compare(vector2D.x0, x0) == 0
        && Double.compare(vector2D.x1, x1) == 0;
  }
}