import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server extends Machine {
    private ServerFrame frame;
    public ArrayList<Client> clients = new ArrayList<Client>();

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

    public void addClient(Client client) {
        clients.add(client);
        frame.addClient(client.getIP());
    }
}
