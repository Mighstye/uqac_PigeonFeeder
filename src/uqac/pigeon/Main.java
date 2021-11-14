package uqac.pigeon;

import uqac.pigeon.gui.Gui;
import uqac.pigeon.thread.GameThread;

public class Main {
    public static void main(String[] args) {
        new GameThread(new Gui("Pigeon Feeder"), 10).start();
    }
}
