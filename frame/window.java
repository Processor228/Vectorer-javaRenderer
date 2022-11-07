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

    }

    @Override
    public void mouseReleased(MouseEvent e) {

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
