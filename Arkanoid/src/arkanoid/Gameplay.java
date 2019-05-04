package arkanoid;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Gameplay implements KeyListener, ActionListener {

    public boolean play = true;
    public int score = 0;
    public Timer timer;
    private int delay = 6;
    static double BALL_SPEED = 2.5;
    int mouseposX;
    int mouseposY;
    public MapGenerator map = new MapGenerator();

    public Paddle paddle = new Paddle();
    public Ball ball_1 = new Ball();

    public Gameplay() {
        timer = new Timer(delay, this);
        timer.start();

    }

   
    @Override
    public void actionPerformed(ActionEvent e) {

        if (play) {
            if (new Rectangle((int) ball_1.getPosX(), (int) ball_1.getPosY(), ball_1.getSize(), ball_1.getSize()).intersects(new Rectangle(paddle.getPosX(), paddle.getPosY(), paddle.getSize(), paddle.getThick()))) {

                double a = ball_1.getPosX() - paddle.getPosX() + ball_1.getSize() / 2;
                double alf_d;
                double alf_r;
                if (a <= (paddle.getSize() / 2)) {
                    alf_d = 30 + 60 * a / (paddle.getSize() / 2);
                    alf_r = Math.toRadians(alf_d);
                    ball_1.setDir((-1) * BALL_SPEED * Math.cos(alf_r), (-1) * BALL_SPEED * Math.sin(alf_r));
                } else {
                    alf_d = 150 - 60 * a / (paddle.getSize() / 2);
                    alf_r = Math.toRadians(alf_d);
                    ball_1.setDir(BALL_SPEED * Math.cos(alf_r), (-1) * BALL_SPEED * Math.sin(alf_r));
                }

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
                        Rectangle ballRect = new Rectangle((int) ball_1.getPosX(), (int) ball_1.getPosY(), ball_1.getSize(), ball_1.getSize());
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) {
                            map.setBrickValue(map.map[i][j] - 1, i, j);
                            score += 5;
                            if (ball_1.getPosX() + ball_1.getSize() - 1 <= brickRect.x || ball_1.getPosX() + 1 >= brickRect.x + brickRect.width) {
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
            if (ball_1.getPosX() < 10) {
                ball_1.setDir(ball_1.ballXdir * (-1), ball_1.ballYdir);
            }
            if (ball_1.getPosY() < 10) {
                ball_1.setDir(ball_1.ballXdir, ball_1.ballYdir * (-1));
            }
            if (ball_1.getPosX() > 790 - ball_1.getSize()) {
                ball_1.setDir(ball_1.ballXdir * (-1), ball_1.ballYdir);
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (paddle.getPosX() >= 800 - paddle.getSize()) {
                paddle.setPos(800 - paddle.getSize());
            } else {
                paddle.moveRight();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (paddle.getPosX() < 10) {
                paddle.setPos(10);
            } else {
                paddle.moveLeft();
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
