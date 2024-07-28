package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

import static primitives.Util.isZero;

public class Camera implements Cloneable {

    private Point p0;
    Vector vUp;
    Vector vTo;
    Vector vRight;
    double distance;
    double width;
    double height;
    int numSamples = 1;
    private boolean adaptive;

    //multi-threading
    private int threadsCount = 0; // -2 auto, -1 range/stream, 0 no threads, 1+ number of threads private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private double printInterval = 0; // printing progress percentage interval
    private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores


    ImageWriter imageWriter;
    RayTracerBase rayTracer;

    public Camera() {
        p0 = null;
        vUp = null;
        vTo = null;
        vRight = null;
        distance = 0;
        width = 0;
        height = 0;
    }

    public Camera(Point point, Vector vector, Vector vector1) {
        p0 = point;
        vTo = vector;
        vUp = vector1;
        vRight = vTo.crossProduct(vUp).normalize();
    }


    /**
     * constructorRay
     * @param nX - number of pixels in the x direction
     * @param nY - number of pixels in the y direction
     * @param j - pixel index in the x direction
     * @param i - pixel index in the y direction
     * @return Ray from the camera to the pixel
     */

    public Ray constructRay(int nX, int nY, double j, double i) {
        Point p = p0.add(vTo.scale(distance)); // Pij = P0 + d*Vto
        double rY = height / nY;
        double rX = width / nX;
        double yI = (i - nY / 2d) * rY + rY / 2d;
        double xJ = (j - nX / 2d) * rX + rX / 2d;
        if (!isZero(xJ)) { // if xJ is not zero
            p = p.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {  // if yI is not zero
            p = p.add(vUp.scale(-yI));
        }
        return new Ray(p0, p.subtract(p0)); // return Ray from the camera to the pixel
    }

    /**
     * this func will loop over all the pixels in the view plane and will calculate the color of each pixel
     * @return the camera object
     */
    public Camera renderImage() {
        int ny = imageWriter.getNy();
        int nx = imageWriter.getNx();
        Pixel.initialize(ny, nx, printInterval);

        if (threadsCount == 0) {
            for (int i = 0; i < ny; ++i)
                for (int j = 0; j < nx; ++j)
                    castRay(nx, ny, j, i);
            return this;
        }
        List<Thread> threads = new LinkedList<>();
        int availableProcessors = threadsCount == -1 ? Runtime.getRuntime().availableProcessors()
                : threadsCount;

        for (int t = 0; t < availableProcessors; t++) {
            threads.add(new Thread(() -> {
                Pixel pixel;
                while ((pixel = Pixel.nextPixel()) != null)
                    castRay(nx, ny, pixel.col(), pixel.row());
            }));
        }
        for (var thread : threads)
            thread.start();
        try {
            for (var thread : threads)
                thread.join();
        } catch (InterruptedException ignore) {
        }

        return this;
    }

    /**
     * this func will cast a ray from the camera to the pixel and will calculate the color of the pixel
     *
     * @param nX - number of pixels in the x direction
     * @param nY - number of pixels in the y direction
     * @param j  - pixel index in the x direction
     * @param i  - pixel index in the y direction
     */
    private void castRay(int nX, int nY, int j, int i) {
        Color sum = new Color(0, 0, 0);

        for (int k = 0; k < numSamples * numSamples; ++k) {
            double x = j + (k % numSamples + (Math.random() - 0.5)) / (double) numSamples;
            double y = i + (k / numSamples + (Math.random() - 0.5)) / (double) numSamples;


            Ray ray = constructRay(nX, nY, x, y);
            Color color = rayTracer.traceRay(ray);
            sum = sum.add(color);
        }

        Color color = sum.scale(1d / (numSamples * numSamples));

        imageWriter.writePixel(j, i, color);
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public Camera printGrid(int interval, Color color){
        for (int i = 0; i < imageWriter.getNx(); i++) {
            for (int j = 0; j < imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    imageWriter.writePixel(i, j, color);
                }
            }
        }
        return this;
    }
    // Getters
    public Point getP0() {return p0;}
    public Vector getvUp() {return vUp;}
    public Vector getvTo() {return vTo;}
    public Vector getvRight() {return vRight;}
    public double getDistance() {return distance;}
    public double getWidth() {return width;}
    public double getHeight() {return height;}
    public int getNumSamples() {return numSamples;}
    public ImageWriter getImageWriter() {return imageWriter;}
    public RayTracerBase getRayTracer() {return rayTracer;}
    public int getThreadsCount() {return threadsCount;}
    public double getPrintInterval() {return printInterval;}
    public boolean isAdaptive() {return adaptive;}

    public Camera writeToImage() {
        imageWriter.writeToImage();
        return this;
    }



    public static class Builder {
        // Builder fields
       private final Camera camera = new Camera();

       /*
       this method sets the location of the camera
         @param p0 - the location of the camera
            @return the builder object

        */
        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }

        /**
         * Sets the direction vectors for the camera.
         *
         * @param vto The vector pointing towards the direction the camera is facing.
         * @param vup The vector pointing upwards relative to the camera's orientation.
         *
         * @return The Builder object, allowing for method chaining.
         *
         * @throws IllegalArgumentException If the provided vectors are not orthogonal.
         */
        public Builder setDirection(Vector vto, Vector vup){
            // Check if the vectors are orthogonal. If not, throw an exception.
            if (vto.dotProduct(vup) != 0) {
                throw new IllegalArgumentException("vTo and vUp not orthogonal");
            }
            // Normalize the vectors and assign them to the camera's direction vectors.
            camera.vTo = vto.normalize();
            camera.vUp = vup.normalize();
            // Calculate the rightward direction vector from the other two vectors.
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            // Return the Builder object for method chaining.
            return this;
        }

        /**
         * Sets the size of the view plane for the camera.
         *
         * @param width The width of the view plane.
         * @param height The height of the view plane.
         *
         * @return The Builder object, allowing for method chaining.
         */
        public Builder setVpSize(double width, double height){
            camera.width = width;
            camera.height = height;
            return this;
        }

        public Builder setAdaptive(boolean adaptive) {
            camera.adaptive = adaptive;
            return this;
        }

        /**
         * Sets the distance between the camera and the view plane.
         *
         * @param distance The distance between the camera and the view plane.
         *
         * @return The Builder object, allowing for method chaining.
         */
        public Builder setVpDistance(double distance){
            camera.distance = distance;
            return this;
        }

        public Builder setNumSamples(int numSamples) {
            camera.numSamples = numSamples;
            return this;
        }

        /**
         * This method checks if all necessary fields of the camera have been set.
         * If a field is missing or invalid, it throws an exception.
         * If all fields are valid, it returns a clone of the camera.
         *
         * @return A clone of the camera object.
         *
         * @throws MissingResourceException If a necessary field is missing.
         * @throws IllegalArgumentException If a field is invalid.
         * @throws CloneNotSupportedException If the camera object cannot be cloned.
         */
        public Camera build() {
            // Check if the camera's location has been set. If not, throw an exception.
            if (camera.p0 == null) {
                throw new MissingResourceException("Missing camera location", "Camera", "p0");
            }
            // Check if the camera's direction has been set. If not, throw an exception.
            if (camera.vTo == null ) {
                throw new MissingResourceException("Missing camera direction", "Camera", "vTo");
            }
            // Check if the camera's direction is valid. If not, throw an exception.
            if (camera.vTo.equals(Vector.ZERO)) {
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
                throw new IllegalArgumentException("Vector(0,0,0) is not a valid camera direction");
            }
            // Check if the camera's upward direction has been set. If not, throw an exception.
            if (camera.vUp == null) {
                throw new MissingResourceException("Missing camera direction", "Camera", "vUp");
            }
            // Check if the camera's upward direction is valid. If not, throw an exception.
            if (camera.vUp.equals(Vector.ZERO)) {
                throw new IllegalArgumentException("Vector(0,0,0) is not a valid camera direction");
            }
            // Check if the camera's view plane width has been set. If not, throw an exception.
            if (camera.width <= 0) {
                throw new MissingResourceException("Missing camera view plane size", "Camera", "width");
            }
            // Check if the camera's view plane height has been set. If not, throw an exception.
            if (camera.height <= 0) {
                throw new MissingResourceException("Missing camera view plane size", "Camera", "height");
            }
            // Check if the camera's view plane distance has been set. If not, throw an exception.
            if (camera.distance <= 0) {
                throw new MissingResourceException("Missing camera view plane distance", "Camera", "distance");
            }
            if (camera.imageWriter == null) {
                throw new MissingResourceException("Missing camera image writer", "Camera", "imageWriter");
            }
            if (camera.rayTracer == null) {
                throw new MissingResourceException("Missing camera ray tracer", "Camera", "rayTracer");
            }

            try {
                // If all fields are valid, return a clone of the camera.
                return (Camera) camera.clone();
            }catch(CloneNotSupportedException e){
                return null;
            }
    }
    public Builder setImageWriter(ImageWriter imageWriter) {
        camera.imageWriter = imageWriter;
        return this;
    }
    public Builder setRayTracer(RayTracerBase rayTracer) {
        camera.rayTracer = rayTracer;
        return this;
    }
        public Builder setMultithreading(int threads) {
            if (threads < -2) throw new IllegalArgumentException("Multithreading must be -2 or higher");
            if (threads >= -1) camera.threadsCount = threads;
            else { // == -2
                int cores = Runtime.getRuntime().availableProcessors() - camera.SPARE_THREADS;
                camera.threadsCount = cores <= 2 ? 1 : cores;
            }
            return this;
        }
        public Builder setPrintInterval(double interval) {
            camera.printInterval = interval;
            return this;
        }

    }

}



