package renderer;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.MissingResourceException;

import static jdk.javadoc.internal.doclets.formats.html.markup.HtmlStyle.exceptions;
import static primitives.Util.isZero;

public class Camera implements Cloneable {
    private Point p0;
    Vector vUp;
    Vector vTo;
    Vector vRight;
    double distance;
    double width;
    double height;

    private Camera() {
        p0 = null;
        vUp = null;
        vTo = null;
        vRight = null;
        distance = 0;
        width = 0;
        height = 0;
    }


    /**
     * constructorRay
     * @param nX - number of pixels in the x direction
     * @param nY - number of pixels in the y direction
     * @param j - pixel index in the x direction
     * @param i - pixel index in the y direction
     * @return Ray from the camera to the pixel
     */

    public Ray constructorRay(int nX, int nY, int j, int i) {
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

    public static Builder getBuilder() {
        return new Builder();
    }

    // Getters
    public Point getP0() {return p0;}
    public Vector getvUp() {return vUp;}
    public Vector getvTo() {return vTo;}
    public Vector getvRight() {return vRight;}
    public double getDistance() {return distance;}
    public double getWidth() {return width;}
    public double getHeight() {return height;}



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
                throw new IllegalArgumentException("vTo and vUp are not orthogonal");
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

        /**
         * Builds and returns a Camera object.
         *
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
        public Camera build() throws CloneNotSupportedException {
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
            // If all fields are valid, return a clone of the camera.
            return (Camera) camera.clone();

    }}}



