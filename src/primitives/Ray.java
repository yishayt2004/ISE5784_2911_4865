package primitives;

/**
 * Class Ray is the basic class representing a ray in the 3D space
 */
public class Ray
{
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
        if(point==null || direction==null)
        {
            throw new IllegalArgumentException("point and direction cannot be null");
        }
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
}
