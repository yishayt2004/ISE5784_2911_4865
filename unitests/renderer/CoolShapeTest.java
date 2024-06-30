package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a cool 3D shape with advanced lighting
 */
public class CoolShapeTest {
    /** Scene for the test */
    private final Scene scene = new Scene("Cool 3D Shape Scene")
            .setAmbientLight(new Ambientlight(new Color(WHITE), new Double3(0.15)));

    /** Camera for the test */
    private final Camera.Builder camera = Camera.getBuilder()
            .setRayTracer(new SimpleRayTracer(scene))
            .setLocation(new Point(0, 0, 1000))
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setVpSize(200, 200).setVpDistance(1000);

    /** Shininess value for the geometries */
    private static final int SHININESS = 100;
    /** Diffusion attenuation factor */
    private static final double KD = 0.5;
    /** Specular attenuation factor */
    private static final double KS = 0.5;

    /** Material for the geometries */
    private final Material material = new Material().setkD(KD).setkS(KS).setnShininess(SHININESS);

    /** Create a cool 3D shape */
    @Test
    public void cool3DShape() {
        // Central sphere
        Sphere centerSphere = (Sphere) new Sphere(30, new Point(0, 0, -100))
                .setEmission(new Color(BLUE))
                .setMaterial(material);

        // Create a 3D spiral of spheres around the central sphere
        int numSpheres = 30;
        double radius = 70;
        double heightStep = 10;
        double angleStep = 2 * Math.PI / numSpheres;
        for (int i = 0; i < numSpheres; i++) {
            double angle = i * angleStep;
            double x = radius * Math.cos(angle);
            double y = radius * Math.sin(angle);
            double z = -100 - (i * heightStep); // Adding depth to the spiral

            Sphere sphere = (Sphere) new Sphere(10 + (i % 5), new Point(x, y, z)) // Varying sizes
                    .setEmission(new Color(
                            (int) (Math.random() * 255),
                            (int) (Math.random() * 255),
                            (int) (Math.random() * 255)))
                    .setMaterial(material);
            scene.geometries.add(sphere);
        }

        // Add the central sphere last so it stands out
        scene.geometries.add(centerSphere);

        // Add lights
        scene.lights.add(new DirectionalLight(new Color(WHITE), new Vector(1, 1, -1)));
        scene.lights.add(new PointLight(new Color(500, 300, 300), new Point(100, 100, 50))
                .setKl(0.001).setKq(0.0002));
        scene.lights.add(new SpotLight(new Color(300, 500, 300), new Point(-100, 100, 50), new Vector(-1, -1, -2))
                .setKl(0.001).setKq(0.0001));
        scene.lights.add(new SpotLight(new Color(500, 300, 300), new Point(100, -100, 50), new Vector(1, 1, -2))
                .setKl(0.001).setKq(0.0001));
        scene.lights.add(new SpotLight(new Color(300, 300, 500), new Point(0, 0, 100), new Vector(0, 0, -1))
                .setKl(0.001).setKq(0.0001));

        camera.setImageWriter(new ImageWriter("cool3DShape", 5000, 5000))
                .build()
                .renderImage()
                .writeToImage();
    }
}
