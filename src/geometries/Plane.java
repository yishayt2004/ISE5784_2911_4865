package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.Collections;
import java.util.List;

import static primitives.Util.*;

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

    /**
     * Getter for the point on the plane
     * @return the normal to the plane
     */
    public Vector getNormal() {
        return normal;
    }

    /**
     * Getter for the normal to the plane
     * @return the point on the plane
     */
@Override
public List<Point> findIntersections(Ray ray)
{
    // according to: t = (n* (p - p0)) / n * v
    Vector numerator = q.subtract(ray.getHead()); // vector from the head of the ray to the reference point of the plane
    double denominator = normal.dotProduct(ray.getDirection()); // the dot product of the normal and the direction of the ray

    if(isZero(denominator)) // if the direction parallel to the plane's normal
        return null;

    double t = alignZero(normal.dotProduct(numerator) / denominator); // the distance
    if(t < 0 || isZero(t))
        return null;

    return List.of(ray.getPoint(t)); // return the intersection point
}

}
