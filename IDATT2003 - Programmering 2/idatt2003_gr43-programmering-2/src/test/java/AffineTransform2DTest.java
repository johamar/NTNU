import edu.ntnu.stud.model.AffineTransform2D;
import edu.ntnu.stud.model.Matrix2x2;
import edu.ntnu.stud.model.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AffineTransform2DTest {
  private Matrix2x2 A;
  private Vector2D b;
  private AffineTransform2D at;
  @BeforeEach
  void setUp() {
    A = new Matrix2x2(1, 2, 3, 4);
    b = new Vector2D(1, 2);
    at = new AffineTransform2D(A, b);
  }

  @Nested
  @DisplayName("Testing the transform method in the AffineTransform2D class")
  class constructorTests{
    @Test
    @DisplayName("Testing the constructor with the matrix A and the vector b")
    public void testConstructor() {
      AffineTransform2D at = new AffineTransform2D(A, b);
      assertEquals(A, at.getMatrix());
      assertEquals(b, at.getVector());
    }

    @Test
    @DisplayName("Testing the transform method with the vector x")
    public void testConstructor2() {
      Vector2D x = new Vector2D(3, 4);
      AffineTransform2D at = new AffineTransform2D(A, x);
      assertEquals(x, at.getVector());
    }

    @Test
    @DisplayName("Testing the transform method with the matrix x")
    public void testConstructor3() {
      Matrix2x2 x = new Matrix2x2(5, 6, 7, 8);
      AffineTransform2D at = new AffineTransform2D(x, b);
      assertEquals(x, at.getMatrix());
    }
  }

  @Nested
  @DisplayName("Testing the transform method in the AffineTransform2D class")
  class transformTests{
    @Test
    @DisplayName("Testing the transform method with the vector x")
    public void testTransform() {
      Vector2D x = new Vector2D(3, 4);
      Vector2D answer = at.transform(x);
      assertEquals(12, answer.getX0());
      assertEquals(27, answer.getX1());
    }
    @Test
    @DisplayName("Testing the transform method with the vector y, negative values")
    public void testTransform2() {
      Vector2D y = new Vector2D(-5, -6);
      Vector2D answer = at.transform(y);
      assertEquals(-16, answer.getX0());
      assertEquals(-37, answer.getX1());
    }

    @Test
    @DisplayName("Testing the transform method with the vector z, decimal values")
    public void testTransform3() {
      Vector2D z = new Vector2D(7.5, 8.5);
      Vector2D answer = at.transform(z);
      assertEquals(25.5, answer.getX0());
      assertEquals(58.5, answer.getX1());
    }
  }

  @Nested
  @DisplayName("Testing the toString method in the AffineTransform2D class")
  class toStringTests{
    @Test
    @DisplayName("Testing the toString method with the matrix A and the vector b")
    public void testToString() {
      String expected = "1.0, 2.0, 3.0, 4.0, 1.0, 2.0";
      assertEquals(expected, at.toString());
    }
  }
}
