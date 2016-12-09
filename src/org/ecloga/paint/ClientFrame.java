package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientFrame extends JFrame {
    private JLabel lblServer;
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

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());

        JLabel lblIP = new JLabel("IP: " + client.getInetAddress());
        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        lblServer = new JLabel("SERVER: Undefined");
        lblServer.setHorizontalAlignment(SwingConstants.CENTER);

        labelPanel.add(lblIP, BorderLayout.NORTH);
        labelPanel.add(lblServer, BorderLayout.SOUTH);

        JComponent canvas = new DrawingCanvas(client, frameWidth, frameHeight);

        JButton btnAdd = new JButton("Add server");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Add server");

                if(input != null && !input.isEmpty()) {
                    Server server = new Server();
                    server.setIP(input.trim());

                    client.connect(server);

                    lblServer.setText("SERVER: " + server.getIP());
                }
            }
        });

        panel.add(labelPanel, BorderLayout.NORTH);
        panel.add(canvas, BorderLayout.CENTER);
        panel.add(btnAdd, BorderLayout.SOUTH);

        setContentPane(panel);
    }

    public static void open() {
        new ClientFrame().setVisible(true);
    }
}
