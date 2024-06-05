package renderer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import geometries.Intersectable;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;

import primitives.*;
import renderer.*;

public class CameraIntegrationTests
{

    private int sumOfIntersections(Camera camera, Intersectable intersectable)
    {
        int nx = 3, ny = 3;
        int counter = 0;

        for (int row = 0; row < nx; row++)
        {
            for (int column = 0; column < ny; column++)
            {
                Ray ray = camera.constructorRay(nx, ny, column, row);
                var intersections = intersectable.findIntersections(ray);
                if (intersections != null)
                {
                    counter += intersections.size();
                }
            }
        }

        return counter;
    }

    /**
     * The testSphereIntegration method tests the sumOfIntersections method for Sphere objects.
     * It includes several test cases to verify the correctness of the method.
     */
    @Test
    public void testSphereIntegration() throws CloneNotSupportedException {
        // TC01: 2 intersections
        Camera c1 = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, 0))
                .setVpSize(9, 9)
                .setVpDistance(1)
                .build();

        Sphere s1 = new Sphere(1, new Point(0, 0, -3));
        assertEquals(2, sumOfIntersections(c1, s1), "wrong number of intersections sphere 01");

        // TC02: 18 intersections
      Camera c2 = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, 1.5))
                .setVpSize(1, 1)
                .setVpDistance(1)
                .build();
        Sphere s2 = new Sphere(2.5, new Point(0, 0, -2.5));
        assertEquals(18, sumOfIntersections(c2, s2), "wrong number of intersections sphere 02");

        // TC03: 10 intersections
        Camera c3 = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, 0.5))
                .setVpSize(3, 3)
                .setVpDistance(1)
                .build();
        Sphere s3 = new Sphere(2, new Point(0, 0, -2));
        assertEquals(10, sumOfIntersections(c3, s3), "wrong number of intersections sphere 03");

        // TC04: 9 intersections
        Camera c4 = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, -3))
                .setVpSize(9, 9)
                .setVpDistance(1)
                .build();
        Sphere s4 = new Sphere(4, new Point(0, 0, -4));
        assertEquals(9, sumOfIntersections(c4, s4), "wrong number of intersections sphere 04");

        // TC05: no intersections
        Camera c5 = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, 0))
                .setVpSize(9, 9)
                .setVpDistance(1)
                .build();
        Sphere s5 = new Sphere(0.5, new Point(0, 0, 1));
        assertEquals(0, sumOfIntersections(c5, s5), "wrong number of intersections sphere 05");
    }


        /**
         * Tests the sum of intersections between a given camera and plane objects.
         */

    @Test
    public void testPlaneIntegration() throws CloneNotSupportedException {

        Camera camera = Camera.getBuilder().
                setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, 0))
                .setVpSize(3, 3)
                .setVpDistance(1)
                .build();

        Camera camera2 = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
                .setLocation(new Point(0, 0, 0))
                .setVpSize(3, 3)
                .setVpDistance(1)
                .build();


        // TC01: 9 intersections
        Plane p1 = new Plane(new Point(0, 0, -3), new Vector(0, 0, 1));
        assertEquals(9, sumOfIntersections(camera, p1),
                "wrong number of intersections plane 01");

        // TC02: 9 intersections
        Plane p2 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 2));
        assertEquals(9, sumOfIntersections(camera, p2),
                "wrong number of intersections plane 02");

        // TC03: 6 intersections
        Plane p3 = new Plane(new Point(0, 0, -5), new Vector(0, 1, 1));
        assertEquals(6, sumOfIntersections(camera, p3),
                "wrong number of intersections plane 03");

        // TC04: no intersections
        Plane p4 = new Plane(new Point(0, 0, 5), new Vector(0, 0, -1));
        assertEquals(0, sumOfIntersections(camera, p4),
                "wrong number of intersections plane 04");
    }

    /**
     * Tests the sum of intersections between a given camera and triangle objects.
     */
    @Test
    public void testTriangleIntegration() throws CloneNotSupportedException {
        Camera camera = Camera.getBuilder()
                .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setLocation(new Point(0, 0, 0))
                .setVpSize(9, 9)
                .setVpDistance(1)
                .build();

        // TC01: 1 intersections
        Triangle t1 = new Triangle(
                new Point(0, 1, -2),
                new Point(1, -1, -2),
                new Point(-1, -1, -2));
        assertEquals(1,
                sumOfIntersections(camera, t1),
                "wrong number of intersections triangle 01");

        // TC01: 2 intersections
        Triangle t2 = new Triangle
                (new Point(0, 20, -2),
                        new Point(1, -1, -2),
                        new Point(-1, -1, -2));

        assertEquals(2,
                sumOfIntersections(camera, t2),
                "wrong number of intersections triangle 02");
    }
}
