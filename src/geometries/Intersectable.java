package geometries;
import java.util.List;
import java.util.LinkedList;
import primitives.Point;
import primitives.Ray;

import java.util.List;
/**
 * Interface Intersectable is
 */
public interface Intersectable {
    public List<Point> findIntersections(Ray ray);

}
