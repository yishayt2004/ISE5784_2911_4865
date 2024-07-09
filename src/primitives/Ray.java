package primitives;

import java.util.List;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

/**
 * Class Ray is the basic class representing a ray in the 3D space
 */
public class Ray
{
    private static final double DELTA = 0.1;
    private Point head;
    private Vector direction;

    /**
     * Ray constructor receiving a point and a vector
     * @param point head of the ray
     * @param direction direction of the ray
     * constructor sets the direction to normalized value
     */
    public Ray(Point point, Vector direction)
    {
        this.head = point;
        this.direction = direction.normalize();
    }





    /**
     * this function checks if the ray is equal to another ray
     * @param obj the other ray
     */
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return head.equals(other.head) && direction.equals(other.direction);
    }

    public Vector getDirection() {
        return direction;
    }

    public Point getHead() {
        return head;
    }


    @Override
    public String toString()
    {

        return "Ray:" +
                "head=" + head +
                ", direction=" + direction ;
    }



    public Point getPoint(double t)//return the point on the ray at distance t from the head
    {
        if(t==0)//if t=0 return the head of the ray
        {
            return head;
        }
        return head.add(direction.scale(t));
    }

    /**
     * find the closest point to the head of the ray
     * @param ray list of points
     * @return the closest point to the head of the ray
     */

    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList()).point;
    }

        /**
        * find the closest point to the head of the ray
        * @param ray list of points
        * @return the closest point to the head of the ray
        */

        public GeoPoint findClosestGeoPoint(List<GeoPoint> list)
        {
            GeoPoint closestPoint;
            double minDistance, temp;

            // null list
            if (list==null)
            {
                return null;
            }

            closestPoint = list.get(0);
            minDistance = head.distanceSquared(closestPoint.point);

            for (Intersectable.GeoPoint p : list)
            {
                temp = head.distanceSquared(p.point);

                if (temp < minDistance)
                {
                    minDistance = temp;
                    closestPoint = p;
                }
            }

            return closestPoint;
        }
    public Ray(Point p0, Vector direction, Vector normal) {
        double dotProduct = direction.dotProduct(normal);
        this.head = p0.add(normal.scale(dotProduct > 0 ? DELTA : -DELTA));
        this.direction = direction.normalize();
    }





}
