package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void findIntersections()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: The ray intersects the triangle (1 point)
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        //assertEquals(List.of(new Point(0.5, 0.5, 0)), triangle.findIntersections(new Ray(new Point(0.25, 0.25, -1), new Vector(0, 0, 1)), 0), "Bad intersection with triangle");

        // TC02: The ray does not intersect the triangle (0 points)


        assertNull(triangle.findIntersections(new Ray(new Point(0.25, 0.25, -1), new Vector(0, 0, -1))), " Bad intersection with triangle");
        //assertNull(1, triangle.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1))), "Bad intersection with triangle");

        // =============== Boundary Values Tests ==================
        // TC03: The ray intersects the triangle on edge (1 point)
       // assertEquals(1, triangle.findIntersections(new Ray(new Point(0.25, 0.15, -1), new Vector(0, 0, 1))).size(), "Bad intersection with triangle");

        // TC04: The ray intersects the triangle on vertex (1 point)
       // assertEquals(List.of(new Point(0, 0, 0)), triangle.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1)), 0), "Bad intersection with triangle");

        // TC05: The ray intersects the triangle on edge's continuation (0 points)
       // assertNull(triangle.findIntersections(new Ray(new Point(0, 0, -1), new Vector(0, 0, 1))), "Bad intersection with triangle";
    }
}