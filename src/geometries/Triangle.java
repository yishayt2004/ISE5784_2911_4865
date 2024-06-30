package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;
import geometries.Intersectable.GeoPoint;


public class Triangle extends Polygon {
    public Triangle(Point p1, Point p2, Point p3)
    {
        super(p1, p2, p3);
    }

    @Override
    public List<Point> findIntersections(Ray ray)//ray to triangle intersection
    {
        List<Point> intersection =  plane.findIntersections(ray);//ray to plane intersection
        if (intersection == null)//ray is parallel to the triangle
            return null;

        Vector v1= vertices.get(0).subtract(ray.getPoint(0));//p0 to p1 vector
        Vector v2= vertices.get(1).subtract(ray.getPoint(0));//p0 to p2 vector
        Vector v3= vertices.get(2).subtract(ray.getPoint(0));//p0 to p3 vector

        Vector n1= v1.crossProduct(v2).normalize();//normal to p0p1 and p0p2
        Vector n2= v2.crossProduct(v3).normalize();//normal to p0p2 and p0p3
        Vector n3= v3.crossProduct(v1).normalize();//normal to p0p3 and p0p1

        double t1= n1.dotProduct(ray.getDirection());
        double t2= n2.dotProduct(ray.getDirection());
        double t3= n3.dotProduct(ray.getDirection());

       if(isZero(t1) || isZero(t2) || isZero(t3))//ray is parallel to the triangle
            return null;

       if(t1>0 && t2>0 && t3>0 || t1<0 && t2<0 && t3<0 )//ray is parallel to the triangle
            return intersection;


        return null;

    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        List<Point> intersections = findIntersections(ray);
        if (intersections == null) return null;
        List<GeoPoint> result = new LinkedList<>();
        for (Point p : intersections) {
            result.add(new GeoPoint(this, p));
        }
        return result;

    }


}