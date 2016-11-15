import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientFrame extends JFrame {
    private JPanel panel;
    private JButton btnAdd;
    private JComponent canvas;
    private JLabel lblIP, lblServer;
    private Client client;

    public ClientFrame() {
        client = new Client();

        setTitle("Client");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int frameWidth = (int) (screenSize.width * 0.5);
        int frameHeight = (int) (screenSize.width * 0.5);

        setSize(frameWidth, frameHeight);
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());

        lblIP = new JLabel("IP: " + client.getIPAddress());
        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        lblServer = new JLabel("SERVER: 0.0.0.0");
        lblServer.setHorizontalAlignment(SwingConstants.CENTER);

        labelPanel.add(lblIP, BorderLayout.NORTH);
        labelPanel.add(lblServer, BorderLayout.SOUTH);

        canvas = new DrawingCanvas(frameWidth, frameHeight);

        btnAdd = new JButton("Add server");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Add server");

                if(input != null && !input.isEmpty()) {
                    //todo validate IP address using regular expressions(regex)
                    lblServer.setText("SERVER: " + input);
                }
            }
        });

        panel.add(labelPanel, BorderLayout.NORTH);
        panel.add(canvas, BorderLayout.CENTER);
        panel.add(btnAdd, BorderLayout.SOUTH);

        setContentPane(panel);
    }

    public static void openFrame() {
        new ClientFrame().setVisible(true);
    }
}
