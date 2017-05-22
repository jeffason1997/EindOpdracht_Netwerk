package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class Coin implements MouseListener{

    private double radius;
    private Point2D midPoint;
    private int speed;

    public int getColomn() {
        return colomn;
    }

    public void setColomn(int colomn) {
        this.colomn = colomn;
    }

    private int colomn;
    private Color color;

    public Coin(double radius, Point2D midPoint, Color color,int colomn) {
        this.radius = radius;
        this.midPoint = midPoint;
        this.color = color;
        this.colomn = colomn;
    }

    public Coin() {

    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point2D getMidPoint() {
        return midPoint;
    }

    public Point2D getTempMidPoint() {
        Point2D tempMidpoint = new Point2D.Double(midPoint.getX()+getRadius()/2,midPoint.getY()+getRadius()/2);
        return tempMidpoint;
    }

    public void setMidPoint(Point2D midPoint) {
        this.midPoint = midPoint;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(getColor());

        int x = (int) getMidPoint().getX();
        int y = (int) getMidPoint().getY();
        g2d.fillOval(x,y,(int)getRadius(),(int)getRadius());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ( SwingUtilities.isLeftMouseButton(e) ){
            this.color = Color.RED;
        } else if ( SwingUtilities.isRightMouseButton(e) ){
            this.color = Color.YELLOW;
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

//    Area areaBoard = new Area(new Rectangle2D.Double(10,10,width,height));
//
//        int space = (int) (diameter/rows);
//        int count = (int)((diameter)+ space);
//        int xClip =  10+ (space/2);
//
//        for(int xx = 0; xx<rows;xx++){
//            int yClip =  10+ (space/2);
//            for(int yy = 0;yy<coloms;yy++){
//                Area circle = new Area(new Ellipse2D.Double(xClip,yClip, diameter, diameter));
//                areaBoard.subtract(circle);
//                yClip+= count;
//            }
//            xClip+= count;
//        }
//        g2d.fill(areaBoard);
}
