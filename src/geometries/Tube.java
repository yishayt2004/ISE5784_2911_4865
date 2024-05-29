package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;
/**
 * Tube class represents a tube in 3D Cartesian coordinate
 * system
 */
public class Tube extends RadialGeometry{

    protected Ray axis;
    /**
     * Tube constructor receiving a radius
     *
     * @param ray
     */
    public Tube(double radius, Ray ray) {
        super(radius);
        axis = ray;
    }

    public Vector getNormal(Point p) {

            // projection of P-O on the ray:
            double t = p.subtract(axis.getHead()).dotProduct(axis.getDirection());
            return  p.subtract(axis.getPoint(t)).normalize();
    }
}
