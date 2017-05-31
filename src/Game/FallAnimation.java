package Game;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Created by Jeffrey Lantinga, 2101060 on 27-3-2017.
 */
public class FallAnimation {

    private double radius;
    private Color color;
    private Coin coin;
    private Point2D midPoint;
    private Point2D ownMidPoint;
    private boolean fallActive;
    private int updatable = 0;

    public FallAnimation(Coin coin, Color color) {
        this.coin = coin;
        radius = coin.getRadius();
        this.color = color;
        midPoint = coin.getMidPoint();
        ownMidPoint = new Point2D.Double(this.midPoint.getX(), 0);
        fallActive = true;
    }

    boolean isFallActive() {
        return fallActive;
    }

    private Point2D getOwnMidPoint() {
        return ownMidPoint;
    }

    private Point2D getMidPoint() {
        return midPoint;
    }

    private double getRadius() {
        return radius;
    }

    Color getColor(){return color;}

    void draw(Graphics2D g2d) {

        g2d.setColor(color);
        int x = (int) getOwnMidPoint().getX();
        int y = (int) getOwnMidPoint().getY() + updatable;


        if (fallActive) {
            g2d.fillOval(x, y, (int) getRadius(), (int) getRadius());
            getOwnMidPoint().setLocation(x, y);
        }
        if (getOwnMidPoint().getY() >= getMidPoint().getY()) {
            fallActive = false;
            coin.setColor(color);
        }
    }

    void upgrade() {
        updatable += 1;
    }
}
