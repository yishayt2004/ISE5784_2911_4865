package geometries;

/**
 * RadialGeometry class represents the radius of a radial geometry in 3D Cartesian coordinate
 * system
 */
public class RadialGeometry {
    public double _radius;

    /**
     * RadialGeometry constructor receiving a radius
     * @param radius radius of the radial geometry
     */
    public RadialGeometry(double radius) {
        _radius = radius;
    }

    /**
     * @return the radius of the radial geometry
     */
    public double getRadius() {
        return _radius;
    }
}
