package org.ecloga.paint;

import java.io.BufferedReader;
import java.io.IOException;

public class ServerListener implements Runnable {

    private Server server;
    private BufferedReader reader;

    public ServerListener(Server server, BufferedReader reader) {
        this.server = server;
        this.reader = reader;
    }

    @Override
    public void run() {
        try {
            String line;

            while((line = reader.readLine()) != null) {
                server.broadcast(line);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
