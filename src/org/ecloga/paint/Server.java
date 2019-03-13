package org.ecloga.paint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

class Server extends Machine {

    private ServerFrame frame;
    private ServerSocket serverSocket;
    private final ArrayList<PrintWriter> writers;

    public Server() {
        writers = new ArrayList<>();

        Server instance = this;

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                instance.stop();
            }
        });
    }

    public void start(ServerFrame frame) throws IOException {
        this.frame = frame;

        setMachine(this);

        serverSocket = new ServerSocket(Machine.PORT);

        new Thread(new ClientHandler(serverSocket, this)).start();
    }

    public void stop() {
        try {
            if(serverSocket != null) {
                serverSocket.close();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String line) {
        Iterator i = writers.iterator();

        while(i.hasNext()) {
            write((PrintWriter) i.next(), line);
        }
    }

    public void addClient(String ip, Socket socket) {
        frame.addIP(ip);

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writers.add(writer);

            new Thread(new Listener(this, reader)).start();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}