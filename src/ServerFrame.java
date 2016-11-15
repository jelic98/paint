import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ServerFrame extends JFrame {
    private JPanel panel;
    private JTextArea taClients;
    private JButton btnAdd;
    private JLabel lblIP;
    private Server server;
    private ArrayList<Client> clients = new ArrayList<Client>();

    public ServerFrame() {
        server = new Server();

        setTitle("Server");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int frameWidth = (int) (screenSize.width * 0.25);
        int frameHeight = (int) (screenSize.width * 0.25);

        setSize(frameWidth, frameHeight);
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        lblIP = new JLabel("IP: " + server.getIPAddress());
        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        int textAreaWidth = frameWidth;
        int textAreaHeight = (int) (frameHeight * 0.5);

        taClients = new JTextArea();
        taClients.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
        taClients.setEditable(false);

        btnAdd = new JButton("Add client");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Add client");

                if(input != null && !input.isEmpty()) {
                    //todo validate IP address using regular expressions(regex)
                }
            }
        });

        panel.add(lblIP, BorderLayout.NORTH);
        panel.add(taClients, BorderLayout.CENTER);
        panel.add(btnAdd, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
    }

    public static void openFrame() {
        new ServerFrame().setVisible(true);
    }
}
