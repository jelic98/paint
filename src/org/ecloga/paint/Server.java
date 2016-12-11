package org.ecloga.paint;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends Machine {

    private ServerFrame frame;
    private ArrayList<PrintWriter> clients;

    public Server() {
        clients = new ArrayList<PrintWriter>();
    }

    public void start(ServerFrame frame) {
        this.frame = frame;

        try {
            ServerSocket serverSocket = new ServerSocket(2345);

            Thread clientListener = new Thread(new ClientHandler(serverSocket, this));
            clientListener.start();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String line) {
        Iterator i = clients.iterator();

        while(i.hasNext()) {
            PrintWriter writer = (PrintWriter) i.next();

            writer.println(line);
            writer.flush();
        }
    }

    public void addClient(String ip) {
        frame.add(ip);
    }

    public ArrayList<PrintWriter> getClients() {

        return clients;
    }
}