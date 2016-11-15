import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaintFrame extends JFrame {
    private JPanel panel;
    private JButton btnServer, btnCLient;
    private JLabel lblChoice;

    public PaintFrame() {
        setTitle("Paint");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int frameWidth = (int) (screenSize.width * 0.25);
        int frameHeight = (int) (screenSize.width * 0.25);

        setSize(frameWidth, frameHeight);
        setLocation(screenSize.width / 2 - frameWidth / 2, screenSize.height / 2 - frameHeight / 2);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        lblChoice = new JLabel("Choose mode");
        lblChoice.setHorizontalAlignment(SwingConstants.CENTER);

        int buttonWidth = frameWidth;
        int buttonHeight = (int) (frameHeight * 0.25);

        btnServer = new JButton("Server");
        btnServer.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerFrame.openFrame();
                closeFrame();
            }
        });

        btnCLient = new JButton("Client");
        btnCLient.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        btnCLient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClientFrame.openFrame();
                closeFrame();
            }
        });

        panel.add(lblChoice, BorderLayout.NORTH);
        panel.add(btnServer, BorderLayout.CENTER);
        panel.add(btnCLient, BorderLayout.SOUTH);

        setContentPane(panel);
        pack();
    }

    private void closeFrame() {
        setVisible(false);
        dispose();
    }

    public static void main(String[] args) {
        new PaintFrame().setVisible(true);
    }
}
