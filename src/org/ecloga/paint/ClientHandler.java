package org.ecloga.paint;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler implements Runnable {

    private final ServerSocket serverSocket;
    private final Server server;

    //odredjivanje ServerSocket i Server objekta
    public ClientHandler(ServerSocket serverSocket, Server server) {
        this.serverSocket = serverSocket;
        this.server = server;
    }

    //izvrsavanje u pozadini
    @Override
    public void run() {
        try {
            //konstantno izvrsavanje
            while(true) {
                //cekanje klijenta da se poveze sa serverom i kreiranje soketa
                Socket socket = serverSocket.accept();

                //uzimanje ip adrese na osnovu soketa
                InetSocketAddress inetSocketAddress = (InetSocketAddress) socket.getRemoteSocketAddress();

                //kreiranje ip adrese
                String ip = inetSocketAddress.getAddress().toString().replace("/", "");

                //dodavanje klijenta
                server.addClient(ip, socket);
            }
        }catch(Exception e){
            //logovanje greske
            e.printStackTrace();
        }
    }
}
