package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

public class DrawingCanvas extends JComponent {

    private Client client;
    private int width, height;

    //odredjivanje Client objekta i sirine i visine platna za crtanje
    public DrawingCanvas(Client client, int width, int height) {
        this.client = client;
        this.width = width;
        this.height = height;
        this.client.setCanvas(this);

        //dodavanje rukovodioca dogadjaja prevlacenja
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //pozivanje metoda za rukovodjenje dogadjaja nasledjene klase
                super.mouseDragged(e);

                //provera konekcije izmedju klijenta i servera
                if(client.isConnected()) {
                    //emitovanje koordinate dogadjaja prevlacenja serveru
                    client.write(client.getWriter(), e.getPoint());
                }
            }
        });
    }

    //osvezivanje platna za crtanje
    @Override
    protected void paintComponent(Graphics g) {
        //kreiranje Graphics2D objekta
        Graphics2D g2 = (Graphics2D) g;

        //odredjivanje boje platna
        g2.setColor(Color.WHITE);

        //odredjivanje oblika i velicine platna
        g2.fill(new Rectangle2D.Double(0, 0, width, height));

        //listanje kroz listu tacaka klijenta
        for(int i = 0; i < client.getPoints().size(); i++) {
            //uzimanje trenutne tacke iz liste tacaka klijenta
            Point point = client.getPoints().get(i);

            //odredjivanje boje tacke
            g2.setColor(Color.BLACK);

            //odredjivanje oblika i velicine tacke
            g2.fillOval(point.x, point.y, (int) (width * 0.05), (int) (height * 0.05));
        }
    }
}
