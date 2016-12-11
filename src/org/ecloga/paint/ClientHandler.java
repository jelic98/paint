package org.ecloga.paint;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream());

                InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
                String ip = inetSocketAddress.getAddress().toString().replace("/", "");

                server.addClient(ip, reader, writer);

                Thread listener = new Thread(new Listener(socket, server));
                listener.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
