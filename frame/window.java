package vector.frame;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.util.Optional;
import javax.swing.JFrame;
import javax.swing.JLabel;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class window extends JFrame implements MouseListener, MouseMotionListener {

    private static int width = 500;
    private int pointCatch = -1;
    private static int height = 500;
    private static Panel mainPic;
    private static final String title = "Vectox";
    public window(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(width, height);
        mainPic = new Panel();
        add(mainPic);
        mainPic.addMouseListener(this);
        mainPic.addMouseMotionListener(this);
        setTitle(title);
        setVisible(true);
    }

    public static void main(String[] args) {
        window demo = new window();
        demo.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        Point mouseLocation = e.getPoint();
//        for(int i=0; i < mainPic.points.size(); i++){
//            System.out.print(mainPic.points.get(i).x + " " + mouseLocation.x + " ");
//            System.out.println(mainPic.points.get(i).y + " " + mouseLocation.y);
//            if( sqrt(pow((mainPic.points.get(i).y - mouseLocation.y), 2) + pow((mainPic.points.get(i).x - mouseLocation.x), 2)) < 10){
//                pointCatch = i;
//                break;
//            }
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        Point mouseLocation = e.getPoint();
//        System.out.println(this.getX() + " " + this.getY());
//        if(pointCatch != -1) {
//            mainPic.points.get(pointCatch).y = mouseLocation.y;
//            mainPic.points.get(pointCatch).x = mouseLocation.x;
//            System.out.println(mainPic.points.get(pointCatch).x + " " + mainPic.points.get(pointCatch).y);
//        }
//        pointCatch = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
