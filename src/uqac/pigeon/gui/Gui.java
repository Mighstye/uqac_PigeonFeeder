package uqac.pigeon.gui;

import javax.swing.*;
import java.awt.*;

public class Gui {
    private JFrame frame;

    //Crée le GUI
    public Gui(String name) {
        frame = new JFrame(name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
    }

    //Ajoute une image dans le JFrame
    public void addImage(JLabel label, int x, int y) {
        JPanel panel = (JPanel) this.frame.getContentPane();
        panel.setLayout(null);
        panel.add(label);
        Dimension size = label.getPreferredSize();
        label.setBounds(x,y,size.width,size.height);
        label.setLocation(x,y);
    }

    public void removeImage(JLabel label){
        JPanel panel = (JPanel) this.frame.getContentPane();
        panel.setLayout(null);
        panel.remove(label);
        panel.revalidate();
        panel.repaint();
    }

    //Rendre visible la fenetre
    public void setVisible() {
        this.frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
