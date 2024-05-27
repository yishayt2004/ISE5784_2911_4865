package primitives;

import java.util.Objects;

/**
 * This class will serve all primitive classes based on three numbers
 * @author Dan Zilberstein
 */
public class Point {

    protected Double3 xyz;


    public static Point ZERO = new Point(0, 0, 0);

    /**
     * Constructor to initialize Point based on three double values
     * @param x first number value
     * @param y second number value
     * @param z third number value
     */
    public Point (double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }


    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    @Override
    public String toString() {
        return "Point: " + xyz.toString();
    }

    /**
    * subtracts the coordinates of the point from the coordinates of another point
     * @param p1 the point to subtract from
     */

    public Vector subtract(Point p1) {
        return new Vector(this.xyz.subtract(p1.xyz));
    }

    /**
     * adds the coordinates of the point to the coordinates of a vector
     * @param v1 the vector to add
     */
    public Point add(Vector v1) {
        return new Point(xyz.add(v1.xyz));
    }

    /**
     * calculates the square of the distance between two points
     * @param p1 the point to calculate the distance from
     */
    public double distanceSquared(Point p1) {
        double dx = xyz.d1 - p1.xyz.d1;
        double dy = xyz.d2 - p1.xyz.d2;
        double dz = xyz.d3 - p1.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    /**
     * calculates the distance between two points
     * @param p1 the point to calculate the distance from
     */
    public double distance(Point p1) {
        return  Math.sqrt(distanceSquared(p1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point point)) return false;
        return Objects.equals(xyz, point.xyz);
    }
}
