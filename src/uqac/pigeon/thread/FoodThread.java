package uqac.pigeon.thread;

import uqac.pigeon.gui.Gui;
import uqac.pigeon.objects.Food;

import java.util.*;

public class FoodThread extends Thread{
    private List<Food> foods = new ArrayList<Food>();
    private Gui gui;

    public FoodThread(Gui gui) {
        this.gui = gui;
    }

    public synchronized void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (Iterator<Food> it = foods.iterator(); it.hasNext();) {
                    Food food = it.next();
                    food.tick();
                    if(food.getTick() > 10 && food.getTick() <= 15)
                        food.makeRotten();
                    if(food.getTick() > 15) {
                        it.remove();
                        removeFood(food);
                    }
                }
            }
        },0,1000);
    }

    public void addFood(int x, int y) {
        Food newFood = new Food(x,y);
        foods.add(newFood);
        displayFood(newFood);
    }

    public void displayFood(Food food){
        gui.addImage(food.getLabel(),food.getX(),food.getY());
    }

    public void removeFood(Food food) {
        gui.removeImage(food.getLabel());
    }

    public List<Food> getFoodList() {
        return foods;
    }

    public synchronized void eatFood(Food food) {
        foods.remove(food);
        removeFood(food);
    }
}
