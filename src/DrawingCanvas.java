import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawingCanvas extends JComponent {
    private int width, height;
    private Graphics2D g2;
    private ArrayList<Point> points = new ArrayList<Point>();

    public DrawingCanvas(int width, int height) {
        this.width = width;
        this.height = height;

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                points.add(e.getPoint());

                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(0, 0, width, height));

        for(Point point : points) {
            g2.setColor(Color.BLACK);
            g2.fillOval(point.x, point.y, (int) (height * 0.05), (int) (height * 0.05));
        }
    }
}
