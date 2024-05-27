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
    void testEquals() {
        // ============ Equivalence Partitions Tests ==============
        Ray r1 = new Ray(new Point(1, 2, 3), new Vector(1, 0, 0));
        Ray r2 = new Ray(new Point(1, 2, 3), new Vector(1, 0, 0));
        assertEquals(r1, r2);
        // =============== Boundary Values Tests ==================
        // different head
        r2 = new Ray(new Point(1, 2, 4), new Vector(1, 0, 0));
        assertNotEquals(r1, r2);
        // different direction
        r2 = new Ray(new Point(1, 2, 3), new Vector(0, 1, 0));
        assertNotEquals(r1, r2);
    }


}