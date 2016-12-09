import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;

public class Server extends Machine {
    public ServerFrame frame;
    public ArrayList<PrintWriter> clients = new ArrayList<PrintWriter>();

    public void start(ServerFrame frame) {
        this.frame = frame;

        try {
            ServerSocket serverSocket = new ServerSocket(2345);

            Thread clientListener = new Thread(new ClientHandler(serverSocket, this));
            clientListener.start();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(String line) {
        Iterator i = clients.iterator();

        System.out.println("ITERATOR " + clients.size());

        while(i.hasNext()) {
            PrintWriter writer = (PrintWriter) i.next();

            System.out.println("BROADCAST " + line);

            writer.println(line);
            writer.flush();
        }
    }
}
