package uqac.pigeon.thread;

import uqac.pigeon.objects.Food;
import uqac.pigeon.objects.Pigeon;

import java.util.ArrayList;

public class PigeonThread extends Thread {
    Pigeon pigeon;
    FoodThread foodThread;
    ArrayList<Food> copy;

    public PigeonThread(Pigeon p, FoodThread foodThread) {
        this.foodThread = foodThread;
        this.pigeon = p;
        copy = new ArrayList<>(foodThread.getFoodList());
    }

    public Pigeon getPigeon() {
        return pigeon;
    }

    public synchronized void createCopy() {
        copy = new ArrayList<>(foodThread.getFoodList());
    }

    public void run() {
        while (true) {
            createCopy();
            boolean needToMove = false;
            for (Food f:copy
                 ) {
                if(f != null) {
                    if (pigeon.getX() == f.getX() && pigeon.getY() == f.getY()) {
                        foodThread.eatFood(f);
                        pigeon.takeWeight();
                        pigeon.setDistance(Double.MAX_VALUE);
                    }
                }
            }
            for (Food f: copy
            ) {
                if(f != null) {
                    double distance = Math.sqrt(Math.pow(f.getX() - pigeon.getX(), 2) + Math.pow(f.getY() - pigeon.getY(), 2));
                    if (pigeon.getDistance() > distance) {
                        pigeon.setTarget(f.getX(), f.getY());
                        needToMove = true;
                    }
                }
            }
            if(needToMove)
                move(pigeon.getClosestFoodX(), pigeon.getClosestFoodY());
        }
    }

    public void move(int x, int y) {
        pigeon.getLabel().setLocation(x,y);
        pigeon.setX(pigeon.getLabel().getX());
        pigeon.setY(pigeon.getLabel().getY());
    }
}