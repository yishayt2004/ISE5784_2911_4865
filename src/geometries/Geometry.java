package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
/**
 * Interface Geometry is the basic interface for all geometric objects
 */

public abstract class Geometry extends Intersectable{

    protected Color emission = Color.BLACK;
    private Material material = new Material();
    /**
     * @param point
     * @return normal to the geometry at the point
     */
    public abstract Vector getNormal(Point point);

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }
    public Color getEmission() {
        return emission;
    }

    public Material getMaterial() {
        return material;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

}