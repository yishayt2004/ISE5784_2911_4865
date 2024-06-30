package lighting;

import primitives.Color;
import primitives.Double3;

import static javax.swing.Spring.scale;

public class Ambientlight extends Light {
    public static Ambientlight NONE = new Ambientlight(Color.BLACK,0.0);

    private Double3 Ka;


    public Ambientlight(Color intensity,Double3 Ka) {
        super(intensity.scale(Ka));
    }

    public Ambientlight(Color intensity,Double Ka) {
        super(intensity.scale(Ka));
    }




}
