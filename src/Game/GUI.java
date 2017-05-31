package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class GUI extends JPanel {

    private TheGame game;

    public GUI(TheGame game) {
        this.game = game;
        JFrame frame = new JFrame("Four in a TheGame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension( 1200, 800));
        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        frame.getContentPane().add(game, BorderLayout.CENTER);
        frame.getContentPane().add(ChatWindow(), BorderLayout.WEST);
        frame.pack();
        frame.setVisible(true);
    }

    private Component ChatWindow() {
        JPanel chatWindow = new JPanel();
        chatWindow.setPreferredSize(new Dimension(200, getHeight()));

        chatWindow.setBackground(game.getMe().getMyColor());
        SpringLayout layout = new SpringLayout();
        chatWindow.setLayout(layout);

        JLabel playerInfo = new JLabel("You are player "+game.getMe().getPlayer());
        playerInfo.setFont(new Font("Serif", Font.BOLD, 20));
        playerInfo.setForeground(Color.BLUE);
        chatWindow.add(playerInfo);

        JLabel exit = new JLabel("Exit");
        exit.setFont(new Font("Serif", Font.BOLD, 25));
        exit.setForeground(Color.BLUE);
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if(game.getMe().getMyColor()==Color.RED)
                exit.setForeground(Color.YELLOW);
                else exit.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                exit.setForeground(Color.BLUE);
            }

            @Override
            public void mousePressed(MouseEvent e){
                System.exit(0);
            }
        });
        chatWindow.add(exit);

        layout.putConstraint(SpringLayout.NORTH, playerInfo, 50, SpringLayout.NORTH, chatWindow);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, playerInfo, 0, SpringLayout.HORIZONTAL_CENTER, chatWindow);
        layout.putConstraint(SpringLayout.SOUTH, exit, -30, SpringLayout.SOUTH, chatWindow);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, exit, 0, SpringLayout.HORIZONTAL_CENTER, chatWindow);

        return chatWindow;
    }
}
