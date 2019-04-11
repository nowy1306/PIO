package arkanoid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel{

    public int sc;
    JButton b1;
    JButton b2;
    JButton b3;
    JFrame obj;
    Gameplay gamePlay;   

    public Menu(String name, JFrame o) {
        sc=0;
        gamePlay = new Gameplay();
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
        ButtonAction sg = new ButtonAction(Color.yellow, 1);
        ButtonAction hs = new ButtonAction(Color.black, 2);
        ButtonAction ex = new ButtonAction(Color.red, 3);

        b1.addActionListener(sg);
        b2.addActionListener(hs);
        b3.addActionListener(ex);

    }
    void setgame(){
        obj.add(gamePlay);
        obj.remove(this);
    }

    private class ButtonAction implements ActionListener {

        private Color bC;
        private int cx;

        public ButtonAction(Color c, int x) {
            bC = c;
            cx = x;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setBackground(bC);
            sc=cx;
            if(cx == 3)
                System.exit(0);
            setgame();

        }

    }

}
