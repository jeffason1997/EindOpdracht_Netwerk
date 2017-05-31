package Multiplayer;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */

import Game.Coin;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements Runnable, GameConstants {

    private boolean myTurn = false;
    private Color myColor = Color.WHITE;
    private Color otherColor = Color.BLACK;
    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private boolean waiting = true;
    Game.TheGame game;
    Game.GUI gui;
    int player;

    public Client(String ip) {
        connectToServer(ip);
        game = new Game.TheGame(7);
        game.setClient(this);
    }

    private void connectToServer(String ip) {
        try {
            Socket socket = new Socket(ip, 8080);

            toServer = new DataOutputStream(socket.getOutputStream());
            fromServer = new DataInputStream(socket.getInputStream());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    public void run() {

        try {
            player = fromServer.readInt();

            if (player == PLAYER1) {
                myColor = Color.RED;
                otherColor = Color.YELLOW;
                myTurn = true;
            } else if (player == PLAYER2) {
                myColor = Color.YELLOW;
                otherColor = Color.RED;
            } else {
                System.out.println("This shouldn't happen");
            }

            gui = new Game.GUI(game);

            while (true) {
                if (player == PLAYER1) {
                    waitForPlayerAction();
                    receiveInfoFromServer();
                } else if (player == PLAYER2) {
                    receiveInfoFromServer();
                    waitForPlayerAction();
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Wait for the player to mark a cell
     */
    private void waitForPlayerAction() throws InterruptedException {
        while (waiting) {
            Thread.sleep(100);
        }
        waiting = true;
    }


    private void sendMove(int i) throws IOException {
        if(myTurn) {
            toServer.writeInt(i);
        }
    }


    private void receiveInfoFromServer() throws IOException {
        int column = fromServer.readInt();
        myTurn = true;
        Game.Coin[][] board = game.getBoard();

        for (int i = game.getRows(); i > 0; i--) {
            Coin tempCoin = board[column][i - 1];
            if (tempCoin.getColor() == Color.WHITE) {
                if (game.getNow() == null)
                    game.setNow(new Game.FallAnimation(tempCoin, otherColor));
            }
        }

    }


    public void myTurn(int column) {
        waiting = false;
        try {
            sendMove(column);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public Color getMyColor() {
        return myColor;
    }

    public void newGame() throws IOException {
        if (player == 1) {
            setMyTurn(true);
            toServer.writeInt(-1);
        } else if (player == 2) {
            setMyTurn(false);
            toServer.writeInt(-1);
        } else {
            System.out.println("this should not happen");
        }
    }

    public int getPlayer() {
        return player;
    }
}

