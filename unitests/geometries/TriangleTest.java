package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/*
 * Unit tests for geometries.Triangle class
 */
class TriangleTest {


    @Test
    void testFindIntersections() {
        // ============ Equivalence Partitions Tests ==============
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));
        // TC01: the intersection point is inside the triangle
        assertEquals(1, triangle.findIntersections(new Ray(new Point(0.25, 0.25, 0.25),
                new Vector(1, 1, 1))).size(),
                "ERROR: the intersection point is inside the triangle");
        // TC02: the intersection point is outside the triangle (in front of one of the vertices)
        assertNull(triangle.findIntersections(new Ray(new Point(1, 1, 1),
                new Vector(1, 1, 1))),
                "ERROR: the intersection point is outside the triangle (in front of one of the vertices)");
        // TC03: the intersection point is outside the triangle (in front of one of the edges)
        assertNull(triangle.findIntersections(new Ray(new Point(0.5, 0.5, 0.5),
                new Vector(1, 1, 1))),
                "ERROR: the intersection point is outside the triangle (in front of one of the edges)");
        //=============== Boundary Values Tests ==================
        // TC11: The point of intersection with the "contained" plane is on one of the ribs
        assertNull(triangle.findIntersections(new Ray(new Point(0.5, 0.5, 0),
                new Vector(1, 1, 1))),
                "ERROR: The point of intersection with the \"contained\" plane is on one of the ribs");
        // TC12: The point of intersection with the "contained" plane is on one of the vertices
        assertNull(triangle.findIntersections(new Ray(new Point(1, 0, 0),
                new Vector(1, 1, 1))),
                "ERROR: The point of intersection with the \"contained\" plane is on one of the vertices");
        // TC13: The point of intersection with the "contained" plane is on a continuation of one of the ribs
        assertNull(triangle.findIntersections(new Ray(new Point(0.5, 0.5, 0),
                new Vector(1, 1, 0))),
                "ERROR: The point of intersection with the \"contained\" plane is on a continuation of one of the ribs");

    }
}