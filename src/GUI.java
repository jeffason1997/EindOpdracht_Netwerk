import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.InetAddress;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class GUI {

    public GUI() {
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

        String ip = "localhost";
        try{
            ip = InetAddress.getLocalHost().getHostAddress();
        }catch (Exception e){
            System.err.println(e);
        }

        JLabel hostIP = new JLabel(ip);
        hostIP.setFont(new Font("Serif", Font.PLAIN, 10));
        mainPanel.add(hostIP);

        JLabel welcome = new JLabel ("Connect four");
        welcome.setFont(new Font("Serif", Font.PLAIN, 50));
        mainPanel.add(welcome);

        JLabel madeBy = new JLabel ("Made by Jeffrey Lantinga & Ricky Hoogerdijk");
        madeBy.setFont(new Font("Serif", Font.PLAIN, 18));
        mainPanel.add(madeBy);

        JLabel host = new JLabel("Make a game");
        host.setFont(new Font("Serif", Font.PLAIN, 35));
        host.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                host.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                host.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e){
                super.mousePressed(e);

                String ipUse = "localhost";
                try{
                    ipUse = InetAddress.getLocalHost().getHostAddress();
                }catch (Exception ex){
                    System.err.println(ex);
                }

                new Thread(new Multiplayer.Server()).start();
                new Thread(new Multiplayer.Client(ipUse)).start();
                frame.dispose();
            }
        });
        mainPanel.add(host);

        JLabel join = new JLabel("Join a game");
        join.setFont(new Font("Serif", Font.PLAIN, 35));
        join.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                join.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                join.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e){
                super.mousePressed(e);
                new JoinAHost();
                frame.dispose();
            }
        });
        mainPanel.add(join);

        JLabel exit = new JLabel("Exit");
        exit.setFont(new Font("Serif", Font.PLAIN, 35));
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                exit.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                exit.setForeground(Color.BLACK);
            }

            @Override
            public void mousePressed(MouseEvent e){
                System.exit(0);
            }
        });
        mainPanel.add(exit);

        layout.putConstraint(SpringLayout.NORTH, welcome, 50, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, welcome, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, madeBy, 0, SpringLayout.SOUTH, welcome);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, madeBy, 0, SpringLayout.HORIZONTAL_CENTER, welcome);
        layout.putConstraint(SpringLayout.NORTH, host, 150, SpringLayout.SOUTH, madeBy);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, host, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, join, 15, SpringLayout.SOUTH, host);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, join, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, exit, 15, SpringLayout.SOUTH, join);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exit, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, hostIP, -20, SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, hostIP, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);


        return mainPanel;
    }
}
