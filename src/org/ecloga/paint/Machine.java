package org.ecloga.paint;

import java.net.*;

public class Machine extends InputOutput {

    static final int PORT = 2345;

    private String ip;

    //odredjivanje IP adrese
    void setIP(String ip) {
        this.ip = ip;
    }

    //uzimanje IP adrese
    String getIP() {
        //provera da li Machine objekat poseduje IP adresu
        if(ip == null) {
            //uzimanje adrese
            getInetAddress();
        }

        //vracanje IP adrese
        return ip;
    }

    //uzimanje adrese
    private String getInetAddress() {
        try {
            //postavljanje IP adrese
            setIP(InetAddress.getLocalHost().getHostAddress());

            //vracanje IP adrese
            return ip;
        }catch(Exception e) {
            //logovanje greske
            e.printStackTrace();
        }

        //vracanje vrednosti za neuspelu konekciju
        return "undefined";
    }
}
