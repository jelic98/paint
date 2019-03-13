package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class ServerFrame extends JFrame {

    private final JTextArea taClients;

    private ServerFrame() {
        setResizable(false);
        setTitle("Server");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.25);
        int frameHeight = (int) (screenSize.width * 0.25);

        setSize(frameWidth, frameHeight);
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        int textAreaWidth = frameWidth;
        int textAreaHeight = (int) (frameHeight * 0.5);

        taClients = new JTextArea();
        taClients.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));
        taClients.setEditable(false);

        Server server = new Server();

        try {
            server.start(this);
        }catch(IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblIP = new JLabel("IP: " + server.getIP());
        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(lblIP, BorderLayout.NORTH);
        panel.add(taClients, BorderLayout.SOUTH);

        setVisible(true);
        setContentPane(panel);
        pack();
    }

    public static void open() {
        new ServerFrame();
    }

    public void addIP(String ip) {
        taClients.append(ip + "\n");
    }
}