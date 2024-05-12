package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test program for the 1st stage
 */
class VectorTest {
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(new Vector(2, 4, 6), new Vector(1, 2, 3).add(new Vector(1, 2, 3)), "ERROR: add() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(new Vector(-2, -4, -6), new Vector(-1, -2, -3).add(new Vector(-1, -2, -3)), "ERROR: add() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(new Vector(1, 2, 3), new Vector(1, 2, 0).add(new Vector(0, 0, 3)), "ERROR: add() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(new Vector(1, 2, 3), new Vector(1, 2, 3).add(new Vector(0, 0, 0)), "ERROR: add() for vector with all coordinates as 0 does not work correctly");
    }

    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(new Vector(2, 4, 6), new Vector(1, 2, 3).scale(2), "ERROR: scale() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(new Vector(-2, -4, -6), new Vector(1, 2, 3).scale(-2), "ERROR: scale() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(new Vector(0, 0, 0), new Vector(1, 2, 3).scale(0), "ERROR: scale() for vector with a zero coordinate does not work correctly");
// =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(new Vector(0, 0, 0), new Vector(0, 0, 0).scale(2), "ERROR: scale() for vector with all coordinates as 0 does not work correctly");
    }

    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(14, new Vector(1, 2, 3).lengthSquared(), "ERROR: lengthSquared() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(14, new Vector(-1, -2, -3).lengthSquared(), "ERROR: lengthSquared() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(13, new Vector(1, 2, 0).lengthSquared(), "ERROR: lengthSquared() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(0, new Vector(0, 0, 0).lengthSquared(), "ERROR: lengthSquared() for vector with all coordinates as 0 does not work correctly");
    }

    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(Math.sqrt(14), new Vector(1, 2, 3).length(), "ERROR: length() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(Math.sqrt(14), new Vector(-1, -2, -3).length(), "ERROR: length() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(Math.sqrt(13), new Vector(1, 2, 0).length(), "ERROR: length() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(0, new Vector(0, 0, 0).length(), "ERROR: length() for vector with all coordinates as 0 does not work correctly");
    }

    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(14, new Vector(1, 2, 3).dotProduct(new Vector(1, 2, 3)), "ERROR: dotProduct() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(14, new Vector(-1, -2, -3).dotProduct(new Vector(-1, -2, -3)), "ERROR: dotProduct() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(0, new Vector(1, 2, 0).dotProduct(new Vector(0, 0, 3)), "ERROR: dotProduct() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(0, new Vector(0, 0, 0).dotProduct(new Vector(0, 0, 0)), "ERROR: dotProduct() for vector with all coordinates as 0 does not work correctly");
    }

}