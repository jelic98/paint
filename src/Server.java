import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Machine {
    private ArrayList<Client> clients = new ArrayList<Client>();

    public Server(boolean startServer) {
        if(startServer) {
            try {
                ServerSocket serverSocket = new ServerSocket(2345);

                while(true) {
                    Socket socket = serverSocket.accept();

                    Thread thread = new Thread(new ClientHandler(socket));
                    thread.start();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addClient(Client client) {
        clients.add(client);
    }
}
