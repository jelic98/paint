package org.ecloga.paint;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler implements Runnable {

    private final ServerSocket serverSocket;
    private final Server server;

    public ClientHandler(ServerSocket serverSocket, Server server) {
        this.serverSocket = serverSocket;
        this.server = server;
    }

    @Override
    public void run() {
        while(true) {
            Socket socket;

            try {
                socket = serverSocket.accept();
            }catch(IOException e) {
                break;
            }

            InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

            String ip = inetSocketAddress.getAddress().toString().replace("/", "");

            server.addClient(ip, socket);
        }
    }
}