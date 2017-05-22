package Game;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class TheGame extends JPanel implements ActionListener {
    private int columns;
    private int rows;
    private ArrayList<Coin> coins = new ArrayList<>();
    private double diameter;
    Coin[][] board;


    public TheGame(int columns) {
        this.columns = columns+1;
        this.rows = columns;
        board = new Coin[this.columns][this.rows];
        diameter = 1;

        addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e) {
                Point2D worldPosition = e.getPoint();

                    for(Coin c:coins){
                            if(c.getTempMidPoint().distance(worldPosition)<(diameter/2)){
                                for(int i = rows ; i>0; i--){
                                    if(board[c.getColomn()][i-1].getColor()==Color.WHITE){
                                        if ( SwingUtilities.isLeftMouseButton(e) ){
                                            board[c.getColomn()][i-1].setColor(Color.RED);
                                            CheckRed();
                                        } else if ( SwingUtilities.isRightMouseButton(e) ){
                                            board[c.getColomn()][i-1].setColor(Color.YELLOW);
                                            CheckYellow();
                                        }
                                        repaint();
                                        break;
                                    }
                                }
                            }
                    }

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        new Timer(1000, this).start();
    }



    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.BLACK);
        g2d.setColor(Color.BLUE);

        int width = ((getHeight()-50)/ columns -1)* columns;
        int height = (width/ columns)*(rows);
        int startX = (getWidth()/2)-(width/2);
        int startY = (getHeight()/2)-(height/2);

        if(diameter!=(width)/(columns +1)){
            diameter = (width)/(columns +1);
            int space = (int) (diameter/ columns+1);
            int count = (int)((diameter)+ space);
            int xClip =  startX  + (space/2);
            int yClip =  startY  + (space/2);

            for (int w = 0; w < columns; w++) {
                for (int h = 0; h < rows; h++) {
                    board[w][h] = new Coin(diameter,new Point2D.Double(xClip,yClip),Color.WHITE,w);
                    coins.add(board[w][h]);
                    yClip+=count;
                }
                yClip=startY  + (space/2);
                xClip+=count;
            }
        }

        g2d.fillRect(startX,startY,width,height);

        for(Coin[] coinsInARow : board){
            for(Coin coinFromTheRow:coinsInARow){
                if(coinFromTheRow!=null) {
                    coinFromTheRow.draw(g2d);
                }
            }
        }
    }

    public ArrayList<Coin> getCoins() {
        return coins;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public boolean CheckXHorizontal(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board horizontally
            for(int w = 0; columns > w; w += 1){
                for(int h = 0; rows > h; h += 1){
                    if(board[w][h].getColor() == Color.RED){ //if it finds an X, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckXVertical(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board vertically
            for(int h = 0; rows > h; h += 1){
                for(int w = 0; columns > w; w += 1){
                    if(board[w][h].getColor() == Color.RED){ //if it finds an X, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an X, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckOVertical(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board vertically
            for(int h = 0; rows > h; h += 1){
                for(int w = 0; columns > w; w += 1){
                    if(board[w][h].getColor() == Color.YELLOW){ //if it finds an O, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an O, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckOHorizontal(){
        //creates boolean to act as flag
        boolean flag = true;

        //creates counter
        int counter = 0;
        while(flag){

            //goes through board vertically
            for(int w = 0; columns > w; w += 1){
                for(int h = 0; rows > h; h += 1){
                    if(board[w][h].getColor() == Color.YELLOW){ //if it finds an O, add 1 to counter
                        counter += 1;
                    }else{
                        counter = 0; // if next piece is not an O, set counter to 0
                    }
                    if(counter >= 4){
                        System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                        flag = false;
                    }
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckXDiagonalForward(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an X is found
            for(int w = 0; columns > w; w += 1){
                for(int h = 0; rows > h; h += 1){
                    if(board[w][h].getColor() == Color.RED){ //if X is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Xs
                            if(checkColumn + w <= columns - 1&& checkRow + h <= rows - 1){
                                if(board[w+checkColumn][h+checkRow].getColor() == Color.RED){ //if X is found, add 1 to counter
                                    counter += 1;
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == rows -1 || checkRow == columns -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckODiagonalForward(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an O is found
            for(int w = 0; columns > w; w += 1){
                for(int h = 0; rows > h; h += 1){
                    if(board[w][h].getColor() == Color.YELLOW){ //if O is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Os
                            if(checkColumn + w <= columns - 1&& checkRow + h <= rows - 1){
                                if(board[w + checkColumn][h + checkRow].getColor() == Color.YELLOW){ //if O is found, add 1 to counter
                                    counter += 1;
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == rows -1 || checkRow == columns -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckXDiagonalBack(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){ //goes through until an X is found
            for(int w = 0; columns > w; w += 1){
                for(int h = 0; rows > h; h += 1){
                    if(board[w][h].getColor() == Color.RED){ //if X is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Xs
                            if(w - checkColumn >= 0 && h - checkRow >= 0){
                                if(board[w - checkColumn][h - checkRow].getColor() == Color.RED){
                                    counter += 1; //if X is found, add 1 to counter
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == columns -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 1 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckODiagonalBack(){
        //flag
        boolean flag = true;

        //counter
        int counter = 0;

        //check boolean
        Boolean check = false;

        //checkers
        int checkColumn = 1;
        int checkRow = 1;

        while(flag){

            //goes through until an O is found
            for(int w = 0; columns > w; w += 1){
                for(int h = 0; rows > h; h += 1){
                    if(board[w][h].getColor() == Color.YELLOW){ //if O is found, add one to counter and go into loop
                        counter += 1;
                        check = true;
                        while(check){ //goes through diagonally looking for Os
                            if(w - checkColumn >= 0 && h - checkRow >= 0){
                                if(board[w - checkColumn][h - checkRow].getColor() == Color.YELLOW){
                                    counter += 1; //if O is found, add 1 to counter
                                }
                            }

                            //adds 1 to checkers
                            checkColumn += 1;
                            checkRow += 1;

                            if(checkColumn == 0 || checkRow == columns -1){ //if outside of board, break
                                check = false;
                                break;
                            }

                            if(counter >= 4){
                                System.out.println("Player 2 wins"); //if counter is greater or equal to 4, player wins
                                check = false;
                                flag = false;
                                break;
                            }
                        }
                    }
                    if(counter >= 4){
                        flag = false;
                        break;
                    }

                    //resets counter and checkers
                    counter = 0;
                    checkColumn = 1;
                    checkRow = 1;
                }
            }
            break;
        }
        return flag;
    }

    public boolean CheckRed(){
        //creates flag
        boolean flag = true;

        //checks all Xs at once, for clearner main loop
        if(!CheckXVertical() || !CheckXHorizontal()|| !CheckXDiagonalBack()|| !CheckXDiagonalForward()){
            flag = false;
        }
        return flag;
    }

    public boolean CheckYellow(){
        //creates flag
        boolean flag = true;

        //checks all Os at once, for clearner main loop
        if(!CheckOVertical() || !CheckOHorizontal() || !CheckODiagonalBack() || !CheckODiagonalForward()){
            flag = false;
        }
        return flag;
    }




}
