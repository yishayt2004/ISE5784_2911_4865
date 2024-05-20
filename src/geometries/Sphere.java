package geometries;

import primitives.Point;
import primitives.Vector;

/**
 * Sphere class represents a sphere in 3D Cartesian coordinate
 * system
 */
public class Sphere extends RadialGeometry {
    protected Point center;



    /**
     * RadialGeometry constructor receiving a radius
     *
     * @param radius radius of the radial geometry
     * @param point
     */
    public Sphere(double radius, Point point) {
        super(radius);
        center = point;
    }

    public Vector getNormal(Point point) {
        return point.subtract(center).normalize();
    }


}
