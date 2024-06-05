package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    @Test
    void findIntersections() {
        Triangle triangle = new Triangle(new Point(0.01, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));
        Ray ray;

        ray = new Ray(new Point(0.1, 0.1, 0.1), new Vector(0, 0, -1));
        //assertNull( triangle.findIntersections(ray));
        assertEquals(1, (triangle.findIntersections(ray)).size());

       // TC01: a ray that starts inside the triangle and cuts the triangle (4 points)
        ray = new Ray(new Point(0.2, 0.2, 0.1), new Vector(0, 0, -1));
        List<Point> intersections1 = triangle.findIntersections(ray);
        assertNotNull(intersections1);
        assertEquals(1, intersections1.size());

        // TC02: a ray that starts outside the triangle in front of the edge (0 points)
        ray = new Ray(new Point(-1, -1, 1), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray));

        // TC03: a ray that starts outside the triangle in front of the vertex (0 points)
        ray = new Ray(new Point(-1, -1, 0), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray));

        //TC04: a ray that starts on the edge
//        ray = new Ray(new Point(0.5, 0, 0), new Vector(0, 0, -1));
//        assertNull(triangle.findIntersections(ray));
//

        // TC06: a ray that starts on the continuation of the vertex (0 points)

        ray = new Ray(new Point(-1, -1, 0), new Vector(0, 0, -1));
        assertNull(triangle.findIntersections(ray));
    }



}