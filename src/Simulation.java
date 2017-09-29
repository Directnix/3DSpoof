import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nick van Endhoven, 2119719 on 20-Sep-17.
 */
public class Simulation extends JPanel implements ActionListener {

    public static void main(String[] args) {
        JFrame  frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setContentPane(new Simulation());
        frame.setVisible(true);
    }

    ArrayList<SimObject> objects = new ArrayList<>();

    Simulation(){
        objects.add(new SimObject(new Point2D.Double(620,540), 12, 1, imageFromResource("tree.png")));
        objects.add(new SimObject(new Point2D.Double(960,540), 6, 0.6f, imageFromResource("endzone.png")));
        objects.add(new SimObject(new Point2D.Double(960,540), 5, 1, imageFromResource("car.png")));
        objects.add(new SimObject(new Point2D.Double(1300,540), 6, 1, imageFromResource("human.png")));

        new Timer(15, this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(SimObject o: objects)
            o.draw((Graphics2D) g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(SimObject o: objects)
            o.update();
        repaint();
    }

    BufferedImage imageFromResource(String img){
        try {
            return  ImageIO.read(this.getClass().getResource("/" + img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
