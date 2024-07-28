package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class AbstractSceneTest {
    private final Scene scene = new Scene("Abstract Test Scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene))
            .setNumSamples(5);


    @Test
    public void abstractScene() {
        // Add geometries
        scene.geometries.add(
                // Transparent outer sphere
                new Sphere(100d, new Point(0, 0, -300))
                        .setEmission(new Color(50, 50, 150))
                        .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.7)),

                // Internal abstract structure
                createAbstractStructure(new Point(0, 0, -300), 50, 20),

                // Reflective floor plane
                new Plane(new Point(0, -100, 0), new Vector(0, 1, 0))
                        .setEmission(new Color(50, 50, 50))
                        .setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(100).setKr(0.1))
        );

        // Set ambient light
        scene.setAmbientLight(new Ambientlight(new Color(WHITE), 0.05));

        // Add lights
        scene.lights.add(new SpotLight(new Color(350, 200, 200), new Point(60, 50, 0), new Vector(0, 0, -1))
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new PointLight(new Color(0, 0, 400), new Point(-80, 80, 0))
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new DirectionalLight(new Color(100, 100, 100), new Vector(1, -1, -1)));

        // Set up camera and render
        cameraBuilder.setLocation(new Point(0, 0, 1000))
                .setVpDistance(1000)
                .setVpSize(500, 500)
                .setImageWriter(new ImageWriter("abstractScene", 1000, 1000))
                .setMultithreading(-1)
                .build()
                .renderImage()
                .writeToImage();
    }

    private Geometries createAbstractStructure(Point center, double size, int segments) {
        Geometries structure = new Geometries();
        double angleStep = 2 * Math.PI / segments;

        for (int i = 0; i < segments; i++) {
            double angle = i * angleStep;
            double x = size * Math.cos(angle);
            double y = size * Math.sin(angle);
            double z = 0;

            Point point1 = center.add(new Vector(x, y, z));
            Point point2 = center.add(new Vector(-x, -y, z));
            Point point3 = center.add(new Vector(0, 0, size));
            Point point4 = center.add(new Vector(0, 0, -size));

            // Add small spheres
            structure.add(
                    new Sphere(5, point1).setEmission(new Color(RED))
                            .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.5)),
                    new Sphere(5, point2).setEmission(new Color(BLUE))
                            .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.5))
            );

            // Add triangles for abstract effect
            if (i > 0) {
                Point prevPoint1 = center.add(new Vector(size * Math.cos(angle - angleStep), size * Math.sin(angle - angleStep), 0));
                Point prevPoint2 = center.add(new Vector(-size * Math.cos(angle - angleStep), -size * Math.sin(angle - angleStep), 0));

                structure.add(
                        new Triangle(prevPoint1, point1, point3)
                                .setEmission(new Color(GREEN))
                                .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.5)),
                        new Triangle(prevPoint2, point2, point4)
                                .setEmission(new Color(YELLOW))
                                .setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(30).setKt(0.5))
                );
            }
        }

        return structure;
    }
}
