package primitives;

public class Ray {
    private Point head;
    private Vector direction;

    public Ray(Point point, Vector direction) {
        this.head = point;
        this.direction = direction.normalize();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return head.equals(other.head) && direction.equals(other.direction);
    }

    @Override
    public String toString() {
        return "Ray:" +
                "head=" + head +
                ", direction=" + direction ;
    }
}
