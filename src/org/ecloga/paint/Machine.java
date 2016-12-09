package org.ecloga.paint;

import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Machine {
    private String ip;

    protected BufferedReader reader;
    protected PrintWriter writer;
    protected ArrayList<Point> points = new ArrayList<Point>();

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
                int x = Integer.parseInt(line.substring(0, line.indexOf(".")));
                int y = Integer.parseInt(line.substring(line.indexOf(".") + 1));

                points.add(new Point(x, y));

                if(this instanceof Client) {
                    ((Client) this).getCanvas().repaint();
                }else if(this instanceof Server) {
                    ((Server) this).broadcast(line);
                }
            }
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void write(Point point) {
        writer.println(point.x + "." + point.y);
        writer.flush();
    }
}
