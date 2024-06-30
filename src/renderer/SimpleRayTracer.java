package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

public class SimpleRayTracer extends RayTracerBase {
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {
        var intersections = scene.geometries.findGeoIntersections(ray);
        if (intersections == null)
            return scene.background;
        return calcColor(ray.findClosestGeoPoint(intersections), ray);
    }



    /**
     * Calculate the color at a point
     *
     * @param intersection the point
     * @param ray          the ray that hit the point
     * @return the color at the point
     */
    private Color calcColor(Intersectable.GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity().add(calcLocalEffects(intersection, ray));
    }

    /**
     * Calculate the local effects at a point
     *
     * @param gp  the point
     * @param ray the ray
     * @return the color at the point
     */
    private Color calcLocalEffects(Intersectable.GeoPoint gp, Ray ray) {
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));

        if (nv == 0) return null;

        Material material = gp.geometry.getMaterial();
        Color color = gp.geometry.getEmission();

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));

            if (Util.compareSign(nl, nv)) {
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl).add(calcSpecular(material, n, l, nl, v))));
            }
        }
        return color;
    }

    /**
     * Calculate the diffusive effect at a point
     *
     * @param material the material at the point
     * @param nl       the dot product of the normal and the light vector
     * @return the diffusive effect at the point
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(Math.abs(nl));
    }

    /**
     * Calculate the specular effect at a point
     *
     * @param material the material at the point
     * @param n        the normal at the point
     * @param l        the light vector at the point
     * @param nl       the dot product of the normal and the light vector
     * @param v        the view vector at the point
     * @return the specular effect at the point
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector reflectVector = l.subtract(n.scale(nl * 2));
        double max0 = Math.max(0, v.scale(-1).dotProduct(reflectVector));
        return material.kS.scale(Math.pow(max0, material.nShininess));
    }




}
