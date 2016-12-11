package org.ecloga.paint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends Machine {

    private ServerFrame frame;
    private ArrayList<PrintWriter> writers;

    public Server() {
        writers = new ArrayList<PrintWriter>();
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

    public void write(String line) {
        Iterator i = writers.iterator();

        while(i.hasNext()) {
            PrintWriter writer = (PrintWriter) i.next();

            writer.println(line);
            writer.flush();
        }
    }

    public void addClient(String ip, Socket socket) {
        frame.add(ip);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writers.add(writer);

            Thread listener = new Thread(new ServerListener(this, reader));
            listener.start();
        }catch(IOException e) {
            e.printStackTrace();
            return;
        }
    }
}
