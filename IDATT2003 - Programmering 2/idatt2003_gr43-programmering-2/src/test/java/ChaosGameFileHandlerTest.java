import edu.ntnu.stud.model.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChaosGameFileHandlerTest {

  private static final String TEST_FILE_PATH = "testChaosGame.txt";
  private ChaosGameFileHandler fileHandler;
  private ChaosGameDescription description;

  @BeforeEach
  @DisplayName("Setting up a new ChaosGameFileHandler object")
  public void setup() {
    fileHandler = new ChaosGameFileHandler();

    Vector2D minCoords = new Vector2D(0, 0);
    Vector2D maxCoords = new Vector2D(1, 1);
    Matrix2x2 matrix = new Matrix2x2(0.5, 0, 0, 0.5);
    Vector2D vector = new Vector2D(0, 0);
    AffineTransform2D transform = new AffineTransform2D(matrix, vector);
    List<Transform2D> transforms = Arrays.asList(transform, transform, transform);
    description = new ChaosGameDescription(minCoords, maxCoords, transforms);
  }

  @AfterEach
  @DisplayName("Cleaning up the test")
  public void cleanup() {
    try {
      Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  @DisplayName("Testing the writeToFile method in the ChaosGameFileHandler class")
  public void testWriteToFile() {
    fileHandler.writeToFile(description, TEST_FILE_PATH);
    assertTrue(Files.exists(Paths.get(TEST_FILE_PATH)));
  }

  @Test
  @DisplayName("Testing the readFromFile method in the ChaosGameFileHandler class")
  public void testReadFromFile() {
    fileHandler.writeToFile(description, TEST_FILE_PATH);
    ChaosGameDescription readDescription = fileHandler.readFromFile(TEST_FILE_PATH);

    assertNotNull(readDescription);
    assertEquals(description.getMinCoords(), readDescription.getMinCoords());
    assertEquals(description.getMaxCoords(), readDescription.getMaxCoords());
    assertEquals(description.getTransforms().size(), readDescription.getTransforms().size());
  }


  @Test
  @DisplayName("Testing the readFromFile method in the ChaosGameFileHandler class")
  public void testReadFromFileJulia() {
    new Vector2D(0, 0);
    new Vector2D(1, 1);
    new JuliaTransform(new Complex(0, 0), 1);
    new JuliaTransform(new Complex(0, 0), -1);
    fileHandler.writeToFile(description, TEST_FILE_PATH);
    ChaosGameDescription readDescription = fileHandler.readFromFile(TEST_FILE_PATH);

    assertNotNull(readDescription);
    assertEquals(description.getMinCoords(), readDescription.getMinCoords());
    assertEquals(description.getMaxCoords(), readDescription.getMaxCoords());
    assertEquals(description.getTransforms().size(), readDescription.getTransforms().size());
  }
}
