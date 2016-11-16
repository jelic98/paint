import java.net.InetSocketAddress;
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
        try {
            while(true) {
                Socket socket = serverSocket.accept();

                Thread thread = new Thread(new Handler(socket, server));
                thread.start();

                InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String ip = inetSocketAddress.getAddress().toString().replace("/", "");

                server.addClient(ip);

                Thread.sleep(1000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
