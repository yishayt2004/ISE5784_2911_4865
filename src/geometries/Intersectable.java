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

    public static class GeoPoint {
        public Geometry geometry;
        public Point point;

        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (!(obj instanceof GeoPoint)) return false; // if obj is not GeoPoint
            GeoPoint other = (GeoPoint)obj; // cast obj to GeoPoint
            return this.geometry.equals(other.geometry) && this.point.equals(other.point); // check if the geometry and point are equal

        }
        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }

        public List<GeoPoint> findGeoIntersections(Ray ray) {
            findGeoIntersectionsHelper(ray);
            return null;
        }

        protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
            return null;
        }
    }
}
