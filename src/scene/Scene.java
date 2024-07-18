package scene;

import geometries.Geometries;

import lighting.Ambientlight;
import lighting.Light;
import lighting.LightSource;
import primitives.Color;
import lighting.Ambientlight;
import renderer.Camera;

import java.util.LinkedList;
import java.util.List;

public class Scene {
    String name;
    public Color background = Color.BLACK;
    public Ambientlight ambientLight = Ambientlight.NONE;
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

public Scene(String name) {
        this.name = name;
    }



    // setters
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setLight(LightSource light) {
        this.lights = lights;
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
