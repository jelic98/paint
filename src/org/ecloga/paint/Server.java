package org.ecloga.paint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends Machine {

    private ServerFrame frame;
    private ArrayList<BufferedReader> readers;
    private ArrayList<PrintWriter> writers;

    public Server() {
        readers = new ArrayList<BufferedReader>();
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

    public void broadcast(String line) {
        Iterator i = writers.iterator();

        while(i.hasNext()) {
            PrintWriter writer = (PrintWriter) i.next();

            writer.println(line);
            writer.flush();
        }
    }

    public void read() {
        Iterator i = readers.iterator();

        while(i.hasNext()) {
            BufferedReader reader = (BufferedReader) i.next();

            String line;

            try {
                while((line = reader.readLine()) != null) {
                    broadcast(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addClient(String ip, BufferedReader reader, PrintWriter writer) {
        frame.add(ip);

        readers.add(reader);
        writers.add(writer);
    }
}
