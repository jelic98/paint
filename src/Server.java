import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Machine {
    private String ip;
    private ArrayList<Client> clients = new ArrayList<Client>();

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(5555);

            while(true) {
                Socket socket = serverSocket.accept();

                Thread thread = new Thread(new ClientHandler(socket, this));
                thread.start();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getIP() {
        return ip;
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
