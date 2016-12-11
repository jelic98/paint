package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Machine {

    private Server server;
    private JComponent canvas;
    private ArrayList<Point> points;

    public Client() {
        points = new ArrayList<Point>();
    }

    public void connect(Server server) throws IOException {
        this.server = server;

        Socket socket = new Socket(server.getIP(), 2345);

        Thread thread = new Thread(new Listener(socket, this));
        thread.start();

        points.clear();
        canvas.repaint();
    }

    public ArrayList<Point> getPoints() {

        return points;
    }

    public void setCanvas(JComponent canvas) {
        this.canvas = canvas;
    }

    public JComponent getCanvas() {

        return canvas;
    }

    public boolean isConnected() {

        return server != null;
    }
}
