import edu.ntnu.stud.model.Complex;
import edu.ntnu.stud.model.JuliaTransform;
import edu.ntnu.stud.model.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JuliaTransformTest {
  private JuliaTransform jt;
  private Complex c;
  @BeforeEach
  void setUp() {
    c = new Complex(0.3, 0.6);
    jt = new JuliaTransform(c, 1);
  }

  @Nested
  @DisplayName("Testing the constructor in the JuliaTransform class")
  class constructorTests {
    @Test
    @DisplayName("Testing the constructor with the complex number c and the integer n")
    public void testConstructor() {
      JuliaTransform jt = new JuliaTransform(c, 1);
      assertEquals(c, jt.getPoint());
      assertEquals(1, jt.getSign());
    }

    @Test
    @DisplayName("Testing the constructor with the complex number c and the sign -1")
    public void testConstructor2() {
      Complex c = new Complex(0.4, 0.2);
      JuliaTransform jt = new JuliaTransform(c, -1);
      assertEquals(c, jt.getPoint());
      assertEquals(-1, jt.getSign());
    }

    @Test
    @DisplayName("Testing the constructor with sign not equal to 1 or -1, throws IllegalArgumentException")
    public void testConstructorNegative() {
      assertThrows(IllegalArgumentException.class, () -> new JuliaTransform(c, 2));
    }

    @Test
    @DisplayName("Testing the constructor with sign not equal to 1 or -1, throws IllegalArgumentException")
    public void testConstructorNegative2() {
      assertThrows(IllegalArgumentException.class, () -> new JuliaTransform(c, -0));
    }

    @Test
    @DisplayName("Testing the constructor with point equal to null, throws IllegalArgumentException")
    public void testConstructorNegative3() {
      assertThrows(IllegalArgumentException.class, () -> new JuliaTransform(null, 1));
    }

  }


  @Nested
    @DisplayName("Testing the transform method in the JuliaTransform class")
  class transformTests {
    @Test
    public void testTransform() {
      Vector2D v1 = new Vector2D(1, 2);
      Vector2D transformed = jt.transform(v1);
      assertEquals(1.0642479937143063, transformed.getX0());
      assertEquals(0.6577414325743258, transformed.getX1());
    }

    @Test
    public void testTransform2() {
      Vector2D v1 = new Vector2D(3, 4);
      Vector2D transformed = jt.transform(v1);
      assertEquals(1.8763873093285515, transformed.getX0());
      assertEquals(0.9059963215208111, transformed.getX1());
    }

    @Test
    public void testTransform3() {
      Vector2D v1 = new Vector2D(0.4, 0.2);
      Vector2D transformed = jt.transform(v1);
      assertEquals(0.5061178531536732, transformed.getX0());
      assertEquals(-0.3951648786024423, transformed.getX1());
    }
  }

    @Nested
  @DisplayName("Testing the toString method in the JuliaTransform class")
    class toStringTests {
        @Test
        public void testToString() {
        assertEquals("0.3, 0.6, 1", jt.toString());
        }

        @Test
        public void testToString2() {
        JuliaTransform jt = new JuliaTransform(new Complex(0.4, 0.2), -1);
        assertEquals("0.4, 0.2, -1", jt.toString());
        }
    }
}
