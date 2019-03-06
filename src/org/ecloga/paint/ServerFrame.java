package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;

class ServerFrame extends JFrame {

    private final JTextArea taClients;

    private ServerFrame() {
        Server server = new Server();

        server.start(this);

        setTitle("Server");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int frameWidth = (int) (screenSize.width * 0.25);
        int frameHeight = (int) (screenSize.width * 0.25);

        setSize(frameWidth, frameHeight);

        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        JPanel panel = new JPanel();

        panel.setLayout(new BorderLayout());

        JLabel lblIP = new JLabel("IP: " + server.getIP());

        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        int textAreaWidth = frameWidth;
        int textAreaHeight = (int) (frameHeight * 0.5);

        taClients = new JTextArea();

        taClients.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));

        taClients.setEditable(false);

        panel.add(lblIP, BorderLayout.NORTH);
        panel.add(taClients, BorderLayout.SOUTH);

        setContentPane(panel);

        pack();
    }

    public static void open() {
        new ServerFrame().setVisible(true);
    }

    public void addIP(String ip) {
        taClients.append(ip + "\n");
    }
}