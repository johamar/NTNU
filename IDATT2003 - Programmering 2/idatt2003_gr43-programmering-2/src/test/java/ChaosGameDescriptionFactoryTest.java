import edu.ntnu.stud.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ChaosGameDescriptionFactoryTest {

  @Test
  @DisplayName("Testing the affineTransformCustom method in the ChaosGameDescriptionFactory class")
  public void testAffineTransformCustom() {
    double minX = -1.0, maxX = 1.0, minY = -1.0, maxY = 1.0;
    double a1 = 0.5, a2 = 0.5, a3 = 0.5, a4 = 0.5, a5 = 0.5, a6 = 0.5;
    double b1 = 0.5, b2 = 0.5, b3 = 0.5, b4 = 0.5, b5 = 0.5, b6 = 0.5;
    double c1 = 0.5, c2 = 0.5, c3 = 0.5, c4 = 0.5, c5 = 0.5, c6 = 0.5;

    ChaosGameDescription description = ChaosGameDescriptionFactory.affineTransformCustom(minX, maxX, minY, maxY,
        a1, a2, a3, a4, a5, a6, b1, b2, b3, b4, b5, b6, c1, c2, c3, c4, c5, c6);

    assertNotNull(description, "Description should not be null");
    assertEquals(minX, description.getMinCoords().getX0());
    assertEquals(maxX, description.getMinCoords().getX1());
    assertEquals(minY, description.getMaxCoords().getX0());
    assertEquals(maxY, description.getMaxCoords().getX1());
    assertEquals(3, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the juliaSetCustom method in the ChaosGameDescriptionFactory class")
  public void testJuliaSetCustom() {
    double minX = -1.0, maxX = 1.0, minY = -1.0, maxY = 1.0;
    double real = 0.5, imaginary = 0.5;

    ChaosGameDescription description = ChaosGameDescriptionFactory.juliaSetCustom(minX, maxX, minY, maxY, real, imaginary);

    assertNotNull(description, "Description should not be null");
    assertEquals(minX, description.getMinCoords().getX0());
    assertEquals(maxX, description.getMinCoords().getX1());
    assertEquals(minY, description.getMaxCoords().getX0());
    assertEquals(maxY, description.getMaxCoords().getX1());
    assertEquals(2, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the sierpinskiTriangle method in the ChaosGameDescriptionFactory class")
  public void testSierpinskiTriangle() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.sierpinskiTriangle();
    assertEquals(0, description.getMinCoords().getX0());
    assertEquals(0, description.getMinCoords().getX1());
    assertEquals(1, description.getMaxCoords().getX0());
    assertEquals(1, description.getMaxCoords().getX1());
    assertEquals(3, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the barnsleyFern method in the ChaosGameDescriptionFactory class")
  public void testBarnsleyFern() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.barnsleyFern();
    assertEquals(-2.1820, description.getMinCoords().getX0(), 0.0001);
    assertEquals(0, description.getMinCoords().getX1());
    assertEquals(2.6558, description.getMaxCoords().getX0(), 0.0001);
    assertEquals(9.9983, description.getMaxCoords().getX1(), 0.0001);
    assertEquals(4, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the kochCurve method in the ChaosGameDescriptionFactory class")
  public void testKochCurve() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.kochCurve();
    assertEquals(-0.5, description.getMinCoords().getX0());
    assertEquals(-0.5, description.getMinCoords().getX1());
    assertEquals(1.5, description.getMaxCoords().getX0());
    assertEquals(1.5, description.getMaxCoords().getX1());
    assertEquals(4, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the dragonCurve method in the ChaosGameDescriptionFactory class")
  public void testDragonCurve() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.dragonCurve();
    assertEquals(-1, description.getMinCoords().getX0());
    assertEquals(-1, description.getMinCoords().getX1());
    assertEquals(1, description.getMaxCoords().getX0());
    assertEquals(1, description.getMaxCoords().getX1());
    assertEquals(2, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the juliaSet1 method in the ChaosGameDescriptionFactory class")
  public void testJuliaSet1() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.juliaSet1();
    assertEquals(-1.6, description.getMinCoords().getX0());
    assertEquals(-1, description.getMinCoords().getX1());
    assertEquals(1.6, description.getMaxCoords().getX0());
    assertEquals(1, description.getMaxCoords().getX1());
    assertEquals(2, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the juliaSet2 method in the ChaosGameDescriptionFactory class")
  public void testJuliaSet2() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.juliaSet2();
    assertEquals(-1.6, description.getMinCoords().getX0());
    assertEquals(-1, description.getMinCoords().getX1());
    assertEquals(1.6, description.getMaxCoords().getX0());
    assertEquals(1, description.getMaxCoords().getX1());
    assertEquals(2, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the juliaSet3 method in the ChaosGameDescriptionFactory class")
  public void testJuliaSet3() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.juliaSet3();
    assertEquals(-1.6, description.getMinCoords().getX0());
    assertEquals(-1, description.getMinCoords().getX1());
    assertEquals(1.6, description.getMaxCoords().getX0());
    assertEquals(1, description.getMaxCoords().getX1());
    assertEquals(2, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the juliaSet4 method in the ChaosGameDescriptionFactory class")
  public void testJuliaSet4() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.juliaSet4();
    assertEquals(-1.6, description.getMinCoords().getX0());
    assertEquals(-1, description.getMinCoords().getX1());
    assertEquals(1.6, description.getMaxCoords().getX0());
    assertEquals(1, description.getMaxCoords().getX1());
    assertEquals(2, description.getTransforms().size());
  }

  @Test
  @DisplayName("Testing the juliaSet5 method in the ChaosGameDescriptionFactory class")
  public void testJuliaSet5() {
    ChaosGameDescription description = ChaosGameDescriptionFactory.juliaSet5();
    assertEquals(-1.6, description.getMinCoords().getX0());
    assertEquals(-1, description.getMinCoords().getX1());
    assertEquals(1.6, description.getMaxCoords().getX0());
    assertEquals(1, description.getMaxCoords().getX1());
    assertEquals(2, description.getTransforms().size());
  }
}
