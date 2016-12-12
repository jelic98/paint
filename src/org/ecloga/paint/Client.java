package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Machine {

    private Server server;
    private JComponent canvas;
    private ArrayList<Point> points;
    private PrintWriter writer;

    //odredjivanje inicijalne vrednosti niza tacaka
    public Client() {
        points = new ArrayList<Point>();
    }

    //povezivanje sa serverom
    public void connect(Server server) throws IOException {
        this.server = server;

        //kreiranje soketa na osnovu IP adrese servera i porta
        Socket socket = new Socket(server.getIP(), 2345);

        //kreiranje BufferedReader objekta na osnovu soketa
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //kreiranje PrintWriter objekta na osnovu soketa
        writer = new PrintWriter(socket.getOutputStream());

        //praznjenje liste tacaka
        points.clear();

        //osvezivanje platna za crtanje
        canvas.repaint();

        //odredjivanje Machine objekta
        setMachine(this);

        //odredjivanje StringBuilder objekta
        setStringBuilder(new StringBuilder());

        //kreiranje programske niti koja ce citati linije BufferedReader objekta
        Thread thread = new Thread(new Listener(this, reader));

        //startovanje programske niti
        thread.start();
    }

    //odredjivanje platna za crtanje
    public void setCanvas(JComponent canvas) {
        this.canvas = canvas;
    }

    //uzimanje platna za crtanje
    public JComponent getCanvas() {

        return canvas;
    }

    //uzimanje liste tacaka
    public ArrayList<Point> getPoints() {

        return points;
    }

    //uzimanje PrintWriter objekta
    public PrintWriter getWriter() {

        return writer;
    }

    //provera konekcije izmedju klijenta i servera
    public boolean isConnected() {

        return server != null;
    }
}
