package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void testGetNormal() {
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
        List<Point> intersectionsTC02 = sphere.findIntersections(new Ray(new Point(-0.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(2, intersectionsTC02.size(), "Wrong number of points");
        assertTrue(intersectionsTC02.contains(new Point(0, 0, 0)), "Intersection point not found");
        assertTrue(intersectionsTC02.contains(new Point(2, 0, 0)), "Intersection point not found");
        // TC03: Ray starts inside the sphere (1 point)
        List<Point> intersectionsTC03 = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, intersectionsTC03.size(), "Wrong number of points");
        assertTrue(intersectionsTC03.contains(new Point(2, 0, 0)), "Intersection point not found");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))), "Ray's line out of sphere");


    }
}