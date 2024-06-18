package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

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


    @Test
    void testFindClosestPoint() {
        // ============ Equivalence Partitions Tests ==================
        Ray ray = new Ray(new Point(1, 1, 1), new Vector(1, 0, 0));
        // TC01: The closest point is in the middle of the list

        List<Point> points = List.of(
                new Point(-1, 0, 0),
                new Point(1, 0, 0),
                new Point(4, 0, 0)
        );
        assertEquals(new Point(1, 0, 0), ray.findClosestPoint(points));
        //============= Boundary Values Tests =============================
        // TC02: The list is empty
        assertNull(ray.findClosestPoint( List.of()));
        // TC03: The closest point is the first point in the list
        points = List.of(
                new Point(1, 0, 0),
                new Point(2, 0, 0),
                new Point(3, 0, 0)
        );
       assertEquals(new Point(1, 0, 0), ray.findClosestPoint(points));
        // TC04: The closest point is the last point in the list
        points = List.of(
                new Point(0, 0, 0),
                new Point(2, 0, 0),
                new Point(1, 0, 0)
        );
        assertEquals(new Point(1, 0, 0), ray.findClosestPoint(points));
    }
}