package org.ecloga.paint;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Machine implements PacketSender {

    private String ip;
    private StringBuilder builder;

    protected BufferedReader reader;
    protected PrintWriter writer;

    public Machine() {
        builder = createStringBuilder();
    }

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
        try {
            String line;

            while((line = reader.readLine()) != null) {
                int x = Integer.parseInt(line.substring(0, line.indexOf(".")));
                int y = Integer.parseInt(line.substring(line.indexOf(".") + 1));

                Client client = (Client) this;

                client.getPoints().add(new Point(x, y));
                client.getCanvas().repaint();
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Point point) {
        builder.setLength(0);
        builder.trimToSize();
        builder.append(point.x).append(".").append(point.y);

        String packet = builder.toString();

        writer.println(packet);
        writer.flush();
    }

    @Override
    public StringBuilder createStringBuilder() {

        return new StringBuilder();
    }
}
