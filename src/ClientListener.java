import java.net.ServerSocket;
import java.net.Socket;

public class ClientListener implements Runnable {
    private ServerSocket serverSocket;
    private Server server;

    public ClientListener(ServerSocket serverSocket, Server server) {
        this.serverSocket = serverSocket;
        this.server = server;
    }

    @Override
    public void run() {
        infiniteLoop: while(true) {
            try {
                Socket socket = serverSocket.accept();

                Thread thread = new Thread(new Handler(socket, server));
                thread.start();

                Client client = new Client();
                client.connect(server);

                server.addClient(client);

                Thread.sleep(1000);
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
