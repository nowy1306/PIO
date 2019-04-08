package arkanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener, MouseMotionListener {

    private boolean play = false;
    private int score = 0;
    private Timer timer;
    private int delay = 8;
    int mouseposX;
    int mouseposY;

    Paddle paddle = new Paddle();
    Ball ball_1 = new Ball();

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

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
        g.fillRect(paddle.getPosX(), paddle.getPosY(), paddle.getSize(), paddle.getThick());

        // piłka
        g.setColor(Color.CYAN);
        g.fillOval(ball_1.getPosX(), ball_1.getPosY(), ball_1.getSize(), ball_1.getSize());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if (play) {
            if (new Rectangle(ball_1.getPosX(), ball_1.getPosY(), ball_1.getSize(), ball_1.getSize()).intersects(new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getSize(), paddle.getThick()))) {
                ball_1.setDir(ball_1.ballXdir, ball_1.ballYdir * (-1));
            }
            ball_1.move();
            if (ball_1.getPosX() < 0) {
                ball_1.setDir(ball_1.ballXdir * (-1), ball_1.ballYdir);
            }
            if (ball_1.getPosY() < 0) {
                ball_1.setDir(ball_1.ballXdir, ball_1.ballYdir * (-1));
            }
            if (ball_1.getPosX() > 770) {
                ball_1.setDir(ball_1.ballXdir * (-1), ball_1.ballYdir);
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.getPosX() >= 800 - paddle.getSize()) {
                paddle.setPos(800 - paddle.getSize());
            } else {
                moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getPosX() < 10) {
                paddle.setPos(10);
            } else {
                moveLeft();
            }
        }

    }

    public void moveRight() {
        play = true;
        paddle.setPos(paddle.getPosX() + 20);
    }

    public void moveLeft() {
        play = true;
        paddle.setPos(paddle.getPosX() - 20);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        mouseposX = e.getX();
        mouseposY = e.getY();
    }

}
