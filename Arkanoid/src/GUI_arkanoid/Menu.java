package GUI_arkanoid;

import arkanoid.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    JButton b1;
    JButton b2;
    JButton b3;
    JFrame obj;

    public Menu(String name, JFrame o) {

        obj = o;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        b1 = new JButton("Start Game");
        b2 = new JButton("High Score");
        b3 = new JButton("Exit Game");

        b1.setAlignmentX(CENTER_ALIGNMENT);
        b1.setMaximumSize(new Dimension(300, 100));

        b2.setAlignmentX(CENTER_ALIGNMENT);
        b2.setMaximumSize(new Dimension(300, 100));

        b3.setAlignmentX(CENTER_ALIGNMENT);
        b3.setMaximumSize(new Dimension(300, 100));

        add(Box.createRigidArea(new Dimension(0, 200)));
        add(b1);
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(b2);
        add(Box.createRigidArea(new Dimension(0, 25)));
        add(b3);
        ButtonAction sg = new ButtonAction(1);
        ButtonAction hs = new ButtonAction(2);
        ButtonAction ex = new ButtonAction(3);

        b1.addActionListener(sg);
        b2.addActionListener(hs);
        b3.addActionListener(ex);

    }

    void setgame() {
        obj.add(new GameplayGui());
        this.setVisible(false);
        obj.repaint();
    }

    void setHighScore() {
        obj.add(new HighScore());
        this.setVisible(false);
        obj.repaint();
    }

    private class ButtonAction implements ActionListener {

        private int choice;

        public ButtonAction(int x) {
            choice = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (choice) {
                case 1:
                    setgame();
                    break;
                case 2:
                    setHighScore();
                    break;
                case 3:
                    System.exit(0);

            }

        }

    }

}
