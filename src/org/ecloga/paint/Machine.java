package org.ecloga.paint;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Machine {

    private String ip;

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
        }catch(UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }
}
