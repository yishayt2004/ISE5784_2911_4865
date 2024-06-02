package geometries;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        // ============ Equivalence Partitions Tests ==============

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


