package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {

    private JTextArea taClients;

    //konfigurisanje forme
    public ServerFrame() {
        //kreiranje novog Server objekta
        Server server = new Server();

        //pokretanje servera
        server.start(this);

        //odredjivanje naziva forme
        setTitle("Server");

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
        JLabel lblIP = new JLabel("IP: " + server.getInetAddress());

        //pozicioniranje labele
        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        //deklaracija varijable za sirinu i visinu povrsine za unos teksta
        int textAreaWidth = frameWidth;
        int textAreaHeight = (int) (frameHeight * 0.5);

        //kreiranje povrsine za unos teksta
        taClients = new JTextArea();

        //odredjivanje velicine povrsine za unos teksta
        taClients.setPreferredSize(new Dimension(textAreaWidth, textAreaHeight));

        //zabranje menjanja sadrzaja povrsine za unos teksta od strane korisnika
        taClients.setEditable(false);

        //ubacivanje komponenti u panel i njihovo smestanje u odredjene pozicije
        panel.add(lblIP, BorderLayout.NORTH);
        panel.add(taClients, BorderLayout.SOUTH);

        //panel postaje glavni
        setContentPane(panel);

        //menjanje velicine forme
        pack();
    }

    //otvaranje forme
    public static void open() {
        //pozivanje konstruktora i dobijanje vidljivosti forme
        new ServerFrame().setVisible(true);
    }

    //dodavanje klijenta
    public void addIP(String ip) {
        //ubacivanje IP adrese klijenta u povrsinu za unos teksta i premestanje kursora u novi red
        taClients.append(ip + "\n");
    }
}
