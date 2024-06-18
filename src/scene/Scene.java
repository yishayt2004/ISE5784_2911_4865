package scene;

import geometries.Geometries;
import lighting.Ambientlight;
import primitives.Color;
import lighting.Ambientlight;

public class Scene {
    String name;
    public Color background = Color.BLACK;
    public Ambientlight ambientLight = Ambientlight.NONE;
    public Geometries geometries = new Geometries();

public Scene(String name) {
        this.name = name;
    }

    // setters
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(Ambientlight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
    this.geometries.add(geometries);
    return this;
    }
}
