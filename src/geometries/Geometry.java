package geometries;

import primitives.Point;
import primitives.Vector;
/**
 * Interface Geometry is the basic interface for all geometric objects
 */

public interface Geometry extends Intersectable{
    /**
     * @param point
     * @return normal to the geometry at the point
     */
    public Vector getNormal(Point point);
}
