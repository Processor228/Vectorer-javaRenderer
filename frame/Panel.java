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
    public ArrayList<Point3d> lights = new ArrayList<Point3d>();  // for now, for simplicity, lets hold it as just set of points.
    public ArrayList<Shape> shapes = new ArrayList<Shape>();
    public double distanceToProjection = 400;
    int Delay = 20;
    int time = 0;
    Color backGroundColor = Color.darkGray;
    /**
     * For testing this program I will set view position (0, 0, 0). Notice that I am going to establish viewer's own
     * local basis, which is always not identical to the global.
     */
    Point3d viewPos = new Point3d(0, 0, 0);
    Timer timer;

    public Panel(){
        timer = new Timer(Delay, this);
        timer.start();
        shapes.add(new Sphere(20, 10, 200, 10, Color.GREEN));
        shapes.add(new Sphere(25, 10, 130, 4, Color.BLUE));

        lights.add(new Point3d(30, 10, 20));
    }

    public void drawPixel(Point2d p, Graphics g, Color c){
        g.setColor(c);
        g.drawRect(p.x, p.y, 1, 1);
        g.setColor(backGroundColor);
    }

    /**
     * method implements the drawing on the screen every program cycle.
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // incrementing time, applying  movement based on time passed.
        time += 2;
        shapes.get(1).pos.x += 3 * cos(time / 10);
        shapes.get(1).pos.z -= 3 * sin(time / 10);

        for(int x = 0; x < this.getWidth(); x++){  // for every pixel on the screen
            for(int y = 0; y < this.getHeight(); y++){

                /* (x - getWidth ..) is for proper orientation on the screen: there is shifting from java frame coordinates
                 to our established coordinate system. */
                Point3d pointOfPixelInSpace = new Point3d((x - this.getWidth()/2), (y - this.getHeight()/2), distanceToProjection);

                Vector3d rayLineDirectionVector = new Vector3d(viewPos, pointOfPixelInSpace); // throwing a ray //

                Color colorToBePrinted = Color.darkGray;
                int degreeOfShader = 0;
                double closestDistance = Double.MAX_VALUE;

                // iterating through every shape on the scene to understand what we gonna have intersection with
                for(int i = 0; i < shapes.size(); i++){
                    Point3d intersection = shapes.get(i).intersectTrace(viewPos, rayLineDirectionVector);

                    double distance = (new Vector3d(viewPos, intersection)).calculateLength();


                    if(distance > 1 && distance < closestDistance){ // if closest
                        closestDistance = distance;
                        colorToBePrinted = shapes.get(i).getColor();


                        degreeOfShader = calculateShader(intersection, shapes.get(i).pos);
                        for(int degree = 0; degree < degreeOfShader; degree++){ // applying our shading
                            colorToBePrinted = colorToBePrinted.darker();
                        }
                    }
                }
                drawPixel(new Point2d(x, y), g, colorToBePrinted);
            }
        }
    }

    /**
     * Calculates shading of a point on the screen.
     * Note: to be overwritten. Very unclear code and usable only for spheres.
     * @param intersection point of intersection
     * @param spherePos position of sphere
     * @return degree of shader.
     */
    @Deprecated(forRemoval = true)
    public int calculateShader(Point3d intersection, Point3d spherePos){
        Vector3d normal = new Vector3d(spherePos, intersection);
        int degreeOfDarkness = 0;

        for(int i = 0; i < lights.size(); i++) {
            Vector3d light = new Vector3d(intersection, lights.get(i));

            boolean doesNotLightShape = false;
            // iterates through every light and checks whether they have effect on shading.
            for(int sphere = 0; sphere < shapes.size(); sphere++){
                if(shapes.get(sphere).pos == spherePos){
                    continue;
                }
                Point3d intersectionWithAnotherShape = shapes.get(sphere).intersectTrace(lights.get(i), light.reversed());
                Vector3d vectorOfIntersection = new Vector3d(lights.get(i), intersectionWithAnotherShape);

                if(intersectionWithAnotherShape.x != Double.MAX_VALUE && vectorOfIntersection.calculateLength() < light.calculateLength()){
                    doesNotLightShape = true;
                    break;
                }
            }

            // bad scheme for lighting. It does it linearly, via very straightforward way. Need to develop more sophisticated one.
            if(doesNotLightShape){
                degreeOfDarkness += 10;
                continue;
            }
            double cosine = normal.cos(light);
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
        }

        return degreeOfDarkness;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
