import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class DrawingCanvas extends JComponent {
    private int width, height;
    private Graphics2D g2;
    private ArrayList<Point> pressPoints = new ArrayList<Point>();
    private ArrayList<Point> releasePoints = new ArrayList<Point>();

    public DrawingCanvas(int width, int height) {
        this.width = width;
        this.height = height;

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                pressPoints.add(e.getPoint());

                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);

                releasePoints.add(e.getPoint());
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(0, 0, width, height));

        for(int i = 1; i < pressPoints.size(); i++) {
            Point currentPoint = pressPoints.get(i);
            Point previousPoint = pressPoints.get(i - 1);

            g2.setColor(Color.BLACK);

            boolean released = false;

            for(Point releasePoint : releasePoints) {
                if(previousPoint.equals(releasePoint)) {
                    released = true;
                    break;
                }
            }

            if(released) {
                g2.drawOval(currentPoint.x, currentPoint.y, 1, 1);
            }else {
                g2.drawLine(previousPoint.x, previousPoint.y, currentPoint.x, currentPoint.y);
            }
        }
    }
}
