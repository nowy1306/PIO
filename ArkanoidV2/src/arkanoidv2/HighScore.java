package arkanoidv2;

import java.awt.*;
import javax.swing.*;


public class HighScore extends JPanel
{
    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;

    public HighScore()
    {
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        //tło
        g.setColor(Color.blue);
        g.fillRect(0, 0, 800, 800);
    }



}
