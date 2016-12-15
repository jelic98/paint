package org.ecloga.paint;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Machine extends InputOutput {

    private String ip;

    //odredjivanje IP adrese
    protected void setIP(String ip) {
        this.ip = ip;
    }

    //uzimanje IP adrese
    protected String getIP() {
        //provera da li Machine objekat poseduje IP adresu
        if(ip == null) {
            //uzimanje adrese
            getInetAddress();
        }

        //vracanje IP adrese
        return ip;
    }

    //uzimanje adrese
    protected String getInetAddress() {
        try {
            //uzimanje lokalnog hosta
            InetAddress inetAddress = InetAddress.getLocalHost();

            //kreiranje IP adrese na osnovu lokalnog hosta
            String ip = inetAddress.getHostAddress();

            //odredjivanje IP adrese
            setIP(ip);

            //vracanje IP adrese
            return ip;
        }catch(UnknownHostException e) {
            //logovanje greske
            e.printStackTrace();
        }

        //vracanje vrednosti za neuspelu konekciju
        return "undefined";
    }
}
