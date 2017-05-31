package Game;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class Coin {

    private double radius;
    private Point2D midPoint;
    private int column;
    private Color color;

    public Coin(double radius, Point2D midPoint, Color color, int column) {
        this.radius = radius;
        this.midPoint = midPoint;
        this.color = color;
        this.column = column;
    }

    double getRadius() {
        return radius;
    }

    public Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }

    Point2D getMidPoint() {
        return midPoint;
    }

    Point2D getTempMidPoint() {
        return new Point2D.Double(midPoint.getX() + getRadius() / 2, midPoint.getY() + getRadius() / 2);
    }

    int getColumn() {
        return column;
    }

    void draw(Graphics2D g2d) {
        g2d.setColor(getColor());

        int x = (int) getMidPoint().getX();
        int y = (int) getMidPoint().getY();
        g2d.fillOval(x, y, (int) getRadius(), (int) getRadius());
    }
}