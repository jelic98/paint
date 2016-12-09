package org.ecloga.paint;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client extends Machine {
    private Server server;
    private JComponent canvas;

    public void connect(Server server) {
        this.server = server;

        try {
            Socket socket = new Socket(server.getIP(), 2345);

            Thread thread = new Thread(new Listener(socket, this));
            thread.start();

            points.clear();
            canvas.repaint();
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

    public boolean isConnected() {

        return server != null;
    }
}
