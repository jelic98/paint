import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

public class DrawingCanvas extends JComponent {
    private Client client;
    private int width, height;
    private Graphics2D g2;

    public DrawingCanvas(Client client, int width, int height) {
        this.client = client;
        this.width = width;
        this.height = height;

        this.client.canvas = this;

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                if(client.server != null) {
                    client.write(e.getPoint());
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(0, 0, width, height));

        for(int i = 0; i < client.points.size(); i++) {
            Point point = client.points.get(i);

            g2.setColor(Color.BLACK);
            g2.fillOval(point.x, point.y, (int) (width * 0.05), (int) (height * 0.05));
        }
    }
}
