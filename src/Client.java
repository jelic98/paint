import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Client extends Machine {
    private String ip;
    private Server server;
    private DataInputStream input;
    private DataOutputStream output;

    public ArrayList<Point> pressPoints = new ArrayList<Point>();
    public ArrayList<Point> releasePoints = new ArrayList<Point>();

    public Client(boolean clientMode) {
        if(!clientMode) {
            try {
                Socket socket = new Socket(server.getIP(), 2345);

                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(boolean released, Point point) {
        String data;

        if(released) {
            releasePoints.add(point);
            data = "R:";
        }else {
            pressPoints.add(point);
            data = "P:";
        }

        data += point.x;
        data += ".";
        data += point.y;

        try {
            output.writeUTF(data);
            output.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void read() {
        try {
            String data = input.readUTF();

            boolean released;

            switch(data.charAt(0)) {
                case 'P':
                    released = false;
                    break;
                case 'R':
                    released = true;
                    break;
            }

            int x = Integer.parseInt(data.substring(2, data.indexOf(".")));
            int y = Integer.parseInt(data.substring(data.indexOf(".") + 1));
        }catch(IOException e) {
            e.printStackTrace();
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
