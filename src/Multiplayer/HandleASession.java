package Multiplayer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class HandleASession implements Runnable, GameConstants {

    Socket playerRed;
    Socket playerYellow;

    public HandleASession(Socket player1, Socket player2) {
        playerRed = player1;
        playerYellow = player2;
    }

    @Override
    public void run() {


        try {
            DataOutputStream toPlayerRed = new DataOutputStream(playerRed.getOutputStream());
            DataInputStream fromPlayerRed = new DataInputStream(playerRed.getInputStream());
            DataOutputStream toPlayerYellow = new DataOutputStream(playerYellow.getOutputStream());
            DataInputStream fromPlayerYellow = new DataInputStream(playerYellow.getInputStream());


            while (true) {
                int column = fromPlayerRed.readInt();
                sendMove(toPlayerYellow, column);


                column = fromPlayerYellow.readInt();
                sendMove(toPlayerRed, column);


            }
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    private void sendMove(DataOutputStream out, int column)
            throws IOException {
        out.writeInt(column); // Send column index
    }



}