import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.model.Matrix2x2;
import edu.ntnu.stud.model.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Matrix2x2Test {
  private static Matrix2x2 m1;
  @BeforeAll
  public static void setUp() {
    m1 = new Matrix2x2(1, 2, 3, 4);
  }

  @Nested
  @DisplayName("Testing the constructor in the Matrix2x2 class")
  class constructorTests {
    @Test
    @DisplayName("Testing the constructor")
    void testConstructor() {
      assertEquals(1, m1.getA00());
      assertEquals(2, m1.getA01());
      assertEquals(3, m1.getA10());
      assertEquals(4, m1.getA11());
    }

    @Test
    @DisplayName("Testing the constructor with decimal values")
    void testConstructorDecimal() {
      Matrix2x2 m2 = new Matrix2x2(1.333, 2.565, 3.444, 4.555);
      assertEquals(1.333, m2.getA00());
      assertEquals(2.565, m2.getA01());
      assertEquals(3.444, m2.getA10());
      assertEquals(4.555, m2.getA11());
    }

    @Test
    @DisplayName("Testing the constructor with negative values")
    void testConstructorNegative() {
      Matrix2x2 m2 = new Matrix2x2(-1, -2, -3, -4);
      assertEquals(-1, m2.getA00());
      assertEquals(-2, m2.getA01());
      assertEquals(-3, m2.getA10());
      assertEquals(-4, m2.getA11());
    }
  }

  @Nested
  @DisplayName("Testing the multiply function in the Matrix2x2 class")
  class multiplyTests {
    @Test
    @DisplayName("Testing the multiply function with simple values")
    public void testMultiply() {
      Vector2D v1 = new Vector2D(1, 2);
      Vector2D v2 = m1.multiply(v1);
      assertEquals(5, v2.getX0());
      assertEquals(11, v2.getX1());
    }

    @Test
    @DisplayName("Testing the multiply function with more simple values")
    public void testMultiply2() {
      Vector2D v1 = new Vector2D(2, 3);
      Vector2D v2 = m1.multiply(v1);
      assertEquals(8, v2.getX0());
      assertEquals(18, v2.getX1());
    }

    @Test
    @DisplayName("Testing the multiply function with negative values")
    public void testMultiplyNegative() {
      Vector2D v1 = new Vector2D(-1, -2);
      Vector2D v2 = m1.multiply(v1);
      assertEquals(-5, v2.getX0());
      assertEquals(-11, v2.getX1());
    }

    @Test
    @DisplayName("Testing the multiply function with zero values")
    public void testMultiplyZero() {
      Vector2D v1 = new Vector2D(0, 0);
      Vector2D v2 = m1.multiply(v1);
      assertEquals(0, v2.getX0());
      assertEquals(0, v2.getX1());
    }
  }

    @Nested
    @DisplayName("Testing the toString function in the Matrix2x2 class")
    class toStringTests {
        @Test
        @DisplayName("Testing the toString function")
        void testToString() {
            assertEquals("1.0, 2.0, 3.0, 4.0", m1.toString());
        }
    }
}
