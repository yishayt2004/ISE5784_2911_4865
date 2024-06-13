package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * RadialGeometry class represents the radius of a radial geometry in 3D Cartesian coordinate
 * system
 */
public class RadialGeometry implements Geometry {
    public double radius;

    /**
     * RadialGeometry constructor receiving a radius
     * @param radius radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        this.radius = radius;
    }

    /**
     * @return the radius of the radial geometry
     */
    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return List.of();
    }
}
