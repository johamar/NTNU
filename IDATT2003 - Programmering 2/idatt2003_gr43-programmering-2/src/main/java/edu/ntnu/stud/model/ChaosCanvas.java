package edu.ntnu.stud.model;

/**
 * This class represents a canvas for a chaos game.
 * Contains a constructor for creating a new ChaosCanvas object,
 * and methods for getting and setting the pixels of the canvas,
 * as well as clearing the canvas and showing it.
 * Goal: act as a model for a canvas for a chaos game.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.2
 */
public class ChaosCanvas {
  private final int[][] canvas;
  private final int width;
  private final int height;
  private final Vector2D minCoords;
  private final Vector2D maxCoords;
  private final AffineTransform2D transformCoordsToIndices;

  /**
   * Constructs a new ChaosCanvas object with the given width,
   * height, minimum coordinates, and maximum coordinates.
   *
   * @param width     the width of the canvas
   * @param height    the height of the canvas
   * @param minCoords the minimum coordinates of the canvas
   * @param maxCoords the maximum coordinates of the canvas
   */
  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
    this.width = width;
    this.height = height;
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    canvas = new int[width][height];
    transformCoordsToIndices = createIndices();
  }

  /**
   * Returns the pixel at the given point.
   *
   * @param point the point to get the pixel at
   * @return the pixel at the given point
   */
  public int getPixel(Vector2D point) {
    Vector2D indices = transformCoordsToIndices.transform(point);
    return canvas[(int) indices.getX0()][(int) indices.getX1()];
  }

  /**
   * Sets the pixel at the given point.
   *
   * @param point the point to set the pixel at
   */
  public void putPixel(Vector2D point) {
    Vector2D indices = transformCoordsToIndices.transform(point);
    int x0 = Math.abs((int) indices.getX0());
    int x1 = Math.abs((int) indices.getX1());
    if (x0 < 0 || x0 >= width || x1 < 0 || x1 >= height) {
      return;
    }
    canvas[x0][x1] = 1;
  }

  /**
   * Returns the canvas as a 2D array.
   *
   * @return the canvas as a 2D array
   */
  public int[][] getCanvasArray() {
    return canvas;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  /**
   * Clears the canvas.
   */
  public void clear() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        canvas[i][j] = 0;
      }
    }
  }

  /**
   * Creates an affine transformation for transforming coordinates to indices.
   * The transformation is returned as an AffineTransform2D object.
   *
   * @return the affine transformation for transforming coordinates to indices
   */
  public AffineTransform2D createIndices() {
    double a01 = (height - 1) / (minCoords.getX1() - maxCoords.getX1());
    double a10 = (width - 1) / (maxCoords.getX0() - minCoords.getX0());
    double x0 = ((height - 1) * maxCoords.getX1() / (maxCoords.getX1() - minCoords.getX1()));
    double x1 = ((width - 1) * minCoords.getX0() / (minCoords.getX0() - maxCoords.getX0()));
    Matrix2x2 matrix = new Matrix2x2(0, a01, a10, 0);
    Vector2D vector = new Vector2D(x0, x1);
    return new AffineTransform2D(matrix, vector);
  }

  /**
   * Shows the canvas in the console.
   */
  public void showAsciiCanvas() {
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        if (canvas[i][j] == 1) {
          System.out.print("X");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }
}
