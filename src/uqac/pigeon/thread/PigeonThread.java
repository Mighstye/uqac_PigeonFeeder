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
            pigeon.setDistance(Double.MAX_VALUE);
            pigeon.setClosestFoodTick(Integer.MAX_VALUE);
            boolean needToMove = false;
            if(pigeon.isEffraye() && pigeon.getX() == pigeon.getFuiteX() && pigeon.getY() == pigeon.getFuiteY()) {
                pigeon.setSpeed(25);
                pigeon.unEffraye();
            }
            if(pigeon.isEffraye()) {
                needToMove = true;
                pigeon.setSpeed(10);
            }
            if(!pigeon.isEffraye() && pigeon.timer() < System.currentTimeMillis()) {
                pigeon.effraye();
            }
            for (Food f:copy
                 ) {
                if(f != null) {
                    if (!pigeon.isEffraye() && pigeon.getX() == f.getX() && pigeon.getY() == f.getY() && !f.isRotten()) {
                        foodThread.eatFood(f);
                    }
                }
            }
            for (Food f: copy
            ) {
                if(f != null) {
                    double distance = Math.sqrt(Math.pow(f.getX() - pigeon.getX(), 2) + Math.pow(f.getY() - pigeon.getY(), 2));
                    int tick = f.getTick();
                    if(!pigeon.isEffraye() && tick < pigeon.getClosestFoodTick() && !f.isRotten()) {
                        goTo(f.getX(), f.getY(), distance, tick);
                        needToMove = true;
                        if (!pigeon.isEffraye() && pigeon.getDistance() > distance && !f.isRotten()) {
                            goTo(f.getX(), f.getY(), distance, tick);
                            needToMove = true;
                        }
                    }
                }
            }
            if(needToMove && !pigeon.isEffraye())
                move(pigeon.getClosestFoodX(), pigeon.getClosestFoodY());
            if(needToMove && pigeon.isEffraye())
                move(pigeon.getFuiteX(), pigeon.getFuiteY());
            try {
                sleep(pigeon.getSpeed());
            } catch (InterruptedException e) {}
        }
    }

    public void goTo(int x, int y, double distance, int tick) {
        pigeon.setTarget(x, y);
        pigeon.setDistance(distance);
        pigeon.setClosestFoodTick(tick);
    }

    public void move(int x, int y) {
        if(pigeon.getX() > x && pigeon.getY() > y) {
            pigeon.getLabel().setLocation(pigeon.getX()-1, pigeon.getY()-1);
            updatePos();
        }
        if(pigeon.getX() > x && pigeon.getY() < y) {
            pigeon.getLabel().setLocation(pigeon.getX()-1, pigeon.getY()+1);
            updatePos();
        }
        if(pigeon.getX() < x && pigeon.getY() > y) {
            pigeon.getLabel().setLocation(pigeon.getX()+1, pigeon.getY()-1);
            updatePos();
        }
        if(pigeon.getX() < x && pigeon.getY() < y) {
            pigeon.getLabel().setLocation(pigeon.getX()+1, pigeon.getY()+1);
            updatePos();
        }
        if(pigeon.getX() == x && pigeon.getY() > y) {
            pigeon.getLabel().setLocation(pigeon.getX(), pigeon.getY()-1);
            updatePos();
        }
        if(pigeon.getX() == x && pigeon.getY() < y) {
            pigeon.getLabel().setLocation(pigeon.getX(), pigeon.getY()+1);
            updatePos();
        }
        if(pigeon.getX() > x && pigeon.getY() == y) {
            pigeon.getLabel().setLocation(pigeon.getX()-1, pigeon.getY());
            updatePos();
        }
        if(pigeon.getX() < x && pigeon.getY() == y) {
            pigeon.getLabel().setLocation(pigeon.getX()+1, pigeon.getY());
            updatePos();
        }
    }

    public void updatePos() {
        pigeon.setX(pigeon.getLabel().getX());
        pigeon.setY(pigeon.getLabel().getY());
    }
}