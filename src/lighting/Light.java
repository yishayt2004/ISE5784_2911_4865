package lighting;

import primitives.Color;

public class Light
{
    protected Color intensity;

    public Light(Color intensity)
    {
        this.intensity = intensity;
    }

    public Color getIntensity()
    {
        return intensity;
    }

    /**
     * this function sets the intensity of the light
     * @param intensity
     * @return
     */

    public Light setIntensity(Color intensity)
    {
        this.intensity = intensity;
        return this;
    }
}
