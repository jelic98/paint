package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

class ClientFrame extends JFrame {

    private final JLabel lblServer;
    private final Client client;

    private ClientFrame() {
        client = new Client();

        setTitle("Client");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.5);
        int frameHeight = (int) (screenSize.width * 0.5);

        setSize(frameWidth, frameHeight);
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BorderLayout());

        JLabel lblIP = new JLabel("IP: " + client.getIP());
        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        lblServer = new JLabel("SERVER: undefined");
        lblServer.setHorizontalAlignment(SwingConstants.CENTER);

        labelPanel.add(lblIP, BorderLayout.NORTH);
        labelPanel.add(lblServer, BorderLayout.SOUTH);

        JButton btnAdd = new JButton("Add server");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                String input = JOptionPane.showInputDialog(null, "Add server", client.getIP());

                if(input != null && !input.isEmpty()) {
                    Server server = new Server();

                    server.setIP(input.trim());
                    try {
                        client.connect(server);
                    }catch(IOException e) {
                        JOptionPane.showMessageDialog(null, "Invalid address", "Error", JOptionPane.ERROR_MESSAGE);

                        e.printStackTrace();

                        return;
                    }

                    lblServer.setText("SERVER: " + server.getIP());
                }
            }
        });

        JComponent canvas = new DrawingCanvas(client, frameWidth, frameHeight);

        panel.add(labelPanel, BorderLayout.NORTH);
        panel.add(canvas, BorderLayout.CENTER);
        panel.add(btnAdd, BorderLayout.SOUTH);

        setContentPane(panel);
    }

    public static void open() {
        new ClientFrame().setVisible(true);
    }
}