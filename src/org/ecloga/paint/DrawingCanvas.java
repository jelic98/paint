package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

class DrawingCanvas extends JComponent {

    private final Client client;
    private final int width;
    private final int height;

    public DrawingCanvas(Client client, int width, int height) {
        this.client = client;
        this.width = width;
        this.height = height;
        this.client.setCanvas(this);

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);

                if(client.isConnected()) {
                    client.write(client.getWriter(), e.getPoint());
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.WHITE);

        g2.fill(new Rectangle2D.Double(0, 0, width, height));

        for(int i = 0; i < client.getPoints().size(); i++) {
            Point point = client.getPoints().get(i);

            g2.setColor(Color.BLACK);

            g2.fillOval(point.x, point.y, (int) (width * 0.05), (int) (height * 0.05));
        }
    }
}