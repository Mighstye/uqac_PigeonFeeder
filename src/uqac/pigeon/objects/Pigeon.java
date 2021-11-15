package uqac.pigeon.objects;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Pigeon {
    private JLabel label;
    private int x;
    private int y;
    private int speed;

    //Timer effraye
    private double timer;
    private boolean effraye;
    private int fuiteX;
    private int fuiteY;

    //Food en vu
    private int closestFoodX = -1;
    private int closestFoodY = -1;
    private int closestFoodTick = Integer.MAX_VALUE;
    private double distance = Double.MAX_VALUE;

    public Pigeon() {
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("pigeon.gif").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
        label = new JLabel(imageIcon);
        resetTimer();
        setSpeed(25);
    }

    public void resetTimer() {
        timer = System.currentTimeMillis() + ThreadLocalRandom.current().nextInt(10000,20001);
    }

    public JLabel getLabel() {
        return this.label;
    }

    public void setCoord(int x, int y) {
        this.x=x;
        this.y=y;
    }

    public void effraye() {
        effraye = true;
        fuiteX = (int)(Math.random()*500);
        fuiteY = (int)(Math.random()*500);
    }

    public void unEffraye() {
        effraye = false;
        resetTimer();
    }

    public int getFuiteX() {
        return fuiteX;
    }

    public void setSpeed(int s) {this.speed = s;}

    public void setFuiteX(int fuiteX) {
        this.fuiteX = fuiteX;
    }

    public int getFuiteY() {
        return fuiteY;
    }

    public void setFuiteY(int fuiteY) {
        this.fuiteY = fuiteY;
    }

    public int getSpeed() {return speed;}

    public boolean isEffraye() {return effraye;}

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

    public int getClosestFoodTick() {return closestFoodTick;}

    public void setClosestFoodTick(int t) {this.closestFoodTick = t;}

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

    public double timer() {return timer;}
}
