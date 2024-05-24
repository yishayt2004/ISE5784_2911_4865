package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
/**
 * Interface Intersectable is
 */
public interface Intersectable {

    /**
     * @param ray
     * @return list of intersection points
     */
    public List<Point> findIntersections(Ray ray);
}
