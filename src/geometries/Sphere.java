package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
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
    public List<Point> findIntersections(Ray ray)
    {
        List<Point> intersections = null;
        Point  o = center ;
        Point P0= ray.getPoint(0); // the start point of the ray

        Vector u = o.subtract(P0);// the vector from the start point of the ray to the center of the sphere
        double t= u.dotProduct(ray.getDirection());
        double d = (double) Math.sqrt(u.lengthSquared()-t*t);// the distance between the center of the sphere and the ray
        double distance=  (double) Math.sqrt(this.getRadius()*this.getRadius()-d*d);
        if(d>=this._radius )
            return null;

        double t1 = t-distance;// the distance between the start point of the ray and the first intersection point
        double t2 = t+distance;// the distance between the start point of the ray and the second intersection point
        if(t1>0 && t2>0)
            return List.of(ray.getPoint(t1),ray.getPoint(t2));
        if(t2>0)
            return List.of(ray.getPoint(t2));
        if(t1>0)
            return List.of(ray.getPoint(t1));
        return null;
    }




}
