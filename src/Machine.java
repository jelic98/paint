import java.net.InetAddress;
import java.net.UnknownHostException;

public class Machine {
    protected String ip;

    protected void setIP(String ip) {
        this.ip = ip;
    }

    protected String getIP() {
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
