import java.io.IOException;
import java.net.ServerSocket;

public class Server extends Machine {
    private ServerFrame frame;

    public void start(ServerFrame frame) {
        this.frame = frame;

        try {
            ServerSocket serverSocket = new ServerSocket(2345);

            Thread thread = new Thread(new ClientListener(serverSocket, this));
            thread.start();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void addClient(String ip) {
        frame.addClient(ip);
    }
}
