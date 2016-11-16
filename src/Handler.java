import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Handler implements Runnable {
    private Socket socket;
    private Machine machine;

    public Handler(Socket socket, Machine machine) {
        this.socket = socket;
        this.machine = machine;
    }

    @Override
    public void run() {
        try {
            machine.input = new DataInputStream(socket.getInputStream());
            machine.output = new DataOutputStream(socket.getOutputStream());

            while(true) {
                machine.read();

                if(machine instanceof Server) {
                    for(Point point : machine.points) {
                        machine.write(point);
                        System.out.println("SERVER RESPONSE");
                    }
                }else if(machine instanceof Client) {
                    ((Client) machine).canvas.repaint();
                }

                Thread.sleep(250);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
