package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test program for primitives. Ray
 */

class RayTest {

    /**
     * Test method for {@link primitives.Ray#equals(Object)}.
     */
    @Test
    void testTestEquals() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a positive vector
        assertEquals(new Ray(new Point(1, 2, 3), new Vector(1, 2, 3)), new Ray(new Point(1, 2, 3), new Vector(1, 2, 3)), "ERROR: equals() for positive vector does not work correctly");
        // TC02: test for a negative vector
        assertEquals(new Ray(new Point(1, 2, 3), new Vector(1, 2, 3)), new Ray(new Point(2, 4, 6), new Vector(1, 2, 3)), "ERROR: equals() for negative vector does not work correctly");
        // TC03: test for a vector with a zero coordinate
        assertEquals(new Ray(new Point(1, 2, 3), new Vector(1, 2, 3)), new Ray(new Point(1, 2, 3), new Vector(0, 0, 3)), "ERROR: equals() for vector with a zero coordinate does not work correctly");
        // =============== Boundary Values Tests ==================
        // TC04: test for a vector with all coordinates as 0
        assertEquals(new Ray(new Point(1, 2, 3), new Vector(1, 2, 3)), new Ray(new Point(1, 2, 3), new Vector(0, 0, 0)), "ERROR: equals() for vector with all coordinates as 0 does not work correctly");
    }


}