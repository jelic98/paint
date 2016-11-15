import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawingCanvas extends JComponent {
    private int width, height;
    private Graphics2D g2;

    public DrawingCanvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(0, 0, width, height));
    }
}
