package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    private Timer timer;
    private int delay = 8;

    private int paddleposX = 310;
    private int paddleSize = 120;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir;
    private int ballYdir;

    @Override
    public void paint(Graphics g) {
        //tło
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 800);

        //granice
        g.setColor(Color.gray);
        g.fillRect(0, 0, 10, 800);
        g.fillRect(0, 0, 800, 10);
        g.fillRect(784, 0, 10, 800);

        // platforma
        g.setColor(Color.blue);
        g.fillRect(paddleposX, 720, paddleSize, 8);

        // piłka
        g.setColor(Color.CYAN);
        g.fillOval(ballposX, ballposY, 20, 20);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
