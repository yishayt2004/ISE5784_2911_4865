package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
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
}