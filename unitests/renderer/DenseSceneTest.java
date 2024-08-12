package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class DenseSceneTest {
    private final Scene scene = new Scene("Dense Test Scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene))
            .setThreadsCount(8);

    @Test
    public void denseScene() {
        // Add geometries
        scene.geometries.add(
                // Large transparent outer sphere
                new Sphere(100d, new Point(0, 0, -300))
                        .setEmission(new Color(50, 50, 150))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.7)),

                // Internal dense structure
                createDenseStructure(new Point(0, 0, -300), 50, 10, 20),

                // Reflective floor plane
                new Plane(new Point(0, -100, 0), new Vector(0, 1, 0))
                        .setEmission(new Color(50, 50, 50))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100).setKr(0.1))
        );

        // Set ambient light
        scene.setAmbientLight(new Ambientlight(new Color(WHITE), 0.2)); // Increase ambient light slightly

        // Add lights with adjusted intensity
        scene.lights.add(new SpotLight(new Color(100, 100, 100), new Point(60, 50, 0), new Vector(0, 0, -1))
                .setKl(1E-5).setKq(1E-7)); // Reduce intensity of SpotLight
        scene.lights.add(new PointLight(new Color(100, 100, 200), new Point(-80, 80, 0))
                .setKl(1E-5).setKq(1E-7)); // Reduce intensity of PointLight
        scene.lights.add(new DirectionalLight(new Color(80, 80, 80), new Vector(1, -1, -1))); // Reduce intensity of DirectionalLight

        // Set up camera and render
        cameraBuilder.setLocation(new Point(0, 0, 1000))
                .setVpDistance(1000)
                .setVpSize(500, 500)
                .setImageWriter(new ImageWriter("denseScene", 5000, 5000))
                .build()
                .renderImage(6)
                .writeToImage();
    }

    private Geometries createDenseStructure(Point center, double size, int layers, int segments) {
        Geometries structure = new Geometries();
        double angleStep = 2 * Math.PI / segments;

        for (int layer = 0; layer < layers; layer++) {
            double currentSize = size * (1 - (double) layer / layers);
            double z = (layer - layers / 2.0) * size * 2 / layers;

            for (int i = 0; i < segments; i++) {
                double angle = i * angleStep;
                double x = currentSize * Math.cos(angle);
                double y = currentSize * Math.sin(angle);

                Point point = center.add(new Vector(x, y, z));

                // Add small spheres
                structure.add(
                        new Sphere(5, point).setEmission(new Color((i * 50) % 255, (layer * 50) % 255, (i + layer * 25) % 255))
                                .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.5))
                );
            }
        }

        return structure;
    }
}
