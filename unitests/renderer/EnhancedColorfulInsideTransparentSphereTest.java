package renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class EnhancedColorfulInsideTransparentSphereTest {
    private final Scene scene = new Scene("Enhanced Colorful Inside Transparent Sphere Test Scene");
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
            .setRayTracer(new SimpleRayTracer(scene));

    @Test
    public void enhancedColorfulInsideTransparentSphere() {
        // Add geometries
        scene.geometries.add(
                // Transparent outer sphere
                new Sphere(100d, new Point(0, 0, -300))
                        .setEmission(new Color(50, 50, 150))
                        .setMaterial(new Material().setkD(0.1).setkS(0.1).setnShininess(20).setKt(0.8)),

                // Reflective floor plane
                new Plane(new Point(0, -100, 0), new Vector(0, 1, 0))
                        .setEmission(new Color(50, 50, 50))
                        .setMaterial(new Material().setkD(0.5).setkS(0.3).setnShininess(100).setKr(0.2)),

                // Small colorful geometries inside the transparent sphere
                new Sphere(10d, new Point(0, 0, -300))
                        .setEmission(new Color(RED))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Sphere(10d, new Point(20, 20, -300))
                        .setEmission(new Color(GREEN))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Sphere(10d, new Point(-20, 20, -300))
                        .setEmission(new Color(BLUE))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Sphere(10d, new Point(20, -20, -300))
                        .setEmission(new Color(YELLOW))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Sphere(10d, new Point(-20, -20, -300))
                        .setEmission(new Color(CYAN))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Sphere(10d, new Point(30, 30, -300))
                        .setEmission(new Color(MAGENTA))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Sphere(10d, new Point(-30, -30, -300))
                        .setEmission(new Color(ORANGE))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Triangle(new Point(0, 0, -290), new Point(10, 20, -290), new Point(20, 0, -290))
                        .setEmission(new Color(MAGENTA))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50)),
                new Polygon(new Point(-10, -10, -290), new Point(10, -10, -290), new Point(10, 10, -290), new Point(-10, 10, -290))
                        .setEmission(new Color(WHITE))
                        .setMaterial(new Material().setkD(0.3).setkS(0.2).setnShininess(50))
        );

        // Set ambient light
        scene.setAmbientLight(new Ambientlight(new Color(WHITE), 0.05));

        // Add lights
        scene.lights.add(new SpotLight(new Color(150, 100, 100), new Point(60, 50, 0), new Vector(0, 0, -1))
                .setKl(2E-5).setKq(1E-7));
        scene.lights.add(new PointLight(new Color(0, 0, 200), new Point(-80, 80, 0))
                .setKl(2E-5).setKq(1E-7));
        scene.lights.add(new DirectionalLight(new Color(50, 50, 50), new Vector(1, -1, -1)));
        scene.lights.add(new PointLight(new Color(300, 300, 300), new Point(0, 80, 0))
                .setKl(2E-5).setKq(1E-7));

        // Set up camera and render
        cameraBuilder.setLocation(new Point(0, 0, 1000))
                .setVpDistance(1000)
                .setVpSize(500, 500)
                .setImageWriter(new ImageWriter("enhancedColorfulInsideTransparentSphere_v6", 5000, 5000))
                .build()
                .renderImage()
                .writeToImage();
    }
}
