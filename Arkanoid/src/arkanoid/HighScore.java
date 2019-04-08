
package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


class HighScore extends JPanel {

    @Override
    public void paint(Graphics g){
        //tło
        g.setColor(Color.red);
        g.fillRect(0, 0, 800, 800);
    }
    
}
