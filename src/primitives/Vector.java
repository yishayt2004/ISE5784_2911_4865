package primitives;

public class Vector extends Point {

    public Vector(double x, double y, double z) {
        super(x, y, z);
        try {
            if (xyz.equals(Point.ZERO)) {
                throw new IllegalArgumentException("Vector cannot be Point(0,0,0)");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

    public Vector(Double3 xyz) {
        super(xyz);
        try {
            if (xyz.equals(Point.ZERO)) {
                throw new IllegalArgumentException("Vector cannot be Point(0,0,0)");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Vector add(Vector v1) {

        return new Vector(xyz.d1 + v1.xyz.d1, xyz.d2 + v1.xyz.d2, xyz.d3 + v1.xyz.d3);
    }

    public Vector scale(double scalingFactor) {
        return new Vector(xyz.d1 * scalingFactor, xyz.d2 * scalingFactor, xyz.d3 * scalingFactor);
    }
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }


    public double length() {
        return Math.sqrt(lengthSquared());
    }


    public int dotProduct(Vector v3) {
        return (int) (xyz.d1 * v3.xyz.d1 + xyz.d2 * v3.xyz.d2 + xyz.d3 * v3.xyz.d3);
    }

    public Vector crossProduct(Vector v2) {
        return new Vector(
                xyz.d2 * v2.xyz.d3 - xyz.d3 * v2.xyz.d2,
                xyz.d3 * v2.xyz.d1 - xyz.d1 * v2.xyz.d3,
                xyz.d1 * v2.xyz.d2 - xyz.d2 * v2.xyz.d1);

    }

    public Vector normalize() {
        double len = length();
        return new Vector(xyz.d1 / len, xyz.d2 / len, xyz.d3 / len);
    }

}