package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
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

    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        // ============ Equivalence Partitions Tests ==============
        // TC01: A ray that starts out of the plane, not parallel to the plane, makes a non-right angle wite the plane, and cuts the plane
        assertEquals(1, plane.findIntersections(new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1))).size(), "ERROR: A ray that starts out of the plane, not parallel to the plane, makes a non-right angle wite the plane, and cuts the plane");
        // TC02: A ray that starts out of the plane, not parallel to the plane, makes a non-right angle with the plane, and does not cut the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 1), new Vector(1, 1, 1))), "ERROR: A ray that starts out of the plane, not parallel to the plane, makes a non-right angle with the plane, and does not cut the plane");
        // =============== Boundary Values Tests ==================
        // TC11: A ray that parallel the plane, and start outside the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 1, 1), new Vector(0, 0, 1))), "ERROR: A ray that parallel the plane, and start outside the plane");
        // TC12: A ray that parallel the plane, and start in the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0, 0, 1))), "ERROR: A ray that parallel the plane, and start in the plane");
        // TC13: A ray that aligned to the plane and start before the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1))), "ERROR: A ray that aligned to the plane and start before the plane");
        // TC14: A ray that aligned to the plane and start in the plane



    }
}