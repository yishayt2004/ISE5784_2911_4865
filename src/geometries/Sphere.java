package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point centerOfSphere = this.center;
        Vector directionOfRay = r


        // calculate the vector of the ray



        return null;
    }




}
