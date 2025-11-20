

import edu.ntnu.stud.model.ChaosCanvas;
import edu.ntnu.stud.model.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ChaosCanvasTest {

  private ChaosCanvas chaosCanvas;
  private Vector2D minCoords;
  private Vector2D maxCoords;

  @BeforeEach
  public void setUp() {
    minCoords = new Vector2D(0, 0);
    maxCoords = new Vector2D(10, 10);
    chaosCanvas = new ChaosCanvas(10, 10, minCoords, maxCoords);
  }

  @Test
  @DisplayName("Testing the putPixel method in the ChaosCanvas class")
  public void testPutPixel() {
    Vector2D point = new Vector2D(5, 5);
    chaosCanvas.putPixel(point);
    assertEquals(1, chaosCanvas.getPixel(point));
  }

  @Test
  @DisplayName("Testing the constructor in the ChaosCanvas class")
  public void testConstructor() {
    assertEquals(10, chaosCanvas.getWidth());
    assertEquals(10, chaosCanvas.getHeight());
  }

  @Test
  @DisplayName("Testing the getPixel method in the ChaosCanvas class")
  public void testGetPixel() {
    Vector2D point = new Vector2D(5, 5);
    assertEquals(0, chaosCanvas.getPixel(point)); // Initial state should be 0
    chaosCanvas.putPixel(point);
    assertEquals(1, chaosCanvas.getPixel(point)); // After putting pixel, it should be 1
  }

  @Test
  @DisplayName("Testing the clear method in the ChaosCanvas class")
  public void testClear() {
    Vector2D point = new Vector2D(5, 5);
    chaosCanvas.putPixel(point);
    chaosCanvas.clear();
    assertEquals(0, chaosCanvas.getPixel(point));
  }

  @Test
  @DisplayName("Testing the getCanvasArray method in the ChaosCanvas class")
  public void testGetCanvasArray() {
    int[][] canvasArray = chaosCanvas.getCanvasArray();
    assertEquals(10, canvasArray.length);
    assertEquals(10, canvasArray[0].length);
  }
}

