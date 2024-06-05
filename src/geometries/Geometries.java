package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{

    private final List<Intersectable> geomtreyList = new LinkedList<Intersectable>();

    public Geometries(){}
    // ***************** Getters ********************** //
    public List<Intersectable> getGeomtreyList() {
        return geomtreyList;
    }

    public Geometries(Intersectable... geometries){
        add(geometries);
    }
    // ***************** Operations ******************** //
    public void add(Intersectable... geometries){
        for (Intersectable geometry : geometries) {
            geomtreyList.add(geometry);
        }
    }

    // ***************** Operations ******************** //
    @Override
    public List<Point> findIntersections(Ray ray)
    {
        if (ray == null) return null; // Handle null ray
        List<Point> intersections = new LinkedList<>();
        for (Intersectable geometry : geomtreyList)
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



}
