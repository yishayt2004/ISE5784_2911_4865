package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class DirectionalLight extends Light implements LightSource{
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction;
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity;
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }

    /**
     * @param i
     * @return
     */
    @Override
    public LightSource setNarrowBeam(int i) {
        return this;
    }

    /**
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point point) {
        return Double.POSITIVE_INFINITY;
    }


    private Vector direction;

}
