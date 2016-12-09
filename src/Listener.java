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
        machine.read();
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
