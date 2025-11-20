import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.stud.model.Complex;
import edu.ntnu.stud.model.Vector2D;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ComplexTest {
  private static Complex c1;
  private static Complex c2;
  private static Complex c3;

  @BeforeEach
  void setUp() {
    c1 = new Complex(1, 2);
    c2 = new Complex(3, 4);
    c3 = new Complex(0.1, -0.4);
  }

  @Nested
  @DisplayName("Testing the constructor in the Complex class")
  class testConstructor {
    @Test
    @DisplayName("Testing the first constructor")
    public void testConstructor1() {
      assertEquals(1, c1.getX0());
      assertEquals(2, c1.getX1());
    }

    @Test
    @DisplayName("Testing the second constructor")
    public void testConstructor2() {
      assertEquals(3, c2.getX0());
      assertEquals(4, c2.getX1());
    }

    @Test
    @DisplayName("Testing the third constructor")
    public void testConstructor3() {
      assertEquals(0.1, c3.getX0());
      assertEquals(-0.4, c3.getX1());
    }
  }

  @Nested
  @DisplayName("Testing the sqrt method in the Complex class")
  class sqrtTests {
    @Test
    @DisplayName("Testing the sqrt method on c1")
    public void testSqrt1() {
      Complex sqrt = c1.sqrt();
      assertEquals(1.272019649514069, sqrt.getX0());
      assertEquals(0.7861513777574233, sqrt.getX1());
    }

    @Test
    @DisplayName("Testing the sqrt method on c2")
    public void testSqrt2() {
      Complex sqrt = c2.sqrt();
      assertEquals(2, sqrt.getX0());
      assertEquals(1, sqrt.getX1());
    }

    @Test
    @DisplayName("Testing the sqrt method on c3")
    public void testSqrt3() {
      Complex sqrt = c3.sqrt();
      assertEquals(0.5061178531536732, sqrt.getX0());
      assertEquals(-0.3951648786024424, sqrt.getX1());
    }
  }

  @Nested
  @DisplayName("Testing functions borrowed from Vector2D")
  class testsFromVector2D {
    @Test
    @DisplayName("Testing the add function")
    public void testAdd() {
      Vector2D v3 = c1.add(c2);
      assertEquals(4, v3.getX0());
      assertEquals(6, v3.getX1());
    }

    @Test
    @DisplayName("Testing the subtract function")
    public void testSubtract() {
      Vector2D v3 = c1.subtract(c2);
      assertEquals(-2, v3.getX0());
      assertEquals(-2, v3.getX1());
    }

    @Test
    @DisplayName("Testing the setX0 function")
    public void testSetX0() {
      c1.setX0(3);
      assertEquals(3, c1.getX0());
    }

    @Test
    @DisplayName("Testing the setX1 function")
    public void testSetX1() {
      c1.setX1(4);
      assertEquals(4, c1.getX1());
    }

    @Test
    @DisplayName("Testing the getX0 function")
    public void testGetX0() {
      assertEquals(1, c1.getX0());
    }

    @Test
    @DisplayName("Testing the getX1 function")
    public void testGetX1() {
      assertEquals(2, c1.getX1());
    }
  }
}
