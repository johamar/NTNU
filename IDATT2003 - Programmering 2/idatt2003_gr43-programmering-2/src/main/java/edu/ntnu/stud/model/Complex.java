package edu.ntnu.stud.model;

/**
 * This class represents a complex number in form of a 2D vector.
 * Extends the Vector2D class.
 * Goal: act as a model for a complex number.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.1
 */
public class Complex extends Vector2D {
  /**
   * Constructs a new Complex object with the given real and imaginary parts.
   *
   * @param realPart      the real part of the complex number
   * @param imaginaryPart the imaginary part of the complex number
   */
  public Complex(double realPart, double imaginaryPart) {
    super(realPart, imaginaryPart);
  }

  /**
   * Calculates the square root of the complex number.
   *
   * @return a new complex number representing the square root of this complex number
   */
  public Complex sqrt() {
    double r = Math.sqrt((Math.sqrt(getX0() * getX0() + getX1() * getX1()) + getX0()) / 2);
    double i = Math.signum(getX1()) * Math.sqrt(
        (Math.sqrt(getX0() * getX0() + getX1() * getX1()) - getX0()) / 2);
    return new Complex(r, i);
  }
}
