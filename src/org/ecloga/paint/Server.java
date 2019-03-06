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
    private final ArrayList<PrintWriter> writers;

    //odredjivanje inicijalne vrednosti niza PrintWriter objekta
    public Server() {
        writers = new ArrayList<>();
    }

    //pokretanje servera
    public void start(ServerFrame frame) {
        this.frame = frame;

        //odredjivanje Machine objekta
        setMachine(this);

        try {
            //kreiranje ServerSocket objekta na osnovu porta
            ServerSocket serverSocket = new ServerSocket(Machine.PORT);

            //kreiranje programske niti koja ce citati linije BufferedReader objekta
            Thread clientListener = new Thread(new ClientHandler(serverSocket, this));

            //startovanje programske niti
            clientListener.start();
        }catch(IOException e) {
            //logovanje greske
            e.printStackTrace();
        }
    }

    //emitovanje linije svim klijentima
    public void broadcast(String line) {
        //kreiranje Iterator objekta za listu PrintWriter objekta
        Iterator i = writers.iterator();

        //listanje kroz listu PrintWriter objekta pomocu Iterator objekta
        while(i.hasNext()) {
            //emotovanje linijje klijentu pomocu trenutnog PrintWriter objekta
            write((PrintWriter) i.next(), line);
        }
    }

    //dodavanje klijenta
    public void addClient(String ip, Socket socket) {
        //dodavanje Ip adrese klijenta
        frame.addIP(ip);

        try {
            //odredjivanje BufferedReader objekta na osnovu soketa
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //odredjivanje PrintWriter objekta na osnovu soketa
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            //dodavanje PrintWriter objekta u listu kojoj pripada
            writers.add(writer);

            //kreiranje programske niti koja ce citati linije BufferedReader objekta
            Thread listener = new Thread(new Listener(this, reader));

            //startovanje programske niti
            listener.start();
        }catch(IOException e) {
            //logovanje greske
            e.printStackTrace();
        }
    }
}
