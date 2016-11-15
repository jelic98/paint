import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends Machine {
    private String ip;
    private Server server;
    private DataInputStream input;
    private DataOutputStream output;

    public Client(boolean clientMode) {
//                int x = Integer.parseInt(position.substring(0, position.indexOf(".")));
//                int y = Integer.parseInt(position.substring(position.indexOf(".") + 1));
        if(!clientMode) {
            try {
                Socket socket = new Socket(server.getIP(), 5555);

                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getIP() {
        return ip;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
