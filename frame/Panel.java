package vector.frame;
import vector.geometry.Point2d;
import vector.geometry.Point3d;
import vector.geometry.Vector3d;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;


/**
 * This class handles rendering picture on the screen.
 */
public class Panel extends JPanel implements ActionListener{
//    public ArrayList<Point2d> points = new ArrayList<Point2d>();
    public ArrayList<Point3d> lights = new ArrayList<Point3d>();  // for now, for simplicity, lets hold it as just set of points.
    public ArrayList<Shape> shapes = new ArrayList<Shape>();
    public double distanceToProjection = 400;
    int Delay = 20;
    int pointHolderRadius = 10;
    int time = 0;
    /**
     * For testing this program I will set view position (0, 0, 0). Notice that I am going to establish viewer's own
     * local basis, which is always not identical to the global.
     */
    Point3d viewPos = new Point3d(0, 0, 0);
    Timer timer;

    public Panel(){
        timer = new Timer(Delay, this);
        timer.start();
        shapes.add(new Sphere(-2, 4, 20, 1, Color.BLUE));
        shapes.add(new Sphere(0, -1, 50, 10, Color.GREEN));
        lights.add(new Point3d(5, 20, 5));
    }

    public void drawPixel(Point2d p, Graphics g, Color c){
        g.setColor(c);
        g.drawRect(p.x, p.y, 1, 1);
        g.setColor(Color.darkGray);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        time += 5;
        shapes.get(1).pos.x += 5 * cos(time / 10);
        shapes.get(1).pos.z -= 5 * sin(time / 10);
        for(int x = 0; x < this.getWidth(); x++){
            for(int y = 0; y < this.getHeight(); y++){
                Point3d pointPixel = new Point3d((x - this.getWidth()/2), (y - this.getHeight()/2), distanceToProjection);
                Vector3d rayLineDirectionVector = new Vector3d(viewPos, pointPixel);
                Color colorToBePrinted = Color.darkGray;
                int degreeOfShader = 0;
                double closestDistance = Double.MAX_VALUE;
                for(int i = 0; i < shapes.size(); i++){
                    Point3d intersection = shapes.get(i).intersectTrace(viewPos, rayLineDirectionVector);

                    Vector3d distance = new Vector3d(viewPos, intersection);

                    if(distance.calculateLength() > 1 && distance.calculateLength() < closestDistance){
                        closestDistance = distance.calculateLength();
                        colorToBePrinted = shapes.get(i).getColor();
                        degreeOfShader = calculateShader(intersection, shapes.get(i).pos);
                        for(int degree = 0; degree < degreeOfShader; degree++){
                            colorToBePrinted = colorToBePrinted.darker();
                        }
                    }
                }
//                System.out.println(degreeOfShader);
                drawPixel(new Point2d(x, y), g, colorToBePrinted);
            }
        }
    }

    public int calculateShader(Point3d intersection, Point3d spherePos){
        Vector3d normal = new Vector3d(spherePos, intersection);
        int degreeOfDarkness = 0;
        for(int i = 0; i < lights.size(); i++) {
            Vector3d light = new Vector3d(intersection, lights.get(i));
            double cosine = normal.cos(light);
//            System.out.print(cosine + ", ");
            if(cosine < 0){
                degreeOfDarkness += 5;
                break;
            }
            if(cosine < .2){
                degreeOfDarkness += 5;
                break;
            }
            if(cosine < .5){
                degreeOfDarkness += 3;
                break;
            }
            if(cosine < .7){
                degreeOfDarkness += 2;
                break;
            }
            if(cosine < .8){
                degreeOfDarkness += 1;
                break;
            }
//            degreeOfDarkness += 4;
        }
//        System.out.println(degreeOfDarkness);
        return degreeOfDarkness;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
