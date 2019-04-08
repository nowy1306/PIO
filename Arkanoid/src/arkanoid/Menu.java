
package arkanoid;

import javax.swing.*;
import java.awt.*;


public class Menu extends JPanel {


    public Menu(String name) {
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JButton b1 = new JButton("Start Game");
        JButton b2 = new JButton("High Score");
        JButton b3 = new JButton("Exit Game");
        
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

    }

}
