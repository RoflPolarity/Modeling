import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Timer;
import java.util.TimerTask;

public class lab1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Moving Circle");
                JPanel panel = new JPanel();
                MovingCircle MovingCircle = new MovingCircle();
                panel.add(MovingCircle);
                frame.getContentPane().add(panel);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        MovingCircle.repaint();
                    }
                },0,1);

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(600, 600);
                frame.setVisible(true);
            }
        });
    }
}

class MovingCircle extends JComponent{
    int  g = 10;
    double time = 0;
    double alpha;
    private double scale;
    double l = 150;
    double w = Math.sqrt(g/l);
    public double x;
    public double y;
    public MovingCircle() {
        scale = 1.0;
        setPreferredSize(new Dimension(600, 600));
        alpha = Math.toRadians(45);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setColor(Color.black);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.black);
        g2d.scale(scale, scale);
        x = this.getWidth()/2 + l * Math.sin(alpha*Math.cos(w * time));
        y = this.getHeight()/16 + l * Math.cos(alpha*Math.cos(w * time));
        time += 0.1;
        Ellipse2D el = new Ellipse2D.Double(x, y, 20, 20);
        Line2D opora = new Line2D.Double(this.getWidth()/2-50,this.getHeight()/16,this.getWidth()/2+50,this.getHeight()/16);
        g2d.draw(opora);
        g2d.draw(new Line2D.Double(opora.getX1()+(opora.getX2()-opora.getX1())/2,opora.getY1(),el.getCenterX(),el.getCenterY()));
        g2d.fill(el);
    }
}