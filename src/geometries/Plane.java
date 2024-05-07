package geometries;

import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    private Point q;
    private Vector normal;

    public Plane(Point vertex, Point vertex1, Point vertex2) {
        this.q = vertex;
        this.normal = vertex1.subtract(vertex).crossProduct(vertex2.subtract(vertex)).normalize();
    }
    public Plane(Point vertex, Vector normal) {
        this.q = vertex;
        this.normal = normal.normalize();
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public Vector getNormal() {
        return null;
    }
}
