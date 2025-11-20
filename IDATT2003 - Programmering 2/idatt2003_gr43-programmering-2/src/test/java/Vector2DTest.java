import edu.ntnu.stud.model.Vector2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class Vector2DTest {
  private Vector2D v1;
  @BeforeEach
  void setUp() {
    v1 = new Vector2D(1, 2);
  }

  @Nested
  @DisplayName("Testing the constructor in the Vector2D class")
  class constructorTests {
    @Test
    @DisplayName("Testing the constructor")
    void testConstructor() {
      assert v1.getX0() == 1;
      assert v1.getX1() == 2;
    }

    @Test
    @DisplayName("Testing the constructor with decimal values")
    void testConstructorDecimal() {
      Vector2D v2 = new Vector2D(1.333, 2.565);
      assert v2.getX0() == 1.333;
      assert v2.getX1() == 2.565;
    }

    @Test
    @DisplayName("Testing the constructor with negative values")
    void testConstructorNegative() {
      Vector2D v2 = new Vector2D(-1, -2);
      assert v2.getX0() == -1;
      assert v2.getX1() == -2;
    }

    @Test
    @DisplayName("Testing the constructor with negative decimal values")
    void testConstructorNegativeDecimal() {
      Vector2D v2 = new Vector2D(-2.444, -2.565);
      assert v2.getX0() == -2.444;
      assert v2.getX1() == -2.565;
    }

    @Test
    @DisplayName("Testing the constructor with zero values")
    void testConstructorZero() {
      Vector2D v2 = new Vector2D(0, 0);
      assert v2.getX0() == 0;
      assert v2.getX1() == 0;
    }
  }

  @Nested
  @DisplayName("Testing the addVector function in the Vector2D class")
  class addVectorTests {
    @Test
    @DisplayName("Testing the add function")
    void testAdd() {
      Vector2D v2 = new Vector2D(3, 4);
      Vector2D v3 = v1.add(v2);
      assert v3.getX0() == 4;
      assert v3.getX1() == 6;
    }

    @Test
    @DisplayName("Testing the add function with negative values")
    void testAddNegative() {
      Vector2D v2 = new Vector2D(-3, -4);
      Vector2D v3 = v1.add(v2);
      assert v3.getX0() == -2;
      assert v3.getX1() == -2;
    }

    @Test
    @DisplayName("Testing the add function with zero values")
    void testAddZero() {
      Vector2D v2 = new Vector2D(0, 0);
      Vector2D v3 = v1.add(v2);
      assert v3.getX0() == 1;
      assert v3.getX1() == 2;
    }

    @Test
    @DisplayName("Testing the add function with one zero value")
    void testAddOneZero() {
      Vector2D v2 = new Vector2D(0, 4);
      Vector2D v3 = v1.add(v2);
      assert v3.getX0() == 1;
      assert v3.getX1() == 6;
    }
  }

  @Nested
  @DisplayName("Testing the subtractVector function in the Vector2D class")
  class subtractVectorTests {
    @Test
    @DisplayName("Testing the subtract function")
    void testSubtract() {
      Vector2D v2 = new Vector2D(3, 4);
      Vector2D v3 = v1.subtract(v2);
      assert v3.getX0() == -2;
      assert v3.getX1() == -2;
    }

    @Test
    @DisplayName("Testing the subtract function with negative values")
    void testSubtractNegative() {
      Vector2D v2 = new Vector2D(-3, -4);
      Vector2D v3 = v1.subtract(v2);
      assert v3.getX0() == 4;
      assert v3.getX1() == 6;
    }

    @Test
    @DisplayName("Testing the subtract function with zero values")
    void testSubtractZero() {
      Vector2D v2 = new Vector2D(0, 0);
      Vector2D v3 = v1.subtract(v2);
      assert v3.getX0() == 1;
      assert v3.getX1() == 2;
    }

    @Test
    @DisplayName("Testing the subtract function with one zero value")
    void testSubtractOneZero() {
      Vector2D v2 = new Vector2D(0, 4);
      Vector2D v3 = v1.subtract(v2);
      assert v3.getX0() == 1;
      assert v3.getX1() == -2;
    }
  }

  @Nested
  @DisplayName("Testing the set functions in the Vector2D class")
  class setTests {
    @Test
    @DisplayName("Testing the setX0 function")
    void testSetX0() {
      v1.setX0(3);
      assert v1.getX0() == 3;
    }

    @Test
    @DisplayName("Testing the setX1 function")
    void testSetX1() {
      v1.setX1(4);
      assert v1.getX1() == 4;
    }

    @Test
    @DisplayName("Testing the setX0 function with negative values")
    void testSetX0Negative() {
      v1.setX0(-3);
      assert v1.getX0() == -3;
    }

    @Test
    @DisplayName("Testing the setX1 function with negative values")
    void testSetX1Negative() {
      v1.setX1(-4);
      assert v1.getX1() == -4;
    }

    @Test
    @DisplayName("Testing the setX0 function with zero values")
    void testSetX0Zero() {
      v1.setX0(0);
      assert v1.getX0() == 0;
    }

    @Test
    @DisplayName("Testing the setX1 function with zero values")
    void testSetX1Zero() {
      v1.setX1(0);
      assert v1.getX1() == 0;
    }
  }

  @Nested
  @DisplayName("Testing the get functions in the Vector2D class")
  class getTests {
    @Test
    @DisplayName("Testing the getX0 function")
    void testGetX0() {
      assert v1.getX0() == 1;
    }

    @Test
    @DisplayName("Testing the getX1 function")
    void testGetX1() {
      assert v1.getX1() == 2;
    }

    @Test
    @DisplayName("Testing the getX0 function with negative values")
    void testGetX0Negative() {
      Vector2D v2 = new Vector2D(-1, -2);
      assert v2.getX0() == -1;
    }

    @Test
    @DisplayName("Testing the getX1 function with negative values")
    void testGetX1Negative() {
      Vector2D v2 = new Vector2D(-1, -2);
      assert v2.getX1() == -2;
    }
  }

  @Nested
  @DisplayName("Testing the toString function in the Vector2D class")
  class testToString {
    @Test
    @DisplayName("Testing the toString function")
    void testRegularToString() {
      assert v1.toString().equals("1.0, 2.0");
    }

    @Test
    @DisplayName("Testing the toString function with negative values")
    void testToStringNegative() {
      Vector2D v2 = new Vector2D(-1, -2);
      assert v2.toString().equals("-1.0, -2.0");
    }

    @Test
    @DisplayName("Testing the toString function with zero values")
    void testToStringZero() {
      Vector2D v2 = new Vector2D(0, 0);
      assert v2.toString().equals("0.0, 0.0");
    }
  }

}