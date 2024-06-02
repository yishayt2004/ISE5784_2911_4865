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
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 1, 0))),
                "Ray's line out of sphere");
        // TC02: Ray starts before and crosses the sphere (2 points)
        List<Point> intersectionsTC02 = sphere.findIntersections(new Ray(new Point(-0.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(2, intersectionsTC02.size(),
                "Wrong number of points");
        assertTrue(intersectionsTC02.contains(new Point(0, 0, 0)),
                "Intersection point not found");
        assertTrue(intersectionsTC02.contains(new Point(2, 0, 0)),
                "Intersection point not found");
        // TC03: Ray starts inside the sphere (1 point)
        List<Point> intersectionsTC03 = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, intersectionsTC03.size(),
                "Wrong number of points");
        assertTrue(intersectionsTC03.contains(new Point(2, 0, 0)),
                "Intersection point not found");
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");
        // =============== Boundary Values Tests ==================
        //**** Group: Ray's line crosses the sphere (but not the center)
        //TC11: Ray starts at sphere and goes inside (1 points)
        List<Point> intersectionsTC11 = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, intersectionsTC11.size(),
                "Wrong number of points");
        assertTrue(intersectionsTC11.contains(new Point(2, 0, 0)),
                "Intersection point not found");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        List<Point> intersectionsTC13 = sphere.findIntersections(new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(2, intersectionsTC13.size(),
                "Wrong number of points");
        assertTrue(intersectionsTC13.contains(new Point(0, 0, 0)),
                "Intersection point not found");
        assertTrue(intersectionsTC13.contains(new Point(2, 0, 0)),
                "Intersection point not found");
        // TC14: Ray starts at sphere and goes inside (1 points)
        List<Point> intersectionsTC14 = sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, intersectionsTC14.size(),
                "Wrong number of points");
        assertTrue(intersectionsTC14.contains(new Point(2, 0, 0)),
                "Intersection point not found");
        // TC15: Ray starts inside (1 points)
        List<Point> intersectionsTC15 = sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, intersectionsTC15.size(),
                "Wrong number of points");
        assertTrue(intersectionsTC15.contains(new Point(2, 0, 0)),
                "Intersection point not found");
        // TC16: Ray starts at the center (1 points)
        List<Point> intersectionsTC16 = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(1, intersectionsTC16.size(),
                "Wrong number of points");
        assertTrue(intersectionsTC16.contains(new Point(2, 0, 0)),
                "Intersection point not found");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 1, 0), new Vector(1, 0, 0))),
                "Ray's line out of sphere");
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(0, 1, 0))),
                "Ray's line out of sphere");
    }
}