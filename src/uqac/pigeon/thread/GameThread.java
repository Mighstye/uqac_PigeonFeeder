package uqac.pigeon.thread;

import uqac.pigeon.gui.Gui;
import uqac.pigeon.listeners.ClickListeners;
import uqac.pigeon.objects.Pigeon;

import java.util.ArrayList;
import java.util.List;

public class GameThread extends Thread{
    private Gui gui;
    private List<PigeonThread> pigeonThreadList = new ArrayList<>();
    private FoodThread foodThread;
    private ClickListeners clickListeners;

    public GameThread(Gui gui, int initnumber){
        this.gui = gui;
        foodThread = new FoodThread(gui);
        addPigeon(initnumber);
    }

    public void run() {
        this.gui.setVisible();
        clickListeners = new ClickListeners(gui,this);
        foodThread.start();
    }

    public void addPigeon(int number){
        for(int i=0; i<number; i++) {
            PigeonThread p = new PigeonThread(new Pigeon(),foodThread);
            pigeonThreadList.add(p);
            p.start();
        }
        displayPigeon();
    }

    public void displayPigeon() {
        for (PigeonThread p:pigeonThreadList
             ) {
            int x = (int)(Math.random()*500);
            int y = (int)(Math.random()*500);
            gui.addImage(p.pigeon.getLabel(), x, y);
            p.getPigeon().setCoord(x,y);
        }
    }

    public FoodThread getFoodThread() {
        return foodThread;
    }
}
