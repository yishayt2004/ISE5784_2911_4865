package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public interface LightSource
{
    public Color getIntensity(Point p);

    public Vector getL(Point p);

    LightSource setNarrowBeam(int i);

    public double getDistance(Point point);

}
