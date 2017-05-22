import Game.Coin;
import Game.TheGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class GUI extends JPanel {

    TheGame game = new TheGame(6);

    public GUI() {
        JFrame frame = new JFrame("Four in a TheGame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Dimension size = getToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension((int) size.getWidth(), (int) size.getHeight()));
        frame.setExtendedState(frame.getExtendedState() | frame.MAXIMIZED_BOTH);
        frame.getContentPane().add(game, BorderLayout.CENTER);
        frame.pack();
        frame.getContentPane().add(ChatWindow(frame), BorderLayout.WEST);
        frame.getContentPane().add(SettingsWindow(frame), BorderLayout.EAST);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.black);
        g2d.setColor(Color.RED);

        for (int X = 0; X < 10; X++) {
            for (int Y = 0; Y < 10; Y++) {
                int width = 40;
                int height = 40;
                int x = width + (50 * X);
                int y = height + (50 * Y);
                g2d.fillRect(x, y, 40, 40);
            }

        }


    }

    private Component ChatWindow(JFrame frame) {
        JPanel chatWindow = new JPanel();
        chatWindow.setPreferredSize(new Dimension(150, getHeight()));
        chatWindow.setBackground(Color.RED);
        SpringLayout layout = new SpringLayout();
        chatWindow.setLayout(layout);

        JButton help = new JButton("New Game");
        help.addActionListener((ActionEvent e) -> {
                    for (Coin c : game.getCoins()) {
                        if (c.getColor() != Color.WHITE) {
                            c.setColor(Color.WHITE);
                        }
                    }
                }
        );
        chatWindow.add(help);

        layout.putConstraint(SpringLayout.NORTH, help, 10, SpringLayout.NORTH, chatWindow);
        layout.putConstraint(SpringLayout.WEST, help, 10, SpringLayout.WEST, chatWindow);


        return chatWindow;
    }

    private Component SettingsWindow(JFrame frame) {
        JPanel settingsWindow = new JPanel();
        settingsWindow.setPreferredSize(new Dimension(150, getHeight()));
        settingsWindow.setBackground(Color.blue);
        SpringLayout layout = new SpringLayout();
        settingsWindow.setLayout(layout);

        JButton help = new JButton("Info");
        help.addActionListener((ActionEvent e) -> {
            frame.dispose();
        });
        settingsWindow.add(help);

        layout.putConstraint(SpringLayout.NORTH, help, 10, SpringLayout.NORTH, settingsWindow);
        layout.putConstraint(SpringLayout.WEST, help, 10, SpringLayout.WEST, settingsWindow);


        return settingsWindow;
    }

}
