package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource
{
    public PointLight(Color intensity, Point position)
    {
        super(intensity);
        this.position = position;
    }

    protected Point position;
    double kC = 1;
    double kL = 0;
    double kQ = 0;

    public PointLight setKc(double kC)
    {
        this.kC = kC;
        return this;
    }
    public PointLight setKl(double kL)
    {
        this.kL = kL;
        return this;
    }
    public PointLight setKq(double kQ)
    {
        this.kQ = kQ;
        return this;
    }

    /**
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        double temp = kC + (kL * d) + (kQ * d * d);
        return intensity.scale(1 / temp);

    }

    /**
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * @param i
     * @return
     */
    @Override
    public LightSource setNarrowBeam(int i) {
        return this;
    }

    public void setkC(double kC) {
        this.kC = kC;
    }



}
