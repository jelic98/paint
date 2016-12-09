import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Machine {
    private String ip;

    protected BufferedReader reader;
    protected PrintWriter writer;

    protected void setIP(String ip) {
        this.ip = ip;
    }

    protected String getIP() {
        if(ip == null) {
            getInetAddress();
        }

        return ip;
    }

    protected String getInetAddress() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            String ip = inetAddress.getHostAddress();

            setIP(ip);

            return ip;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void read() {
        String line;

        try {
            while((line = reader.readLine()) != null) {
                System.out.print("READ ");

                if(this instanceof Client) {
                    System.out.print("CLIENT: ");

                    Client client = (Client) this;

                    int x = Integer.parseInt(line.substring(0, line.indexOf(".")));
                    int y = Integer.parseInt(line.substring(line.indexOf(".") + 1));

                    Point point = new Point(x, y);

                    client.points.add(point);
                    client.canvas.repaint();
                }else if(this instanceof Server) {
                    System.out.print("SERVER: ");

                    Server server = (Server) this;
                    server.broadcast(line);
                }

                System.out.println(line);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Point point) {
        writer.println(point.x + "." + point.y);
        writer.flush();

        System.out.print("WRITE ");

        if(this instanceof Client) {
            System.out.print("CLIENT: ");
        }else if(this instanceof Server) {
            System.out.print("SERVER: ");
        }

        System.out.println(point.x + "." + point.y);
    }
}
