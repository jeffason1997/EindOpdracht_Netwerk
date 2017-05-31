import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class JoinAHost {

    public JoinAHost() {
        JFrame frame = new JFrame("Four in a TheGame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 800));
        frame.getContentPane().add(MainPanel(frame), BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public Component MainPanel(JFrame frame) {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(103,250,166));
        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);

        JLabel ipText = new JLabel("IP of the host");
        ipText.setFont(new Font("Serif", Font.BOLD, 50));
        mainPanel.add(ipText);

        JTextField ipField = new JTextField();
        ipField.setPreferredSize(new Dimension(300,40));
        ipField.setFont(new Font("Serif", Font.BOLD, 20));
        mainPanel.add(ipField);

        JLabel findHost = new JLabel("Find game");
        findHost.setFont(new Font("Serif", Font.BOLD, 25));
        findHost.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                findHost.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                findHost.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e){
                String ip = ipField.getText();
                new Thread(new Multiplayer.Client(ip)).start();
                frame.dispose();
            }
        });
        mainPanel.add(findHost);

        JLabel Back = new JLabel("Back to menu");
        Back.setFont(new Font("Serif", Font.BOLD, 20));
        Back.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Back.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                Back.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e){
                new GUI();
                frame.dispose();
            }
        });
        mainPanel.add(Back);

        layout.putConstraint(SpringLayout.NORTH,ipText,50,SpringLayout.NORTH,mainPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,ipText,0,SpringLayout.HORIZONTAL_CENTER,mainPanel);
        layout.putConstraint(SpringLayout.NORTH,ipField,150,SpringLayout.SOUTH,ipText);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,ipField,0,SpringLayout.HORIZONTAL_CENTER,mainPanel);
        layout.putConstraint(SpringLayout.NORTH,findHost,20,SpringLayout.SOUTH,ipField);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,findHost,0,SpringLayout.HORIZONTAL_CENTER,mainPanel);
        layout.putConstraint(SpringLayout.NORTH,Back,20,SpringLayout.SOUTH,findHost);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER,Back,0,SpringLayout.HORIZONTAL_CENTER,mainPanel);



        return mainPanel;
    }
}
