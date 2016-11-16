import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;
    private Machine machine;

    public Handler(Socket socket, Machine machine) {
        this.socket = socket;
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            machine.input = new DataInputStream(socket.getInputStream());
            machine.output = new DataOutputStream(socket.getOutputStream());

            while(true) {
                machine.read();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
