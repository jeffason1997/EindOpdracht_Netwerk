package Game;


import Multiplayer.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class TheGame extends JPanel implements Runnable, ActionListener {
    private int columns;
    private int rows;
    private double diameter;
    private ArrayList<Coin> coins = new ArrayList<>();
    private Coin[][] board;
    private FallAnimation now = null;
    private Area areaBoard;
    private Multiplayer.Client me;
    private Thread win;
    private Color lastColor = Color.WHITE;

    public TheGame(int columns) {
        this.columns = columns + 1;
        rows = columns;
        board = new Coin[this.columns][this.rows];
        diameter = 1;
        areaBoard = new Area(new Rectangle2D.Double(1, 1, 1, 1));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                Point2D worldPosition = e.getPoint();
                if (me.isMyTurn() && me.getMyColor() != lastColor) {
                    for (Coin c : coins) {
                        if (c.getTempMidPoint().distance(worldPosition) < (diameter / 2)) {
                            for (int i = rows; i > 0; i--) {
                                if (board[c.getColumn()][i - 1].getColor() == Color.WHITE && me.getMyColor() != lastColor && SwingUtilities.isLeftMouseButton(e)) {
                                    now = new Game.FallAnimation(board[c.getColumn()][i - 1], me.getMyColor());
                                    me.myTurn(board[c.getColumn()][i - 1].getColumn());
                                    me.setMyTurn(false);
                                    repaint();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });

        new Timer(10, this).start();
        win = new Thread(new WinChecker(this, this.columns, rows));
    }

    public void setClient(Multiplayer.Client me) {
        this.me = me;
    }

    public Coin[][] getBoard() {
        return board;
    }

    private void makeBoard(int width) {

        int height = (width / columns) * (rows);
        int startX = (getWidth() / 2) - (width / 2);
        int startY = (getHeight() / 2) - (height / 2);
        int space = (int) (diameter / columns + 1);
        int count = (int) ((diameter) + space);
        int xClip = startX + (space / 2);
        int yClip = startY + (space / 2);
        areaBoard = new Area(new Rectangle2D.Double(startX, startY, width, height));

        for (int w = 0; w < columns; w++) {
            for (int h = 0; h < rows; h++) {
                board[w][h] = new Coin(diameter, new Point2D.Double(xClip, yClip), Color.WHITE, w);
                coins.add(board[w][h]);
                Area circle = new Area(new Ellipse2D.Double(xClip, yClip, diameter, diameter));
                areaBoard.subtract(circle);
                yClip += count;
            }
            yClip = startY + (space / 2);
            xClip += count;
        }
        if (!win.isAlive()) {
            win.start();
        }
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    public int getRows() {
        return rows;
    }

    public void setNow(FallAnimation fallNow) {
        now = fallNow;
    }

    private void setLastColor() {
        this.lastColor = now.getColor();
    }

    public FallAnimation getNow() {
        return now;
    }

    Client getMe() {
        return me;
    }

    public void newGame() {
        for (Coin[] coinRow : board) {
            for (Coin coin : coinRow) {
                if (coin != null) {
                    coin.setColor(Color.WHITE);
                    try {
                        me.newGame();
                    } catch (IOException ex) {
                        System.err.println(ex);
                    }
                }
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(new Color(103, 250, 166));
        g2d.setColor(Color.BLUE);

        int width = ((getHeight()) / columns - 1) * columns;
        if (diameter != (width) / (columns + 1)) {
            diameter = (width) / (columns + 1);
            makeBoard(width);
        }

        for (Coin[] coinRow : board) {
            for (Coin coin : coinRow) {
                if (coin != null) {
                    coin.draw(g2d);
                }
            }
        }
        if (now != null) {
            if (now.isFallActive()) {
                now.draw(g2d);
            } else {
                me.setMyTurn(true);
                now = null;
            }
        }
        g2d.setColor(Color.BLUE);
        g2d.fill(areaBoard);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        if (now != null) {
            now.upgrade();
            setLastColor();
        }
    }

    @Override
    public void run() {

    }
}
