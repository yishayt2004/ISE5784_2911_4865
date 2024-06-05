package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Polygon;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Testing Polygons
 * @author Dan
 */

public class PolygonTests {
    /**
     * Delta value for accuracy when comparing the numbers of type 'double' in
     * assertEquals
     */
    private final double DELTA = 0.000001;

    /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                        new Point(1, 0, 0),
                        new Point(0, 1, 0),
                        new Point(-1, 1, 1)),
                "Failed constructing a correct polygon");

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertix on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertice on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here - using a quad
        Point[] pts =
                { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
        Polygon pol = new Polygon(pts);
        // ensure there are no exceptions
        assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
        // generate the test result
        Vector result = pol.getNormal(new Point(0, 0, 1));
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Polygon's normal is not a unit vector");
        // ensure the result is orthogonal to all the edges
        for (int i = 0; i < 3; ++i)
            assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), DELTA,
                    "Polygon's normal is not orthogonal to one of the edges");
    }

    @Test
    public void testFindIntersections()
    {
        Polygon polygon = new Polygon(new Point(2, 0, 0), new Point(0, 2, 0),
                new Point(-2, 0, 0), new Point(0, -2, 0));
        Point a = new Point(0.5, 0.5, -1);
        Point b = new Point(2, 0, 1);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Point in the polygon
        List<Point> result01 = polygon.findIntersections(new Ray(a,
                new Vector(-0.5, -1.5, 1)));
        assertEquals(1, result01.size(), "Wrong number of points 01");

        assertEquals(List.of(new Point(0, -1, 0)), result01, "Ray crosses polygon 01");
        // TC02: Point doesn't in the polygon
        assertNull(polygon.findIntersections(new Ray(a,
                        new Vector(2.5, 2.5, 1))),
                "Ray's line doesn't intersect polygon 02");

        // TC03: Point in front of the polygon vertex
        assertNull(polygon.findIntersections(new Ray(a,
                        new Vector(2.5, -0.5, 1))),
                "Ray's line doesn't intersect polygon 03");

        // =============== Boundary Values Tests ==================

        // TC11: Point in the polygon edge
        assertNull(polygon.findIntersections(new Ray(a,
                        new Vector(0.5, 0.5, 1))),
                "Ray's line doesn't intersect polygon 11");

        // TC12: Point in the polygon vertex
        assertNull(polygon.findIntersections(new Ray(a,
                        new Vector(1.5, -0.5, 1))),
                "Ray's line doesn't intersect polygon 12");

        // TC13: Point in the continuation of polygon edge
        assertNull(polygon.findIntersections(new Ray(a,
                        new Vector(2.5, -1.5, 1))),
                "Ray's line doesn't intersect polygon 13");
    }

    }

