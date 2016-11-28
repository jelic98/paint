import java.awt.*;
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
        if(machine instanceof Server) {
            Server server = (Server) machine;

            for(String line : server.read()) {
                server.broadcast(line);
            }
        }else if(machine instanceof Client) {
            Client client = (Client) machine;

            for(String line : client.read()) {
                int x = Integer.parseInt(line.substring(0, line.indexOf(".")));
                int y = Integer.parseInt(line.substring(line.indexOf(".") + 1));

                Point point = new Point(x, y);

                client.points.add(point);
            }

            client.canvas.repaint();
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
