package vector.frame;

import vector.geometry.Point3d;
import vector.geometry.Vector3d;

import java.awt.*;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

public class Sphere extends Shape {

    private double radius;
    private Color color;

    Sphere(double x, double y, double z, double radius, Color color) {
        super(x, y, z);
        this.radius = radius;
        this.color = color;
    }

    /**
     * Gives point of intersections with a sphere in basis of viewer.
     * @param viewer the position of viewer in basis of viewer.
     * @param direction vector of direction from viewer to projection layout.
     * @return Point of intersection.
     */
    @Override
    Point3d intersectTrace(Point3d viewer, Vector3d direction) {
        Vector3d CentSphereToViewer = new Vector3d(this.pos, viewer);

        double a = direction.dot(direction);
        double b = 2 * CentSphereToViewer.dot(direction);
        double c = CentSphereToViewer.dot(CentSphereToViewer) - radius * radius;

        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return new Point3d(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        }

        double t1 = (-b + sqrt(discriminant)) / (2 * a);
        double t2 = (-b - sqrt(discriminant)) / (2 * a);

        t1 = min(t1, t2);

        if(t1 < 0){
            return new Point3d(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        }

        return new Point3d(viewer.x + t1 * direction.x, viewer.y + direction.y * t1, viewer.z + direction.z * t1);
    }

    public double getRadius() {
        return radius;
    }
    public Color getColor(){
        return this.color;
    }
}
