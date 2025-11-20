import edu.ntnu.stud.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChaosGameTest {
  private ChaosGame chaosGame;

  @BeforeEach
  @DisplayName("Setting up a new ChaosGame object")
  public void setUp() {
    JuliaTransform transform1 = new JuliaTransform(new Complex(0, 0), 1);
    JuliaTransform transform2 = new JuliaTransform(new Complex(0, 0), -1);
    ChaosGameDescription description = new ChaosGameDescription(
        new Vector2D(-1.6, -1),
        new Vector2D(1.6, 1),
        Arrays.asList(transform1, transform2)
    );
    chaosGame = new ChaosGame(description, 800, 800);
  }


  @Test
  @DisplayName("Testing the getCanvas method in the ChaosGame class")
  public void testGetCanvas() {
    assertEquals(chaosGame.getCanvas().getWidth(), 800);
    assertEquals(chaosGame.getCanvas().getHeight(), 800);
  }

  @Test
  @DisplayName("Testing the getDescription method in the ChaosGame class")
  public void testGetDescription() {
    assertEquals(chaosGame.getDescription().getMinCoords().getX0(), -1.6);
    assertEquals(chaosGame.getDescription().getMinCoords().getX1(), -1);
    assertEquals(chaosGame.getDescription().getMaxCoords().getX0(), 1.6);
    assertEquals(chaosGame.getDescription().getMaxCoords().getX1(), 1);
  }



  @Test
  @DisplayName("Testing the getCanvas method in the ChaosGame class")
  public void testGetCanvas2() {
    chaosGame.runSteps(1000);
    assertEquals(chaosGame.getCanvas().getCanvasArray()[1].length, 800);
    assertEquals(chaosGame.getCanvas().getCanvasArray()[0].length, 800);
  }


  @Test
  @DisplayName("Testing the getCanvas method in the ChaosGame class")
  public void testGetCanvas3() {
    chaosGame.runSteps(1000);
    assertEquals(chaosGame.getCanvas().getCanvasArray()[1][1], 0);
    assertEquals(chaosGame.getCanvas().getCanvasArray()[0][0], 0);
  }


  @Test
  @DisplayName("Testing the runSteps method in the ChaosGame class")
  public void testRunSteps() {
    chaosGame.runSteps(1000);
    assertTrue(chaosGame.getCanvas().getCanvasArray()[1][1] == 0 || chaosGame.getCanvas().getCanvasArray()[1][1] == 1);
  }
}
