package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
    void testFindIntersections() {
        Sphere sphere = new Sphere(1, new Point(1, 0, 0));
        // ============ Equivalence Partitions Tests ==============
        // TC01: all the ray is out of the sphere
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 0), new Vector(1, 0, 0))), "ERROR: the ray is out of the sphere");
        // TC02: the ray starts before the sphere and g oes through the sphere
        assertEquals(1, sphere.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0))).size(), "ERROR: the ray starts before the sphere and goes through the sphere");
        // TC03: the ray starts inside the sphere
        assertEquals(1, sphere.findIntersections(new Ray(new Point(1.5, 0, 0), new Vector(1, 0, 0))).size(), "ERROR: the ray starts inside the sphere");
        // TC04: the ray starts after the sphere
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))), "ERROR: the ray starts after the sphere");
        // =============== Boundary Values Tests ==================
        // TC11: the ray starts on the sphere and goes through the sphere
        assertNull(sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0))), "ERROR: the ray starts on the sphere and goes through the sphere");
        // TC12: the ray starts on the sphere and goes outside the sphere
        assertNull(sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0, 0))), "ERROR: the ray starts on the sphere and goes outside the sphere");
        // TC13: the ray starts on the sphere and goes inside the sphere
        assertEquals(1, sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(0, 0, 1))).size(), "ERROR: the ray starts on the sphere and goes inside the sphere");



    }
}