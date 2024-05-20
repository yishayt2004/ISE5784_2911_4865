package primitives;

/**
 * Class Vector is the basic class representing a vector in the 3D space
 */
public class Vector extends Point {

    /**
     * Vector constructor receiving 3 coordinates
     * @param x x coordinate
     * @param y y coordinate
     * @param z z coordinate
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
            if (x==0 && y==0 && z==0){
                throw new IllegalArgumentException("Vector cannot be Point(0,0,0)");
            }


    }

    @Override
    public String toString(){
        return "Vector: " + xyz.toString();
    }

    /**
     * Vector constructor receiving a point
     * @param xyz point with 3 coordinates
     */
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
/**
     * Vector constructor receiving a point
     * @param xyz point with 3 coordinates
     */
    public Vector add(Vector v1) {

        return new Vector(xyz.add(v1.xyz));
    }

    /**
     * scales the vector by a scalar
     * @param scalingFactor the scalar to scale the vector by
     */
    public Vector scale(double scalingFactor) {
        return new Vector(xyz.d1 * scalingFactor, xyz.d2 * scalingFactor, xyz.d3 * scalingFactor);
    }

    /**
     * calculates the square of the length of the vector
     * @return the square of the length of the vector
     */
    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    /**
     * calculates the length of the vector
     * @return the length of the vector
     */
    public double length() {
        return Math.sqrt(lengthSquared());
    }

    /**
     * calculates the dot product of the vector with another vector
     * @param v3 the other vector
     */
    public double dotProduct(Vector v3) {
        return  (xyz.d1 * v3.xyz.d1 + xyz.d2 * v3.xyz.d2 + xyz.d3 * v3.xyz.d3);
    }

    /**
     * calculates the cross product of the vector with another vector
     * @param v2 the other vector
     */
    public Vector crossProduct(Vector v2) {
        return new Vector(
                xyz.d2 * v2.xyz.d3 - xyz.d3 * v2.xyz.d2,
                xyz.d3 * v2.xyz.d1 - xyz.d1 * v2.xyz.d3,
                xyz.d1 * v2.xyz.d2 - xyz.d2 * v2.xyz.d1);

    }

    /**
     * normalizes the vector
     * @return the normalized vector

     */
    public Vector normalize() {
        double len = length();
        return new Vector(xyz.d1 / len, xyz.d2 / len, xyz.d3 / len);
    }


}