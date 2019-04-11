package arkanoid;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {

    private boolean play = false;
    private int score = 0;
    public Timer timer;
    private int delay = 8;
    int mouseposX;
    int mouseposY;
    MapGenerator map = new MapGenerator();

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

        //map
        map.draw((Graphics2D) g);
        // platforma
        g.setColor(Color.blue);
        g.fillRect(paddle.getPosX(), paddle.getPosY(), paddle.getSize(), paddle.getThick());

        // piłka
        g.setColor(Color.CYAN);
        g.fillOval(ball_1.getPosX(), ball_1.getPosY(), ball_1.getSize(), ball_1.getSize());
        
        
        if (ball_1.getPosY() > 800) {
            play = false;
            ball_1.setDir(0, 0);
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 50));
            g.drawString("GAME OVER Score: " + score, 150, 420);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //timer.start();
        if (play) {
            if (new Rectangle(ball_1.getPosX(), ball_1.getPosY(), ball_1.getSize(), ball_1.getSize()).intersects(new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getSize(), paddle.getThick()))) {
                ball_1.setDir(ball_1.ballXdir, ball_1.ballYdir * (-1));
            }
            A:
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.brickWidth + 62 + j * 5;
                        int brickY = i * map.brickHeight + 60 + i * 5;
                        int brickWidth = map.brickWidth;
                        int brickHight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHight);
                        Rectangle ballRect = new Rectangle(ball_1.getPosX(), ball_1.getPosY(), ball_1.getSize(), ball_1.getSize());
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(map.map[i][j] - 1, i, j);
                            score += 5;
                            if (ball_1.getPosX() + 19 <= brickRect.x || ball_1.getPosX() + 1 >= brickRect.x + brickRect.width) {
                                ball_1.setDir(ball_1.ballXdir * (-1), ball_1.ballYdir);
                            } else {
                                ball_1.setDir(ball_1.ballXdir, ball_1.ballYdir * (-1));
                            }
                            break A;
                        }
                    }
                }
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

}
