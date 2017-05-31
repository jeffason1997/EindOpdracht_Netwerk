package Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class WinChecker implements Runnable{

    Coin[][] board;
    private int columns;
    private int rows;
    TheGame game;

    public WinChecker(TheGame game, int columns, int rows) {
        this.game = game;
        this.columns = columns;
        this.rows = rows;
    }


    public boolean CheckVertical(Color color) {
        boolean flag = true;
        int counter = 0;
        while (flag) {
            for (int w = 0; w < columns; w++) {
                for (int h = rows - 1; h > 0; h--) {
                    if (board[w][h].getColor() == color) {
                        counter += 1;
                    } else {
                        counter = 0;
                    }

                    if (counter >= 4) {
                        System.out.println("Player " + color.toString() + " wins Verti"); //if counter is greater or equal to 4, player wins
                        flag = false;
                        break;
                    }
                }
                counter = 0;
            }
            break;
        }
        return flag;
    }

    public boolean CheckHorizontal(Color color) {

        boolean flag = true;
        int counter = 0;
        while (flag) {


            for (int h = rows - 1; h > 0; h--) {
                for (int w = 0; w < columns; w++) {
                    if (board[w][h].getColor() == color) {
                        counter += 1;
                    } else {
                        counter = 0;
                    }
                    if (counter >= 4) {
                        System.out.println("Player " + color.toString() + " wins Hori"); //if counter is greater or equal to 4, player wins
                        flag = false;
                        break;
                    }
                }
                counter = 0;
            }
            break;
        }
        return flag;
    }

    public boolean CheckDiagonalForward(Color color) {

        boolean flag = true;
        int counter = 0;
        Boolean check = false;
        int checkColumn = 1;
        int checkRow = 1;

        while (flag) {
            for (int w = 0; w < columns; w++) {
                for (int h = rows - 1; h > 0; h--) {
                    if (board[w][h].getColor() == color) {
                        counter += 1;
                        check = true;
                        while (check) {
                            if (w + checkColumn < columns && h - checkRow >= 0) {
                                if (board[w + checkColumn][h - checkRow].getColor() == color) { //if X is found, add 1 to counter
                                    counter += 1;
                                } else {
                                    break;
                                }
                            }


                            checkColumn += 1;
                            checkRow += 1;

                            if (checkColumn == -1 || h - checkRow == -1) {
                                check = false;
                                break;
                            }

                            if (counter >= 4) {
                                System.out.println("Player " + color.toString() + " wins DiaFor");
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (counter >= 4) {
                        flag = false;
                        break;
                    }


                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckDiagonalBack(Color color) {

        boolean flag = true;
        int counter = 0;
        Boolean check;
        int checkColumn = 1;
        int checkRow = 1;

        while (flag) {

            for (int w = 0; w < columns; w++) {
                for (int h = rows - 1; h > 0; h--) {
                    if (board[w][h].getColor() == color) {
                        counter += 1;
                        check = true;
                        while (check) {
                            if ((w - checkColumn >= 0 && h - checkRow >= 0)) {
                                if (board[w - checkColumn][h - checkRow].getColor() == color) {
                                    counter += 1;
                                } else {
                                    counter = 0;
                                    break;
                                }
                            }
                            checkColumn += 1;
                            checkRow += 1;

                            if (checkColumn == columns || checkRow == rows - 1) {
                                check = false;
                            }
                            if (counter >= 4) {
                                System.out.println("Player " + color.toString() + " wins DiaBack");
                                check = false;
                                flag = false;
                            }
                        }
                    }
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean checkDraw(){
        boolean flag = true;
        for(Coin[] c : board){
            for (Coin coin:c){
                if(coin.getColor()==Color.WHITE){
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
            }
        }

        return flag;
    }

    @Override
    public void run() {
        while(true){
            board = game.getBoard();

            if (!CheckVertical(Color.RED) || !CheckHorizontal(Color.RED) || !CheckDiagonalBack(Color.RED) || !CheckDiagonalForward(Color.RED)) {
                JOptionPane.showMessageDialog(null, "The Winner is Red");
//                game.newGame();
                System.exit(0);
            }

            if (!CheckVertical(Color.YELLOW) || !CheckHorizontal(Color.YELLOW) || !CheckDiagonalBack(Color.YELLOW) || !CheckDiagonalForward(Color.YELLOW)) {
                JOptionPane.showMessageDialog(null, "The Winner is Yellow");
//                game.newGame();
                System.exit(0);
            }

            if (!checkDraw()) {
                JOptionPane.showMessageDialog(null, "Its a draw, you noobs");
//                game.newGame();
                System.exit(0);
            }

        }
    }
}
