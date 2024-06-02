package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void testAdd() {
        // Create some geometries
        Sphere sphere = new Sphere(2, new Point(0, 0, 0));
        Plane plane = new Plane(
                new Point(0, 0, -3),
                new Point(1, 0, 3),
                new Point(1, 1, -3));

        // Create Geometries object
        Geometries g = new Geometries();

        // Add geometries
        g.add(sphere, plane);

        // Ensure they are added correctly
        assertEquals(2, g.getGeomtreyList().size(), "Wrong number of geometries");

    }

    @Test
    void testFindIntersections() {
        Geometries g = new Geometries(new Sphere(2, new Point(0, 0, 0)),
                new Plane(
                        new Point(0, 0, -3),
                        new Point(1, 0, 3),
                        new Point(1, 1, -3)),
                new Triangle(
                        new Point(3, 0, 0),
                        new Point(0, 3, 0),
                        new Point(2, 2, 3)));
//                new Polygon(
//                        new Point(-4, 0, -1),
//                        new Point(0, -4, 0),
//                        new Point(0, -4, 3),
//                        new Point(-4, 0, 2)));

        // ============ Equivalence Partitions Tests ==============
        // TC01: couple of geometries
        List<Point> result = g.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 1, 1)));
        assertEquals(2, result.size(), "Wrong number of points");
        //=============boundary values tests================
        // TC02: No one intersect
//        List<Point> result2 = g.findIntersections(new Ray(new Point(0, 0, 0), new Vector(-1, -1, -1)));
//        assertNull(result2, "Wrong number of points");
        // TC03: All intersect
        List<Point> result3 = g.findIntersections(new Ray(new Point(0, 0, 0), new Vector(1, 0, 0)));
        assertEquals(2, result3.size(), "Wrong number of points");
        // TC04: just one intersect

        List<Point> result4 = g.findIntersections(new Ray(new Point(0, 0, 0), new Vector(0, 1, 0)));

        assertEquals(1, result4.size(), "Wrong number of points");

}}