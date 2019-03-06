package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

class Client extends Machine {

    private static final int TIMEOUT_MILLIS = 3000;

    private Server server;
    private JComponent canvas;
    private PrintWriter writer;
    private final ArrayList<Point> points;

    public Client() {
        points = new ArrayList<>();
    }

    public void connect(Server server) throws IOException {
        this.server = server;

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(server.getIP(), Machine.PORT), TIMEOUT_MILLIS);

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