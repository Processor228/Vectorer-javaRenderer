package vector.geometry;

import static java.lang.Math.sqrt;

public class Vector3d {
    public double x;
    public double y;
    public double z;

    /**
     * instantiates a vector as a difference of two points.
     * @param p1 coordinate of beginning
     * @param p2 coordinate of end
     */
    public Vector3d(Point3d p1, Point3d p2){
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
        this.z = p2.z - p1.z;
    }

    public Vector3d(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3d normalized(){
        double length = this.calculateLength();
        double x = this.x / length;
        double y = this.y / length;
        double z = this.z / length;
        return new Vector3d(x, y, z);
    }

    public Vector3d reversed(){
        return new Vector3d(-x, -y, -z);
    }


    public double dot(Vector3d v2){
        return x * v2.x + y * v2.y + z * v2.z;
    }

    public double calculateLength(){
        return sqrt(x * x + y * y + z * z);
    }

    /**
     * Calculates cosine of angle between a given vector and this.
     * @param vec2 vector that angle to be calculated with.
     * @return cosine of angle.
     */
    public double cos(Vector3d vec2){
        return (x * vec2.x + y * vec2.y + z * vec2.z)/(vec2.calculateLength() * this.calculateLength());
    }

}
