package org.ecloga.paint;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private ServerSocket serverSocket;
    private Server server;

    public ClientHandler(ServerSocket serverSocket, Server server) {
        this.serverSocket = serverSocket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Socket socket = serverSocket.accept();

                InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String ip = inetSocketAddress.getAddress().toString().replace("/", "");

                server.addClient(ip, socket);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
