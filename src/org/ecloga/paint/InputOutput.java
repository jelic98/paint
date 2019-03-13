package org.ecloga.paint;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class InputOutput implements StringMaker {

    private Server server;
    private Client client;
    private StringBuilder builder;

    void read(BufferedReader reader) {
        try {
            String line;

            if(client != null) {
                while((line = reader.readLine()) != null) {
                    int x = Integer.parseInt(line.substring(0, line.indexOf(".")));
                    int y = Integer.parseInt(line.substring(line.indexOf(".") + 1));

                    client.getPoints().add(new Point(x, y));
                    client.getCanvas().repaint();
                }
            }else if(server != null) {
                while((line = reader.readLine()) != null) {
                    server.broadcast(line);
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            }catch(IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    void write(PrintWriter writer, Point point) {
        builder.setLength(0);
        builder.trimToSize();
        builder.append(point.x).append(".").append(point.y);

        write(writer, builder.toString());
    }

    void write(PrintWriter writer, String line) {
        writer.println(line);
        writer.flush();
    }

    void setMachine(Machine machine) {
        if(machine instanceof Client) {
            client = (Client) machine;
        }else if(machine instanceof Server) {
            server = (Server) machine;
        }
    }

    @Override
    public void setStringBuilder(StringBuilder builder) {
        this.builder = builder;
    }
}