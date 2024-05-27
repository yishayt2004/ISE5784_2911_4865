package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal()
    {
        // ============ Equivalence Partitions Tests ==============
        // TC01: test for a point on the sphere
        Sphere sphere = new Sphere(1, new Point(1, 0, 0));
        assertEquals(new Vector(1, 0, 0), sphere.getNormal(new Point(2, 0, 0)), "Bad normal to sphere");
    }

    @Test
    void findIntersections() {
        Sphere sphere = new Sphere(1, new Point(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        assertEquals(2, sphere.findIntersections(new Ray(new Point(-0.5, 0, 0), new Vector(1, 0, 0))).size(), "Wrong number of points");

    }
}