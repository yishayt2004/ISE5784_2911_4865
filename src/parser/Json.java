package parser;

import com.google.gson.Gson;
import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.Ambientlight;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;
import renderer.ImageWriter;
import scene.Scene;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Json class provides static methods for converting ImageWriter and Scene objects to JSON format and vice versa.
 */
public class Json
{

    /**
     * Reads a JSON file at the specified filePath and converts it into a Scene object.
     *
     * @param filePath the path to the JSON file
     * @return the Scene object created from the JSON file
     * @throws RuntimeException if the JSON file is not found or there is an error parsing the file
     */
    public static Scene sceneFromJson(String filePath)
    {
        Gson gson = new Gson();
        FileReader file;

        try
        {
            // Reading Json from a file
            file = new FileReader(filePath);
            PseudoClasses.jsonScene = gson.fromJson(file, parser.JsonScene.class);
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        // for easier access
        parser.PseudoScene scene = PseudoClasses.jsonScene.scene;

        // Start building Scene
        Scene sceneBuilder = new Scene("json scene");

        // Parse background color
        String background = scene.background;
        Point temp = parseStringToPoint(background);
        sceneBuilder.setBackground(
                new Color(
                        temp.getX(), temp.getY(), temp.getZ()
                ));

        // Parse AmbientLight
        Point ambientLight = parseStringToPoint(scene.ambientLight.intensity);
        sceneBuilder.setAmbientLight(
                new Ambientlight(
                        new Color(ambientLight.getX(), ambientLight.getY(), ambientLight.getZ()),
                        new Double3(1)
                ));

        // Parse Geometries
        parser.PseudoGeometryData geometriesData = scene.geometryData;
        Geometries geometries = new Geometries();

        if (geometriesData.triangles != null)
        {
            // Triangle parse
            for (parser.PseudoTriangle triangle : geometriesData.triangles)
            {
                // Getting points
                Point p0 = parseStringToPoint(triangle.p0);
                Point p1 = parseStringToPoint(triangle.p1);
                Point p2 = parseStringToPoint(triangle.p2);

                geometries.add(
                        new Triangle(p0, p1, p2)
                );
            }
        }

        if (geometriesData.spheres != null)
        {
            // Sphere parse
            for (parser.PseudoSphere sphere : geometriesData.spheres)
            {
                // Getting point and radius
                Point center = parseStringToPoint(sphere.center);
                double radius = Double.parseDouble(sphere.radius);

                geometries.add(
                        new Sphere(radius, center)
                );
            }
        }

        // Plane parse
        if (geometriesData.planes != null)
        {
            for (parser.PseudoPlane plane : geometriesData.planes)
            {
                // Getting point and vector
                Point p0 = parseStringToPoint(plane.p0);
                Point tmp = parseStringToPoint(plane.normal);
                Vector normal = new Vector(tmp.getX(), tmp.getY(), tmp.getZ());

                geometries.add(
                        new Plane(p0, normal)
                );
            }
        }

        // Adding geometries to scene
        sceneBuilder.setGeometries(geometries);

        return sceneBuilder;
    }

    /**
     * Parses a string representation of coordinates into a Point object.
     *
     * @param s the string representation of coordinates (format: "x y z")
     * @return the Point object created from the coordinates
     */
    private static Point parseStringToPoint(String s)
    {
        String[] coordinates = s.split(" ");
        int x = Integer.parseInt(coordinates[0]);
        int y = Integer.parseInt(coordinates[1]);
        int z = Integer.parseInt(coordinates[2]);

        return new Point(x, y, z);
    }
}