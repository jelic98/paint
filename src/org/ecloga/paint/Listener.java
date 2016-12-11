package org.ecloga.paint;

import java.io.*;
import java.net.Socket;

public class Listener implements Runnable {

    private Socket socket;
    private Machine machine;

    public Listener(Socket socket, Machine machine) {
        this.socket = socket;
        this.machine = machine;

        createStreams();
    }

    @Override
    public void run() {
        if(machine instanceof Client) {
            machine.read();
        }else if(machine instanceof Server) {
            Thread listener = new Thread(new ServerListener((Server) machine, machine.reader));
            listener.start();
        }
    }

    private void createStreams() {
        try {
            machine.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            machine.writer = new PrintWriter(socket.getOutputStream());
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
