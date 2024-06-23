package geometries;
import java.util.List;
import java.util.LinkedList;
import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * Interface Intersectable is
 */
public abstract class Intersectable {
    public List<Point> findIntersections(Ray ray);

    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) {return true;}
            if (o == null || getClass() != o.getClass()) {return false;}
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        public List<GeoPoint> findGeoIntersections(Ray ray) {
           return findGeoIntersectionsHelper(ray);
        }

        protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
            return null;
        }
    }
}
