package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable{
    private final List<Intersectable> geomtreyList = new LinkedList<Intersectable>();

    public Geometries(){}

    public Geometries(Intersectable... geometries){
        add(geometries);
    }

    public void add(Intersectable... geometries){
        for (Intersectable geometry : geometries) {
            geomtreyList.add(geometry);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
