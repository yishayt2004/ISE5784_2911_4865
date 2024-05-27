package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Tube class represents a tube in 3D Cartesian coordinate
 * system
 */
public class Tube extends RadialGeometry{

   protected double Ray;

    /**
     * Tube constructor receiving a radius
     *
     * @param Ray radius of the tube
     * @param ray
     */
    public Tube(double Ray, primitives.Ray ray) {
        super(Ray);
        this.Ray = Ray;
    }
    @Override
    public Vector getNormal(Point p) {
        return null;
    }
}
