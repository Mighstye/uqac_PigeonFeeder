package uqac.pigeon.objects;

import javax.swing.*;
import java.awt.*;

public class Pigeon {
    private JLabel label;
    private int x;
    private int y;
    private int weight;

    //Food en vu
    private int closestFoodX = -1;
    private int closestFoodY = -1;
    private double distance = Double.MAX_VALUE;

    public Pigeon() {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("pigeon.gif").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        label = new JLabel(imageIcon);
        weight = 0;
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setCoord(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getClosestFoodX() {
        return closestFoodX;
    }

    public int getClosestFoodY() {
        return closestFoodY;
    }

    public void setTarget(int x, int y) {
        this.closestFoodX = x;
        this.closestFoodY = y;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void takeWeight() {
        this.weight++;
        check();
    }

    public void check() {}
}
