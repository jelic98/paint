import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Machine {
    public Server server;
    public JComponent canvas;
    public ArrayList<Point> points = new ArrayList<Point>();

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
}
