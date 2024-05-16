package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Class Plane is the basic class representing a plane in the 3D space
 */
public class Plane implements Geometry {
    private final Point q;
    private final Vector normal;

    /**
     * Plane constructor receiving 3 points
     * @param vertex point on the plane
     * @param vertex1 point on the plane
     * @param vertex2 point on the plane
     * normal is calculated by cross-product of the 2 vectors from vertex to vertex1 and vertex2
     */
    public Plane(Point vertex, Point vertex1, Point vertex2) {
        this.q = vertex;
        this.normal = vertex2.subtract(vertex).crossProduct(vertex1.subtract(vertex)).normalize();
    }
    /**
     * Plane constructor receiving a point and a normal
     * @param vertex point on the plane
     * @param normal normal to the plane
     * constructor sets the normal to normalized value
     */
    public Plane(Point vertex, Vector normal) {
        this.q = vertex;
        this.normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return normal;
    }

    public Vector getNormal() {
        return normal;
    }
}
