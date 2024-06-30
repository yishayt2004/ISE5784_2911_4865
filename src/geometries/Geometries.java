package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Geometries extends Intersectable{

    private final List<Intersectable> geometries = new LinkedList<Intersectable>();

    public Geometries(){}

    public Geometries(Intersectable... geometries){
        add(geometries);
    }
    // ***************** Operations ******************** //
    public void add(Intersectable... geometries){
        this.geometries.addAll(Arrays.asList(geometries));
    }

    // ***************** Operations ******************** //
    @Override
    public List<Point> findIntersections(Ray ray)
    {
        List<Point> intersections = new LinkedList<>();
        for (Intersectable geometry : geometries)
        { // Iterate over all geometries
            List<Point> geometryIntersections = geometry.findIntersections(ray); // Find intersections with current geometry
            if (geometryIntersections != null)
            { // If there are intersections
                intersections.addAll(geometryIntersections); // Add them to the list
            }
        }
        if (intersections.isEmpty()) return null; // If there are no intersections
        return intersections; // Return the list of intersections
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray)
    {
        if (geometries.isEmpty()) return null; // If there are no geometries
        List<GeoPoint> intersections = new LinkedList<>();
        for (Intersectable geometry : geometries)
        { // Iterate over all geometries
            List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray); // Find intersections with current geometry
            if (geometryIntersections != null)
            { // If there are intersections
                intersections.addAll(geometryIntersections); // Add them to the list
            }
        }
        if (intersections.isEmpty()) return null; // If there are no intersections
        return intersections; // Return the list of intersections
    }





}
