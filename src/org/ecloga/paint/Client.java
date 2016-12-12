package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Machine {

    private Server server;
    private JComponent canvas;
    private ArrayList<Point> points;
    private PrintWriter writer;

    public Client() {
        points = new ArrayList<Point>();
    }

    public void connect(Server server) throws IOException {
        this.server = server;

        Socket socket = new Socket(server.getIP(), 2345);

        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());

        points.clear();
        canvas.repaint();

        setMachine(this);
        setStringBuilder(new StringBuilder());

        Thread thread = new Thread(new Listener(this, reader));
        thread.start();
    }

    public void setCanvas(JComponent canvas) {
        this.canvas = canvas;
    }

    public JComponent getCanvas() {

        return canvas;
    }

    public ArrayList<Point> getPoints() {

        return points;
    }

    public PrintWriter getWriter() {

        return writer;
    }

    public boolean isConnected() {

        return server != null;
    }
}
