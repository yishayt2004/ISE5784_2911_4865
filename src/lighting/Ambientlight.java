package lighting;

import primitives.Color;
import primitives.Double3;

import static javax.swing.Spring.scale;

public class Ambientlight {
    public static Ambientlight NONE = new Ambientlight(Color.BLACK,0.0);
    private final Color intensity;
    private Double3 Ka;


    public Ambientlight(Color intensity,Double3 Ka) {
        this.intensity = intensity.scale(Ka);
    }
    public Ambientlight(Color intensity,Double Ka) {
        this.intensity = intensity.scale(Ka);
    }

    public Color getIntensity() {
        return intensity;
    }


}
