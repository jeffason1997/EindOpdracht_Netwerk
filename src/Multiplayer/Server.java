package Multiplayer;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class Server extends JFrame implements GameConstants, Runnable {

    JTextArea jtaLog;

    public Server() {
        jtaLog = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(jtaLog);
        add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setTitle("Multiplayer");
        setVisible(true);

    }


    @Override
    public void run() {

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            jtaLog.append(new Date() + ": Multiplayer started at socket 8000\n");

            int sessionNo = 1;

            while (true) {
                jtaLog.append(new Date() + ": Wait for players to join session " + sessionNo + '\n');

                Socket player1 = serverSocket.accept();
                new DataOutputStream(player1.getOutputStream()).writeInt(PLAYER1);
                jtaLog.append(new Date() + ": Player 1 joined session " + sessionNo + '\n');
                jtaLog.append("Player 1's IP address" + player1.getInetAddress().getHostAddress() + '\n');


                Socket player2 = serverSocket.accept();
                new DataOutputStream(player2.getOutputStream()).writeInt(PLAYER2);
                jtaLog.append(new Date() + ": Player 2 joined session " + sessionNo + '\n');
                jtaLog.append("Player 2's IP address" + player2.getInetAddress().getHostAddress() + '\n');

                jtaLog.append(new Date() + ": Start a thread for session " + sessionNo++ + '\n');

                HandleASession task = new HandleASession(player1, player2);
                new Thread(task).start();
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }
}



