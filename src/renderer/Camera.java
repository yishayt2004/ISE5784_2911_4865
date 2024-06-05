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



    public Ray constructorRay(int nX, int nY, int j, int i) {
        Point pIJ = p0.add(vTo.scale(distance));
        double rY = height / nY;
        double rX = width / nX;
        double yI = (i - nY / 2d) * rY + rY / 2d;
        double xJ = (j - nX / 2d) * rX + rX / 2d;
        if (!isZero(xJ)) {
            pIJ = pIJ.add(vRight.scale(xJ));
        }
        if (!isZero(yI)) {
            pIJ = pIJ.add(vUp.scale(-yI));
        }
        return new Ray(p0, pIJ.subtract(p0));
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
       private final Camera camera = new Camera();
//       public Builder(Camera camera) {
//              camera.p0 = camera.p0;
//              camera.vUp = camera.vUp;
//              camera.vTo = camera.vTo;
//              camera.vRight = camera.vRight;
//              camera.distance = camera.distance;
//              camera.width = camera.width;
//              camera.height = camera.height;
//       }

        public Builder setLocation(Point p0) {
            camera.p0 = p0;
            return this;
        }
        public Builder setDirection(Vector vto, Vector vup){
            if (vto.dotProduct(vup) != 0) {
                throw new IllegalArgumentException("vTo and vUp are not orthogonal");
            }
            camera.vTo = vto.normalize();
            camera.vUp = vup.normalize();
            camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
            return this;
        }
        public Builder setVpSize(double width, double height){
            camera.width = width;
            camera.height = height;
            return this;
        }
        public Builder setVpDistance(double distance){
            camera.distance = distance;
            return this;
        }
        public Camera build() throws CloneNotSupportedException {
            if (camera.p0 == null) {
                throw new MissingResourceException("Missing camera location", "Camera", "p0");
            }
            if (camera.vTo == null ) {
                throw new MissingResourceException("Missing camera direction", "Camera", "vTo");
            }
            if (camera.vTo.equals(Vector.ZERO)) {
                camera.vRight = camera.vTo.crossProduct(camera.vUp).normalize();
                throw new IllegalArgumentException("Vector(0,0,0) is not a valid camera direction");
            }
            if (camera.vUp == null) {
                throw new MissingResourceException("Missing camera direction", "Camera", "vUp");
            }
            if (camera.vUp.equals(Vector.ZERO)) {
                throw new IllegalArgumentException("Vector(0,0,0) is not a valid camera direction");
            }
            if (camera.width <= 0) {
                throw new MissingResourceException("Missing camera view plane size", "Camera", "width");
            }
            if (camera.height <= 0) {
                throw new MissingResourceException("Missing camera view plane size", "Camera", "height");
            }
            if (camera.distance <= 0) {
                throw new MissingResourceException("Missing camera view plane distance", "Camera", "distance");
            }
            return (Camera) camera.clone();







    }}}



