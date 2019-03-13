package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PaintFrame extends JFrame {

    private PaintFrame() {
        setTitle("Paint");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int frameWidth = (int) (screenSize.width * 0.25);
        int frameHeight = (int) (screenSize.width * 0.25);

        setSize(frameWidth, frameHeight);
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel lblChoice = new JLabel("Choose mode");
        lblChoice.setHorizontalAlignment(SwingConstants.CENTER);

        int buttonWidth = frameWidth;
        int buttonHeight = (int) (frameHeight * 0.25);

        JButton btnServer = new JButton("Server");
        btnServer.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerFrame.open();
            }
        });

        JButton btnCLient = new JButton("Client");
        btnCLient.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnCLient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFrame.open();
            }
        });

        panel.add(lblChoice, BorderLayout.NORTH);
        panel.add(btnServer, BorderLayout.CENTER);
        panel.add(btnCLient, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
    }

    private static void open() {
        new PaintFrame().setVisible(true);
    }

    public static void main(String[] args) {
        open();
    }
}