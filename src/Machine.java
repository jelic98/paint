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

    public ArrayList<String> read() {
        String line;
        ArrayList<String> lines = new ArrayList<String>();

        try {
            while((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }catch(IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    public void write(Point point) {
        writer.println(point.x + "." + point.y);
        writer.flush();
    }
}
