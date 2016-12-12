package org.ecloga.paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class ClientFrame extends JFrame {

    private JLabel lblServer;
    private Client client;

    //konfigurisanje forme
    public ClientFrame() {
        //kreiranje novog Client objekta
        client = new Client();

        //odredjivanje naziva forme
        setTitle("Client");

        //zatvaranje forme klikom na 'X'
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //zabrana promene velicine forme od strane korisnika
        setResizable(false);

        //uzimanje velicine ekrana
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        //deklaracija varijabli za sirinu i visinu forme
        int frameWidth = (int) (screenSize.width * 0.5);
        int frameHeight = (int) (screenSize.width * 0.5);

        //odredjivanje sirine i visine forme
        setSize(frameWidth, frameHeight);

        //odredjivanje lokacije forme na ekranu
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        //kreiranje panela
        JPanel panel = new JPanel();

        //odredjivanje tipa layouta panela
        panel.setLayout(new BorderLayout());

        //kreiranje panela za labelu
        JPanel labelPanel = new JPanel();

        //odredjivanje tipa layouta panela za labele
        labelPanel.setLayout(new BorderLayout());

        //kreiranje labele za IP adresu klijenta i odredjivanje njenog teksta
        JLabel lblIP = new JLabel("IP: " + client.getInetAddress());

        //odredjivanje poravnanja teksta u labeli za IP adresu klijenta
        lblIP.setHorizontalAlignment(SwingConstants.CENTER);

        //kreiranje labele za IP adresu servera i odredjivanje njenog teksta
        lblServer = new JLabel("SERVER: undefined");

        //odredjivanje poravnanja teksta u labeli
        lblServer.setHorizontalAlignment(SwingConstants.CENTER);

        //ubacivanje labela u panel za labele i njihovo smestanje u odredjene pozicije
        labelPanel.add(lblIP, BorderLayout.NORTH);
        labelPanel.add(lblServer, BorderLayout.SOUTH);

        //kreiranje platna za crtanje i odredjivanje njegovog klijenta, sirine i visine
        JComponent canvas = new DrawingCanvas(client, frameWidth, frameHeight);

        //kreiranje dugmeta za dodavanje servera
        JButton btnAdd = new JButton("Add server");

        //rukovodjenje dogadjajem klika
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                //kreiranje dijaloga za unos IP adrese servera i odredjivanje njene inicijalne vrednosti
                String input = JOptionPane.showInputDialog(null, "Add server", "localhost");

                //proveravanje da li je polje za unos IP adrese servera prazno
                if(input != null && !input.isEmpty()) {
                    //Kreiranje novog Server objekta
                    Server server = new Server();

                    //odredjivanje IP adrese servera
                    server.setIP(input.trim());

                    try {
                        //povezivanje klijenta sa serverom
                        client.connect(server);
                    }catch(IOException e) {
                        //prikazivanje greske o nevazecoj IP adresi servera korisniku u vidu dijaloga
                        JOptionPane.showMessageDialog(null, "Invalid address", "Error", JOptionPane.ERROR_MESSAGE);

                        //logovanje greske
                        e.printStackTrace();

                        //zavrsetak daljeg izvrsavanja metoda
                        return;
                    }

                    //odredjivanje teksta labele za IP adresu servera
                    lblServer.setText("SERVER: " + server.getIP());
                }
            }
        });

        //ubacivanje komponenti u panel i njihovo smestanje u odredjene pozicije
        panel.add(labelPanel, BorderLayout.NORTH);
        panel.add(canvas, BorderLayout.CENTER);
        panel.add(btnAdd, BorderLayout.SOUTH);

        //panel postaje glavni
        setContentPane(panel);
    }

    //otvaranje forme
    public static void open() {
        //pozivanje konstruktora i dobijanje vidljivosti forme
        new ClientFrame().setVisible(true);
    }
}
