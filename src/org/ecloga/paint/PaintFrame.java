package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintFrame extends JFrame {

    //konfigurasanje forme
    public PaintFrame() {
        //odredjivanje naziva forme
        setTitle("Paint");

        //zatvaranje forme klikom na 'X'
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //zabrana promene velicine forme od strane korisnika
        setResizable(false);

        //uzimanje velicine ekrana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //deklaracija varijabli za sirinu i visinu dugmica
        int frameWidth = (int) (screenSize.width * 0.25);
        int frameHeight = (int) (screenSize.width * 0.25);

        //odredjivanje sirine i visine forme
        setSize(frameWidth, frameHeight);

        //odredjivanje lokacije forme na ekranu
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        //kreiranje panela
        JPanel panel = new JPanel();

        //odredjivanje tipa layouta panela
        panel.setLayout(new BorderLayout());

        //kreiranje labele i odredjivanje njenog teksta
        JLabel lblChoice = new JLabel("Choose mode");

        //pozicioniranje labele
        lblChoice.setHorizontalAlignment(SwingConstants.CENTER);

        //deklaracija varijabli za sirinu i visinu dugmica
        int buttonWidth = frameWidth;
        int buttonHeight = (int) (frameHeight * 0.25);

        //kreiranje dugmeta za prvi izbor
        JButton btnServer = new JButton("Server");

        //odredjivanje sirine i visine dugmica
        btnServer.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        //rukovodjenje dogadjajem klika
        btnServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //otvaranje forme servera i zatvaranje trenutne forme
                ServerFrame.open();
                close();
            }
        });

        //kreiranje dugmeta za drugi izbor
        JButton btnCLient = new JButton("Client");

        //odredjivanje sirine i visine dugmica
        btnCLient.setPreferredSize(new Dimension(buttonWidth, buttonHeight));

        //rukovodjenje dogadjajem klika
        btnCLient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //otvaranje forme klijenta
                ClientFrame.open();

                //zatvaranje forme
                close();
            }
        });

        //ubacivanje komponenti u panel i njihovo smestanje u odredjene pozicije
        panel.add(lblChoice, BorderLayout.NORTH);
        panel.add(btnServer, BorderLayout.CENTER);
        panel.add(btnCLient, BorderLayout.SOUTH);

        //panel postaje glavni
        setContentPane(panel);

        //menjanje velicine forme
        pack();
    }

    //zatvaranje forme
    private void close() {
        //gubljenje vidljivosti
        setVisible(false);

        //izbacivanje forme
        dispose();
    }

    //otvaranje forme
    private static void open() {
        //pozivanje konstruktora i dobijanje vidljivosti forme
        new PaintFrame().setVisible(true);
    }

    //pokretanje programa
    public static void main(String[] args) {
        //ptvaranje forme
        open();
    }
}
