import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client extends Machine {
    public Server server;
    public JComponent canvas;

    public void connect(Server server) {
        this.server = server;

        try {
            Socket socket = new Socket(server.getIP(), 2345);

            Thread thread = new Thread(new Handler(socket, this));
            thread.start();

            points.clear();
            canvas.repaint();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
