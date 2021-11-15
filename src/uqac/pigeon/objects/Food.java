package uqac.pigeon.objects;

import javax.swing.*;
import java.awt.*;

public class Food {
    private boolean rotten;
    private JLabel label;
    private int x;
    private int y;
    private int tick;

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
        this.rotten = false;
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("food.png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
        label = new JLabel(imageIcon);
        tick = 0;
    }

    public boolean isRotten() {
        return rotten;
    }

    public void makeRotten() {
        this.rotten = true;
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("rotten.png").getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
        imageIcon.getImage().flush();
        label.setIcon(imageIcon);
    }

    public JLabel getLabel() {
        return label;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void tick() {
        this.tick++;
    }

    public int getTick() {
        return this.tick;
    }
}
