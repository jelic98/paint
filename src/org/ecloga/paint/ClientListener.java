package org.ecloga.paint;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

public class ClientListener implements Runnable {

    private Client client;
    private BufferedReader reader;

    public ClientListener(Client client, BufferedReader reader) {
        this.client = client;
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            String line;

            while((line = reader.readLine()) != null) {
                int x = Integer.parseInt(line.substring(0, line.indexOf(".")));
                int y = Integer.parseInt(line.substring(line.indexOf(".") + 1));

                client.getPoints().add(new Point(x, y));
                client.getCanvas().repaint();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
