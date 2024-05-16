package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    /**
     * Test method for .
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p = new Plane(new Point(1, 0, 0), new Point(0, 2, 0), new Point(1, 1, 0));
        assertEquals(new Vector(0, 0, 1).normalize(), p.getNormal(new Point(1, 1, 1)), "Bad normal to plane");

    }
}