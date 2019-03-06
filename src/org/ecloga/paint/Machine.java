package org.ecloga.paint;

import java.net.*;

public class Machine extends InputOutput {

    static final int PORT = 2345;
    private String ip;

    void setIP(String ip) {
        this.ip = ip;
    }

    String getIP() {
        if(ip == null) {
            getInetAddress();
        }

        return ip;
    }

    private String getInetAddress() {
        try {
            setIP(InetAddress.getLocalHost().getHostAddress());

            return ip;
        }catch(Exception e) {
            e.printStackTrace();
        }

        return "undefined";
    }
}