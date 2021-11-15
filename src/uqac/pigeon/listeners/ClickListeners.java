package uqac.pigeon.listeners;

import uqac.pigeon.gui.Gui;
import uqac.pigeon.thread.GameThread;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListeners {
    private Gui gui;

    public ClickListeners(Gui gui, GameThread game) {
        this.gui = gui;
        gui.getFrame().addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                game.getFoodThread().addFood(e.getX(), e.getY());
            }
        });
    }
}
