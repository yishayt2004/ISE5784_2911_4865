package primitives;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test program for primitives. Point
 */
class PointTest {

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        Point p246 = new Point(2, 4, 6);
        Point p123 = new Point(1, 2, 3);
        assertEquals(new Vector(1, 2, 3),
                p246.subtract(p123),
                "ERROR: subtract() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(new Vector(-1, -2, -3),
                p123.subtract(p246),
                "ERROR: subtract() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(new Vector(1, 2, 3), p123.subtract(new Point(0, 0, 0)), "ERROR: subtract() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(new Vector(1, 2, 3), p123.subtract(new Point(0, 0, 0)), "ERROR: subtract() for vector with all coordinates as 0 does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ===============
        // TC01: test for a positive vector
        assertEquals(new Point(2, 4, 6), new Point(1, 2, 3).add(new Vector(1, 2, 3)), "ERROR: add() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(new Point(1, 0, 0), new Point(1, 2, 3).add(new Vector(0, -2, -3)), "ERROR: add() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(new Point(1, 2, 3), new Point(1, 2, 0).add(new Vector(0, 0, 3)), "ERROR: add() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(new Point(1, 3, 3), new Point(1, 2, 3).add(new Vector(0, 1, 0)), "ERROR: add() for vector with all coordinates as 0 does not work correctly");

    }

    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(14, new Point(1, 2, 3).distanceSquared(new Point(2, 4, 6)), "ERROR: distanceSquared() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(14, new Point(-1, -2, -3).distanceSquared(new Point(-2, -4, -6)), "ERROR: distanceSquared() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(9, new Point(1, 2, 0).distanceSquared(new Point(0, 0, 2)), "ERROR: distanceSquared() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(14, new Point(1, 2, 3).distanceSquared(new Point(0, 0, 0)), "ERROR: distanceSquared() for vector with all coordinates as 0 does not work correctly");
    }

    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */

    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(Math.sqrt(14), new Point(1, 2, 3).distance(new Point(2, 4, 6)), "ERROR: distance() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(Math.sqrt(14), new Point(-1, -2, -3).distance(new Point(-2, -4, -6)), "ERROR: distance() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(Math.sqrt(14), new Point(1, 2, 0).distance(new Point(0, 0, 3)), "ERROR: distance() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(Math.sqrt(14), new Point(1, 2, 3).distance(new Point(0, 0, 0)), "ERROR: distance() for vector with all coordinates as 0 does not work correctly");

    }

    @Test
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: true
        assertTrue(new Point(1, 2, 3).equals(new Point(1, 2, 3)), "ERROR: equals() for positive vector does not work correctly");
        // TC02: false
        assertFalse(new Point(1, 2, 3).equals(new Point(2, 4, 6)), "ERROR: equals() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertFalse(new Point(1, 2, 0).equals(new Point(0, 0, 3)), "ERROR: equals() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertFalse(new Point(1, 2, 3).equals(new Point(0, 0, 0)), "ERROR: equals() for vector with all coordinates as 0 does not work correctly");

    }
}