package geometries;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Nested
class PlaneTests {

    /**
     * Test method for .
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Plane p = new Plane(new Point(1, 0, 0), new Point(0, 2, 0), new Point(1, 1, 0));
        assertEquals(new Vector(0, 0, 1).normalize(),
                p.getNormal(new Point(1, 1, 1)),
                "Bad normal to plane");

    }





// Other imports...

    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(3, 0, 0), new Point(-3, 0, 0), new Point(0, 0, 2));

        Ray ray;

        // TC01: Ray's line is not parallel to the plane and makes non-right angle with the plane (0 points)
        ray = new Ray(new Point(0, 1, 1), new Vector(1, -1, 0));
        assertNull(plane.findIntersections(ray),
                "ERROR: Ray's line is not parallel to the plane and makes non-right angle with the plane");

        // TC02: Ray's line is not parallel to the plane and makes non-right angle with the plane (1 point)
        ray = new Ray(new Point(0, 1, 1), new Vector(1, 1, 0));
        assertEquals(1, plane.findIntersections(ray).size(),
                "ERROR: Ray's line is not parallel to the plane and makes non-right angle with the plane");

        // TC03: Ray's line is parallel to the plane and on the plane (0 points)
        ray = new Ray(new Point(3, 0, 2), new Vector(1, 0, 0));
        assertNull(plane.findIntersections(ray),
                "ERROR: Ray's line is parallel to the plane and on the plane");

        // TC04: Ray's line is parallel to the plane and not on the plane (0 points)
        ray = new Ray(new Point(3, 0, 3), new Vector(1, 0, 0));
       assertNull(plane.findIntersections(ray),
               "ERROR: Ray's line is parallel to the plane and not on the plane");

        // TC05: Ray's line that make a right angle with the plane and begin before the plane (1 point)
//       ray = new Ray(new Point(3, 0, 3), new Vector(0, 0, -1));
//       assertEquals(1, plane.findIntersections(ray).size(), "TC05 failed");

        // TC06: Ray's line that make a right angle with the plane and begin in the plane (0 points)
        ray = new Ray(new Point(0, 0, 2), new Vector(0, 0, -1));
        assertNull(plane.findIntersections(ray),
                "ERROR: Ray's line that make a right angle with the plane and begin in the plane");

        // TC07: Ray's line that make a right angle with the plane and begin after the plane (0 points)
        ray = new Ray(new Point(-3, 0, 3), new Vector(0, 0, -1));
        assertNull(plane.findIntersections(ray),
                "ERROR: Ray's line that make a right angle with the plane and begin after the plane");

        // TC08: Ray's line that is not parallel to the plane and begin in the same point which appears as reference point in the plane (0 points)
        ray = new Ray(new Point(3, 0, 0), new Vector(1, 1, 0));
        assertNull(plane.findIntersections(ray),
                "ERROR: Ray's line that is not parallel to the plane and begin in the same point which appears as reference point in the plane");

//        // TC09: Ray's line that is not parallel to the plane and begin on the plane (0 points)
//        ray = new Ray(new Point(0, 0, 2), new Vector(1, 1, 0));
//        assertNull(plane.findIntersections(ray), "TC09 failed");
    }










    /**
     * Test method for constructor
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a proper result
        assertDoesNotThrow(() -> (new Plane(new Point(1, 0, 0), new Point(0, 1
                , 0), new Point(0, 0, 1))), "the constructor does not work correctly");

        // =============== Boundary Values Tests ==================
        // TC02: test for points on the same line
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(2, 0, 0), new Point(3, 0, 0)), "Failed to throw an exception when creating a plane with points on the same line");

        // TC03: test for points that converge to the same point
        assertThrows(IllegalArgumentException.class, () -> new Plane(new Point(1, 0, 0), new Point(1, 0, 0), new Point(1, 0, 0)), "Failed to throw an exception when creating a plane with points that converge to the same point");
    }
    // TC03: test for a vector with all coordinates as 0
}


