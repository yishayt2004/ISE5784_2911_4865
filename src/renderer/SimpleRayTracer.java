package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class SimpleRayTracer extends RayTracerBase {
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray){
        List<Intersectable.GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null){
            return scene.background;
        }
        Intersectable.GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint);
    }


    public Color calcColor(Intersectable.GeoPoint point){
        return scene.ambientLight.getIntensity();
    }



}
