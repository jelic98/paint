import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Machine {
    private String ip;

    protected DataInputStream input;
    protected DataOutputStream output;

    public ArrayList<Point> points = new ArrayList<Point>();

    protected void setIP(String ip) {
        this.ip = ip;
    }

    protected String getIP() {
        if(ip == null) {
            setIP(getInetAddress());
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
        try {
            while(input.available() > 0) {
                String data = input.readUTF();

                int x = Integer.parseInt(data.substring(0, data.indexOf(".")));
                int y = Integer.parseInt(data.substring(data.indexOf(".") + 1));

                Point point = new Point(x, y);

                points.add(point);

                write(point);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Point point) {
        String data = point.x + "." + point.y;

        try {
            output.writeUTF(data);
            output.flush();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
