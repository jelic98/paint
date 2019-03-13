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
    private Socket socket;
    private BufferedReader reader;
    private JComponent canvas;
    private PrintWriter writer;
    private final ArrayList<Point> points;

    public Client() {
        points = new ArrayList<>();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                //disconnect();
            }
        });
    }

    public void connect(Server server) throws IOException {
        this.server = server;

        socket = new Socket();
        socket.connect(new InetSocketAddress(server.getIP(), Machine.PORT), TIMEOUT_MILLIS);

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream());

        points.clear();
        canvas.repaint();

        setMachine(this);
        setStringBuilder(new StringBuilder());

        new Thread(new Listener(this, reader)).start();
    }

    public void disconnect() {
        try {
            if(socket != null) {
                socket.close();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
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