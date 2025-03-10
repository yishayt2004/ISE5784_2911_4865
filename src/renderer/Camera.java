package renderer;


import primitives.Color;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import renderer.PixelManager.*;


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
    private PixelManager pixelManager;

    //multi-threading
    private int threadsCount = 0; // -2 auto, -1 range/stream, 0 no threads, 1+ number of threads private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores
    private double printInterval = 0; // printing progress percentage interval
    private final int SPARE_THREADS = 2; // Spare threads if trying to use all the cores

    /**
     * all the corners for the corner list in adaptive super sampling
     */
    private static final int TOP_LEFT = 0;
    private static final int TOP_RIGHT = 1;
    private static final int BOTTOM_LEFT = 2;
    private static final int BOTTOM_RIGHT = 3;

    private boolean isAdaptive = false;



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
    /**
     * Render the image without sample Size
     * @return the camera
     */
    public Camera renderImage() {

        // Calculate the number of pixels in the rows and columns
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();

        pixelManager = new PixelManager(nY, nX, printInterval);

        if(threadsCount==0) {
            // Render the image
            for (int i = 0; i < nY; i++)
                for (int j = 0; j < nX; j++) {
                    castRay(nX, nY, j, i);
                }
        }
        else { // see further... option 2
            var threads = new LinkedList<Thread>(); // list of threads
            while (threadsCount-- > 0) // add appropriate number of threads
                threads.add(new Thread(() -> { // add a thread with its code
                    Pixel pixel; // current pixel(row,col)
                    // allocate pixel(row,col) in loop until there are no more pixels
                    while ((pixel = pixelManager.nextPixel()) != null)
                        // cast ray through pixel (and color it – inside castRay)
                        castRay(nX, nY, pixel.col(), pixel.row());
                }));
            // start all the threads
            for (var thread : threads) thread.start();
            // wait until all the threads have finished
            try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}
        }

        return this;
    }

    /**
     * Render the image with beam
     * @param sampleSize
     * @return the camera
     */
    public Camera renderImage(int sampleSize) {
        if (sampleSize == 1)
        {
            return renderImage();
        }
        numSamples = sampleSize;
        // Calculate the number of pixels in the rows and columns
        int nX = imageWriter.getNx();
        int nY = imageWriter.getNy();
        pixelManager = new PixelManager(nY, nX, printInterval);

            if(threadsCount==0)
                // Render the image
                for (int i = 0; i < nY; i++)
                    for (int j = 0; j < nX; j++) {
                        castRay(nX, nY, j, i);
                    }
            else { // see further... option 2
                var threads = new LinkedList<Thread>(); // list of threads
                while (threadsCount-- > 0) // add appropriate number of threads
                    threads.add(new Thread(() -> { // add a thread with its code
                        Pixel pixel; // current pixel(row,col)
                        // allocate pixel(row,col) in loop until there are no more pixels
                        while ((pixel = pixelManager.nextPixel()) != null)
                            // cast ray through pixel (and color it – inside castRay)
                            castRay(nX, nY, pixel.col(), pixel.row());
                    }));
                // start all the threads
                for (var thread : threads) thread.start();
                // wait until all the threads have finished
                try { for (var thread : threads) thread.join(); } catch (InterruptedException ignore) {}
            }
            return this;



    }
    /**
     * Return list of the 4 edges of a pixel.
     *
     * @param nX - the index of the pixel in geometric coordinates (X,)
     * @param nY - the index of the pixel in geometric coordinates (,Y)
     * @param j - the index of the pixel in matrix coordinates [i][]
     * @param i - the index of the pixel in matrix coordinates [][j]
     * @return list of the corners: [top left, top right, bottom left, bottom right]
     */
    public List<Point> constructFourEdges(int nX, int nY, int j, int i) {
        Point center = constructPoint(nX, nY, j, i, false);
        Vector up = vUp.scale(height / (2 * nY)), down = vUp.scale(-height / (2 * nY)), left = vRight.scale(-width / (2 * nX)), right = vRight.scale(width / (2 * nX));
        return new ArrayList<>(List.of(center.add(up).add(left), center.add(up).add(right), center.add(down).add(left), center.add(down).add(right)));
    }



    private Point constructPoint(int nX, int nY, int j, int i, boolean b) {
        Point p = p0.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;
        double yI = (i - nY / 2d) * rY + rY / 2d;
        double xJ = (j - nX / 2d) * rX + rX / 2d;
        if (!isZero(xJ)) {
            p = p.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            p = p.add(vUp.scale(-yI));
        }
        return p;
    }



    /**
     * Calculate the color of a pixel by adaptive super sampling formula.
     *
     * @param topLeft the top left corner of the sub pixel
     * @param topRight the top right corner of the sub pixel
     * @param bottomLeft the bottom left corner of the sub pixel
     * @param bottomRight the bottom right corner of the sub pixel
     * @param points all the points and their colors, so we won't calc the same color twice
     * @param initialLevel the level of the recursion
     * @return the color of the pixel of the sub pixel
     */
    private Color traceAdaptiveRays(Point topLeft, Point topRight, Point bottomLeft, Point bottomRight, HashMap<Point, Color> points, int initialLevel) {
        Point center = topLeft.getMiddle(bottomRight);

        // The end of the recursion
        if (initialLevel == 0) {
            return rayTracer.traceRay(new Ray(p0, center.subtract(p0)));
        }

        Color topLeftColor = points.getOrDefault(topLeft, null);
        if (topLeftColor == null) {
            topLeftColor = rayTracer.traceRay(new Ray(p0, topLeft.subtract(p0)));
            points.put(topLeft, topLeftColor);
        }

        Color topRightColor = points.getOrDefault(topRight, null);
        if (topRightColor == null) {
            topRightColor = rayTracer.traceRay(new Ray(p0, topRight.subtract(p0)));
            points.put(topRight, topRightColor);
        }

        Color bottomLeftColor = points.getOrDefault(bottomLeft, null);
        if (bottomLeftColor == null) {
            bottomLeftColor = rayTracer.traceRay(new Ray(p0, bottomLeft.subtract(p0)));
            points.put(bottomLeft, bottomLeftColor);
        }

        Color bottomRightColor = points.getOrDefault(bottomRight, null);
        if (bottomRightColor == null) {
            bottomRightColor = rayTracer.traceRay(new Ray(p0, bottomRight.subtract(p0)));
            points.put(bottomRight, bottomRightColor);
        }

        if (topLeftColor.equals(topRightColor) && topLeftColor.equals(bottomLeftColor) && topLeftColor.equals(bottomRightColor)) {
            return topLeftColor;
        } else {
            Point topMiddle = topLeft.getMiddle(topRight);
            Point bottomMiddle = bottomLeft.getMiddle(bottomRight);
            Point middleLeft = topLeft.getMiddle(bottomLeft);
            Point middleRight = topRight.getMiddle(bottomRight);

            topLeftColor = traceAdaptiveRays(topLeft, topMiddle, middleLeft, center, points, initialLevel - 1).scale(0.25);
            topRightColor = traceAdaptiveRays(topMiddle, topRight, center, middleRight, points, initialLevel - 1).scale(0.25);
            bottomLeftColor = traceAdaptiveRays(middleLeft, center, bottomLeft, bottomMiddle, points, initialLevel - 1).scale(0.25);
            bottomRightColor = traceAdaptiveRays(center, middleRight, bottomMiddle, bottomRight, points, initialLevel - 1).scale(0.25);

            return topLeftColor.add(topRightColor).add(bottomLeftColor).add(bottomRightColor);
        }
    }


    /**
     * this func will cast a ray from the camera to the pixel and will calculate the color of the pixel
     *
     * @param nX - number of pixels in the x direction
     * @param nY - number of pixels in the y direction
     * @param j  - pixel index in the x direction
     * @param i  - pixel index in the y direction
     */
    /**
     * Cast ray from any pixel and paint it.
     *
     * @param nX - number of pixels in the x direction
     * @param nY - number of pixels in the y direction
     * @param j  - pixel index in the x direction
     * @param i  - pixel index in the y direction
     */
    private void castRay(int nX, int nY, int j, int i) {
        // Regular - one ray to each pixel
        if (numSamples == 1) {
            imageWriter.writePixel(j, i, rayTracer.traceRay(constructRay(nX, nY, j, i)));
        } else { // If we are doing super sampling:
            if (isAdaptive = false) { // Without acceleration
                Color sum = new Color(0, 0, 0);
                for (int k = 0; k < numSamples * numSamples; ++k) { // For each sample to the pixel - calculate the color and sum it
                    double x = j + (k % numSamples + (Math.random() - 0.5)) / numSamples;
                    double y = i + (k / numSamples + (Math.random() - 0.5)) / numSamples;

                    Ray ray = constructRay(nX, nY, x, y);
                    sum = sum.add(rayTracer.traceRay(ray));
                }
                imageWriter.writePixel(j, i, sum.scale(1d / (numSamples * numSamples)));
            } else { // With acceleration
                HashMap<Point, Color> points = new HashMap<>();
                int initialLevel = (int) (Math.log(numSamples - 1) / Math.log(2));
                List<Point> corners = constructFourEdges(nX, nY, j, i);
                imageWriter.writePixel(j, i, traceAdaptiveRays(corners.get(TOP_LEFT), corners.get(TOP_RIGHT), corners.get(BOTTOM_LEFT), corners.get(BOTTOM_RIGHT), points, initialLevel));
            }
        }
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

        public Builder setIsAdaptive(boolean isAdaptive) {
            camera.isAdaptive = isAdaptive;
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


        public Builder setThreadsCount(int _threadsCount) {
            camera.threadsCount = _threadsCount;
            return this;
        }
        public Builder setPrintInterval(double interval) {
            camera.printInterval = interval;
            return this;
        }

    }



    }







