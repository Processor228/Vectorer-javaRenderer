package vector.frame;

import vector.geometry.Point2d;
import vector.geometry.Point3d;
import vector.geometry.Vector3d;

import java.awt.*;

import static java.lang.Math.min;
import static java.lang.Math.sqrt;

abstract public class Shape {
    public Point3d pos;
    private Color color;

    abstract Point3d intersectTrace(Point3d viewerPos, Vector3d direction);

    Shape(double x, double y, double z){
        this.pos = new Point3d(x, y, z);
    }

    public Color getColor(){
        return this.color;
    };
}

