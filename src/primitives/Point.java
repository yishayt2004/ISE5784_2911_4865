package primitives;

public class Point {

    protected Double3 xyz;

    public static Point ZERO = new Point(0, 0, 0);

    public Point (double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }

    public Point(Double3 xyz) {
        this.xyz = xyz;
    }

    public Vector subtract(Point p1) {
        return new Vector(xyz.d1 - p1.xyz.d1, xyz.d2 - p1.xyz.d2, xyz.d3 - p1.xyz.d3);
    }


    public Point add(Vector v1) {
        return new Point(xyz.d1 + v1.xyz.d1, xyz.d2 + v1.xyz.d2, xyz.d3 + v1.xyz.d3);
    }

    public double distanceSquared(Point p1) {
        double dx = xyz.d1 - p1.xyz.d1;
        double dy = xyz.d2 - p1.xyz.d2;
        double dz = xyz.d3 - p1.xyz.d3;
        return dx * dx + dy * dy + dz * dz;
    }

    public double distance(Point p1) {
        return  Math.sqrt(distanceSquared(p1));
    }



}
