package org.ecloga.paint;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class InputOutput implements StringMaker {

    private Server server;
    private Client client;
    private StringBuilder builder;

    //citanje linija BufferedReader objekta
    protected void read(BufferedReader reader) {
        try {
            //postavljanje inicijalne vrednosti linije na NULL
            String line;

            //provera tipa Machine objekta
            if(client != null) {
                //citanje linija sve dok one postoje
                while((line = reader.readLine()) != null) {
                    //izvlacenje koordinata tacke iz linije
                    int x = Integer.parseInt(line.substring(0, line.indexOf(".")));
                    int y = Integer.parseInt(line.substring(line.indexOf(".") + 1));

                    //kreiranje tacke i njeno dodavanje u listu tacaka klijenta
                    client.getPoints().add(new Point(x, y));

                    //osvezivanje platna za crtanje
                    client.getCanvas().repaint();
                }
            }else if(server != null) {
                //citanje linija sve dok one postoje
                while((line = reader.readLine()) != null) {
                    //emitovanje linije svim klijentima
                    server.broadcast(line);
                }
            }

            //zavrsetak citanja zatvaranjem BufferedReader objekta
            reader.close();
        }catch(IOException e) {
            //logovanje greske
            e.printStackTrace();
        }
    }

    //emitovanje tacke serveru pomocu PrintWriter objekta
    protected void write(PrintWriter writer, Point point) {
        //praznjenje StringBuilder objekta
        builder.setLength(0);
        builder.trimToSize();

        //dodavanje koordinata tacke u StringBuilder objekat
        builder.append(point.x).append(".").append(point.y);

        //kreiranje linije preko StringBuilder objekta
        String packet = builder.toString();

        //pozivanje istoimenog metoda za emitovanje linije
        write(writer, packet);
    }

    //emitovanje linije pomocu PrintWriter objekta
    protected void write(PrintWriter writer, String line) {
        //emitovanje linije
        writer.println(line);

        //zavrsetak emitovanja
        writer.flush();
    }

    //odredjivanje Machine objekta
    protected void setMachine(Machine machine) {
        //provera tipa Machine objekta
        if(machine instanceof Client) {
            //odredjivanje Client objekta
            client = (Client) machine;
        }else if(machine instanceof Server) {
            //odredjivanje Server objekta
            server = (Server) machine;
        }
    }

    //odredjivanje StringBuilder objekta
    @Override
    public void setStringBuilder(StringBuilder builder) {
        this.builder = builder;
    }
}
