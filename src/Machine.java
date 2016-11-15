import java.net.InetAddress;
import java.net.UnknownHostException;

public class Machine {
    protected String getIPAddress() {
        try {
            InetAddress IP = InetAddress.getLocalHost();

            return IP.getHostAddress();
        }catch(UnknownHostException e) {
            e.printStackTrace();
        }

        return null;
    }
}
