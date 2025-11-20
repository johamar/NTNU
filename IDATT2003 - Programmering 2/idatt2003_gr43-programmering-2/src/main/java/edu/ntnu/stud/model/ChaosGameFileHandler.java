package edu.ntnu.stud.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a chaos game description.
 *
 * @author johanarntsen
 * @version 1.0
 * @since 0.2
 */

public class ChaosGameFileHandler {
  /**
   * Reads a chaos game description from a file.
   *
   * @param pathString the path to the file
   * @return the chaos game description read from the file
   */
  public ChaosGameDescription readFromFile(String pathString) {
    String transformType = null;
    Path path = Paths.get(pathString);
    Vector2D minCoords = null;
    Vector2D maxCoords = null;
    List<Transform2D> transforms = new ArrayList<>();

    try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
      String line;
      int lineCount = 0;
      while ((line = bufferedReader.readLine()) != null) {
        lineCount++;
        line = line.split("#")[0].trim();
        String[] parts = line.split(", ");

        if (lineCount == 1) {
          transformType = parts[0].toLowerCase();
        } else if (lineCount == 2) {
          minCoords = new Vector2D(Double.parseDouble(parts[0]), Double.parseDouble(parts[1]));
        } else if (lineCount == 3) {
          maxCoords = new Vector2D(Double.parseDouble(parts[0]),
              Double.parseDouble(parts[1]));
        } else if (lineCount > 3 && transformType.equals("julia")) {
          Complex point = new Complex(Double.parseDouble(parts[0]),
              Double.parseDouble(parts[1]));
          transforms.add(new JuliaTransform(point, 1));
          transforms.add(new JuliaTransform(point, -1));
        } else if (lineCount > 3) {
          Matrix2x2 matrix = new Matrix2x2(Double.parseDouble(parts[0]),
              Double.parseDouble(parts[1]),
              Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
          Vector2D vector = new Vector2D(Double.parseDouble(parts[4]),
              Double.parseDouble(parts[5]));
          transforms.add(new AffineTransform2D(matrix, vector));
        }
      }
    } catch (IOException e) {
      System.out.println("An error occurred while reading from the file.");
    }
    return new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  /**
   * Writes a chaos game description to a file.
   *
   * @param description the chaos game description to write to the file
   * @param pathString  the path to the file
   */
  public void writeToFile(ChaosGameDescription description, String pathString) {
    File file = new File(pathString);
    BufferedWriter writer = null;
    try {
      FileWriter fileWriter = new FileWriter(file);
      writer = new BufferedWriter(fileWriter);

      boolean containsJulia = false;
      for (Transform2D transform : description.getTransforms()) {
        if (transform instanceof JuliaTransform) {
          containsJulia = true;
          break;
        }
      }
      writer = new BufferedWriter(fileWriter);
      if (containsJulia) {
        writer.write("Julia # Type of transform");
        writer.newLine();
        writer.write(description.getMinCoords().toString() + " # min x, min y");
        writer.newLine();
        writer.write(description.getMaxCoords().toString() + " # max x, max y");
        writer.newLine();
        Complex point = ((JuliaTransform) description.getTransforms().get(0)).getPoint();
        writer.write(point + " # Real and imaginary parts of the constant c");
      } else {
        writer.write("Affine2D # Type of transform");
        writer.newLine();
        writer.write(description.getMinCoords().toString() + " # min x, min y");
        writer.newLine();
        writer.write(description.getMaxCoords().toString() + " # max x, max y");
        writer.newLine();
        int i = 1;
        for (Transform2D transform : description.getTransforms()) {
          writer.write(transform.toString() + " # Affine transformation nr." + i);
          writer.newLine();
          i++;
        }
      }

    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file.");
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (IOException e) {
        System.out.println("An error occurred while closing the writer.");
      }
    }
  }
}
