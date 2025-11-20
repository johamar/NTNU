package edu.ntnu.stud.model;

import java.util.Arrays;

/**
 * This class represents a chaos game description factory.
 * Contains methods for creating different chaos game descriptions.
 * Goal: act as a factory for creating chaos game descriptions.
 *
 * @version 1.0
 * @since 0.3
 */
public class ChaosGameDescriptionFactory {

  /**
   * Constructs a new ChaosGameDescription Affine object with the
   * given minimum and maximum coordinates.
   *
   * @param minX the minimum x-coordinate
   * @param maxX the maximum x-coordinate
   * @param minY the minimum y-coordinate
   * @param maxY the maximum y-coordinate
   * @param a1 the first element of the first row of the matrix for the first transformation
   * @param a2 the second element of the first row of the matrix for the first transformation
   * @param a3 the first element of the second row of the matrix for the first transformation
   * @param a4 the second element of the second row of the matrix for the first transformation
   * @param a5 the first element of the vector for the first transformation
   * @param a6 the second element of the vector for the first transformation
   * @param b1 the first element of the first row of the matrix for the second transformation
   * @param b2 the second element of the first row of the matrix for the second transformation
   * @param b3 the first element of the second row of the matrix for the second transformation
   * @param b4 the second element of the second row of the matrix for the second transformation
   * @param b5 the first element of the vector for the second transformation
   * @param b6 the second element of the vector for the second transformation
   * @param c1 the first element of the first row of the matrix for the third transformation
   * @param c2 the second element of the first row of the matrix for the third transformation
   * @param c3 the first element of the second row of the matrix for the third transformation
   * @param c4 the second element of the second row of the matrix for the third transformation
   * @param c5 the first element of the vector for the third transformation
   * @param c6 the second element of the vector for the third transformation
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription affineTransformCustom(
      double minX, double maxX, double minY, double maxY,
      double a1, double a2, double a3, double a4, double a5,
      double a6, double b1, double b2, double b3, double b4,
      double b5, double b6, double c1, double c2, double c3,
      double c4, double c5, double c6) {
    Vector2D minCoords = new Vector2D(minX, maxX);
    Vector2D maxCoords = new Vector2D(minY, maxY);
    Matrix2x2 matrixA = new Matrix2x2(a1, a2, a3, a4);
    Vector2D vectorA = new Vector2D(a5, a6);
    Matrix2x2 matrixB = new Matrix2x2(b1, b2, b3, b4);
    Vector2D vectorB = new Vector2D(b5, b6);
    Matrix2x2 matrixC = new Matrix2x2(c1, c2, c3, c4);
    Vector2D vectorC = new Vector2D(c5, c6);
    AffineTransform2D transformA = new AffineTransform2D(matrixA, vectorA);
    AffineTransform2D transformB = new AffineTransform2D(matrixB, vectorB);
    AffineTransform2D transformC = new AffineTransform2D(matrixC, vectorC);
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transformA, transformB, transformC));
  }

  /**
   * Constructs a new ChaosGameDescription Julia set object with the
   * given minimum and maximum coordinates.
   *
   * @param minX the minimum x-coordinate
   * @param maxX the maximum x-coordinate
   * @param minY the minimum y-coordinate
   * @param maxY the maximum y-coordinate
   * @param real the real part of the complex number
   * @param imaginary the imaginary part of the complex number
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription juliaSetCustom(
      double minX, double maxX, double minY, double maxY,
      double real, double imaginary) {
    Vector2D minCoords = new Vector2D(minX, maxX);
    Vector2D maxCoords = new Vector2D(minY, maxY);
    Complex c = new Complex(real, imaginary);
    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   * The chaos game description is for the Sierpinski triangle.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription sierpinskiTriangle() {
    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    AffineTransform2D transform1 = new AffineTransform2D(
        new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0));
    AffineTransform2D transform2 = new AffineTransform2D(
        new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.25, 0.5));
    AffineTransform2D transform3 = new AffineTransform2D(
        new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0.5, 0));
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2, transform3));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   * The chaos game description is for the Barnsley fern.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription barnsleyFern() {
    Vector2D minCoords = new Vector2D(-2.1820, 0);
    Vector2D maxCoords = new Vector2D(2.6558, 9.9983);
    AffineTransform2D transform1 = new AffineTransform2D(
        new Matrix2x2(0, 0, 0, 0.16), new Vector2D(0, 0));
    AffineTransform2D transform2 = new AffineTransform2D(
        new Matrix2x2(0.85, 0.04, -0.04, 0.85), new Vector2D(0, 1.60));
    AffineTransform2D transform3 = new AffineTransform2D(
        new Matrix2x2(0.20, -0.26, 0.23, 0.22), new Vector2D(0, 1.60));
    AffineTransform2D transform4 = new AffineTransform2D(
        new Matrix2x2(-0.15, 0.28, 0.26, 0.24), new Vector2D(0, 0.44));
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2, transform3, transform4));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription kochCurve() {
    Vector2D minCoords = new Vector2D(-0.5, -0.5);
    Vector2D maxCoords = new Vector2D(1.5, 1.5);
    AffineTransform2D transform1 = new AffineTransform2D(
        new Matrix2x2(0.3333, 0, 0, 0.3333), new Vector2D(0, 0));
    AffineTransform2D transform2 = new AffineTransform2D(
        new Matrix2x2(0.3333, 0, 0, 0.3333), new Vector2D(0.3333, 0));
    AffineTransform2D transform3 = new AffineTransform2D(
        new Matrix2x2(0.16667, 0.288675, -0.288675, 0.16667), new Vector2D(0.5, 0.288675));
    AffineTransform2D transform4 = new AffineTransform2D(
        new Matrix2x2(0.3333, 0, 0, 0.3333), new Vector2D(0.66667, 0));
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2, transform3, transform4));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription dragonCurve() {
    Vector2D minCoords = new Vector2D(-1, -1);
    Vector2D maxCoords = new Vector2D(1, 1);
    AffineTransform2D transform1 = new AffineTransform2D(
        new Matrix2x2(0.5, -0.5, 0.5, 0.5), new Vector2D(0, 0));
    AffineTransform2D transform2 = new AffineTransform2D(
        new Matrix2x2(0.5, 0.5, -0.5, 0.5), new Vector2D(0.5, 0));
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription juliaSet1() {
    Complex c = new Complex(-0.4, 0.6);
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription juliaSet2() {
    Complex c = new Complex(-0.8, 0.156);
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2));
  }

  /**
   *  Constructs a new ChaosGameDescription object with the
   *  given minimum and maximum coordinates.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription juliaSet3() {
    Complex c = new Complex(-0.70176, -0.3842);
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription juliaSet4() {
    Complex c = new Complex(-0.835, -0.2321);
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2));
  }

  /**
   * Constructs a new ChaosGameDescription object with the
   * given minimum and maximum coordinates.
   *
   * @return a new ChaosGameDescription object
   */
  public static ChaosGameDescription juliaSet5() {
    Complex c = new Complex(-.74543, .11301);
    Vector2D minCoords = new Vector2D(-1.6, -1);
    Vector2D maxCoords = new Vector2D(1.6, 1);
    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);
    return new ChaosGameDescription(minCoords, maxCoords,
        Arrays.asList(transform1, transform2));
  }
}
