package edu.ntnu.stud.model;

/**
 * This class represents a Julia transformation.
 * Contains a constructor that takes a point and a sign as arguments,
 * and a method for transforming a 2D vector.
 * Goal: act as a model for a Julia transformation.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.1
 */
public class JuliaTransform implements Transform2D {
  /**
   * The complex point used for the transformation.
   */
  private final Complex point;
  /**
   * The sign used for the transformation (+/- 1).
   */
  private final int sign;

  /**
   * Constructs a new JuliaTransform object with the given point and sign.
   *
   * @param point the point to be used for the transformation
   * @param sign  the sign to be used for the transformation
   */
  public JuliaTransform(Complex point, int sign) {
    if (sign != 1 && sign != -1) {
      throw new IllegalArgumentException("Sign must be +1 or -1");
    }
    if (point == null) {
      throw new IllegalArgumentException("Point cannot be null");
    }
    this.point = point;
    this.sign = sign;
  }

  /**
   * Returns the point of the Julia transformation.
   *
   * @return the point of the Julia transformation
   */
  public Complex getPoint() {
    return point;
  }

  /**
   * Returns the sign of the Julia transformation.
   *
   * @return the sign of the Julia transformation
   */
  public int getSign() {
    return sign;
  }

  /**
   * Applies the Julia transformation to a point.
   * The point is first subtracted by the point,
   * and then the square root of the result is calculated.
   *
   * @param pointZ the point to be transformed
   * @return the transformed point
   */
  public Vector2D transform(Vector2D pointZ) {
    Vector2D subtracted = pointZ.subtract(this.point);
    Complex z = new Complex(subtracted.getX0(), subtracted.getX1());
    z = z.sqrt();
    return new Complex(z.getX0() * this.sign, z.getX1() * this.sign);
  }

  /**
   * Returns a string representation of the Julia transformation.
   *
   * @return a string representation of the Julia transformation
   */
  @Override
  public String toString() {
    return point + ", " + sign;
  }
}
