package vector.geometry;

public class Vector2d {
    public int x;
    public int y;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2d(){
        this.x = 0;
        this.y = 0;
    }

    public Vector2d(Point2d p1, Point2d p2){
        this.x = p2.x - p1.x;
        this.y = p2.y - p1.y;
    }

}
