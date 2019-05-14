
package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class HighScore extends JPanel {

    @Override
    public void paint(Graphics g){
        //tło
        g.setColor(Color.blue);
        g.fillRect(0, 0, 800, 800);
    }
    
}
