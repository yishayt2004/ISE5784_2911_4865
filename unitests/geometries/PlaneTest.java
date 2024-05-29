package geometries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
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
        assertEquals(new Vector(0, 0, 1).normalize(),
                p.getNormal(new Point(1, 1, 1)),
                "Bad normal to plane");

    }

    @Test
    void testFindIntersections() {
        Plane plane = new Plane(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        Point p111 = new Point(1, 1, 1);
        Vector v001 = new Vector(0, 0, 1);
        Vector v111 = new Vector(1, 1, 1);
        // ============ Equivalence Partitions Tests ==============
        // TC01: A ray that starts out of the plane, not parallel to the plane, makes a non-right angle wite the plane, and cuts the plane
        assertEquals(1,
                plane.findIntersections(new Ray(p111,new Vector(-1,-1,-1))).size(),
                "ERROR: A ray that starts out of the plane, not parallel to the plane," +
                        " makes a non-right angle with the plane, and cuts the plane");
        // TC02: A ray that starts out of the plane, not parallel to the plane, makes a non-right angle with the plane, and does not cut the plane
        assertNull(plane.findIntersections(new Ray(p111, v111)),
                "ERROR: A ray that starts out of the plane, not parallel to the plane," +
                        " makes a non-right angle with the plane, and does not cut the plane");
        // =============== Boundary Values Tests ==================
        // TC11: A ray that parallel the plane, and start outside the plane
        assertNull(plane.findIntersections(new Ray(p111, v001)),
                "ERROR: A ray that parallel the plane, and start outside the plane");
        // TC12: A ray that parallel the plane, and start in the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), v001)),
                "ERROR: A ray that parallel the plane, and start in the plane");
        // TC13: A ray that aligned to the plane and start before the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, -1), v001)),
                "ERROR: A ray that aligned to the plane and start before the plane");
        // TC14: A ray that aligned to the plane and start in the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 0), v001)),
                "ERROR: A ray that aligned to the plane and start in the plane");
        // TC15: A ray that aligned to the plane and start after the plane
        assertNull(plane.findIntersections(new Ray(new Point(0, 0, 1), v001)),
                "ERROR: A ray that aligned to the plane and start after the plane");
        //TC16: A ray that is neither parallel nor perpendicular to the plane but starts inside the plane
        assertNull(plane.findIntersections(new Ray(new Point(0.5, 0.5, 0.5), v111)),
                "ERROR: A ray that is neither parallel nor perpendicular to the plane but starts inside the plane");
        //TC17: A ray that is neither parallel nor perpendicular to the plane but starts outside the plane
        assertNull(plane.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 1, 1))),
                "ERROR: One case that is similar to the previous case, but the beginning of the fund is exactly" +
                        "in the reference code of The plane (the code stored in the object of the plane in addition to the normal vector");




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


/**
 * Test method for .
      */