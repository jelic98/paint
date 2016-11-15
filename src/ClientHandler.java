import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            while(true) {
                String position = input.readUTF();

                output.writeUTF(position);
                output.flush();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
