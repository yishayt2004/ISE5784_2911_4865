package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

public class Cylinder {
    public double _height;
    public double _radius;

    public Cylinder(Ray ray, double height, double radius) {
        _height = height;
        _radius = radius;
    }

    public Vector getNormal(Point point) {
        return null;
    }
}
